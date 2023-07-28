import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable, of } from 'rxjs';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor() {
  }

  public saveUser(user: any): void {
    const userJson = JSON.stringify(user);
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, userJson);
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
}
