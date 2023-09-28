import {Component, OnInit} from '@angular/core';
import {Customer} from "../../common/customer";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  customer!: Customer;

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.setCustomer();
    console.log(this.customer)
  }

  setCustomer() {
    if (this.customer == null) {
      this.loginService.getCustomer().subscribe(customer => this.customer = customer);
    }
  }

}
