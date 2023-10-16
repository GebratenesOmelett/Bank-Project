import {Component, OnInit} from '@angular/core';
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
      this.customer = customer
    });
    this.loginService.transferReceived.subscribe(transfer => this.transferSet = transfer);

  }

}
