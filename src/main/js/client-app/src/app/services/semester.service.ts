import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Semester} from "../models/semester";

@Injectable({
  providedIn: 'root'
})
export class SemesterService {
  private readonly baseUrl = '/api/semesters';

  constructor(private http: HttpClient) {
  }

  getActiveSemester(): Observable<Semester> {
    return this.http.get<Semester>(`${this.baseUrl}/active`);
  }

  enrollStudentInSemester(studentId: number, semesterId: number): Observable<void> {
    const headers = {'Content-Type': 'application/x-www-form-urlencoded'};
    const body = new URLSearchParams();
    body.set('studentId', studentId.toString());
    body.set('semesterId', semesterId.toString());
    return this.http.post<void>(`${this.baseUrl}/enroll-student`, body.toString(), {headers: headers});
  }

}
