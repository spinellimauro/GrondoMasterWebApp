package master;

import java.util.Arrays;
import java.util.List;

public class Partido {
			int numeroFecha = 0;
			DT dtLocal;
			DT dtVisitante;
			boolean terminado = false;
			Torneo torneo;
			List<Jugador> golesLocal = Arrays.asList();
			List<Jugador> golesVisitante = Arrays.asList();
			List<Jugador> listaAmarillas = Arrays.asList();
			List<Jugador> listaRojas = Arrays.asList();

			public boolean getJugoPartido(DT dt) {
				return dtLocal.equals(dt) || dtVisitante.equals(dt);
			}

			// Goles
			public String getScore() {
				return getGolesLocal().size() + " - " + getGolesVisitante().size();
			}

			public int getGoles(Jugador jugador) {
				Collections.frequency((golesLocal + golesVisitante).toList, jugador);
			}

			public void addGol(Jugador jugador) {
				if(dtLocal.getListaJugadores().contains(jugador)) golesLocal.add(jugador); else golesVisitante.add(jugador);
				ObservableUtils.firePropertyChanged(this, "score");
			}

			public void removeGol(Jugador jugador) {
				if(dtLocal.getListaJugadores().contains(jugador)) golesLocal.remove(jugador); else golesVisitante.remove(jugador);
				ObservableUtils.firePropertyChanged(this, "score");
			}

			// Amonestaciones
			public void addAmarilla(Jugador jugador) {
				if(getAmarillas(jugador) < 2 && getRojas(jugador) < 1) listaAmarillas.add(jugador);
			}

			public void removeAmarilla(Jugador jugador) {
				listaAmarillas.remove(jugador);
			}

			public void addRoja(Jugador jugador) {
				if(getRojas(jugador) < 1 && getAmarillas(jugador) < 2) listaRojas.add(jugador);
			}

			public void removeRoja(Jugador jugador) {
				listaRojas.remove(jugador);
			}

			public boolean fueAmonestado(Jugador jugador) {
				return (getAmarillas(jugador) == 1);
			}

			public boolean fueExpulsado(Jugador jugador) {
				return (getAmarillas(jugador) == 2 || getRojas(jugador) == 1);
			}

			// Estadisticas - Jugador
			public int getAmarillas(Jugador jugador) {
				Collections.frequency(listaAmarillas, jugador);
			}

			public int getRojas(Jugador jugador) {
				Collections.frequency(listaRojas, jugador);
			}

			// Estadisticas - DT
			public int getPuntos(DT dt) {
				if (dt.equals(dtLocal)) {
					if (golesLocal.size > golesVisitante.size)
						3
					else if(golesLocal.size < golesVisitante.size) 0 else 1
				} else {
					if (golesLocal.size < golesVisitante.size)
						3
					else if(golesLocal.size > golesVisitante.size) 0 else 1
				}
			}

			public getGolesFavor(DT dt) {
				if(dt.equals(dtLocal)) golesLocal.size else golesVisitante.size();
			}

			public getGolesContra(DT dt) {
				if(dt.equals(dtLocal)) golesVisitante.size else golesLocal.size();
			}

			// Listas
			public List<Jugador> getSuspendidos() {
				(dtLocal.listaJugadores + dtVisitante.listaJugadores).filter[torneo.estaSuspendido(it, numeroFecha)].toList();
			}
			
			public List<Jugador> getLesionados() {
				(dtLocal.listaJugadores + dtVisitante.listaJugadores).filter[estaLesionado].toList();
			}

			// Terminar Partido
			public void terminarPartido() {
				if (terminado)
					throw new Exception("El partido ya terminó");

				terminado = true;
				lesionados.forEach[decLesion];
				dtLocal.incPlata(getPremio(dtLocal));
				dtVisitante.incPlata(getPremio(dtVisitante));
			}

			public double getPremio(DT dt) {
				if (dt.puntos == 3)
					torneo.premios.getPremioEvento("Victoria") + torneo.premios.getPremioEvento("Gol") * ( getGolesFavor(dt) - getGolesContra(dt) );
				else if (dt.puntos == 1)
					torneo.premios.getPremioEvento("Empate");
				else
					0;
			}
}
