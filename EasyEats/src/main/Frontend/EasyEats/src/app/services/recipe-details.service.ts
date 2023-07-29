import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RecipeReview} from "../models/recipe-review";

@Injectable({
  providedIn: 'root'
})
export class RecipeDetailsService {

  url = "http://localhost:8081/recipe"
  constructor(private httpClient: HttpClient) { }

  getRecipeWithReview(recipe_id: String): Observable<RecipeReview> {
    return this.httpClient.get<RecipeReview>(`${this.url}/${recipe_id}`)
  }

}
