import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserPersonalInfo} from "../models/user-personal-info";
import {EnrolledSemester} from "../models/enrolled-semester";
import {EnrolledSubject} from "../models/enrolled-subject";
import {Exam} from "../models/exam";
import {StudentRequest} from "../models/student-request";

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

  getAllPassedExamsForStudent(id: number): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.baseUrl}/${id}/exams`);
  }

  getAllRequestsForStudent(id: number): Observable<StudentRequest[]> {
    return this.http.get<StudentRequest[]>(`${this.baseUrl}/${id}/requests`);
  }
}
