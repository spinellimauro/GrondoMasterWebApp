import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from '../../utils/config.service';
import { Usuario } from '../../models/Usuario';
import { IConfirmEmail } from 'src/app/interfaces/IConfirmarEmail';
import { IChangePassword } from 'src/app/interfaces/IChangePassword';

@Injectable()
export class UsuariosService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  getUsuarios() {
    return this.http.get(this.configService.UsuarioURI + 'Logged/GetUsuarios');
  }

  createUsuario(model: Usuario) {
    return this.http.post(this.configService.AuthURI + 'signup', model);
  }

  editUsuario(idUsuario: number, model: Usuario) {
    return this.http.put(
      this.configService.UsuarioURI + 'Logged/MUsuario/' + idUsuario,
      model
    );
  }

  deleteUsuario(idUsuario) {
    return this.http.delete(
      this.configService.UsuarioURI + 'Logged/BUsuario/' + idUsuario
    );
  }

  getUsuario(id: string) {
    return this.http.get(
      this.configService.UsuarioURI + 'Logged/GetUsuario/' + id
    );
  }

  login(model: Usuario) {
    return this.http.post(this.configService.AuthURI + 'signin', model);
  }

  confirmEmail(model: IConfirmEmail) {
    return this.http.post(
      this.configService.UsuarioURI + 'ConfirmEmail',
      model
    );
  }

  changePassword(model: IChangePassword) {
    return this.http.post(
      this.configService.UsuarioURI + 'changePassword',
      model
    );
  }

  confirmPassword(model: IChangePassword) {
    return this.http.put(
      this.configService.UsuarioURI + 'confirmForgotPassword',
      model
    );
  }

  forgotPassword(email: string) {
    return this.http.get(
      this.configService.UsuarioURI + 'forgotPassword/' + email
    );
  }

  validateTokenRequest(token: string) {
    return this.http.get(
      this.configService.UsuarioURI + 'validateToken/' + token
    );
  }

  getRoles() {
    return this.http.get(this.configService.UsuarioURI + 'GetRoles');
  }
}
