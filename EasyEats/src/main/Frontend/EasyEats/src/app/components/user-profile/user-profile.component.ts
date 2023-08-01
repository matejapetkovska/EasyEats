import { Component, OnInit } from "@angular/core";
import { User } from "../../models/user";
import { UserService } from "../../services/UserService";
import { Router } from "@angular/router";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User | undefined;
  isEditMode = false;
  editedUser: User = {};


  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (user) => {
        if(user) {
          this.user = user;
          this.user.image = "../../assets/user_images/"+this.user.image
          this.editedUser = { ...user };
          console.log('User data:', this.user);
        }
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  toggleEdit(): void {
    this.isEditMode = !this.isEditMode;
  }

  saveChanges(): void {
    this.userService.updateUser(this.editedUser).subscribe(
      (response) => {
        this.user = { ...this.editedUser };
        this.userService.saveUserUpdate(this.editedUser);
        this.isEditMode = false;
      },
      (error) => {
        console.error('Error updating user data:', error);
      }
    );
  }

}

