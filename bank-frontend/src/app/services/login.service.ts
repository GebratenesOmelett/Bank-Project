import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject, take, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {CustomerReceived} from "../common/customer-received";
import {CustomerLogin} from "../common/customer-login";



@Injectable({
  providedIn: 'root'
})
export class LoginService {
  defaultBaseUrl = "https://bank-project-production.up.railway.app";
  private loginUrl = this.defaultBaseUrl + "/api/customers/login";

  customerReceived: Subject<CustomerReceived> = new BehaviorSubject<CustomerReceived>(null!);

  constructor(private httpClient: HttpClient) {
  }

  logout() {
    this.customerReceived.next(null!)
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
  subtractFunds(funds : number){
    this.customerReceived.pipe(take(1)).subscribe(customer => {
      customer.funds = customer.funds - funds;
      this.customerReceived.next(customer);
    });
  }
}


