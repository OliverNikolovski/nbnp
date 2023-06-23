import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {EnrolledSemester} from "../../models/enrolled-semester";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";
import {SemesterService} from "../../services/semester.service";
import {Semester} from "../../models/semester";
import {ToastrService} from "ngx-toastr";
import {catchError, EMPTY, mergeMap, tap} from "rxjs";

@Component({
  selector: 'app-enrolled-semesters',
  templateUrl: './enrolled-semesters.component.html'
})
export class EnrolledSemestersComponent implements OnInit {
  authenticatedUser!: User;
  enrolledSemesters: EnrolledSemester[] = [];
  activeSemester: Semester | null = null;

  constructor(private userService: UserService,
              private authService: AuthenticationService,
              private semesterService: SemesterService,
              private toastrService: ToastrService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
  }

  ngOnInit(): void {
    this.semesterService.getActiveSemester()
      .subscribe({
        next: semester => this.activeSemester = semester,
        error: err => console.log(err.error.message)
      });

    this.userService.getSemestersForUser(this.authenticatedUser.id)
      .subscribe(semesters => this.enrolledSemesters = semesters);
  }

  enrollStudentInActiveSemester() {
    this.semesterService.enrollStudentInSemester(this.authenticatedUser.id, this.activeSemester!.id)
      .pipe(
        catchError(error => {
          this.toastrService.error("Настана проблем при запишување на семестарот");
          return EMPTY;
        }),
        tap(() => this.toastrService.success("Успешно запишан семестар")),
        mergeMap(() => this.userService.getSemestersForUser(this.authenticatedUser.id))
      ).subscribe(semesters => this.enrolledSemesters = semesters);
  }

  get hasStudentPreviouslyEnrolledInActiveSemester(): boolean {
    return this.enrolledSemesters.some(enrolledSemester => enrolledSemester.semesterId === this.activeSemester?.id)
  }

  get canEnrollInNewActiveSemester(): boolean {
    return this.activeSemester != null &&
      !this.hasStudentPreviouslyEnrolledInActiveSemester;
  }

}
