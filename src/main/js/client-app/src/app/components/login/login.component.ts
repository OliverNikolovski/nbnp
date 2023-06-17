import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup

  constructor(private _formBuilder: FormBuilder,
              private _authService: AuthenticationService,
              private _router: Router,
              private _toastr: ToastrService) {
    this.loginForm = this._formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this._authService.login(this.email, this.password)
      .subscribe({
        next: user => {
          this._authService.setAuthenticatedUser(user);
          this._router.navigate(['/']);
        },
        error: error => this._toastr.error("Invalid username or password.", "Error!")
      })
  }

  get email(): string {
    return this.loginForm.get('email')?.value;
  }

  get password(): string {
    return this.loginForm.get('password')?.value;
  }

}
