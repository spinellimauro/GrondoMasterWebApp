import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoggedService } from 'src/app/utils/logged.service';
import { ToastrService } from 'ngx-toastr';
import { ConfigService } from 'src/app/utils/config.service';
import { UsuariosService } from '../usuarioService';
import { passwordValidator, compareValidator } from 'src/app/utils/helpers';
import { IChangePassword } from 'src/app/interfaces/IChangePassword';

@Component({
  templateUrl: './cambiarcontrasena.component.html',
})
export class CambiarContrasenaComponent implements OnInit {
  passwordForm: FormGroup;
  hide1 = true;
  hide2 = true;
  hide3 = true;
  config: ConfigService;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private toastyService: ToastrService,
    public configs: ConfigService,
    private loggedService: LoggedService,
    private usuarioService: UsuariosService
  ) {
    this.config = configs;
  }

  ngOnInit() {
    this.passwordForm = this.fb.group(
      {
        currentPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(25),
            // passwordValidator
          ],
        ],
        newPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(25),
            // passwordValidator
          ],
        ],
        newPasswordConfirmed: [
          '',
          [
            Validators.required,
            Validators.minLength(8),
            Validators.maxLength(25),
            // passwordValidator
          ],
        ],
      },
      {
        validator: compareValidator('newPassword', 'newPasswordConfirmed'),
      }
    );
  }

  get currentPassword() {
    return this.passwordForm.controls.currentPassword;
  }

  get newPassword() {
    return this.passwordForm.controls.newPassword;
  }

  get newPasswordConfirmed() {
    return this.passwordForm.controls.newPasswordConfirmed;
  }
  CambiarContrasenaComponent;

  onSubmit() {
    const form: IChangePassword = this.passwordForm.value;
    form.Id = this.loggedService.logged.Id;

    this.usuarioService.changePassword(form).subscribe(() => {
      this.toastyService.success('Cambio de contraseña exitosa', 'Atención');

      this.loggedService.logOut();
      this.router.navigateByUrl('login');
    });
  }
}
