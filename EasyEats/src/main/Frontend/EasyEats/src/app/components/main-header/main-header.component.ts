import {Component, OnInit} from '@angular/core';
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

  username: string='';
  user: User | undefined;

  constructor(private userService: UserService, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.getUserFromToken()
  }

  getUserFromToken(){
    const token = localStorage.getItem('token')
    this.userService.getUserFromToken(token).subscribe({
      next: (user) => {
        this.user = user
      },
      error: () => {
        console.log("error in getting user from token")
      }
    })
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

}
