import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/user";
import {UserService} from "../../services/UserService";
import {AuthService} from "../../services/AuthService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.css']
})
export class MainHeaderComponent implements OnInit{

  isLoggedIn = false;
  username: string='';
  user: User | undefined;


  constructor(private userService: UserService, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.userService.isLoggedIn();

    if (this.isLoggedIn) {
      this.userService.getUser().subscribe(
        (user) => {
          if(user) {
            this.user = user;
            console.log('User data:', this.user);
          }
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
    }
  }

  logout() {
    this.authService.logout().subscribe({
      next: response => {
        this.userService.clean()
        this.router.navigate(['/login']);
      },
      error: error => {
        console.log(error)

      }
    })
  }

}
