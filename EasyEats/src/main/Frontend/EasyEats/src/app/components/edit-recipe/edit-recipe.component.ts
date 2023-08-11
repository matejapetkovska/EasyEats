import {Component} from '@angular/core';
import {Category} from "../../models/category";
import {SubCategory} from "../../models/sub_category";
import {Ingredient} from "../../models/ingredient";
import {CategoryService} from "../../services/category-service.service";
import {SubcategoryService} from "../../services/subcategory-service.service";
import {RecipeService} from "../../services/recipe-service.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Recipe} from "../../models/recipe";
import {RecipeDetailsService} from "../../services/recipe-details.service";

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent {
  categories: Category[] | undefined

  subCategories: SubCategory[] | undefined

  title: String = ''

  description: String = ''

  selectedCategoryId: Number | undefined

  selectedSubCategoryId: Number | undefined

  ingredient: Ingredient = {
    name: '',
    quantity: 0,
    measurementUnit: ''
  }

  ingredients: Ingredient[] = []

  recipe: Recipe | undefined

  errorMessage: String = ''

  constructor(private recipeDetailsService: RecipeDetailsService,
              private recipeService: RecipeService,
              private categoryService: CategoryService,
              private subCategoryService: SubcategoryService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const recipe_id = params.get('recipe_id');
      this.fetchRecipeDetails(recipe_id);
    });
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

  fetchRecipeDetails(recipe_id: string | null): void {
    if (!recipe_id) return;
    this.recipeDetailsService.getRecipe(recipe_id).subscribe(
      (data) => {
        this.recipe = data;
        this.title = this.recipe.title;
        this.description = this.recipe.description;
        this.selectedCategoryId = this.recipe.category.id;
        this.selectedSubCategoryId = this.recipe.subCategory.id;
        this.ingredients = this.recipe.ingredients;
      },
      (error) => {
        console.error('Error fetching recipe details:', error);
      }
    );
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
    const token = localStorage.getItem('token')
    if (this.title != null && this.description != null && this.selectedCategoryId != null &&
      this.selectedSubCategoryId != null && this.ingredients != null && token != null) {
      formData.append('title', this.title.toString())
      formData.append('description', this.description.toString())
      formData.append('category_id', this.selectedCategoryId.toString())
      formData.append('subCategory_id', this.selectedSubCategoryId.toString())
      formData.append('ingredients', JSON.stringify(this.ingredients))
      formData.append('token', token.toString())
    }
    return formData
  }

  onEditRecipe() {
    const formData = this.createFormData()
    this.route.paramMap.subscribe(params => {
      const recipe_id = params.get('recipe_id')
      this.recipeService.editRecipe(recipe_id, formData)
        .subscribe({
          next: () => {
            this.router.navigate(['/recipes']);
            console.log('Recipe edited successfully');
          },
          error: (error) => {
            if (error.status === 400) {
              this.errorMessage = error.error.message
              console.log(this.errorMessage)
            }
            console.log('Error in editing recipe');
          }
        });
    })
  }
}
