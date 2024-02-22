export class CustomerCreate {
  constructor(public firstName: string,
              public lastName: string,
              public password: string,
              public passwordRepeat: string,
              public email: string
  ) {
  }
}
