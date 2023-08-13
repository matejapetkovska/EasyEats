import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {RegisterRequest} from '../models/registerRequest';
import {AuthResponse} from '../models/authResponse';
import {LoginRequest} from '../models/loginRequest';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isAuthenticated());


  constructor(private httpClient: HttpClient) {
  }

  register(request: RegisterRequest): Observable<AuthResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post<AuthResponse>("http://localhost:8081/api/auth/register", request, {headers});
  }

  login(request: LoginRequest): Observable<AuthResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    this.isAuthenticatedSubject.next(true);

    return this.httpClient.post<AuthResponse>("http://localhost:8081/api/auth/authenticate", request, {headers});

  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  isAuthenticated$(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }

}
