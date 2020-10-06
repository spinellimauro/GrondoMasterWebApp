import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoggedService } from './utils/logged.service';
import { ILogged } from './interfaces/ILogged';
import { MatMenuTrigger } from '@angular/material/menu';
import { MenuItem, PrimeNGConfig } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  public logged?: ILogged;
  items: MenuItem[];
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  constructor(
    private router: Router,
    private loggedService: LoggedService,
    private primengConfig: PrimeNGConfig
  ) {
    this.loggedService
      .getLogged()
      .subscribe((_logged: ILogged) => (this.logged = _logged));
  }

  ngOnInit() {
    this.primengConfig.ripple = true;

    if (this.loggedService.validToken()) {
      this.loggedService.setLogged();
    }

    if (!this.isLogged) this.router.navigateByUrl('login');

    console.log(this.logged);

    this.items = [
      {
        icon: 'fas fa-home'
      },
      {
          label: 'Equipo',
          icon: 'fas fa-tshirt',
          items: [{
                  label: 'Plantilla', 
                  icon: 'far fa-list-alt',
              },
              {
                label: 'Ofertas',
                icon: 'fas fa-comment-dollar'
              }
          ]
      },
      {
          label: 'Mercado',
          icon: 'fas fa-hand-holding-usd',
          items: [
              {
                label: 'Sofifa',
                icon: 'fas fa-search-dollar'
              },
              {
                label: 'Transferibles',
                icon: 'fas fa-coins'
              },
              {
                label: 'Traspasos',
                icon: 'fas fa-exchange-alt'
              },
              {
                label: 'Equipos',
                icon: 'fas fa-users'
              }
          ]
      },
      {
          label: 'Torneo',
          icon: 'far fa-calendar-alt',
          items: [
              {
                label: 'Fixture',
                icon: 'fas fa-calendar-day'
              },
              {
                label: 'Estadisticas',
                icon: 'fas fa-chart-bar'
              }
          ]
      }
  ];
  }

  isLogged() {
    return this.logged != null;
  }

  logOut() {
    this.loggedService.logOut();
    this.router.navigateByUrl('login');
  }

  toHome() {
    this.router.navigateByUrl('/');
  }
}
