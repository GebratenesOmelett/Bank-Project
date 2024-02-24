import {Injectable} from '@angular/core';
import {CustomerCreate} from "../common/customer-create";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginService} from "./login.service";
import {BehaviorSubject, exhaustMap, Observable, Subject, take, tap} from "rxjs";
import {Transfer} from "../common/transfer";
import {TransferCreate} from "../common/transfer-create";
import {Page} from "../common/page";

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
  pageReceived: Subject<Page> = new BehaviorSubject<Page>(null!)

  register(customerCreate: CustomerCreate) {
    return this.httpClient.post(this.registerUrl, customerCreate);
  }

  logout() {
    this.transferReceivedList.next(null!);
  }

  getTransfersByEmail(page : number): Observable<any> {
    return this.loginService.customerReceived.pipe(take(1), exhaustMap(customer => {
      let headers_object = new HttpHeaders().set("Authorization", "Bearer " + customer.token);
      let finalUrl = this.getTransfersUrl + customer.email +"?page="+ page;
      return this.httpClient.get<GetResponseTransfer>(finalUrl, {headers: headers_object}).pipe(tap(transfersPage => {

        let page = new Page(transfersPage.totalPages + 1, transfersPage.size, transfersPage.number, transfersPage.number);
        this.pageReceived.next(page);
        let listTransfer = transfersPage.content;
        this.transferReceivedList.next(listTransfer);
      }))
    }))
  }


  postTransfer(transferCreate: TransferCreate): Observable<any> {
    return this.loginService.customerReceived.pipe(take(1), exhaustMap(customer => {
      let headers_object = new HttpHeaders().set("Authorization", "Bearer " + customer.token);
      return this.httpClient.post<Transfer>(this.postTransferUrl, transferCreate, {headers: headers_object});
    }));
  }

  addTransfer(transfer: Transfer) {
    this.transferReceivedList.pipe(take(1)).subscribe(list => {
      list.unshift(transfer);
      this.transferReceivedList.next(list);
    })
  }
}

interface GetResponseTransfer {
  content: Transfer[],
  totalElements: number,
  totalPages: number,
  size: number;
  number: number
}
