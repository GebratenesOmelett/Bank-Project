import {Injectable} from '@angular/core';
import {CustomerCreate} from "../common/customer-create";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginService} from "./login.service";
import {BehaviorSubject, exhaustMap, Observable, Subject, take, tap} from "rxjs";
import {Transfer} from "../common/transfer";
import {TransferCreate} from "../common/transfer-create";
import {CustomerReceived} from "../common/customer-received";

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private httpClient: HttpClient,
              private loginService: LoginService) {
  }

  private registerUrl = this.loginService.defaultBaseUrl + "/api/customers";
  private getTransfersUrl = this.loginService.defaultBaseUrl + "/api/transfers/email/";
  private postTransferUrl = this.loginService.defaultBaseUrl + "/api/transfers";

  transferReceivedList: Subject<Transfer[]> = new BehaviorSubject<Transfer[]>([])

  register(customerCreate: CustomerCreate) {
    return this.httpClient.post(this.registerUrl, customerCreate);
  }
  logout() {
    this.transferReceivedList = new BehaviorSubject<Transfer[]>(null!);
  }
  getTransfersByEmail(): Observable<any> {
    return this.loginService.customerReceived.pipe(take(2), exhaustMap(customer => {
      let headers_object = new HttpHeaders().set("Authorization", "Bearer " + customer.token);
      let finalUrl = this.getTransfersUrl + customer.email;
      return this.httpClient.get<Transfer[]>(finalUrl, {headers: headers_object}).pipe(tap(transfers => {
        this.transferReceivedList.next(transfers);
      }))
    }))
  }

  postTransfer(transferCreate: TransferCreate): Observable<any> {
    return this.loginService.customerReceived.pipe(take(1), exhaustMap(customer => {
      let headers_object = new HttpHeaders().set("Authorization", "Bearer " + customer.token);
      return this.httpClient.post<Transfer>(this.postTransferUrl,transferCreate, {headers: headers_object});
    }));
  }
  addTransfer(transfer : Transfer){
    this.transferReceivedList.pipe(take(1)).subscribe(list => {
      list.unshift(transfer);
      this.transferReceivedList.next(list);
    })
  }
}
