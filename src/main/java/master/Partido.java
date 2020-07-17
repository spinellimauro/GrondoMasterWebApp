package master;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
			
			public int getNumeroFecha() {
				return numeroFecha;
			}
			
			public boolean getJugoPartido(DT dt) {
				return dtLocal.equals(dt) || dtVisitante.equals(dt);
			}

			// Goles
			public String getScore() {
				return golesLocal.size() + " - " + golesVisitante.size();
			}

			public int getGoles(Jugador jugador) {
				List<Jugador> goles = Stream.concat(golesLocal.stream(), golesVisitante.stream())
                        .collect(Collectors.toList());
				return Collections.frequency(goles.stream().collect(Collectors.toList()), jugador);
			}

			public void addGol(Jugador jugador) {
				if(dtLocal.getListaJugadores().contains(jugador)) golesLocal.add(jugador); else golesVisitante.add(jugador);
			}

			public void removeGol(Jugador jugador) {
				if(dtLocal.getListaJugadores().contains(jugador)) golesLocal.remove(jugador); else golesVisitante.remove(jugador);
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
				return Collections.frequency(listaAmarillas, jugador);
			}

			public int getRojas(Jugador jugador) {
				return Collections.frequency(listaRojas, jugador);
			}

			// Estadisticas - DT
			public int getPuntos(DT dt) {
				if (dt.equals(dtLocal)) {
					if (golesLocal.size() > golesVisitante.size()) 
						return 3;
					else if(golesLocal.size() < golesVisitante.size()) return 0; else return 1;
				} else {
					if (golesLocal.size() < golesVisitante.size())
						return 3;
					else if(golesLocal.size() > golesVisitante.size()) return 0; else return 1;
				}
			}

			public int getGolesFavor(DT dt) {
				if(dt.equals(dtLocal)) return golesLocal.size(); else return golesVisitante.size();
			}

			public int getGolesContra(DT dt) {
				if(dt.equals(dtLocal)) return golesVisitante.size(); else return golesLocal.size();
			}

			// Listas
			public List<Jugador> getSuspendidos() {
				List<Jugador> listaJugadoresCompleta = Stream.concat(dtLocal.getListaJugadores().stream(), dtVisitante.getListaJugadores().stream())
                        .collect(Collectors.toList());
				return listaJugadoresCompleta.stream().filter(jugador -> torneo.estaSuspendido(jugador, numeroFecha)).collect(Collectors.toList());
			}
			
			public List<Jugador> getLesionados() {
				List<Jugador> listaJugadoresCompleta = Stream.concat(dtLocal.getListaJugadores().stream(), dtVisitante.getListaJugadores().stream())
                        .collect(Collectors.toList());
				return listaJugadoresCompleta.stream().filter(jugador -> jugador.estaLesionado()).collect(Collectors.toList());
			}

			// Terminar Partido
			public void terminarPartido() throws Exception {
				if (terminado) {
					throw new Exception("El partido ya terminó");
				}

				terminado = true;
				getLesionados().stream().forEach(jugador -> jugador.decLesion());
				dtLocal.incPlata(getPremio(dtLocal));
				dtVisitante.incPlata(getPremio(dtVisitante));
			}

			public double getPremio(DT dt) {
				if (getPuntos(dt) == 3) 
					return torneo.premios.getPremioEvento("Victoria") + torneo.premios.getPremioEvento("Gol") * ( getGolesFavor(dt) - getGolesContra(dt) );
				else if (getPuntos(dt) == 1)
					return torneo.premios.getPremioEvento("Empate");
				else
					return 0;
			}

			public boolean getTerminado() {
				return terminado;
			}

			public List<Jugador> getListaRojas() {
				return listaRojas;
			}

			public List<Jugador> getListaAmarillas() {
				return listaAmarillas;
			}

			public List<DT> getGolesVisitante() {
				return golesVisitante;
			}

			public List<DT> getGolesLocal() {
				return golesLocal;
			}

}
