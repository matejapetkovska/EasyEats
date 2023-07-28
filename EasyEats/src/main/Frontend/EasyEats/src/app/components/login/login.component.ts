import {Component} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserService} from "../../services/UserService";
import {AuthService} from "../../services/AuthService";
import {NgForm} from "@angular/forms";
import {User} from "../../models/user";

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


  onSubmit(form: NgForm) {
    const loginRequest: LoginRequest = { username: this.username, password: this.password };

    this.http.post<any>('http://localhost:8081/api/login', loginRequest).subscribe(
      (response) => {
        this.userService.saveUser(response);
        this.router.navigate(['/user-profile']);
      },
      (error: HttpErrorResponse) => {
        this.errorMessage = error.error.message;
      }
    );
  }

}

