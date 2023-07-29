import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe-service.service';

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent implements OnInit {

  recipes: Recipe[] | undefined

  queryText = new FormControl("");

  constructor(private recipeService: RecipeService){ }

  ngOnInit(): void {
    this.getAllRecipes();
  }

  getAllRecipes(){
    this.recipeService.getAllRecipes()
      .subscribe({
        next: (recipes) => {
            this.recipes = recipes;
            this.addPathToImages(this.recipes)
        },
        error: () => {
          console.error('error in fetching recipes');
        }
    });
  }

  search(){
    this.queryText.valueChanges.pipe(
      debounceTime(400),
      distinctUntilChanged(),
      switchMap(value => this.recipeService.getRecipesByQueryText(value))
    ).subscribe(result => {
      this.recipes = result as Array<Recipe>;
      this.addPathToImages(this.recipes)
    });
  }

  addPathToImages(list: Recipe[]){
    for(let i=0; i<list.length; i++){
      list[i].image="../../../assets/recipe_images/"+list[i].image;
    }
  }

}
