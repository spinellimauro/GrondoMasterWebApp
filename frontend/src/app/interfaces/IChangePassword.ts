export interface IChangePassword {
  Id: string;
  oldPassword: string;
  newPassword: string;
  newPasswordConfirmed: string;
  token: string;
}
