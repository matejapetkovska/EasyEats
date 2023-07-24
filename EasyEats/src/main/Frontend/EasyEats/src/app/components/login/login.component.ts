import { Component } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

interface LoginRequest {
  username: string;
  password: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router) {}


  onSubmit() {
    const loginRequest: LoginRequest = { username: this.username, password: this.password };

    this.http.post<any>('http://localhost:8081/api/login', loginRequest).subscribe(
      (response) => {
        this.router.navigate(['/home']);
      },
      (error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) {
          this.errorMessage = 'An error occurred: ' + error.error.message; //client-side error
        } else {
          this.errorMessage = 'Error: ' + error.error.message; //server-side error
        }
      }
    );
  }

}
