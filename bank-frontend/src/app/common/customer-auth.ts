export class CustomerAuth {
  constructor(
    public email: string,
    public token: string,
    public expiresIn: string) {
  }
}
