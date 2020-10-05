import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoggedService } from './utils/logged.service';
import { ILogged } from './interfaces/ILogged';
import { MatMenuTrigger } from '@angular/material/menu';
import { PrimeNGConfig } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  public logged?: ILogged;
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
