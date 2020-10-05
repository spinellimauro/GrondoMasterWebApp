import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConfigService } from 'src/app/utils/config.service';
import { UsuariosService } from '../usuarioService';
import { LoggedService } from 'src/app/utils/logged.service';
import { passwordValidator } from 'src/app/utils/helpers';
import { MatDialog } from '@angular/material/dialog';
import { UsuarioDialog } from 'src/app/dialogs/usuario/usuario.dialog';
import { ToastrService } from 'ngx-toastr';
import { OlvidoContrasenaDialog } from '../olvido-contrasena/olvido-contrasena.dialog';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm: FormGroup;

  public Validaciones: {
    Login: {
      Usuario: string;
      Password: string;
    };
    ValidacionContrasenaNueva: string[];
  };

  public Etiquetas: {
    Login: {
      Titulo: string;
    };
    Register: string;
  };

  constructor(
    private fb: FormBuilder,
    private logged: LoggedService,
    private router: Router,
    public config: ConfigService,
    private usuarioService: UsuariosService,
    private dialog: MatDialog,
    private toastyService: ToastrService,
    private configService: ConfigService
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(30),
          //   passwordValidator,
        ],
      ],
    });
  }
  ngOnInit(): void {
    this.Etiquetas = {
      Login: {
        Titulo: this.configService.etiquetas.Login.Titulo,
      },
      Register: this.configService.etiquetas.Register,
    };

    this.Validaciones = {
      Login: {
        Usuario: this.configService.validaciones.Login.Usuario,
        Password: this.configService.validaciones.Login.Password,
      },
      ValidacionContrasenaNueva: this.configService.validaciones
        .ValidacionContrasenaNueva,
    };
  }

  get usuarioRed() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {
    this.usuarioService.login(this.loginForm.value).subscribe((data: any) => {
      const token = data.accessToken;
      this.logged.setToken(token);
      this.logged.setLogged();

      this.router.navigateByUrl('');
    });
  }

  registro() {
    this.dialog
      .open(UsuarioDialog, { width: '500px', height: 'auto', data: {} })
      .afterClosed()
      .subscribe((data) => {
        if (data != null) {
          this.toastyService.success(
            this.configService.mensajes.TituloSuccess,
            this.configService.mensajes.AUsuario
          );
        }
      });
  }

  forgotPassword() {
    this.dialog.open(OlvidoContrasenaDialog, {
      width: 'auto',
      height: 'auto',
    });
  }
}
