import {Component} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {UserService} from "../../services/UserService";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  user: User = {
    first_name: '',
    last_name: '',
    username: '',
    email: '',
    password: '',
    repeatPass: '',
    image: '',
    role: 'USER',
  };

  errorMessage: string = '';

  constructor(private router: Router, private userService: UserService, private http: HttpClient) {
  }

  onSignUp(form: NgForm) {
    this.http.post<any>('http://localhost:8081/signup', this.user).subscribe(
      (response) => {
        this.userService.saveUser(response);
        this.router.navigate(['/home']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 400) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = 'An error occurred during registration.';
        }
      }
    );
  }
}
