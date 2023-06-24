import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {RequestType} from "../models/request-type";
import {RequestCreate} from "../request-objects/request-create";

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private readonly baseUrl = 'http://localhost:4200/api/requests'

  constructor(private http: HttpClient) {
  }

  getAllRequestTypes(): Observable<RequestType[]> {
    return this.http.get<RequestType[]>(`${this.baseUrl}/all`);
  }

  createRequestForStudent(request: RequestCreate): Observable<void> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<void>(`${this.baseUrl}`, request, {headers: headers});
  }
}
