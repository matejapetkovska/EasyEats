import {Component, OnInit} from '@angular/core';
import {RecipeDetailsService} from "../../services/recipe-details.service";
import {ActivatedRoute} from "@angular/router";
import {RecipeReview} from "../../models/recipe-review";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit{

  recipeReview: RecipeReview | undefined

  comment: String | undefined

  rating: String | undefined

  errorMessage: String=""

  constructor(private recipeDetailsService: RecipeDetailsService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
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
        this.recipeReview.recipe.image="../../../assets/recipe_images/"+this.recipeReview.recipe.image;
      },
      (error) => {
        console.error('Error fetching recipe details:', error);
      }
    );
  }

  createFormData(): FormData{
    const formData = new FormData();
    if(this.comment != null && this.rating != null){
      formData.append('comment', this.comment.toString())
      formData.append('rating', this.rating.toString())
    }
    return formData
  }

  onAddReview() {
    const formData = this.createFormData();
    this.route.paramMap.subscribe(params => {
      const recipe_id = params.get('recipe_id');
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
    });
  }
}
