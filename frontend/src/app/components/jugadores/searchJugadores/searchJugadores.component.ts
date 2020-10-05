import { Component, OnInit } from '@angular/core';
import { Jugador } from 'src/app/models/Jugador';
import { ConfigService } from 'src/app/utils/config.service';
import { UtilService } from 'src/app/utils/util.service';
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
    private configService: ConfigService,
    private utilService: UtilService
  ) {}

  ngOnInit() {
    this.cols = [
      { field: 'nombre', header: 'Nombre' },
      { field: 'nacionalidadCorta', header: 'Nacionalidad' },
      { field: 'nivel', header: 'Nivel' },
      { field: 'potencial', header: 'Potencial' },
    ];

    this.jugadorService
      .getJugadoresBySearch('Vinicius')
      .subscribe((jugadores: Jugador[]) => {
        this.jugadores = jugadores;
      });
  }

  changeSource($event) {
    $event.target.src = '../../../../assets/images/notfound_0_120.png';
  }
}
