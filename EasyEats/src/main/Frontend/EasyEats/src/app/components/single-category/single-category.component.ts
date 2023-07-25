import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Recipe } from 'src/app/models/recipe';
import { CategoryService } from 'src/app/services/category-service.service';
import { RecipeService } from 'src/app/services/recipe-service.service';

@Component({
  selector: 'app-single-category',
  templateUrl: './single-category.component.html',
  styleUrls: ['./single-category.component.css']
})
export class SingleCategoryComponent implements OnInit {

  category_id!: string

  recipes: Recipe[] | undefined

  category: Category | undefined

  constructor(private route: ActivatedRoute,
              private recipeService: RecipeService,
              private router: Router,
              private categoryService: CategoryService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.category_id = params['category_id'];
      this.fetchRecipes();
      this.getCategoryById();
    });
  }

  getCategoryById(){
    this.categoryService.getCategoryById(this.category_id)
      .subscribe({
        next: (category) => {
          this.category = category;
        },
        error: () => {
          console.error('error in getting category by id');
        }
      })
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

  onClickOfSubCategory(subCategory_id: Number){
    this.recipeService.getAllRecipesByCategoryIdAndSubCategoryId(this.category_id, subCategory_id)
    .subscribe({
      next: (recipes) => {
        if(recipes != null){
          this.recipes = recipes;
        }else{
          this.router.navigate(['/categories']);
        }
      },
      error: () => {
        console.error('error in fetching recipes by category and sub category');
      }
  });
  }

}
