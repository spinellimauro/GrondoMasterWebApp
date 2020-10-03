import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from '../../utils/config.service';

@Injectable()
export class JugadorService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  getJugadoresBySearch(query: string) {
    return this.http.get(
      this.configService.JugadorURI + 'get-jugadores-by-search/' + query
    );
  }
}
