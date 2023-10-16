import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AxiosService} from "../../services/axios.service";
import {LoginService} from "../../services/login.service";
import {CustomerReceived} from "../../common/customer-received";
import {Router} from "@angular/router";
import {GeneralValidation} from "../../validate/general-validation";

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  transferFormGroup!: FormGroup;
  customer!: CustomerReceived;

  customerDoesExist!: boolean;

  constructor(private formBuilder: FormBuilder,
              private axiosService: AxiosService,
              private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.transferFormGroup = this.formBuilder.group({
      transfer: this.formBuilder.group({
          title: new FormControl('', [Validators.required, GeneralValidation.notOnlyWhiteSpace]),
          funds: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$"), Validators.min(0.01)]),
          receiverId: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")])
        }
      )
    });
    this.loginService.customerReceived.subscribe(customer => this.customer = customer);
  }

  get getTitle() {
    return this.transferFormGroup.get('transfer.title')?.value;
  }

  get getFunds() {
    return this.transferFormGroup.get('transfer.funds')?.value;
  }

  get getReceiverId() {
    return this.transferFormGroup.get('transfer.receiverId')?.value;
  }

  get title() {
    return this.transferFormGroup.get('transfer.title');
  }

  get funds() {
    return this.transferFormGroup.get('transfer.funds');
  }

  get receiverId() {
    return this.transferFormGroup.get('transfer.receiverId');
  }

  onSubmit() {
    if (this.transferFormGroup.invalid) {
      this.transferFormGroup.markAllAsTouched();
      return
    } else if (this.customer.id == this.getReceiverId) {
      return
    } else if (this.customer.funds < this.getFunds) {
      return
    }
    this.axiosService.request(
      "POST",
      "/api/transfers",
      {
        title: this.getTitle,
        funds: this.getFunds,
        loggedCustomerId: this.customer.id,
        receiverId: this.getReceiverId,
      }
    ).then(() => {
        this.router.navigateByUrl("/main")
        this.fullRefresh()
      }
    ).catch(() =>
      this.customerDoesExist = true);
  }

  fullRefresh() {
    this.loginService.getTransfersByEmail(this.customer.email);
    this.loginService.getCustomerByEmail(this.customer.email);
  }

}
