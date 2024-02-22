export class TransferCreate {
  constructor(public title: string,
              public funds: number,
              public loggedCustomerId: number,
              public receiverId: number
  ) {
  }
}
