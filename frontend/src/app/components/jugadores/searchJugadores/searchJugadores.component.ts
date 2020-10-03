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
      { field: 'nacionalidadCorta', header: 'Nacionalidad' },
      { field: 'nivel', header: 'Nivel' },
      { field: 'potencial', header: 'Potencial' },
    ];

    this.jugadorService
      .getJugadoresBySearch('Vinicius')
      .subscribe((jugadores: Jugador[]) => {
        jugadores.forEach(jugador => {
          var idNormalizado = jugador.id.toString().length < 6 ? jugador.id.toString().padStart(1,"0") : jugador.id.toString()
          var idImagen = idNormalizado.substring(0,3) + "/" + idNormalizado.substring(3,6)
          jugador.idImagen = idImagen
        }) 
        this.jugadores = jugadores
      });
  }
  
}
