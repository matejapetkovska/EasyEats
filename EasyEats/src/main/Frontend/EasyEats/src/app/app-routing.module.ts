import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {WelcomeScreenComponent} from "./components/welcome-screen/welcome-screen.component";
import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import {SignupComponent} from "./components/signup/signup.component";
import {LoginComponent} from "./components/login/login.component";
import { SingleCategoryComponent } from './components/single-category/single-category.component';
import { AllRecipesComponent } from './components/all-recipes/all-recipes.component';
import { AddRecipeComponent } from './components/add-recipe/add-recipe.component';
import {UserProfileComponent} from "./components/user-profile/user-profile.component";
import {RecipeComponent} from "./components/recipe/recipe.component";
import {EditRecipeComponent} from "./components/edit-recipe/edit-recipe.component";



const routes: Routes = [
  {path: 'welcome', component: WelcomeScreenComponent},
  {path:'', redirectTo:'/welcome', pathMatch:'full'},
  {path:'home', component: HomeScreenComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'recipes/:category_id', component: SingleCategoryComponent},
  {path: 'recipes', component: AllRecipesComponent},
  {path: 'addrecipe', component: AddRecipeComponent},
  {path: 'editrecipe/:recipe_id', component: EditRecipeComponent},
  {path: 'user-profile', component: UserProfileComponent},
  {path: 'recipe/:recipe_id', component: RecipeComponent},
  { path: 'user-profile/:username', component: UserProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
