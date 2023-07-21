import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {NgOptimizedImage} from "@angular/common";
import { WelcomeScreenComponent } from './welcome-screen/welcome-screen.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    WelcomeScreenComponent
  ],
    imports: [
        BrowserModule,
        NgOptimizedImage,
        AppRoutingModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
