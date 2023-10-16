
export class CustomerReceived {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  funds: number;

  constructor(id: number, firstName: string, lastName: string, email: string, funds: number) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.funds = funds;
  }
}
