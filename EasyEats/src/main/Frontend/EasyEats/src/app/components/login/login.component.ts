import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserService} from "../../services/UserService";
import {AuthService} from "../../services/AuthService";

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
  errorMessage: string='';

  constructor(private authService: AuthService, private router: Router, private userService: UserService, private http: HttpClient) {
  }


  onSubmit() {
    const loginRequest: LoginRequest = {username: this.username, password: this.password};

    this.http.post<any>('http://localhost:8081/api/login', loginRequest).subscribe(
      (response) => {
        // Assuming the response contains a 'token' property with the JWT token
        localStorage.setItem('authToken', response.token);
        console.log('Token stored in localStorage:', response.token);
        this.router.navigate(['/user-profile']);
      },
      (error: HttpErrorResponse) => {
        console.error('Error response from login API:', error);
        this.errorMessage = error.error.message;
      }
    );
  }

}

