import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {CustomerReceived} from "../../common/customer-received";
import {Transfer} from "../../common/transfer";
import {RequestService} from "../../services/request.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isAuthenticated = false;
  customer!: CustomerReceived;
  transferList!: Transfer[];

  constructor(private loginService: LoginService,
              private requestService: RequestService) {
  }

  ngOnInit(): void {
    this.loginService.customerReceived.subscribe(customer => {
      this.isAuthenticated = !!customer;
      this.customer = customer
      if(this.isAuthenticated){
        this.requestService.getTransfersByEmail().subscribe(transfer =>{
          this.transferList = transfer;
        })
      }
    });
  }

}
