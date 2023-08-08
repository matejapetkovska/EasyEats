import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/AuthService";
import { LoginRequest } from 'src/app/models/loginRequest';
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  request: LoginRequest = {
    email:"",
    password:""
  }

  errorMessage:String ="";

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(){
    this.authService.login(this.request).subscribe({
      next:(response) => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['home'])
      },
      error:(error: HttpErrorResponse) =>{
        console.log("error in logging in")
        this.errorMessage = error.error.message;
      }
    })
  }

}

