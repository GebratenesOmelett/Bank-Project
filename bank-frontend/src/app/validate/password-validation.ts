import {AbstractControl, ValidatorFn} from "@angular/forms";

export class PasswordValidation {
  static passwordsShouldBeTheSame(password: string, passwordRepeat: string) : ValidatorFn {
    return (control: AbstractControl) => {
      const pass = control.get(password)?.value;
      const confirmPass = control.get(passwordRepeat)?.value;
      if (pass !== confirmPass) {
        return {passwordsShouldBeTheSame: true};
      }
      return null!;
    }
  }
}

