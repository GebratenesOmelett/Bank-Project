import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CheckuotService} from "../../services/checkuot.service";
import {CustomerCreate} from "../../common/customerCreate";
import {AxiosService} from "../../services/axios.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  checkoutFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private checkoutService: CheckuotService,
              private axiosService: AxiosService) {
  }

  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: [''],
        lastName: [''],
        email: [''],
        password: [''],
        passwordRepeat: ['']
      })
    });
  }

  get firstName(){
    return this.checkoutFormGroup.get('customer.firstName')?.value;
  }
  get lastName(){
    return this.checkoutFormGroup.get('customer.lastName')?.value;
  }
  get email(){
    return this.checkoutFormGroup.get('customer.email')?.value;
  }
  get password(){
    return this.checkoutFormGroup.get('customer.password')?.value;
  }
  get passwordRepeat(){
    return this.checkoutFormGroup.get('customer.passwordRepeat')?.value;
  }
  onSubmit() {
    this.axiosService.request(
      "POST",
      "/api/customers",
      {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
        passwordRepeat: this.passwordRepeat
      }
    )
  }
}
