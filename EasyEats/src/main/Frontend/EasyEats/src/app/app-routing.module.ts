import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomeScreenComponent} from "./welcome-screen/welcome-screen.component";
import { HomeScreenComponent } from './home-screen/home-screen.component';
import {SignupComponent} from "./signup/signup.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: 'welcome', component: WelcomeScreenComponent},
  {path:'', redirectTo:'/welcome', pathMatch:'full'},
  {path:'home', component: HomeScreenComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
