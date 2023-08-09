import {Component, OnInit} from '@angular/core';
import {RecipeDetailsService} from "../../services/recipe-details.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RecipeReview} from "../../models/recipe-review";
import {UserService} from "../../services/UserService";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  recipeReview: RecipeReview | undefined

  comment: String | undefined

  rating: String | undefined

  isLoggedIn = false

  errorMessage: String = ""

  loggedInUserId: Number | undefined

  constructor(private recipeDetailsService: RecipeDetailsService,
              private userService: UserService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.userService.isLoggedIn()
    this.route.paramMap.subscribe(params => {
      const recipe_id = params.get('recipe_id');
      this.fetchRecipeDetails(recipe_id);
    });
    this.getLoggedInUserId()
  }

  fetchRecipeDetails(recipe_id: string | null): void {
    if (!recipe_id) return;
    this.recipeDetailsService.getRecipeWithReview(recipe_id).subscribe(
      (data) => {
        this.recipeReview = data;
        this.addPathToImages(this.recipeReview)
      },
      (error) => {
        console.error('Error fetching recipe details:', error);
      }
    );
  }

  getLoggedInUserId(){
    const token = localStorage.getItem('token')
    this.userService.getUserFromToken(token).subscribe({
      next: (user) => {
        this.loggedInUserId = user.id
        console.log(this.loggedInUserId)
      },
      error: () => {
        console.log("error in getting user from token")
      }
    })
  }

  createFormData(): FormData {
    const formData = new FormData();
    if (this.comment != null && this.rating != null && this.loggedInUserId != null) {
      formData.append('comment', this.comment.toString())
      formData.append('rating', this.rating.toString())
      formData.append('user_id', this.loggedInUserId.toString())
    }
    return formData
  }

  onAddReview() {
    const formData = this.createFormData();
    this.route.paramMap.subscribe(params => {
      const recipe_id = params.get('recipe_id')
      this.recipeDetailsService.addReview(recipe_id, formData).subscribe({
        next: (newReview) => {
          this.recipeReview!!.reviews.push(newReview);
          this.comment = '';
          this.rating = '';
        },
        error: (error) => {
          if (error.status === 400) {
            this.errorMessage = error.error.message;
          }
        }
      });
    })
  }
  addPathToImages(recipe: RecipeReview){
      recipe.recipe.image="../../../assets/recipe_images/"+recipe.recipe.image;
  }
}
