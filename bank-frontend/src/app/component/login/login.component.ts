import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {CustomerLogin} from "../../common/customer-login";
import {RequestService} from "../../services/request.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginFailed!: boolean;
  loginFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private router: Router,
              private requestService : RequestService) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      login: this.formBuilder.group({
        email: [''],
        password: ['']
      })
    })
  }

  get email() {
    return this.loginFormGroup.get("login.email")?.value;
  }

  get password() {
    return this.loginFormGroup.get("login.password")?.value;
  }

  onSubmit() {
    let customer = new CustomerLogin(this.email, this.password);
    this.loginService.login(customer).subscribe({
      next: response=>{
        this.router.navigate(['/main'])
      },
      error: err => {
        this.loginFailed = true
      }
    })
  }
}
