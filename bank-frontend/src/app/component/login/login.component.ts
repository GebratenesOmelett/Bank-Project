import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private router: Router) {
  }
  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      login: this.formBuilder.group({
        email: [''],
        password: ['']
      })
    })
  }

  onSubmit() {
    let customerLogin = this.loginFormGroup.controls['login'].value;

    this.loginService.loginCustomer(customerLogin).subscribe(data =>{
      if(data.message == "Login Succeed"){
        console.log("successfully logged")
        this.router.navigateByUrl('/main').then();
      }
      else if(data.message == "Login Failed"){
        console.log("failed")
      }
      else{
        console.log("something got wrong")
      }
    })

  }
}
