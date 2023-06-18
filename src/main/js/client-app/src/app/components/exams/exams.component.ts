import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";
import {Exam} from "../../models/exam";

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html'
})
export class ExamsComponent implements OnInit {
  authenticatedUser!: User;
  passedExams: Exam[] = [];
  displayedColumns: string[];

  constructor(private userService: UserService,
              private authService: AuthenticationService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
    this.displayedColumns = ['rowNum', 'code', 'subject', 'session', 'date', 'semester', 'credits', 'winterOrSummer', 'grade'];
  }

  ngOnInit() {
    this.userService.getAllPassedExamsForStudent(this.authenticatedUser.id)
      .subscribe(exams => this.passedExams = exams);
  }
}
