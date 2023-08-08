// import {Component} from '@angular/core';
// import {HttpClient, HttpErrorResponse} from "@angular/common/http";
// import {Router} from "@angular/router";
// import {UserService} from "../../services/UserService";
// import {AuthService} from "../../services/AuthService";
// import {NgForm} from "@angular/forms";
// import {User} from "../../models/user";
//
// interface LoginRequest {
//   username: string;
//   password: string;
// }
//
// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent {
//
//   username: string = '';
//   password: string = '';
//   errorMessage: string='';
//
//   constructor(private authService: AuthService, private router: Router, private userService: UserService, private http: HttpClient) {
//   }
//
//   onSubmit(form: NgForm) {
//     const loginRequest: LoginRequest = { username: this.username, password: this.password };
//
//     this.http.post<any>('http://localhost:8081/api/login', loginRequest).subscribe(
//       (response) => {
//         this.userService.saveUser(response);
//         this.router.navigate(['/home']);
//       },
//       (error: HttpErrorResponse) => {
//         this.errorMessage = error.error.message;
//       }
//     );
//   }
//   // onSubmit(form: NgForm) {
//   //   this.authService.login(this.username, this.password).subscribe(
//   //     (response) => {
//   //       // Assuming the server response contains the JWT token as response.token
//   //       const token = response.token;
//   //       this.authService.setAuthToken(token);
//   //       // Optionally, you can store the token in localStorage for persistence
//   //       localStorage.setItem('authToken', token);
//   //       this.router.navigate(['/home']);
//   //       console.log('Login successful');
//   //     },
//   //     (error) => {
//   //       this.errorMessage = error.error.message;
//   //       console.error('Login failed', error);
//   //     }
//   //   );
//   // }
// }
//
