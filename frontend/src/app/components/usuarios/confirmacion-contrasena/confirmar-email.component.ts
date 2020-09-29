import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { UsuariosService } from '../usuarioService';

@Component({
    templateUrl: 'confirmar-email.component.html'
})

export class ConfirmarEmailComponent implements OnInit, OnDestroy {

    private subscription: Subscription;

    constructor(
        private router: Router,
        private activeRoute: ActivatedRoute,
        private usuariosService: UsuariosService,
        private toastyService: ToastrService) { }

    ngOnInit() {

        this.subscription = this.activeRoute.params
            .subscribe(params => {

                this.usuariosService
                    .confirmEmail({ Id: params['id'], Code: params['code'] })
                    .subscribe(
                        data => {

                            this.toastyService.success(
                                "Éxito",
                                "Se ha confirmado su cuenta"
                            );

                            this.router.navigateByUrl('login');
                        });
            });

    }

    ngOnDestroy() {

        this.subscription.unsubscribe();

    }

}