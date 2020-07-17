package master;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Torneo {
	String nombreTorneo = "";
	List<DT> listaParticipantes = Arrays.asList();
	List<Partido> listaPartidos = Arrays.asList();
	PremiosTorneos premios = new PremiosTorneos();
	int limiteAmarillas = 3;
	boolean terminado = false;

	
	public Torneo(String _nombreTorneo) {
		nombreTorneo = _nombreTorneo;
	}
	

	public void sortearFechas() {
		listaPartidos.clear();
		Collections.shuffle(listaParticipantes);
		int visitante;
		DT libre = new DT("Libre");
		if(listaParticipantes.size() % 2 != 0) listaParticipantes.add(libre);

		for (int fecha = 0; fecha < getNumeroFechas(); fecha++) {

			for (int partido = 0; partido < (listaParticipantes.size()) / 2; partido++) {

				int local = (fecha + partido) % getNumeroFechas();
				 if(partido == 0) {
					visitante = getNumeroFechas();
				 }
				 else {
					visitante = (getNumeroFechas() - partido + fecha) % getNumeroFechas();
				 }

				Partido partidoNuevo = new Partido();
				partidoNuevo.numeroFecha = fecha + 1;
				partidoNuevo.dtLocal = listaParticipantes.get(local);
				partidoNuevo.dtVisitante = listaParticipantes.get(visitante);

				if(!partidoNuevo.getJugoPartido(libre)) addPartido(partidoNuevo);
			}
		}
		

		listaParticipantes.remove(libre);
		
	}

	public void addPartido(Partido partido) {
		partido.torneo = this;
		listaPartidos.add(partido);
	}

	private int getNumeroFechas() {
		int nroDts = listaParticipantes.size();
		if(nroDts % 2 == 0) {
			return nroDts - 1;
		}
		else return nroDts;
	}

	public List<Integer> getListaFechas() {
		(1 .. numeroFechas).toList;
	}

	public List<Partido> getFecha(int entero) {
		return listaPartidos.stream().filter(partido -> partido.getNumeroFecha() == entero).collect(Collectors.toList());
	}
	
	public List<Jugador> getListaJugadores() {
		return listaParticipantes.stream().flatMap(dt -> dt.getListaJugadores().stream()).collect(Collectors.toList());
	}

	// Estadísticas
	public List<DT> getListaPosiciones() {
		Collections.sort(listaParticipantes, (DT dt, DT dt2) -> getPuntos(dt)-getPuntos(dt2));
		return listaParticipantes;
	}

	public List<Jugador> getListaGoleadores() {
		List<Jugador> goleadores = getListaJugadores().stream().filter(jugador -> getGoles(jugador) != 0).collect(Collectors.toList());
		Collections.sort(goleadores, (Jugador jugador, Jugador jugador2) -> getGoles(jugador)-getGoles(jugador2));
		Collections.reverse(goleadores);
		return goleadores;
	}

	public List<DT> getListaFairPlay() {
		Collections.sort(listaParticipantes, (DT dt, DT dt2) -> getPuntosFairPlay(dt)-getPuntosFairPlay(dt2));
		return listaParticipantes;
	}

	public List<EstadisticaTorneo> getTablaPosiciones() {
		return getListaPosiciones().stream().map(dt -> new EstadisticaTorneo(dt, this)).collect(Collectors.toList());
	}

	public List<EstadisticaFairPlay> getTablaFairPlay() {
		return getListaFairPlay().stream().map(dt -> new EstadisticaFairPlay(dt, this)).collect(Collectors.toList());
	}

	public List<EstadisticaJugador> getTablaGoleadores() {
		return getListaGoleadores().stream().map(jugador -> new EstadisticaJugador(jugador, this)).collect(Collectors.toList());
	}

	// Estadisticas - DT
	public int getAmarillas(DT dt) {
		return dt.getListaJugadores().stream().reduce(0, (acum, jugador) -> acum + getAmarillas(jugador), Integer::sum);
	}

	public int getRojas(DT dt) {
		return dt.getListaJugadores().stream().reduce(0, (acum, jugador) -> acum + getRojas(jugador), Integer::sum);
	}

	public int getPuntosFairPlay(DT dt) {
		return getAmarillas(dt) * 4 + getRojas(dt) * 12;
	}

	public List<Partido> getPartidosJugados(DT dt) {
		List<Partido> partidosTerminados = listaPartidos.stream().filter(partido -> partido.getTerminado()).collect(Collectors.toList());
		return partidosTerminados.stream().filter(partido -> partido.getJugoPartido(dt)).collect(Collectors.toList());
	}

	public int getGolesFavor(DT dt) {
		return getPartidosJugados(dt).stream().reduce(0, (acum, partido) -> acum + partido.getGolesFavor(dt), Integer::sum);
//		users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge());

//		fold(0)[acum, partido|acum + partido.getGolesFavor(dt)];
	}

	public int getGolesContra(DT dt) {
		return getPartidosJugados(dt).stream().reduce(0, (acum, partido) -> acum + partido.getGolesContra(dt), Integer::sum);
	}

	public int getPuntos(DT dt) {
		return getPartidosJugados(dt).stream().reduce(0, (acum, partido) -> acum + partido.getPuntos(dt), Integer::sum);
	}

	// Estadisticas - Jugador
	public int getGoles(Jugador jugador) { //Agregar adentro en el + el merge de ambas listas
		List<Jugador> listaGoles = listaPartidos.stream().flatMap(partido -> (Stream.concat(partido.getGolesVisitante().stream(),partido.getGolesLocal().stream()))).collect(Collectors.toList());
		return Collections.frequency(listaGoles, jugador);
	}

	public int getAmarillas(Jugador jugador) {
		List<Jugador> listaRojas = listaPartidos.stream().flatMap(partido -> partido.getListaAmarillas().stream()).collect(Collectors.toList());
		return Collections.frequency(listaRojas, jugador);
	}

	public int getRojas(Jugador jugador) {
		List<Jugador> listaRojas = listaPartidos.stream().flatMap(partido -> partido.getListaRojas().stream()).collect(Collectors.toList());
		return Collections.frequency(listaRojas, jugador);
	}

	public boolean estaSuspendido(Jugador jugador, int fecha) {
		List<Partido> fechaAnterior = getFecha(fecha - 1);

		return fechaAnterior.stream().anyMatch(partido -> partido.fueExpulsado(jugador)) ||
			( fechaAnterior.stream().anyMatch(partido -> partido.fueAmonestado(jugador)) && (getAmarillas(jugador) % limiteAmarillas == 0));
	}

	public DT getPropietario(Jugador jugador) {
		return listaParticipantes.stream().filter(dt -> dt.getListaJugadores().contains(jugador)).findFirst().get();
	}

	public void terminarTorneo() throws Exception {
		if (terminado)
			throw new Exception("El torneo ya terminó");

		if (listaPartidos.stream().anyMatch(partido -> partido.getTerminado()));
			throw new Exception("Hay partidos sin terminar");

		if (premios.getCantPremios() > listaParticipantes.size())
			throw new Exception("Faltan " + (premios.getCantPremios() - listaParticipantes.size()) + " DT más");

		terminado = true;

		for (int i = 0; i < premios.getCantPremios(); i++)
			getListaPosiciones().get(i).incPlata(premios.getPremio(i + 1));
	}


	public List<Partido> getListaPartidos() {
		return listaPartidos;
	}
}

class EstadisticaTorneo {
	String nombre;
	String equipo;
	int pj;
	int g;
	int e;
	int p;
	String goles;
	int pts;

	public EstadisticaTorneo(DT dt, Torneo torneo) {
		nombre = dt.getNombreDT();
		equipo = dt.getNombreEquipo();
		pj = torneo.getPartidosJugados(dt).size();
		g = torneo.getPartidosJugados(dt).stream().filter(partido -> partido.getPuntos(dt) == 3).collect(Collectors.toList()).size();
		e = torneo.getPartidosJugados(dt).stream().filter(partido -> partido.getPuntos(dt) == 1).collect(Collectors.toList()).size();
		p = torneo.getPartidosJugados(dt).stream().filter(partido -> partido.getPuntos(dt) == 0).collect(Collectors.toList()).size();
		goles = torneo.getGolesFavor(dt) + ":" + torneo.getGolesContra(dt);
		pts = torneo.getPuntos(dt);
	}
}

class EstadisticaJugador {
	int id;
	String nombre;
	int goles;

	public EstadisticaJugador(Jugador jugador, Torneo torneo) {
		id = jugador.getId();
		nombre = jugador.getNombre();
		goles = torneo.getGoles(jugador);
	}
}

class EstadisticaFairPlay {
	String nombre;
	String equipo;
	int amarillas;
	int rojas;
	int puntos;

	public EstadisticaFairPlay(DT dt, Torneo torneo) {
		nombre = dt.getNombreDT();
		equipo = dt.getNombreEquipo();
		amarillas = torneo.getAmarillas(dt);
		rojas = torneo.getRojas(dt);
		puntos = torneo.getPuntosFairPlay(dt);
	}
}

	