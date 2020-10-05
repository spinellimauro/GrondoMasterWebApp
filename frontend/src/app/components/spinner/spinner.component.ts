import { Component, Injectable, OnInit } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable()
export class SpinnerService {
  private spinnerCache = new Set<SpinnerComponent>();

  constructor() {}

  _register(spinner: SpinnerComponent): void {
    this.spinnerCache.add(spinner);
  }

  show(): void {
    this.spinnerCache.forEach((spinner) => {
      spinner.show = true;
    });
  }

  hide(): void {
    this.spinnerCache.forEach((spinner) => {
      spinner.show = false;
    });
  }
}

@Component({
  selector: 'app-spinner',
  templateUrl: 'spinner.component.html',
})
export class SpinnerComponent implements OnInit {
  show = false;

  constructor(private spinnerService: SpinnerService) {}

  ngOnInit(): void {
    this.spinnerService._register(this);
  }
}

@Injectable()
export class SpinnerInterceptor implements HttpInterceptor {
  constructor(private spinner: SpinnerService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      tap(
        () => setTimeout(() => this.spinner.show(), 0),
        () => setTimeout(() => this.spinner.hide(), 0),
        () => setTimeout(() => this.spinner.hide(), 0)
      )
    );
  }
}
