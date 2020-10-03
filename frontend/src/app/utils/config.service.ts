import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class ConfigService {
  constructor(private http: HttpClient) {}

  private _mensajes: Mensajes;
  private _validaciones: Validaciones;
  private _etiquetas: Etiquetas;
  private _uri: URI;
  private _configuraciones: Configuraciones;

  readData() {
    const promise = this.http
      .get('assets/config.json', { responseType: 'text' })
      .toPromise();

    promise.then((data) => {
      const config = JSON.parse(data);

      this._uri = config['URI'];
      this._mensajes = config['Mensajes'];
      this._validaciones = config['Validaciones'];
      this._etiquetas = config['Etiquetas'];
      this._configuraciones = config['Configuraciones'];
    });

    return promise;
  }

  get mensajes(): Mensajes {
    return this._mensajes;
  }

  get validaciones(): Validaciones {
    return this._validaciones;
  }

  get etiquetas(): Etiquetas {
    return this._etiquetas;
  }

  get configuraciones(): Configuraciones {
    return this._configuraciones;
  }

  get AuthURI(): string {
    return this.URI(this._uri.Auth);
  }

  get UsuarioURI(): string {
    return this.URI(this._uri.Usuario);
  }

  get JugadorURI(): string {
    return this.URI(this._uri.Jugador);
  }

  get ServerURI(): string {
    return this._uri.Server;
  }

  private URI(controllerName: string): string {
    return this._uri.Server + this._uri.Api + controllerName + '/';
  }
}

interface Mensajes {
  TituloSuccess: string;

  AUsuario: string;
  MUsuario: string;
  BUsuario: string;
}

interface Validaciones {
  Users: {
    Rol: string;
    Nombre: string;
    Apellido: string;
    Email: string;
    Password: string;
    PasswordMinLength: string;
    PasswordMaxLength: string;
    PasswordSpecific: string;
  };

  Login: {
    Usuario: string;
    Password: string;
  };

  ValidacionContrasenaNueva: string[];
  ConfirmacionContrasenaNueva: string;
}

interface Etiquetas {
  Users: {
    Rol: string;
    Datos: string;
    Habilitacion: string;
  };

  Login: {
    Titulo: string;
  };

  Register: string;
  Guardar: string;
  Seleccione: string;
}

interface URI {
  Server: string;
  Api: string;
  Auth: string;
  Usuario: string;
  Jugador: string;
}

interface Configuraciones {
  ImagenDefaultJugador: string;
}
