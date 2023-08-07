import { Component, OnInit } from "@angular/core";
import { User } from "../../models/user";
import { UserService } from "../../services/UserService";
import { Router } from "@angular/router";
import { Recipe } from "src/app/models/recipe";
import { RecipeService } from "src/app/services/recipe-service.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User | undefined;

  isEditMode = false;

  isPostMode = false;

  editedUser: User = {};

  recipes: Recipe[] | undefined

  constructor(private userService: UserService,
              private router: Router,
              private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe(
      (user) => {
        if(user) {
          this.user = user;
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

  onClickViewPosts(){
    this.recipeService.getRecipesByUserId(this.user?.id)
      .subscribe({
        next: (recipes) => {
            this.recipes = recipes;
            console.log(recipes)
        },
        error: () => {
          console.error('error in fetching recipes');
        }
    });
    this.togglePost()
  }

  togglePost(){
    this.isPostMode = !this.isPostMode;
  }
}

