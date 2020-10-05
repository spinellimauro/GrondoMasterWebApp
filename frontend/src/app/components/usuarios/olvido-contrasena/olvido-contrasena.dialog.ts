import { MatDialogRef } from '@angular/material/dialog';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ConfigService } from 'src/app/utils/config.service';
import { UsuariosService } from '../usuarioService';
import { ToastrService } from 'ngx-toastr';

@Component({
    templateUrl: 'olvido-contrasena.dialog.html',
})

export class OlvidoContrasenaDialog implements OnInit {

    userForm: FormGroup;

    constructor(
        private fb: FormBuilder,
        private usuariosService: UsuariosService,
        public config: ConfigService,
        private toastyService: ToastrService,
        public dialogRef: MatDialogRef<OlvidoContrasenaDialog>) { }

    ngOnInit() {

        this.userForm = this.fb.group({
            Email: ['', [Validators.required, Validators.email]]
        });

    }

    get email() {
        return this.userForm.get('Email');
    }

    aceptar() {
        this.usuariosService.forgotPassword(this.userForm.get('Email').value).subscribe(() => {
            this.toastyService.success(
                "Mail enviado",
                "Éxito",
            );
            this.dialogRef.close();
        });
    }

}
