package master;

import java.util.Arrays;
import java.util.List;
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

	private DT getMaster() {
		new DT("Master","ARG123");
	}

	private List<Jugador> getListaJugador() {
		listaDT.stream().map(dt -> dt.getListaJugadores()).collect(Collectors.toSet());
	}

	private List<DT> getDTsQuePagan() {
		listaDT.stream().filter(dt -> dt.getTorneosDisponibles() == 0).collect(Collectors.toSet());
	}

	private List<Jugador> getListaTransferibles() {
//		listaDT.stream().map(getListaJugador()).flatten.filter[precioVenta > 0].toSet    //TODO: VER COMO HACER ESTA PARTE (NUEVA FORMA)
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

	void addDT(DT dt) {
		if (listaDT.stream().anyMatch(dtList -> dtList.getNombreDT().equals(dt.getNombreDT())))
			throw new Exception("Ese nombre de DT ya está en uso");

		if (listaDT.stream().anyMatch(dtList -> dtList.getNombreDT().equals(dt.getNombreDT())))
			throw new Exception("Ese nombre de Equipo ya está en uso");

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
		DT propietario = listaDT.stream().filter(dt -> dt.getListaJugadores().contains(jugador)).findFirst();
		
		if(listaDT.stream().filter(dt -> dt.getListaJugadores().contains(jugador)).findFirst() == null) {
			return libre;
		}else {
			return propietario;
		};
	}

//	void update() {
//		listaJugador.forEach[update]
//	}

	// Cálculo del Historial //TODO: Ver bien despues.
	public List<Partido> getPartidosJugados(DT dt, DT otroDT) {
//		listaTorneos.map[listaPartidos].flatten.filter[getJugoPartido(dt) && getJugoPartido(otroDT) && terminado].toList
	}

	public int getPartidosGanados(DT dt, DT otroDT) {
		getPartidosJugados(dt, otroDT).stream().filter(getPuntos(dt) == 3).size();
	}

	public int getPartidosEmpatados(DT dt, DT otroDT) {
		getPartidosJugados(dt, otroDT).filter[getPuntos(dt) == 1].size;
	}

	public int getPartidosPerdidos(DT dt, DT otroDT) {
		getPartidosJugados(dt, otroDT).filter[getPuntos(dt) == 0].size;
	}
}