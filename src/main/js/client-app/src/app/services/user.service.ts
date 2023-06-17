import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserPersonalInfo} from "../models/user-personal-info";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly baseUrl = 'http://localhost:4200/api'

  constructor(private http: HttpClient) {
  }

  getPersonalInfoForUser(id: number): Observable<UserPersonalInfo> {
    return this.http.get<UserPersonalInfo>(`${this.baseUrl}/${id}/info`);
  }

}
