import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CheckuotService} from "../../services/checkuot.service";
import {Customer} from "../../common/customer";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  checkoutFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private checkoutService: CheckuotService) {
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


  onSubmit() {
    console.log(this.checkoutFormGroup.get('customer')?.value);

    let customer = this.checkoutFormGroup.controls['customer'].value;


    this.checkoutService.registerCustomer(customer).subscribe({
      next: response =>{
        alert(`You have created a new account`)
      },
      error: err =>{
        alert(`Failed to create new user`)
      }
    });
  }
}
