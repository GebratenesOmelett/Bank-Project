import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {RequestService} from "../../services/request.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar-component',
  templateUrl: './nav-bar-component.component.html',
  styleUrls: ['./nav-bar-component.component.css']
})
export class NavBarComponentComponent implements OnInit{
  isAuthenticated = false;
  constructor(private loginService: LoginService,
              private requestService : RequestService,
              private route: Router) {
  }

  ngOnInit(): void {
    this.loginService.customerReceived.subscribe(customer =>{
      this.isAuthenticated = !!customer;
    })
  }

  logout(){
    this.route.navigate(['/home']);
    this.loginService.logout();
    this.requestService.logout();
  }
}
