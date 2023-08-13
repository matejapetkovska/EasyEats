import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../services/UserService";

@Component({
  selector: 'app-welcome-screen',
  templateUrl: './welcome-screen.component.html',
  styleUrls: ['./welcome-screen.component.css']
})
export class WelcomeScreenComponent implements OnInit {

  isLoggedIn = false;

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.userService.isLoggedIn()
  }

  redirectToSignUp() {
    this.router.navigateByUrl('/signup');
  }

  redirectToHomePage() {
    this.router.navigateByUrl('/home');
  }
}
