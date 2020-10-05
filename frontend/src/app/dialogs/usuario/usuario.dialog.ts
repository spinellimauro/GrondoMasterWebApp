import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, Inject, OnInit } from '@angular/core';
import { UsuariosService } from 'src/app/components/usuarios/usuarioService';
import { ConfigService } from 'src/app/utils/config.service';
import { loadData, passwordValidator } from 'src/app/utils/helpers';

@Component({
  templateUrl: 'usuario.dialog.html',
})
export class UsuarioDialog implements OnInit {
  usuario: FormGroup;
  esUnAlta: boolean;

  public Validaciones: {
    Users: {
      Nombre: string;
      Apellido: string;
      Email: string;
      Password: string;
      PasswordMinLength: string;
      PasswordMaxLength: string;
      PasswordSpecific: string;
    };
    ValidacionContrasenaNueva: string[];
  };

  public Etiquetas: {
    Users: {
      Datos: string;
      Habilitacion: string;
    };
    Guardar: string;
    Seleccione: string;
  };

  constructor(
    private fb: FormBuilder,
    private usuariosService: UsuariosService,
    public configService: ConfigService,
    private dialogRef: MatDialogRef<UsuarioDialog>,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {
    this.usuario = this.fb.group({
      id: ['0'],
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      username: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(30),
        ],
      ],
    });

    if (data.usuario != null) {
      this.usuario.controls['username'].disable();
      this.usuario.controls['password'].clearValidators();
      this.usuario.controls['password'].updateValueAndValidity();
      loadData(this.usuario, data.usuario);
    }

    this.esUnAlta = data.usuario == null;
  }

  ngOnInit() {
    this.Etiquetas = {
      Users: {
        Datos: this.configService.etiquetas.Users.Datos,
        Habilitacion: this.configService.etiquetas.Users.Habilitacion,
      },
      Guardar: this.configService.etiquetas.Guardar,
      Seleccione: this.configService.etiquetas.Seleccione,
    };

    this.Validaciones = {
      Users: {
        Nombre: this.configService.validaciones.Users.Nombre,
        Apellido: this.configService.validaciones.Users.Apellido,
        Email: this.configService.validaciones.Users.Email,
        Password: this.configService.validaciones.Users.Password,
        PasswordMinLength: this.configService.validaciones.Users
          .PasswordMinLength,
        PasswordMaxLength: this.configService.validaciones.Users
          .PasswordMaxLength,
        PasswordSpecific: this.configService.validaciones.Users
          .PasswordSpecific,
      },
      ValidacionContrasenaNueva: this.configService.validaciones
        .ValidacionContrasenaNueva,
    };
  }

  guardar() {
    const model = this.usuario.getRawValue();
    if (this.esUnAlta) {
      this.usuariosService.createUsuario(model).subscribe((data) => {
        console.log(data);
        this.dialogRef.close(data);
      });
    } else {
      this.usuariosService
        .editUsuario(model.userId, model)
        .subscribe((data) => this.dialogRef.close(data));
    }
  }

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id.toString() : c1 === c2;
  }
}
