import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { Recipe } from 'src/app/models/recipe';
import { SubCategory } from 'src/app/models/sub_category';
import { RecipeService } from 'src/app/services/recipe-service.service';
import { SubcategoryService } from 'src/app/services/subcategory-service.service';

@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.css']
})
export class AllRecipesComponent implements OnInit {

  recipes: Recipe[] | undefined

  subCategories: SubCategory[] | undefined

  queryText = new FormControl("");

  constructor(private recipeService: RecipeService,
              private subCategoryService: SubcategoryService){ }

  ngOnInit(): void {
    this.getAllRecipes();
    this.getAllSubCategories();
  }

  getAllRecipes(){
    this.recipeService.getAllRecipes()
      .subscribe({
        next: (recipes) => {
            this.recipes = recipes;
        },
        error: () => {
          console.error('error in fetching recipes');
        }
    });
  }

  getAllSubCategories(){
    this.subCategoryService.getAllSubCategories()
      .subscribe({
        next: (subCategories) => {
            this.subCategories = subCategories;
            console.log(this.subCategories)
        },
        error: () => {
          console.error('error in fetching subcategories');
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
    });
  }

  onClickOfSubCategory(subCategory_id: Number){
    this.recipeService.getAllRecipesBySubCategoryId(subCategory_id)
      .subscribe({
        next: (recipes) => {
            this.recipes = recipes;
        },
        error: () => {
          console.error('error in fetching recipes by subCategory id');
        }
    });
  }

}
