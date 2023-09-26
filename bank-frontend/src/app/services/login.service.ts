import {Injectable} from '@angular/core';
import {CustomerLogin} from "../common/customer-login";
import {map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Customer} from "../common/customer";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  customer!: Customer;

  private loginUrl = "http://localhost:8080/api/customers/login"

  constructor(private httpClient: HttpClient) {
  }

  loginCustomer(customerLogin: CustomerLogin): Observable<any> {
    return this.httpClient.post<CustomerLogin>(this.loginUrl, customerLogin);
  }

  getCustomer(email: string): Observable<Customer> {
    const searchUrl = `http://localhost:8080/api/customers/email/${email}`;

    return this.httpClient.get<GerResponseCustomer>(searchUrl).pipe(
      map(response => response.customer));
  }
}

interface GerResponseCustomer {
  customer: Customer;
}
