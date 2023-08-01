import {Component, OnInit} from '@angular/core';
import {RecipeDetailsService} from "../../services/recipe-details.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RecipeReview} from "../../models/recipe-review";
import {UserService} from "../../services/UserService";
import {RecipeService} from "../../services/recipe-service.service";

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

  constructor(private recipeDetailsService: RecipeDetailsService,
              private recipeService: RecipeService,
              private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.userService.isLoggedIn()
    this.route.paramMap.subscribe(params => {
      const recipe_id = params.get('recipe_id');
      this.fetchRecipeDetails(recipe_id);
    });
  }

  fetchRecipeDetails(recipe_id: string | null): void {
    if (!recipe_id) return;
    this.recipeDetailsService.getRecipeWithReview(recipe_id).subscribe(
      (data) => {
        this.recipeReview = data;
      },
      (error) => {
        console.error('Error fetching recipe details:', error);
      }
    );
  }

  createFormData(): FormData {
    const formData = new FormData();
    if (this.comment != null && this.rating != null) {
      formData.append('comment', this.comment.toString())
      formData.append('rating', this.rating.toString())
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

  onDeleteRecipe() {
    this.route.paramMap.subscribe(params => {
        const recipe_id = params.get('recipe_id')
        this.recipeService.deleteRecipe(recipe_id).subscribe(
          {
            next: () => {
              this.router.navigate(['/recipes'])
            },
            error: (error) => {
              if (error.status === 400) {
                this.errorMessage = error.error.message
                console.log(this.errorMessage)
              }
              console.log('Error in editing recipe');
            }
          }
        )
      }
    )
  }
}
