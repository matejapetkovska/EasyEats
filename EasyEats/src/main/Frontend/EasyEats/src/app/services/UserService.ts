import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import {HttpClient } from "@angular/common/http";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    return !!user;
  }

  getUserFromToken(token: String | null): Observable<User>{
    return this.http.get<User>(`http://localhost:8081/api/user/token?token=${token}`)
  }

  updateUser(user: User | undefined, token: string | null): Observable<User | null> {
    return this.http.put<User>(`http://localhost:8081/api/user/${user?.id}?token=${token}`, {
      first_name: user?.first_name,
      last_name: user?.last_name,
      email: user?.email,
      userName: user?.userName,
      passw: user?.password,
      role: user?.role,
      image: user?.image,
    });
  }

  changeProfilePicture(user: User, formData: FormData): Observable<User>{
    return this.http.put<User>(`http://localhost:8081/api/user/profilepicture/${user.id}`, formData)
  }
}

