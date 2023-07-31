import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RecipeReview} from "../models/recipe-review";
import {Recipe} from "../models/recipe";

@Injectable({
  providedIn: 'root'
})
export class RecipeDetailsService {

  url = "http://localhost:8081/recipe"
  constructor(private httpClient: HttpClient) { }

  getRecipe(recipe_id: String): Observable<Recipe> {
    return this.httpClient.get<Recipe>(`${this.url}/recipeDetails/${recipe_id}`)
  }

  getRecipeWithReview(recipe_id: String): Observable<RecipeReview> {
    return this.httpClient.get<RecipeReview>(`${this.url}/${recipe_id}`)
  }

  addReview(recipe_id: String | null, formData: FormData): Observable<any> {
    return this.httpClient.post<FormData>(`${this.url}/${recipe_id}`, formData)
  }

}
