
export class CustomerReceived {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  funds: number;
  token: string,
  expiresIn: string

  constructor(id: number, firstName: string, lastName: string, funds: number, email: string, token: string, expiresIn: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.funds = funds;
    this.email = email;
    this.token = token;
    this.expiresIn = expiresIn;
  }
}
