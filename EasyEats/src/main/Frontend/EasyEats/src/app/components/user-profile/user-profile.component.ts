import {Component, OnInit} from "@angular/core";
import {User} from "../../models/user";
import {UserService} from "../../services/UserService";
import {ActivatedRoute, Router} from "@angular/router";
import {Recipe} from "src/app/models/recipe";
import {RecipeService} from "src/app/services/recipe-service.service";

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

  recipeId= ''

  errorMessage = ''

  constructor(private userService: UserService,
              private router: Router,
              private recipeService: RecipeService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getUserFromToken()
    if(this.user != undefined){
      console.log(this.user)
      console.log(this.user.email)
    }
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

  toggleEdit(): void {
    this.isEditMode = !this.isEditMode;

    if (this.isEditMode) {
      this.editedUser = { ...this.user };
    }
  }


  editUserProfile(): void {
    const token = localStorage.getItem('token')

    this.userService.updateUser(this.editedUser, token).subscribe(
      (updatedUser) => {
        if (updatedUser) {
          this.user = { ...this.editedUser };
          this.isEditMode = false;
        }
      },
      (error) => {
        console.error('Error updating user data:', error);
      }
    );
  }



  onClickViewPosts() {
    this.recipeService.getRecipesByUserId(this.user?.id)
      .subscribe({
        next: (recipes) => {
          this.recipes = recipes;
          this.addPathToImages(this.recipes)
        },
        error: () => {
          console.error('error in fetching recipes');
        }
      });
    this.togglePost()
  }

  togglePost() {
    this.isPostMode = !this.isPostMode;
  }

  onDeleteRecipe(recipe: Recipe) {
    this.recipeId = recipe.id.toString()
    this.recipeService.deleteRecipe(this.recipeId).subscribe(
      {
        next: () => {
          this.router.navigate(['/recipes'])
        },
        error: (error) => {
          if (error.status === 400) {
            this.errorMessage = error.error.message;
            console.log(this.errorMessage);
          }
          console.log('Error in deleting recipe:', error);
        }
      }
    );
  }

  addPathToImages(list: Recipe[]) {
    for (let i = 0; i < list.length; i++) {
      list[i].image = "../../../assets/recipe_images/" + list[i].image;
    }
  }

}

