import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HTTP_INTERCEPTORS} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req=req.clone({
      withCredentials:true
    });
    return next.handle(req)
  }
}

export const HttpInterceptorProviders=[
  {provide: HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true}]

