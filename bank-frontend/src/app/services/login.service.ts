import {Injectable} from '@angular/core';
import {CustomerLogin} from "../common/customer-login";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CustomerCreate} from "../common/customerCreate";
import {AxiosService} from "./axios.service";
import {CustomerReceived} from "../common/customer-received";
import {Transfer} from "../common/transfer";

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  customerReceived: Subject<CustomerReceived> = new BehaviorSubject<CustomerReceived>(new CustomerReceived(0, "", "", "", 0));
  transferReceived: Subject<Transfer[]> = new BehaviorSubject<Transfer[]>([])

  private loginUrl = "http://localhost:8080/api/customers/login"

  constructor(private httpClient: HttpClient,
              private axiosService: AxiosService) {
  }

  getCustomerByEmail(email: string) {
    this.axiosService.request(
      "GET",
      `/api/customers/email/${email}`,
      null
    ).then(response => {
        this.customerReceived.next(response.data);
      }
    );
  }

  getTransfersByEmail(email: string) {
    this.axiosService.request(
      "GET",
      `api/transfers/email/${email}`,
      null
    ).then(response => {
        this.transferReceived.next(response.data)
      }
    );
  }
}


