import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private httpClient : HttpClient) {}
  
  getAllRecipesByCategoryId(category_id: String): Observable<Recipe[]>{
    return this.httpClient.get<Recipe[]>(`http://localhost:8081/recipes/${category_id}`)
  }
}
