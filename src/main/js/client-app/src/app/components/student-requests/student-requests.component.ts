import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {AuthenticationService} from "../../services/authentication.service";
import {StudentRequest} from "../../models/student-request";
import {RequestService} from "../../services/request.service";
import {RequestType} from "../../models/request-type";
import {mergeMap} from "rxjs";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RequestCreate} from "../request-objects/request-create";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-user-requests',
  templateUrl: './student-requests.component.html',
  styleUrls: ['./student-requests.component.css']
})
export class StudentRequestsComponent implements OnInit {
  authenticatedUser!: User;
  requests: StudentRequest[] = [];
  displayedColumns: string[];
  requestTypes: RequestType[] = [];
  formGroup = this.formBuilder.group({
    'requestTypeId': ['', Validators.required],
    'description': ['', Validators.required]
  });

  constructor(private userService: UserService,
              private authService: AuthenticationService,
              private requestService: RequestService,
              private formBuilder: FormBuilder,
              private toastrService: ToastrService) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
    this.displayedColumns = ['rowNum', 'requestDate', 'name', 'description', 'status'];
  }

  ngOnInit() {
    this.userService.getAllRequestsForStudent(this.authenticatedUser.id)
      .subscribe(requests => this.requests = requests);

    this.requestService.getAllRequestTypes()
      .subscribe(requestTypes => this.requestTypes = requestTypes);
  }

  getStatusClassObject(status: string) {
    return {
      'text-danger': status === 'REJECTED',
      'text-success': status === 'APPROVED',
      'text-dark': status === 'PENDING'
    };
  }
  onSubmit() {
    this.requestService.createRequestForStudent(this.getRequestObject()).pipe(
      mergeMap(() => this.userService.getAllRequestsForStudent(this.authenticatedUser.id))
    ).subscribe({
      next: studentRequests => {
        this.requests = studentRequests;
        this.requestTypeIdControl.reset();
        this.descriptionControl.reset();
        this.toastrService.success("New request successfully submitted.");
      },
      error: err => this.toastrService.success(err.error.message)
    });
  }

  getRequestObject(): RequestCreate {
    return {
      studentId: this.authenticatedUser.id,
      requestTypeId: +this.requestTypeIdControl.value,
      description: this.descriptionControl.value
    }
  }

  get requestTypeIdControl(): AbstractControl {
    return this.formGroup.get('requestTypeId')!!;
  }

  get descriptionControl(): AbstractControl {
    return this.formGroup.get('description')!!;
  }
}
