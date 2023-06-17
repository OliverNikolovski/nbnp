import {Component} from "@angular/core";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private _authService: AuthenticationService,
              private _router: Router) {
  }

  onLogout() {
    this._authService.logout()
      .subscribe(() => {
        this._authService.clearAuthenticatedUserFromSessionStorage();
        this._router.navigate(['/login']);
      })
  }
}
