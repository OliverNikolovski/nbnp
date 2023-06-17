import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly baseUrl = 'http://localhost:4200/api'

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<User> {
    const headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
    const body = new URLSearchParams();
    body.set('username', email);
    body.set('password', password);
    return this.http.post<User>(`${this.baseUrl}/login`, body.toString(), {headers: headers, observe: 'body'});
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/logout`, {});
  }
  isUserAuthenticated(): boolean {
    return sessionStorage.getItem('user') != null;
  }

  // so slednive metodi go zapishuvame, chitame i brisheme logiraniot user od session storage
  // ni treba da go chuvame vo session storage za site komponenti da mozat da znaat deka ima logiran korisnik
  // i da mozat da pristapat do negovite informacii kako na primer ROLE
  getAuthenticatedUserFromSessionStorage(): User {
    const userJsonString = sessionStorage.getItem('user');
    if (userJsonString) {
      return JSON.parse(userJsonString);
    }
    throw Error("There is no authenticated user.");
  }

  writeAuthenticatedUserToSessionStorage(user: User) {
    sessionStorage.setItem('user', JSON.stringify(user));
  }

  clearAuthenticatedUserFromSessionStorage() {
    sessionStorage.removeItem('user');
  }

}
