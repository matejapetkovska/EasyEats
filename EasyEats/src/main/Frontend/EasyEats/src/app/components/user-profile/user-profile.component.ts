import {User} from "../../models/user";
import {UserService} from "../../services/UserService";
import {Router} from "@angular/router";
import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{

  user: User | undefined;

  constructor(private userService: UserService, private router: Router) { }


  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (user) => {
        if (user) {
          this.user = user;
          console.log('User data:', this.user);
        } else {
          console.warn('No user data found.');
        }
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

}
