import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, of } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  public saveUser(user: any): void {
    const userJson = JSON.stringify(user);
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, userJson);
  }

  public saveUserUpdate(user: User): void {
    this.updateUser(user).subscribe(
      (updatedUser) => {
        if (updatedUser) {
          const userJson = JSON.stringify(updatedUser);
          window.sessionStorage.removeItem(USER_KEY);
          window.sessionStorage.setItem(USER_KEY, userJson);
        }
      },
      (error) => {
        console.error('Error updating user data:', error);
      }
    );
  }


  public getUser(): Observable<User | null> {
    const userJson = window.sessionStorage.getItem(USER_KEY);
    if (userJson) {
      try {
        const user: User = JSON.parse(userJson);
        return of(user);
      } catch (error) {
        return of(null);
      }
    }
    return of(null);
  }


  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    return !!user;
  }

  clean(): void {
    window.sessionStorage.clear();
  }

  updateUser(user: User | undefined): Observable<User | null> {
    return this.http.put<User>(`http://localhost:8081/api/user/${user?.id}`, user);
  }

  getUserFromToken(token: String | null): Observable<User>{
    return this.http.get<User>(`http://localhost:8081/api/user/token?token=${token}`)
  }
}
