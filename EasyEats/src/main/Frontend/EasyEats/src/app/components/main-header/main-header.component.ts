import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.css']
})
export class MainHeaderComponent {
  constructor(private http: HttpClient) { }

  onLogOut():void {
    this.http.get("/logout")
  }
}
