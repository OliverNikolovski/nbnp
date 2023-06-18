import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";
import {StudentRequest} from "../../models/student-request";

@Component({
  selector: 'app-user-requests',
  templateUrl: './student-requests.component.html'
})
export class StudentRequestsComponent implements OnInit {
  authenticatedUser!: User;
  requests: StudentRequest[] = [];
  displayedColumns: string[];

  constructor(private userService: UserService,
              private authService: AuthenticationService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
    this.displayedColumns = ['rowNum', 'requestDate', 'name', 'status'];
  }

  ngOnInit() {
    this.userService.getAllRequestsForStudent(this.authenticatedUser.id)
      .subscribe(requests => this.requests = requests);
  }

  getStatusClassObject(status: string) {
    return {
      'text-danger': status === 'REJECTED',
      'text-success': status === 'APPROVED',
      'text-dark': status === 'PENDING'
    };
  }
}
