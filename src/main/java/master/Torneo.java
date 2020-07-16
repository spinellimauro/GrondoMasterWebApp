package master;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

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
		listaPartidos.stream().filter(partido -> partido.getNumeroFecha() == entero).collect(Collectors.toList());
	}
	
	public List<Jugador> getListaJugadores() {
		return listaParticipantes.stream().flatMap(dt -> dt.getListaJugadores()).collect(Collectors.toList());
	}

	// Estadísticas
	public List<DT> getListaPosiciones() {
		listaParticipantes.sortBy(getPuntos(it));
	}

	public List<Jugador> getListaGoleadores() {
		getListaJugadores().stream().filter(jugador -> getGoles(jugador) != 0).collect(Collectors.toList()).sortBy[getGoles(it)].reverse
	}

	public List<DT> getListaFairPlay() {
		listaParticipantes.sortBy[getPuntosFairPlay(it)]
	}

	public List<EstadisticaTorneo> getTablaPosiciones() {
		listaPosiciones.map[new EstadisticaTorneo(it, this)]
	}

	public List<EstadisticaFairPlay> getTablaFairPlay() {
		listaFairPlay.map[new EstadisticaFairPlay(it, this)]
	}

	public List<EstadisticaJugador> getTablaGoleadores() {
		return getListaGoleadores().stream().map(jugador -> new EstadisticaJugador(jugador, this));
	}

	// Estadisticas - DT
	public int getAmarillas(DT dt) {
		return dt.listaJugadores.fold(0)[acum, jugador|acum + getAmarillas(jugador)];
	}

	public int getRojas(DT dt) {
		return dt.listaJugadores.fold(0)[acum, jugador|acum + getRojas(jugador)];
	}

	public int getPuntosFairPlay(DT dt) {
		return getAmarillas(dt) * 4 + getRojas(dt) * 12;
	}

	public List<Partido> getPartidosJugados(DT dt) {
		List<Partido> partidosTerminados = listaPartidos.stream().filter(partido -> partido.getTerminado()).collect(Collectors.toList());
		partidosTerminados.stream().filter(partido -> partido.getJugoPartido(dt)).collect(Collectors.toList());
	}

	def int getGolesFavor(DT dt) {
		getPartidosJugados(dt).fold(0)[acum, partido|acum + partido.getGolesFavor(dt)];
	}

	def int getGolesContra(DT dt) {
		getPartidosJugados(dt).fold(0)[acum, partido|acum + partido.getGolesContra(dt)];
	}

	def int getPuntos(DT dt) {
		getPartidosJugados(dt).fold(0)[acum, partido|acum + partido.getPuntos(dt)];
	}

	// Estadisticas - Jugador
	public int getGoles(Jugador jugador) {
		val listaGoles = listaPartidos.map[golesLocal + golesVisitante].flatten.toList;
		Collections.frequency(listaGoles, jugador);
	}

	public int getAmarillas(Jugador jugador) {
		val listaRojas = listaPartidos.map[listaAmarillas].flatten.toList;
		Collections.frequency(listaRojas, jugador);
	}

	public int getRojas(Jugador jugador) {
		val listaRojas = listaPartidos.map[listaRojas].flatten.toList;
		Collections.frequency(listaRojas, jugador);
	}

	public boolean estaSuspendido(Jugador jugador, int fecha) {
		Partido fechaAnterior = getFecha(fecha - 1);

		fechaAnterior.stream().anyMatch(partido -> partido.fueExpulsado(jugador)) ||
			( fechaAnterior.stream().anyMatch(partido -> partido.fueAmonestado(jugador)) && (getAmarillas(jugador) % limiteAmarillas == 0));
	}

	public DT getPropietario(Jugador jugador) {
		listaParticipantes.stream().filter(dt -> dt.getListaJugadores().contains(jugador)).findFirst();
	}

	public void terminarTorneo() {
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

	