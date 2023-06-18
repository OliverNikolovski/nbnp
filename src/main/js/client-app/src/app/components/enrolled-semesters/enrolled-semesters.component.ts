import {Component} from "@angular/core";
import {User} from "../../models/user";
import {Observable} from "rxjs";
import {EnrolledSemester} from "../../models/enrolled-semester";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-enrolled-semesters',
  templateUrl: './enrolled-semesters.component.html'
})
export class EnrolledSemestersComponent {
  authenticatedUser!: User;
  enrolledSemesters$: Observable<EnrolledSemester[]>;

  constructor(private userService: UserService,
              private authService: AuthenticationService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
    this.enrolledSemesters$ = this.userService.getSemestersForUser(this.authenticatedUser.id);
  }

}
