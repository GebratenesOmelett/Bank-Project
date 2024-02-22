import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {GeneralValidation} from "../../validate/general-validation";
import {Transfer} from "../../common/transfer";
import {RequestService} from "../../services/request.service";
import {TransferCreate} from "../../common/transfer-create";

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  transferFormGroup!: FormGroup;
  transferSet!: Transfer[];
  addressBookHaspMap = new Set<number>;
  isAuthenticated = false;
  customerId! : number;
  customerFunds! : number;
  customerDoesExist!: boolean;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private requestService: RequestService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.transferFormGroup = this.formBuilder.group({
      transfer: this.formBuilder.group({
          title: new FormControl('', [Validators.required, GeneralValidation.notOnlyWhiteSpace]),
          funds: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$"), Validators.min(0.01)]),
          receiverId: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")])
        }
      )
    });
    this.loginService.customerReceived.subscribe(customer =>{
      this.isAuthenticated = !!customer;
      if(this.isAuthenticated){
        this.customerId = customer.id
        this.customerFunds = customer.funds;
        this.requestService.transferReceivedList.subscribe(transfer=>{
          this.transferSet = transfer;
          this.getAddressBook()
        })
      }
    })
  }

  get getTitle() {
    return this.transferFormGroup.get('transfer.title')?.value;
  }

  get getFunds() {
    return this.transferFormGroup.get('transfer.funds')?.value;
  }

  get getReceiverId() {
    return this.transferFormGroup.get('transfer.receiverId')?.value;
  }

  get title() {
    return this.transferFormGroup.get('transfer.title');
  }

  get funds() {
    return this.transferFormGroup.get('transfer.funds');
  }

  get receiverId() {
    return this.transferFormGroup.get('transfer.receiverId');
  }
  getAddressBook(){
    for(let i = 0; i < this.transferSet.length; i++){
      this.addressBookHaspMap.add(this.transferSet[i].receiverId);
    }
  }

  onSubmit() {
    this.customerDoesExist = false;
    if (this.transferFormGroup.invalid) {
      this.transferFormGroup.markAllAsTouched();
      return
    } else if (this.customerId == this.getReceiverId) {
      return
    } else if (this.customerFunds < this.getFunds) {
      return
    }
    let task = new TransferCreate(this.getTitle,this.getFunds, this.customerId, this.getReceiverId)
    // this.axiosService.request(
    //   "POST",
    //   "/api/transfers",
    //   {
    //     title: this.getTitle,
    //     funds: this.getFunds,
    //     loggedCustomerId: this.customer.id,
    //     receiverId: this.getReceiverId,
    //   }
    // ).then(() => {
    //     this.router.navigateByUrl("/main")
    //     this.fullRefresh()
    //   }
    // ).catch(() =>
    //   this.customerDoesExist = true);
    this.requestService.postTransfer(task).subscribe(transfer=>{
      this.requestService.addTransfer(transfer);
    })
  }
}
