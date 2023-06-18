import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {EnrolledSemester} from "../../models/enrolled-semester";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";
import {EnrolledSubject} from "../../models/enrolled-subject";
import {MatOptionSelectionChange} from "@angular/material/core";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html'
})
export class SubjectsComponent implements OnInit {
  authenticatedUser!: User;
  enrolledSemesters: EnrolledSemester[] = [];
  enrolledSubjectsInSemester: EnrolledSubject[] = [];
  displayedColumns: string[];

  constructor(private userService: UserService,
              private authService: AuthenticationService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
    this.displayedColumns = ['rowNum','code', 'subject', 'semesterNumber', 'program', 'studyType', 'professor', 'semesterType'];
  }

  ngOnInit(): void {
    this.userService.getSemestersForUser(this.authenticatedUser.id)
      .subscribe(semesters => this.enrolledSemesters = semesters);
  }

  getSchoolYearForSemester(semester: EnrolledSemester): string {
    const year = new Date(semester.startDate).getFullYear();
    return '(' + year + '/' + (year + 1) + ')';
  }

  onSemesterChange(event: MatOptionSelectionChange<number>) {
    // bidejki funkcijata se povikuva dva pati (koga se pravi unselect i select), nie sakame da se izvrsi kodot samo pri select na nova vrednost
    if (event.isUserInput) {
      const selectedSemesterId = event.source.value;
      this.userService.getSubjectsInSemesterForStudent(this.authenticatedUser.id, selectedSemesterId)
        .subscribe(subjects => this.enrolledSubjectsInSemester = subjects);
    }
  }
}
