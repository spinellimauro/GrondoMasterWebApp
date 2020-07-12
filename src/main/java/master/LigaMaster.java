package master;

public final class LigaMaster {

	private static LigaMaster ligaMaster;

	private List<Torneo> listaTorneos = newArrayList(new Torneo => [nombreTorneo = "Nuevo Torneo"]);
	private List<DT> listaDT = newArrayList;
	Mercado mercado=new Mercado;

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
		new DT => [
			nombreDT = "Master";
			password = "ARG123";
		]
	}

	private List<Jugador> getListaJugador() {
		return listaDT.map[listaJugadores].toSet;
	}

	private List<DT> getDTsQuePagan() {
		listaDT.filter[torneosDisponibles == 0].toSet
	}

	private List<Jugador> getListaTransferibles() {
		listaDT.map[listaJugador].flatten.filter[precioVenta > 0].toSet
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
		if (listaDT.exists[nombreDT.equals(dt.nombreDT)])
			throw new Exception("Ese nombre de DT ya está en uso")

		if (listaDT.exists[nombreDT.equals(dt.nombreDT)])
			throw new Exception("Ese nombre de Equipo ya está en uso")

		listaDT.add(dt)
	}

	void addTorneo(Torneo torneo) {
		listaTorneos.add(torneo)
	}

	void removeTorneo(Torneo torneo) {
		listaTorneos.remove(torneo)
	}

	

	public DT getPropietario(Jugador jugador) {
		DT libre = new DT => [nombreDT = "Libre"]

		listaDT.findFirst[listaJugadores.contains(jugador)] ?: libre
	}

	void update() {
		listaJugador.forEach[update]
	}

	// Cálculo del Historial
	def List<Partido> getPartidosJugados(DT dt, DT otroDT) {
		listaTorneos.map[listaPartidos].flatten.filter[getJugoPartido(dt) && getJugoPartido(otroDT) && terminado].toList
	}

	int getPartidosGanados(DT dt, DT otroDT) {
		getPartidosJugados(dt, otroDT).filter[getPuntos(dt) == 3].size
	}

	int getPartidosEmpatados(DT dt, DT otroDT) {
		getPartidosJugados(dt, otroDT).filter[getPuntos(dt) == 1].size
	}

	int getPartidosPerdidos(DT dt, DT otroDT) {
		getPartidosJugados(dt, otroDT).filter[getPuntos(dt) == 0].size
	}
}