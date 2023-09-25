import { Injectable } from '@angular/core';
import {CustomerLogin} from "../common/customer-login";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = "http://localhost:8080/api/customers/login"

  constructor(private httpClient: HttpClient) { }

  loginCustomer(customerLogin: CustomerLogin): Observable<any>{
    return this.httpClient.post<CustomerLogin>(this.loginUrl, customerLogin);
  }
}
