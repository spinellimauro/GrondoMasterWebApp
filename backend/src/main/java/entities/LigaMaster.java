package entities;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class LigaMaster {

	private static LigaMaster ligaMaster;

	private List<Torneo> listaTorneos = Arrays.asList(new Torneo("Nuevo Torneo"));
	private List<DT> listaDT = Arrays.asList();
	Mercado mercado = new Mercado();

	private LigaMaster() {
    }

	public static LigaMaster getInstance() {
		if (ligaMaster == null) {
			ligaMaster = new LigaMaster();
		} else {
			System.out.println(
					"No se puede crear el objeto porque ya existe un objeto de la clase LigaMaster");
		}

		return ligaMaster;
	}

	public DT getMaster() {
		return new DT("Master","ARG123");
	}

	public List<Jugador> getListaJugador() {
		return listaDT.stream().flatMap(dt -> dt.getListaJugadores().stream()).collect(Collectors.toList());
	}

	public Set<DT> getDTsQuePagan() {
		return listaDT.stream().filter(dt -> dt.getTorneosDisponibles() == 0).collect(Collectors.toSet());
	}

	public Set<Jugador> getListaTransferibles() {
		List<Jugador> jugadores = listaDT.stream().flatMap(dt -> getListaJugador().stream()).collect(Collectors.toList());
		return jugadores.stream().filter(jugador -> jugador.getPrecioVenta() > 0).collect(Collectors.toSet());
//		.filter[precioVenta > 0].toSet
	}

//	private void leerBase() {
//		try
//			instance = new XStream().fromXML(new FileReader("data.xml")) as LigaMaster
//		catch (Exception e) { }
//	}

//	void guardarBase() {
//		val printer = new PrintWriter("data.xml")
//		new XStream().toXML(instance, printer)
//		printer.close
//	}

	void addDT(DT dt) throws Exception {
		if (listaDT.stream().anyMatch(dtList -> dtList.getNombreDT().equals(dt.getNombreDT())))
			throw new Exception("Ese nombre de DT ya est� en uso");

		if (listaDT.stream().anyMatch(dtList -> dtList.getNombreDT().equals(dt.getNombreDT())))
			throw new Exception("Ese nombre de Equipo ya est� en uso");

		listaDT.add(dt);
	}

	void addTorneo(Torneo torneo) {
		listaTorneos.add(torneo);
	}

	void removeTorneo(Torneo torneo) {
		listaTorneos.remove(torneo);
	}

	

	public DT getPropietario(Jugador jugador) {
		DT libre = new DT("Libre");
		DT propietario = listaDT.stream().filter(dt -> dt.getListaJugadores().contains(jugador)).findFirst().get();
		
		if(listaDT.stream().filter(dt -> dt.getListaJugadores().contains(jugador)).findFirst() == null) {
			return libre;
		}else {
			return propietario;
		}
	}

//	void update() {
//		listaJugador.forEach[update]
//	}

	// C�lculo del Historial //TODO: Ver bien despues.
	public List<Partido> getPartidosJugados(DT dt, DT otroDT) {
		List<Partido> partidos = listaTorneos.stream().flatMap(torneo -> torneo.getListaPartidos().stream()).collect(Collectors.toList());
		return partidos.stream().filter(partido -> partido.getJugoPartido(dt) && partido.getJugoPartido(otroDT) && partido.getTerminado()).collect(Collectors.toList());
	}

	public int getPartidosGanados(DT dt, DT otroDT) {
		return getPartidosJugados(dt, otroDT).stream().filter(partido -> partido.getPuntos(dt) == 3).collect(Collectors.toList()).size();
	}

	public int getPartidosEmpatados(DT dt, DT otroDT) {
		return getPartidosJugados(dt, otroDT).stream().filter(partido -> partido.getPuntos(dt) == 1).collect(Collectors.toList()).size();
	}

	public int getPartidosPerdidos(DT dt, DT otroDT) {
		return getPartidosJugados(dt, otroDT).stream().filter(partido -> partido.getPuntos(dt) == 0).collect(Collectors.toList()).size();
	}
}