import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AxiosService} from "./axios.service";
import {CustomerReceived} from "../common/customer-received";
import {CustomerLogin} from "../common/customer-login";


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  defaultBaseUrl = "http://localhost:8080";
  private loginUrl = this.defaultBaseUrl + "/api/customers/login";

  customerReceived: Subject<CustomerReceived> = new BehaviorSubject<CustomerReceived>(null!);

  constructor(private httpClient: HttpClient) {
  }

  logout() {
    this.customerReceived = new BehaviorSubject<CustomerReceived>(null!);
  }


  login(customerLogin: CustomerLogin): Observable<any> {
    return this.httpClient.post<CustomerReceived>(this.loginUrl, customerLogin).pipe(
      tap(resData => {
        const expirationDate = new Date(new Date().getTime() + +resData.expiresIn);
        const customer = new CustomerReceived(resData.id,
            resData.firstName,
            resData.lastName,
            resData.funds,
            resData.email,
            resData.token,
            expirationDate
          );
        this.customerReceived.next(customer);
      })
    )
  }

}


