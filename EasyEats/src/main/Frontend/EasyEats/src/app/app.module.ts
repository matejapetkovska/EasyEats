import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {NgOptimizedImage} from "@angular/common";
import {WelcomeScreenComponent} from './components/welcome-screen/welcome-screen.component';
import {AppRoutingModule} from './app-routing.module';
import {HomeScreenComponent} from './components/home-screen/home-screen.component';
import {HttpClientModule} from '@angular/common/http';
import {SignupComponent} from "./components/signup/signup.component";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from './components/login/login.component';
import { MainHeaderComponent } from './components/main-header/main-header.component';
import { SingleCategoryComponent } from './components/single-category/single-category.component';
import { AllRecipesComponent } from './components/all-recipes/all-recipes.component';
import {RecipeComponent} from "./components/recipe/recipe.component";


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
    RecipeComponent
  ],
  imports: [
    BrowserModule,
    NgOptimizedImage,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
