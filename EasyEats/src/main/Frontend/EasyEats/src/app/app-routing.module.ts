import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomeScreenComponent} from "./components/welcome-screen/welcome-screen.component";
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import {SignupComponent} from "./components/signup/signup.component";
import {LoginComponent} from "./components/login/login.component";
import { SingleCategoryComponent } from './components/single-category/single-category.component';
import { AllRecipesComponent } from './components/all-recipes/all-recipes.component';
import {UserProfileComponent} from "./components/user-profile/user-profile.component";


const routes: Routes = [
  {path: 'welcome', component: WelcomeScreenComponent},
  {path:'', redirectTo:'/welcome', pathMatch:'full'},
  {path:'home', component: HomeScreenComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'recipes/:category_id', component: SingleCategoryComponent},
  {path: 'recipes', component: AllRecipesComponent},
  {path: 'user-profile', component: UserProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
