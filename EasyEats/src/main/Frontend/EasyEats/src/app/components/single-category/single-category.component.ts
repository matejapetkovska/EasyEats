import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe-service.service';

@Component({
  selector: 'app-single-category',
  templateUrl: './single-category.component.html',
  styleUrls: ['./single-category.component.css']
})
export class SingleCategoryComponent implements OnInit {

  category_id!: string

  recipes: Recipe[] | undefined

  constructor(private route: ActivatedRoute, private recipeService: RecipeService, private router: Router) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.category_id = params['category_id'];
      this.fetchRecipes();
    });
  }

  fetchRecipes() {
    this.recipeService.getAllRecipesByCategoryId(this.category_id)
      .subscribe({
        next: (recipes) => {
          if(recipes != null){
            this.recipes = recipes;
          }else{
            this.router.navigate(['/home']);
          }
        },
        error: () => {
          console.error('error in fetching recipes by category');
        }
    });
  }

}
