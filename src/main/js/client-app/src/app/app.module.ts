import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './components/app/app.component';
import {LoginComponent} from "./components/login/login.component";
import {AppRoutingModule} from "./app-routing.module";
import {HomeComponent} from "./components/home/home.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {UserInfoComponent} from "./components/user-info/user-info.component";
import {EnrolledSemestersComponent} from "./components/enrolled-semesters/enrolled-semesters.component";
import {SubjectsComponent} from "./components/subjects/subjects.component";
import {ExamsComponent} from "./components/exams/exams.component";
import {UserRequestsComponent} from "./components/user-requests/user-requests.component";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from "ngx-toastr";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    UserInfoComponent,
    EnrolledSemestersComponent,
    SubjectsComponent,
    ExamsComponent,
    UserRequestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
