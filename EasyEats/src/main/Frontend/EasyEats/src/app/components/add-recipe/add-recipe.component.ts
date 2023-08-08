import {Component, OnInit} from '@angular/core';
import {Category} from 'src/app/models/category';
import {Ingredient} from 'src/app/models/ingredient';
import {SubCategory} from 'src/app/models/sub_category';
import {CategoryService} from 'src/app/services/category-service.service';
import {RecipeService} from 'src/app/services/recipe-service.service';
import {SubcategoryService} from 'src/app/services/subcategory-service.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

  categories: Category[] | undefined

  subCategories: SubCategory[] | undefined

  title: String = ''

  description: String = ''

  selectedFile: File | null = null

  selectedCategoryId: Number | undefined

  selectedSubCategoryId: Number | undefined

  ingredient: Ingredient = {
    name: '',
    quantity: 0,
    measurementUnit: ''
  }

  ingredients: Ingredient[] = []

  errorMessage: String = ''

  constructor(private categoryService: CategoryService,
              private subCategoryService: SubcategoryService,
              private recipeService: RecipeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllCategories();
    this.getAllSubCategories();
  }

  getAllCategories() {
    this.categoryService.getAllCategories()
      .subscribe({
        next: (categories) => {
          this.categories = categories;
        },
        error: () => {
          console.log('error in getting categories');
        }
      });
  }

  getAllSubCategories() {
    this.subCategoryService.getAllSubCategories()
      .subscribe({
        next: (subCategories) => {
          this.subCategories = subCategories;
        },
        error: () => {
          console.log('error in getting categories');
        }
      });
  }

  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onAddIngredient() {
    if (this.ingredient.name != "" && this.ingredient.quantity != 0) {
      let copyOfIngredient = {...this.ingredient}
      this.ingredients.push(copyOfIngredient)
      this.ingredient.name = ''
      this.ingredient.quantity = 0
      this.ingredient.measurementUnit = ''
    }
  }

  createFormData(): FormData {
    const formData = new FormData();
    if (this.title != null && this.description != null &&
      this.selectedFile != null && this.selectedCategoryId != null &&
      this.selectedSubCategoryId != null && this.ingredients != null) {
      formData.append('title', this.title.toString())
      formData.append('description', this.description.toString())
      formData.append('file', this.selectedFile);
      formData.append('category_id', this.selectedCategoryId.toString())
      formData.append('subCategory_id', this.selectedSubCategoryId.toString())
      formData.append('ingredients', JSON.stringify(this.ingredients))
    }
    return formData
  }

  onAddRecipe() {
    const formData = this.createFormData()
    this.recipeService.addRecipe(formData)
      .subscribe({
        next: () => {
          console.log('Recipe added successfully');
          this.router.navigate(['/recipes'])
        },
        error: (error) => {
          if (error.status === 400) {
            this.errorMessage = error.error.message
          }
          console.log('error in adding recipe');
          this.router.navigate(['/login'])
        }
      });
  }
}


