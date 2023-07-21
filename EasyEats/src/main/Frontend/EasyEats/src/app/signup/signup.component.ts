import { Component } from '@angular/core';
import { User } from '../userInterface';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  user: User = {
    email: '',
    first_name: '',
    image: '',
    last_name: '',
    password: '',
    role: '',
    username: ''
  };

  constructor(private http: HttpClient, private router: Router) { }

  errorMessage: string = '';

  onSignUp() {
    if (!this.isValidEmail(this.user.email)) {
      this.errorMessage = 'Please enter a valid email address.';
    } else if (this.user.password.length < 6) {
      this.errorMessage = 'Password must be at least 6 characters long.';
    } else if (!this.isValidUsername(this.user.username)) {
      this.errorMessage = 'Please enter a valid username';
    } else {
      const url = 'http://localhost:8081/api/signup';
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

      this.http.post<User>(url, this.user, { headers }).subscribe(
        (response) => {
          console.log('User registered successfully!', response);
          this.router.navigate(['/home']);
        },
        (error: HttpErrorResponse) => {
          console.error('Error during user registration', error);
          if (error.status === 400) {
            this.errorMessage = 'Please fill in all required fields correctly.';
          } else if (error.status === 409) {
            const errorResponse = error.error as any;
            if (errorResponse.error === 'Username already exists.') {
              this.errorMessage = 'Username already exists.';
            } else if (errorResponse.error === 'Email already exists.') {
              this.errorMessage = 'Email already exists.';
            } else {
              this.errorMessage = 'An error occurred during user registration. Please try again later.';
            }
          } else {
            this.errorMessage = 'An error occurred during user registration. Please try again later.';
          }
        }
      );
    }
  }


  isValidEmail(email: string): boolean {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
  }


  isValidUsername(username: string): boolean {
    const usernameRegex = /^[a-zA-Z0-9.,'\-?]+$/;
    return usernameRegex.test(username);
  }
}
