import {AbstractControl, ValidationErrors} from "@angular/forms";

export class GeneralValidation {
  static notOnlyWhiteSpace(control: AbstractControl) : ValidationErrors{
    if((control.value != null) && (control.value.trim().length === 0)){
      return {'notOnlyWhiteSpace': true}
    }
    else{
      return null!;
    }
  }
}
