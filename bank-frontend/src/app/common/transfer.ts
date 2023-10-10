export class Transfer {
  title: string;
  funds: number;
  receiverId: number;
  transferDate: string;
  transferTime: string;

  constructor(title: string, funds: number, receiverId: number, transferDate: string, transferTime: string) {
    this.title = title;
    this.funds = funds;
    this.receiverId = receiverId;
    this.transferDate = transferDate;
    this.transferTime = transferTime;
  }
}
