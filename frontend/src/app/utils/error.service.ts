import { Router } from '@angular/router';
import { ToastrService, ToastrConfig } from 'ngx-toastr';
import { Injectable, ErrorHandler } from '@angular/core';
import { NgZone, Injector } from '@angular/core';
import { isString } from 'util';


@Injectable()
export class AppErrorHandler implements ErrorHandler {

    private toastyService: ToastrService;
    private router: Router;

    constructor(private injector: Injector, private ngZone: NgZone) {
    }

    handleError(error: any) {

        this.ngZone.run(
            () => {

                console.log(error);

                this.toastyService = this.injector.get(ToastrService);
                this.router = this.injector.get(Router);

                let serverError;
                switch (error.status) {
                    case 0:
                        serverError = "Error de conexión al servidor. Revise su conexión a Internet y reintente o aguarde unos segundos.";
                        break;
                    case 401:
                        serverError = "Ingrese Usuario/Contraseña.";
                        this.router.navigateByUrl('login');
                        break;
                    case 403:
                        serverError = "No tiene permisos para realizar esta acción.";
                        this.router.navigateByUrl('/');
                        break;
                    case 404:
                        serverError = "No se encontró la URL de acceso a datos.";
                        break;
                    case 500:

                        serverError = error.headers ? error.headers.get('Application-Error') : "Error interno.";
                        break;
                    default:
                        serverError = error.statusText;
                        break;

                }

                const err = error;
                const apiError = isString(error.error) ? error.error : '';
                const bodyError = isString(error._body) ? error._body : '';

                this.toastyService.error(

                    apiError || bodyError || serverError || err,
                    "Atención"
                );

            }
        );

    }

}
