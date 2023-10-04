import {Injectable} from '@angular/core';
import {CustomerLogin} from "../common/customer-login";
import {map, Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Customer} from "../common/customer";

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  email: string = "";

  private loginUrl = "http://localhost:8080/api/customers/login"

  constructor(private httpClient: HttpClient) {
  }

  loginCustomer(customerLogin: CustomerLogin): Observable<any> {
    const headers = new HttpHeaders({Authorization: 'Basic '+btoa(customerLogin.email+":"+customerLogin.password)})
    return this.httpClient.post<CustomerLogin>(this.loginUrl, customerLogin, {headers, responseType:'text' as 'json'});
  }

  getCustomer(): Observable<Customer>{
    console.log(this.email)
    const searchUrl = `http://localhost:8080/api/customers/email/${this.email}`;
    return this.httpClient.get<Customer>(searchUrl);
  }





}


