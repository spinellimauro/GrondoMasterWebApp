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

	@JsonIgnore
	def List<Jugador> getListaGoleadores() {
		listaJugadores.filter[getGoles(it) != 0].sortBy[getGoles(it)].reverse
	}

	@JsonIgnore
	def List<DT> getListaFairPlay() {
		listaParticipantes.sortBy[getPuntosFairPlay(it)]
	}

	def List<EstadisticaTorneo> getTablaPosiciones() {
		listaPosiciones.map[new EstadisticaTorneo(it, this)]
	}

	def List<EstadisticaFairPlay> getTablaFairPlay() {
		listaFairPlay.map[new EstadisticaFairPlay(it, this)]
	}

	def List<EstadisticaJugador> getTablaGoleadores() {
		listaGoleadores.map[new EstadisticaJugador(it, this)]
	}

	// Estadisticas - DT
	def int getAmarillas(DT dt) {
		dt.listaJugadores.fold(0)[acum, jugador|acum + getAmarillas(jugador)]
	}

	def int getRojas(DT dt) {
		dt.listaJugadores.fold(0)[acum, jugador|acum + getRojas(jugador)]
	}

	def int getPuntosFairPlay(DT dt) {
		getAmarillas(dt) * 4 + getRojas(dt) * 12;
	}

	def List<Partido> getPartidosJugados(DT dt) {
		listaPartidos.filter[it.terminado].filter[getJugoPartido(dt)].toList;
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
	def int getGoles(Jugador jugador) {
		val listaGoles = listaPartidos.map[golesLocal + golesVisitante].flatten.toList;
		Collections.frequency(listaGoles, jugador);
	}

	def int getAmarillas(Jugador jugador) {
		val listaRojas = listaPartidos.map[listaAmarillas].flatten.toList;
		Collections.frequency(listaRojas, jugador);
	}

	def int getRojas(Jugador jugador) {
		val listaRojas = listaPartidos.map[listaRojas].flatten.toList;
		Collections.frequency(listaRojas, jugador);
	}

	def boolean estaSuspendido(Jugador jugador, int fecha) {
		val fechaAnterior = getFecha(fecha - 1);

		fechaAnterior.exists[fueExpulsado(jugador)] ||
			( fechaAnterior.exists[fueAmonestado(jugador)] && (getAmarillas(jugador) % limiteAmarillas == 0));
	}

	public DT getPropietario(Jugador jugador) {
		listaParticipantes.findFirst[listaJugadores.contains(jugador)];
	}

	public void terminarTorneo() {
		if (terminado)
			throw new Exception("El torneo ya terminó");

		if (listaPartidos.exists[p|!p.terminado])
			throw new Exception("Hay partidos sin terminar");

		if (premios.cantPremios > listaParticipantes.size)
			throw new Exception("Faltan " + (premios.cantPremios - listaParticipantes.size) + " DT más");

		terminado = true;

		for (var int i = 0; i < premios.cantPremios; i++)
			listaPosiciones.get(i).incPlata(premios.getPremio(i + 1));
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
		g = torneo.getPartidosJugados(dt).filter[getPuntos(dt) == 3].size();
		e = torneo.getPartidosJugados(dt).filter[getPuntos(dt) == 1].size();
		p = torneo.getPartidosJugados(dt).filter[getPuntos(dt) == 0].size();
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

	