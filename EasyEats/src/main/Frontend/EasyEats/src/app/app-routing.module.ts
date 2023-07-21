import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomeScreenComponent} from "./welcome-screen/welcome-screen.component";

const routes: Routes = [
  {path: 'welcome', component: WelcomeScreenComponent},
  {path:'', redirectTo:'/welcome', pathMatch:'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
