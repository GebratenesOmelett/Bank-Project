import {Component, OnInit} from '@angular/core';
import {CustomerCreate} from "../../common/customerCreate";
import {LoginService} from "../../services/login.service";
import {CustomerReceived} from "../../common/customer-received";
import {Transfer} from "../../common/transfer";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  customer!: CustomerReceived;
  transferSet!: Transfer[];

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
      this.loginService.customerReceived.subscribe(customer => {
        if(customer?.email === this.customer?.email){
          console.log(customer?.email)
          console.log(this.customer?.email)
          this.loginService.getCustomerByEmail(this.customer.email);
          this.loginService.getTransfersByEmail(this.customer.email);
        }
        this.customer = customer
      });
      this.loginService.transferReceived.subscribe(transfer => this.transferSet = transfer);

  }


}
