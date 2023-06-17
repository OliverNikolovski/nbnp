import {inject} from "@angular/core";
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

export namespace AuthGuard {

  // ovaa funkcija se koristi za da ne dozvolime najaven korisnik povtorno da otide na /login i da se najavi pak
  export const canActivateLogin = () => {
    const authService = inject(AuthenticationService);
    const router = inject(Router);

    if (!authService.isUserAuthenticated()) {
      return true;
    }
    else {
      router.navigate(['/']);
      return false;
    }
  }

  // ovaa funkcija se koristi za da ne dozvolime nenajaven korisnik da pristapi do zashtiteni ruti
  export const canActivate = () => {
    const authService = inject(AuthenticationService);
    const router = inject(Router);

    if (authService.isUserAuthenticated()) {
      return true;
    } else {
      router.navigate(['/login']);
      return false;
    }
  }

}
