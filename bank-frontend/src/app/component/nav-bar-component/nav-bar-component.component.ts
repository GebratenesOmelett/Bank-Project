import { Component } from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-nav-bar-component',
  templateUrl: './nav-bar-component.component.html',
  styleUrls: ['./nav-bar-component.component.css']
})
export class NavBarComponentComponent {
  logged: boolean = this.loginService.logged;
  constructor(public loginService: LoginService) {
  }

  login(){
    this.loginService.login();
  }
  logout(){
    this.loginService.logout();
  }
}
