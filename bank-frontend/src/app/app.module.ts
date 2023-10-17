import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { RegistrationComponent } from './component/registration/registration.component';
import {HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './component/home/home.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NgOptimizedImage} from "@angular/common";
import { LoginComponent } from './component/login/login.component';
import { MainComponent } from './component/main/main.component';
import { TransferComponent } from './component/transfer/transfer.component';
import { NavBarComponentComponent } from './component/nav-bar-component/nav-bar-component.component';

const routes: Routes = [
  {path: 'transfer', component: TransferComponent},
  {path: 'main', component: MainComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: '**', redirectTo: '/home', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    LoginComponent,
    MainComponent,
    TransferComponent,
    NavBarComponentComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule,
    NgOptimizedImage
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
