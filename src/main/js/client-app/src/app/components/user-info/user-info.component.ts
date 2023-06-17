import {Component, OnInit} from "@angular/core";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentication.service";
import {ToastrService} from "ngx-toastr";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  authenticatedUser!: User;
  formGroup = this._initForm();

  constructor(private userService: UserService,
              private authService: AuthenticationService,
              private toastr: ToastrService,
              private formBuilder: FormBuilder) {
    this.authenticatedUser = this.authService.getAuthenticatedUserFromSessionStorage();
  }

  ngOnInit(): void {
    this.userService.getPersonalInfoForUser(this.authenticatedUser.id)
      .subscribe({
        next: value => {
          this.formGroup.setValue(value);
        },
        error: err => {
          this.toastr.error(err.error.message)
        }
      });
  }

  private _initForm() {
    return this.formBuilder.group({
      id: [0],
      firstName: [''],
      middleName: [''],
      lastName: [''],
      embg: [''],
      city: [''],
      country: [''],
      nationality: [''],
      address: [''],
      gender: [''],
      phoneNumber: [''],
      personalEmail: [''],
      birthDate: [new Date()],
      facultyEmail: [''],
      indeks: [''],
      resume: [''],
      yearOfEnrollment: [0],
      averageGrade: [0],
      totalCredits: [0]
    });
  }

  get role(): string {
    return this.authenticatedUser.roles[0]; // users have only one role
  }

}
