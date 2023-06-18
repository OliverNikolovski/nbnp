import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserPersonalInfo} from "../models/user-personal-info";
import {EnrolledSemester} from "../models/enrolled-semester";
import {EnrolledSubject} from "../models/enrolled-subject";

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

  getSemestersForUser(id: number): Observable<EnrolledSemester[]> {
    return this.http.get<EnrolledSemester[]>(`${this.baseUrl}/${id}/semesters`);
  }

  getSubjectsInSemesterForStudent(studentId: number, semesterId: number): Observable<EnrolledSubject[]> {
    return this.http.get<EnrolledSubject[]>(`${this.baseUrl}/${studentId}/subjects?semesterId=${semesterId}`);
  }

}
