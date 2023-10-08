import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CustomerCreate} from "../common/customerCreate";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckuotService {

  private createUrl = "http://localhost:8080/api/customers";

  constructor(private httpClient: HttpClient ) {};

  registerCustomer(customer: CustomerCreate): Observable<any>{
    return this.httpClient.post<CustomerCreate>(this.createUrl, customer);
  }
}
