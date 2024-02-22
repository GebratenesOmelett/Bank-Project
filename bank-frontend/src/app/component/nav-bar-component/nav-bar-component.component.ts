import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-nav-bar-component',
  templateUrl: './nav-bar-component.component.html',
  styleUrls: ['./nav-bar-component.component.css']
})
export class NavBarComponentComponent implements OnInit{
  isAuthenticated = false;
  constructor(public loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.customerReceived.subscribe(customer =>{
      this.isAuthenticated = !!customer;
    })
  }

  logout(){
    this.loginService.logout();
  }
}
