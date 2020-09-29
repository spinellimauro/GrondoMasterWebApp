import { LoggedService } from './logged.service';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Rol } from '../models/Rol';


@Injectable()
export class LoggedGuard implements CanActivate {

    private roles: Rol[] = [];

    constructor(
        private loggedService: LoggedService,
        private toastyService: ToastrService,
        private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        const currentUrl = state.url;

        if (this.loggedService.validToken()) {

            const urlCambiarContraseña = '/cambio-contrasena';

            const ExpiredPassword = this.loggedService.logged.ExpiredPassword;

            if (ExpiredPassword) {

                if (currentUrl !== urlCambiarContraseña) {
                    this.router.navigateByUrl(urlCambiarContraseña);

                    this.toastyService.error(
                        "La contraseña ha expirado",
                        "Atención"
                    );

                    return false;
                }
            }

            var tienePermisos = null;

            // if (this.roles.length == 0)
            this.roles = this.loggedService.roles;

            this.roles.forEach(rol => {
                var rolObtenido = rol.CanEnter(currentUrl);

                if (rolObtenido != null || rolObtenido != undefined)
                    tienePermisos = rolObtenido;
            })

            if (tienePermisos == null)
                this.router.navigateByUrl('/');

            return true;
        }
        else if (currentUrl == "/") {
            this.loggedService.logOut();
            this.router.navigateByUrl('login');
        }
        else {
            this.loggedService.logOut();

            this.toastyService.error(
                "Ha ocurrido un error",
                "Error"
            );

            this.router.navigateByUrl('login');
        }

    }
}