import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserPersonalInfo} from "../models/user-personal-info";
import {EnrolledSemester} from "../models/enrolled-semester";
import {EnrolledSubject} from "../models/enrolled-subject";
import {Exam} from "../models/exam";
import {StudentRequest} from "../models/student-request";
import {AppSubject} from "../models/app-subject";
import {EnrollSubjectsForStudentRequest} from "../request-objects/enroll-subjects-for-student-request";

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  private readonly baseUrl = 'http://localhost:4200/api/subjects'

  constructor(private http: HttpClient) {
  }

  enrollStudentInSubject(studentId: number, semesterId: number, subjectId: number): Observable<void> {
    const headers = {'Content-Type': 'application/x-www-form-urlencoded'};
    const body = new URLSearchParams();
    body.set('studentId', studentId.toString());
    body.set('semesterId', semesterId.toString());
    body.set('subjectId', subjectId.toString());
    return this.http.post<void>(`${this.baseUrl}/enroll`, body.toString(), {headers});
  }

  enrollSubjectsForStudent(request: EnrollSubjectsForStudentRequest): Observable<void> {
    const headers = {'Content-Type': 'application/json'};
    return this.http.post<void>(`${this.baseUrl}/enroll-subjects`, request, {headers});
  }

  getAllSubjectsForSemesterType(semester: string): Observable<AppSubject[]> {
    return this.http.get<AppSubject[]>(`${this.baseUrl}/${semester}`);
  }
}
