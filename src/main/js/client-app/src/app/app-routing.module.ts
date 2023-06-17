import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {AuthGuard} from "./guards/auth.guard";
import {UserInfoComponent} from "./components/user-info/user-info.component";
import {EnrolledSemestersComponent} from "./components/enrolled-semesters/enrolled-semesters.component";
import {SubjectsComponent} from "./components/subjects/subjects.component";
import {ExamsComponent} from "./components/exams/exams.component";
import {UserRequestsComponent} from "./components/user-requests/user-requests.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard.canActivate],
    children: [
      {path: '', redirectTo: '/info', pathMatch: 'full'},
      {path: 'info', component: UserInfoComponent, canActivate: [AuthGuard.canActivate]},
      {path: 'semesters', component: EnrolledSemestersComponent, canActivate: [AuthGuard.canActivate]},
      {path: 'subjects', component: SubjectsComponent, canActivate: [AuthGuard.canActivate]},
      {path: 'exams', component: ExamsComponent, canActivate: [AuthGuard.canActivate]},
      {path: 'requests', component: UserRequestsComponent, canActivate: [AuthGuard.canActivate]},
    ]
  },
  {path: 'login', component: LoginComponent, canActivate: [AuthGuard.canActivateLogin]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
