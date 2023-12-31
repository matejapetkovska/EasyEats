import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {NgOptimizedImage} from "@angular/common";
import {WelcomeScreenComponent} from './components/welcome-screen/welcome-screen.component';
import {AppRoutingModule} from './app-routing.module';
import {HomeScreenComponent} from './components/home-screen/home-screen.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {SignupComponent} from "./components/signup/signup.component";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from './components/login/login.component';
import {MainHeaderComponent} from './components/main-header/main-header.component';
import {SingleCategoryComponent} from './components/single-category/single-category.component';
import {AllRecipesComponent} from './components/all-recipes/all-recipes.component';
import {RecipeComponent} from "./components/recipe/recipe.component";
import {AddRecipeComponent} from './components/add-recipe/add-recipe.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {EditRecipeComponent} from './components/edit-recipe/edit-recipe.component';
import {AuthInterceptor} from './services/AuthInterceptor';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    WelcomeScreenComponent,
    HomeScreenComponent,
    SignupComponent,
    LoginComponent,
    MainHeaderComponent,
    SingleCategoryComponent,
    AllRecipesComponent,
    RecipeComponent,
    AddRecipeComponent,
    UserProfileComponent,
    EditRecipeComponent
  ],
  imports: [
    BrowserModule,
    NgOptimizedImage,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
