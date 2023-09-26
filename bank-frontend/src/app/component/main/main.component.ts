import {Component, OnInit} from '@angular/core';
import {Customer} from "../../common/customer";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{

  customer!: Customer;
  ngOnInit(): void {
  }

  setCustomer(customer: Customer){
    this.customer = customer;
  }

}
