import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {GeneralValidation} from "../../validate/general-validation";
import {PasswordValidation} from "../../validate/password-validation";
import {CustomerCreate} from "../../common/customer-create";
import {RequestService} from "../../services/request.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  emailDoesExist!: boolean;

  checkoutFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private requestService: RequestService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: new FormControl('', [Validators.required, GeneralValidation.notOnlyWhiteSpace]),
        lastName: new FormControl('', [Validators.required, GeneralValidation.notOnlyWhiteSpace]),
        email: new FormControl('', [Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
        password: new FormControl('', [Validators.required, GeneralValidation.notOnlyWhiteSpace, Validators.minLength(8)]),
        passwordRepeat: new FormControl('', [])
      },
        {
          validators : PasswordValidation.passwordsShouldBeTheSame("password", "passwordRepeat")
        })
    });
  }
  get passwordValidation(){
    return this.checkoutFormGroup.controls['customer'];
  }

  get getFirstName() {
    return this.checkoutFormGroup.get('customer.firstName')?.value;
  }

  get getLastName() {
    return this.checkoutFormGroup.get('customer.lastName')?.value;
  }

  get getEmail() {
    return this.checkoutFormGroup.get('customer.email')?.value;
  }

  get getPassword() {
    return this.checkoutFormGroup.get('customer.password')?.value;
  }

  get getPasswordRepeat() {
    return this.checkoutFormGroup.get('customer.passwordRepeat')?.value;
  }

  get firstName() {
    return this.checkoutFormGroup.get('customer.firstName');
  }

  get lastName() {
    return this.checkoutFormGroup.get('customer.lastName');
  }

  get email() {
    return this.checkoutFormGroup.get('customer.email');
  }

  get password() {
    return this.checkoutFormGroup.get('customer.password');
  }

  get passwordRepeat() {
    return this.checkoutFormGroup.get('customer.passwordRepeat');
  }



  onSubmit() {
    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
      return
    }
    let customer = new CustomerCreate(this.getFirstName, this.getLastName, this.getPassword, this.getPasswordRepeat, this.getEmail)
    this.requestService.register(customer).subscribe({
      next: response=>{
        this.router.navigate(['/home'])
      },
      error: err => {
        if(err.status == 400){
          this.emailDoesExist = true;
        }
      }
    })
  }
}
