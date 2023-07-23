import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class SignupServiceService {
  constructor(private httpClient: HttpClient) { }
  signUp(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    return this.httpClient.post<User>("http://localhost:8081/signup", user, httpOptions)
  }
}
