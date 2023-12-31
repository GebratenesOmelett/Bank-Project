import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {AxiosService} from "../../services/axios.service";


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
              private axiosService: AxiosService) {
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
    this.axiosService.request(
      "POST",
      "/api/customers/login",
      {
        email: this.email,
        password: this.password
      }
    ).then(data => {
      if (data.data.status) {
        this.loginService.getCustomerByEmail(this.email)
        this.loginService.getTransfersByEmail(this.email)
        this.loginService.login();
        this.router.navigateByUrl("/main")
      } else if (!data.data.status) {
        console.log("failed")
        this.loginFailed = true
      } else {
        console.log("something went wrong")
      }
    })
  }
}
