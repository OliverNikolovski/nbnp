import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl = 'http://localhost:8080'

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
    return this.http.get<void>(`${this.baseUrl}/logout`);
  }
  isUserAuthenticated(): boolean {
    return sessionStorage.getItem('user') != null;
  }

  getAuthenticatedUser(): User | null {
    const userJsonString = sessionStorage.getItem('user');
    if (userJsonString) {
      return JSON.parse(userJsonString);
    }
    return null;
  }

  setAuthenticatedUser(user: User) {
    sessionStorage.setItem('user', JSON.stringify(user));
  }

  clearAuthenticatedUser() {
    sessionStorage.removeItem('user');
  }

}
