import { Component, OnInit } from '@angular/core';
import { Jugador } from 'src/app/models/Jugador';
import { ConfigService } from 'src/app/utils/config.service';
import { JugadorService } from '../jugadorService';

@Component({
  selector: 'searchJugadores-root',
  templateUrl: './searchJugadores.component.html',
  styleUrls: ['./searchJugadores.component.scss'],
})
export class SearchJugadoresComponent implements OnInit {
  jugadores: Jugador[] = [];

  cols: any[] = [];

  constructor(
    private jugadorService: JugadorService,
    private configService: ConfigService
  ) {}

  ngOnInit() {
    this.cols = [
      { field: 'nombre', header: 'Nombre' },
      { field: 'nacionalidad', header: 'Nacionalidad' },
      { field: 'nivel', header: 'Nivel' },
      { field: 'potencial', header: 'Potencial' },
    ];

    this.jugadorService
      .getJugadoresBySearch('lionel')
      .subscribe((jugadores: Jugador[]) => (this.jugadores = jugadores));
  }
}
