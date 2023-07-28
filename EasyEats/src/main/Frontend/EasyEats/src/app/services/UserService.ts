import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Observable, of} from "rxjs";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor() {}

  // clean(): void {
  //   window.localStorage.clear();
  // }
  //
  // public saveUser(user: any): void {
  //   window.localStorage.removeItem(USER_KEY);
  //   window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  //
  // }

  public getUser(): Observable<User | null> {
    const userJson = window.localStorage.getItem(USER_KEY);
    if (userJson) {
      const user: User = JSON.parse(userJson);
      return of(user);
    }

    return of(null);
  }

  // public isLoggedIn(): boolean {
  //   const user = window.localStorage.getItem(USER_KEY);
  //   if (user) {
  //     return true;
  //   }
  //
  //   return false;
  // }
}

