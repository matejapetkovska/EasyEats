import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = "http://localhost:8081/categories"

  constructor(private httpClient : HttpClient) {}

  getAllCategories(): Observable<Category[]>{
    return this.httpClient.get<Category[]>(`${this.url}`)
  }

  getCategoryById(category_id: String): Observable<Category>{
    return this.httpClient.get<Category>(`${this.url}/${category_id}`)
  }

}
