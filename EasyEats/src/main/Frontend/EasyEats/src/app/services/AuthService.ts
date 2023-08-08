import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserService} from "./UserService";
import { RegisterRequest } from '../models/registerRequest';
import { AuthResponse } from '../models/authResponse';
import { LoginRequest } from '../models/loginRequest';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  register(request: RegisterRequest): Observable<AuthResponse>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json' 
    });
    return this.httpClient.post<AuthResponse>("http://localhost:8081/api/auth/register", request, { headers });
  }

  login(request: LoginRequest): Observable<AuthResponse>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json' 
    });
    return this.httpClient.post<AuthResponse>("http://localhost:8081/api/auth/authenticate", request, { headers });
  
  }

}
