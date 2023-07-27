import {Component} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {SignupServiceService} from "../../services/signup-service.service";

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

  constructor(private signUpService: SignupServiceService, private router: Router) {
  }

  // onSignUp() {
  //   this.signUpService.signUp(this.user).subscribe(
  //     (response) => {
  //       this.router.navigate(['/home']);
  //     },
  //     (error: HttpErrorResponse) => {
  //       if (error.status === 400) {
  //         this.errorMessage = error.error.message;
  //       } else {
  //         this.errorMessage = 'An error occurred during registration.';
  //       }
  //     }
  //   );
  // }

  onSignUp() {
    this.signUpService.signUp(this.user).subscribe(
      (response) => {
        this.router.navigate(['/home']);
      },
      (error: HttpErrorResponse) => {
        console.log('Error occurred:', error);
        if (error.status === 400) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = 'An error occurred during registration.';
        }
      }
    );
  }
}
