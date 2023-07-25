import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  url = "http://localhost:8081/recipes"

  constructor(private httpClient : HttpClient) {}
  
  getAllRecipesByCategoryId(category_id: String): Observable<Recipe[]>{
    return this.httpClient.get<Recipe[]>(`${this.url}/${category_id}`)
  }

  getAllRecipesByCategoryIdAndSubCategoryId(category_id: String,
                                            subCategory_id: Number): Observable<Recipe[]>{
    return this.httpClient.get<Recipe[]>(`${this.url}/${category_id}/${subCategory_id}`)
  }
}
