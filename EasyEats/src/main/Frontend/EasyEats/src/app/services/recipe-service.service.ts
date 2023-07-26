import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';
import {Ingredient} from "../models/ingredient";

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

  getAllRecipes(): Observable<Recipe[]>{
    return this.httpClient.get<Recipe[]>(`${this.url}/`)
  }

  getRecipesByQueryText(queryText: String | null): Observable<Recipe[]>{
    if(queryText == null || queryText == ""){
      return this.httpClient.get<Recipe[]>(`${this.url}/`)
    }else{
      return this.httpClient.get<Recipe[]>(`${this.url}/query/${queryText}`)
    }
  }
  
  getAllRecipesBySubCategoryId(subCategory_id: Number): Observable<Recipe[]>{
    return this.httpClient.get<Recipe[]>(`${this.url}/subcategory/${subCategory_id}`)
  }
}
