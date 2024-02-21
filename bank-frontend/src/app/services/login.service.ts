import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AxiosService} from "./axios.service";
import {CustomerReceived} from "../common/customer-received";
import {Transfer} from "../common/transfer";
import {CustomerCreate} from "../common/customer-create";
import {CustomerLogin} from "../common/customer-login";
import {CustomerAuth} from "../common/customer-auth";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private defaultBaseUrl = "http://localhost:8080";
  private loginUrl = this.defaultBaseUrl + "/api/customers/login";
  private registerUrl = this.defaultBaseUrl + "/api/customers";

  logged!: boolean;

  customerReceived: Subject<CustomerReceived> = new BehaviorSubject<CustomerReceived>(null!);
  transferReceived: Subject<Transfer[]> = new BehaviorSubject<Transfer[]>([])


  constructor(private httpClient: HttpClient,
              private axiosService: AxiosService) {
  }

  // login(){
  //   this.logged = true;
  // }
  logout() {
    this.logged = false;
    this.customerReceived = new BehaviorSubject<CustomerReceived>(null!);
    this.transferReceived = new BehaviorSubject<Transfer[]>([])
  }

  login(customeLogin: CustomerLogin): Observable<any> {
    return this.httpClient.post<CustomerAuth>(this.loginUrl, customeLogin).pipe(
      tap(resData => {
        const expirationDate = new Date(new Date().getTime() + +resData.expiresIn);
        const customer = new Customer(resData.email, resData.token, expirationDate);
        this.customer.next(customer);
      })
    )
  }


// getCustomerByEmail(email: string) {
//   this.axiosService.request(
//     "GET",
//     `/api/customers/email/${email}`,
//     null
//   ).then(response => {
//       this.customerReceived.next(response.data);
//     }
//   );
// }


  getTransfersByEmail(email : string
  ) {
    this.axiosService.request(
      "GET",
      `/api/transfers/email/${email}`,
      null
    ).then(response => {
        this.transferReceived.next(response.data)
      }
    );
  }


}


