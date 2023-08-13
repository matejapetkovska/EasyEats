import {Component} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from "@angular/router";
import {RegisterRequest} from 'src/app/models/registerRequest';
import {AuthService} from 'src/app/services/AuthService';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  request: RegisterRequest = {
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    password: "",
  }

  errorMessage: String = "";

  constructor(private authService: AuthService, private router: Router) {
  }

  onSubmit() {
    this.authService.register(this.request).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['home'])
      },
      error: (error: HttpErrorResponse) => {
        console.log("error in logging in")
        this.errorMessage = error.error.message;
      }
    })
  }

}
