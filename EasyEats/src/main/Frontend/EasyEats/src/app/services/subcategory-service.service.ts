import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {SubCategory} from '../models/sub_category';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubcategoryService {

  url = "http://localhost:8081/subcategories"

  constructor(private httpClient: HttpClient) {
  }

  getAllSubCategories(): Observable<SubCategory[]> {
    return this.httpClient.get<SubCategory[]>(`${this.url}`)
  }
}
