import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Customer} from "../common/customer";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckuotService {

  private createUrl = "http://localhost:8080/api/customers";

  constructor(private httpClient: HttpClient ) {};

  registerCustomer(customer: Customer): Observable<any>{
    return this.httpClient.post<Customer>(this.createUrl, customer);
  }
}
