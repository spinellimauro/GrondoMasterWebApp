import { MatDialog } from "@angular/material/dialog";
import { Injectable } from "@angular/core";
import { JwtHelperService } from "@auth0/angular-jwt";
import { ILogged } from "../interfaces/ILogged";
import { Subject } from "rxjs";
import { UsuariosService } from "../components/usuarios/usuarioService";
import { Rol, RolAdmin, RolUser } from "../models/Rol";

@Injectable({
  providedIn: "root",
})
export class LoggedService {
  private subject = new Subject<ILogged>();
  private _logged: ILogged;
  private _roles: Rol[] = [];
  constructor(
    private jwtService: JwtHelperService,
    private dialog: MatDialog,
    private usuarioService: UsuariosService
  ) {
    this.subject.subscribe((logged) => {
      this._logged = logged;
    });
  }

  get logged() {
    return this._logged;
  }

  checkType() {
    this._logged.Rol.forEach((rol) => {
      this.setRol(rol.authority);
    });
  }

  setRol(rol: string) {
    console.log(rol);
    if (rol == "ROLE_ADMIN") this._roles.push(new RolAdmin());
    if (rol == "ROLE_USER") this._roles.push(new RolUser());
  }

  isInRole(rol: string) {
    if (this._logged == null) {
      return false;
    } else {
      return (this._logged.Rol || []).includes(rol);
    }
  }

  getLogged() {
    return this.subject.asObservable();
  }

  setLogged() {
    this.subject.next(this.jwtService.decodeToken());
    this.checkType();
  }

  get roles(): Rol[] {
    return this._roles;
  }

  setToken(token) {
    localStorage.setItem("Grondomaster", token);
  }

  validToken() {
    if (getToken()) {
      return !this.jwtService.isTokenExpired();
    } else {
      return false;
    }
  }

  logOut() {
    localStorage.removeItem("Grondomaster");
    this.dialog.closeAll();
    this.subject.next(null);
  }
}

export function getToken(): string {
  return localStorage.getItem("Grondomaster");
}
