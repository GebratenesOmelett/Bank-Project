import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {CustomerReceived} from "../../common/customer-received";
import {Transfer} from "../../common/transfer";
import {RequestService} from "../../services/request.service";
import {Page} from "../../common/page";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isAuthenticated = false;
  customer!: CustomerReceived;
  transferList!: Transfer[];
  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  constructor(private loginService: LoginService,
              private requestService: RequestService) {
  }

  ngOnInit(): void {
    this.loginService.customerReceived.subscribe(customer => {
      this.isAuthenticated = !!customer;
      this.customer = customer
      if (this.isAuthenticated) {
        this.requestService.getTransfersByEmail(0).subscribe(transfer => {
          this.transferList = transfer.content;
          this.requestService.pageReceived.subscribe(page => {
            this.thePageNumber = page.number;
            this.thePageSize = page.size;
            this.theTotalElements = page.totalElements;
          })
        })
      }
    });
  }

  handleListProduct() {
    this.requestService.getTransfersByEmail(this.thePageNumber - 1)
      .subscribe(data => {
        this.transferList = data.content;
        this.thePageNumber = data.number + 1;
        this.thePageSize = data.size;
        this.theTotalElements = data.totalElements;
      });
  }
}
