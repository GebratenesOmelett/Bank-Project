import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CheckuotService} from "../../services/checkuot.service";
import {AxiosService} from "../../services/axios.service";
import {LoginService} from "../../services/login.service";
import {CustomerReceived} from "../../common/customer-received";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit{

  transferFormGroup!: FormGroup;
  customer!: CustomerReceived;

  constructor(private formBuilder: FormBuilder,
              private  checkoutService: CheckuotService,
              private axiosService: AxiosService,
              private loginService: LoginService,
              private router: Router ) {
  }

  ngOnInit(): void {
    this.transferFormGroup = this.formBuilder.group({
      transfer: this.formBuilder.group({
        title: [''],
        funds: [''],
        receiverId: ['']
      })
    });
    this.loginService.customerReceived.subscribe(customer => this.customer = customer);
  }
  get title(){
    return this.transferFormGroup.get('transfer.title')?.value;
  }
  get funds(){
    return this.transferFormGroup.get('transfer.funds')?.value;
  }
  get receiverId(){
    return this.transferFormGroup.get('transfer.receiverId')?.value;
  }

  onSubmit() {
    this.axiosService.request(
      "POST",
      "/api/transfers",
      {
        title: this.title,
        funds: this.funds,
        loggedCustomerId: this.customer.id,
        receiverId: this.receiverId,
      }
    ).then(data =>
      this.router.navigateByUrl("/main")
    );
  }
}
