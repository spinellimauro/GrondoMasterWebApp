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
  busqueda = '';
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
      { field: 'edad', header: 'Edad' },
      { field: 'posiciones', header: 'Posiciones' },
      { field: 'nivel', header: 'Nivel' },
      { field: 'potencial', header: 'Potencial' }
    ];

    
  }

  changeSource($event) {
    $event.target.src = '../../../../assets/images/notfound_0_120.png';
  }

  buscar() {
    this.jugadorService
      .getJugadoresBySearch(this.busqueda)
      .subscribe((jugadores: Jugador[]) => {
        jugadores.forEach(jugador => {
          var nacionalidadLowerCase = jugador.nacionalidad.toLowerCase();
          jugador.nacionalidad = nacionalidadLowerCase;
          this.jugadores = jugadores;
        });
       
      });
  }
}
