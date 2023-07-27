import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Recipe} from "../models/recipe";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RecipeDetailsService {

  url = "http://localhost:8081/recipe"
  constructor(private httpClient: HttpClient) { }
  getRecipeDetails(recipe_id: String): Observable<Recipe> {
    return this.httpClient.get<Recipe>(`${this.url}/${recipe_id}`)
  }
}
