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
      },
      (error) => {
        console.error('Error fetching recipe details:', error);
      }
    );
  }

}
