export class Customer {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  passwordRepeat: string;
  constructor(firstName: string, lastName: string, email: string, password: string, passwordRepeat: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.passwordRepeat = passwordRepeat;
  }
}
