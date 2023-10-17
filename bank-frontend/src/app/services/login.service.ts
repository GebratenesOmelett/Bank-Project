import {Injectable} from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AxiosService} from "./axios.service";
import {CustomerReceived} from "../common/customer-received";
import {Transfer} from "../common/transfer";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  logged!: boolean;

  customerReceived: Subject<CustomerReceived> = new BehaviorSubject<CustomerReceived>(new CustomerReceived(0, "", "", "", 0));
  transferReceived: Subject<Transfer[]> = new BehaviorSubject<Transfer[]>([])


  constructor(private httpClient: HttpClient,
              private axiosService: AxiosService) {
  }
  login(){
    this.logged = true;
  }
  logout(){
    this.logged = false;
    this.customerReceived = new BehaviorSubject<CustomerReceived>(new CustomerReceived(0, "", "", "", 0));
    this.transferReceived = new BehaviorSubject<Transfer[]>([])
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
      `/api/transfers/email/${email}`,
      null
    ).then(response => {
        this.transferReceived.next(response.data)
      }
    );
  }


}


