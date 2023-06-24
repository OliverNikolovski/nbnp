import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {EnrolledSemester} from "../../models/enrolled-semester";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";
import {EnrolledSubject} from "../../models/enrolled-subject";
import {MatOptionSelectionChange} from "@angular/material/core";
import {SemesterService} from "../../services/semester.service";
import {Semester} from "../../models/semester";
import {SubjectService} from "../../services/subject.service";
import {AppSubject} from "../../models/app-subject";
import {EnrollSubjectsForStudentRequest} from "../../request-objects/enroll-subjects-for-student-request";
import {ToastrService} from "ngx-toastr";
import {catchError, EMPTY, mergeMap, tap} from "rxjs";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html'
})
export class SubjectsComponent implements OnInit {
  authenticatedUser!: User;
  enrolledSemesters: EnrolledSemester[] = [];
  enrolledSubjectsInSemester: EnrolledSubject[] = [];
  displayedColumns: string[];
  activeSemester: Semester | null = null;
  selectedSemesterId?: number;
  allAvailableSubjectsInSelectedSemester: AppSubject[] = [];
  selectedSubjectsToEnroll: AppSubject[] = [];
  format = {
    add: 'Додади',
    remove: 'Избриши',
    all: 'Селектирај ги сите',
    none: 'Отселектирај ги сите',
    direction: 'left-to-right',
    draggable: true,
    locale: undefined
  }

  constructor(private userService: UserService,
              private authService: AuthenticationService,
              private semesterService: SemesterService,
              private subjectService: SubjectService,
              private toastrService: ToastrService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
    this.displayedColumns = ['rowNum', 'code', 'subject', 'semesterNumber', 'program', 'studyType', 'professor', 'semesterType'];
  }

  ngOnInit(): void {
    this.userService.getSemestersForUser(this.authenticatedUser.id)
      .subscribe(semesters => this.enrolledSemesters = semesters);

    this.semesterService.getActiveSemester()
      .subscribe({
        next: semester => this.activeSemester = semester,
        error: err => console.log(err.error.message)
      });
  }

  getSchoolYearForSemester(semester: EnrolledSemester): string {
    const year = new Date(semester.startDate).getFullYear();
    return '(' + year + '/' + (year + 1) + ')';
  }

  onSemesterChange(event: MatOptionSelectionChange<number>) {
    this.allAvailableSubjectsInSelectedSemester = [];
    this.selectedSubjectsToEnroll = [];
    // bidejki funkcijata se povikuva dva pati (koga se pravi unselect i select), nie sakame da se izvrsi kodot samo pri select na nova vrednost
    if (event.isUserInput) {
      this.selectedSemesterId = event.source.value;
      this.userService.getSubjectsInSemesterForStudent(this.authenticatedUser.id, this.selectedSemesterId)
        .subscribe(subjects => this.enrolledSubjectsInSemester = subjects);
    }
  }

  showSubjectEnrollmentButton(): boolean {
    const selectedEnrolledSemester =
      this.enrolledSemesters.find(s => s.semesterId === this.selectedSemesterId);
    if (!selectedEnrolledSemester)
      return false;
    const isSelectedSemesterActive = selectedEnrolledSemester?.semesterId === this.activeSemester?.id;
    if (!isSelectedSemesterActive)
      return false;
    return this.enrolledSubjectsInSemester.length === 0;
  }

  onEnrollSubjectsButtonClicked() {
    this.subjectService.getAllSubjectsForSemesterType(this.activeSemester!.type)
      .subscribe(subjects => {
        this.allAvailableSubjectsInSelectedSemester = subjects;
      });
  }

  confirmEnrollSubjects() {
    const subjectIds = this.selectedSubjectsToEnroll.map(subject => subject.id);
    const request: EnrollSubjectsForStudentRequest = {
      studentId: this.authenticatedUser.id,
      semesterId: this.selectedSemesterId!,
      subjectIds: subjectIds
    };
    this.subjectService.enrollSubjectsForStudent(request)
      .pipe(
        catchError(error => {
          this.toastrService.error("Се појави проблем при потврдување на уписот");
          return EMPTY;
        }),
        tap(() => {
          this.toastrService.success("Уписот е успешно потврден");
          this.clearFields();
        }),
        mergeMap(() => this.userService.getSubjectsInSemesterForStudent(this.authenticatedUser.id, this.selectedSemesterId!))
      ).subscribe(enrolledSubjects => this.enrolledSubjectsInSemester = enrolledSubjects);
  }

  clearFields() {
    this.enrolledSubjectsInSemester = [];
    this.allAvailableSubjectsInSelectedSemester = [];
    this.selectedSubjectsToEnroll = [];
  }
}
