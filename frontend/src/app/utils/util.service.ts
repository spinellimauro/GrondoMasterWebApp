import { Injectable } from '@angular/core';
import { Jugador } from '../models/Jugador';
import { ConfigService } from './config.service';

@Injectable()
export class UtilService {
  constructor(private configService: ConfigService) {}

  checkImagenUrlJugador(jugador: Jugador) {
    if (jugador.urlImage.includes('notfound'))
      jugador.urlImage = this.configService.configuraciones.ImagenDefaultJugador;

    return jugador;
  }
}
