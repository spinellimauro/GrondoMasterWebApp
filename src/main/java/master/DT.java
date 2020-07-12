package master;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DT {
	
	private int id;
	private String nombreEquipo;
	private List<Jugador> newArrayList;
	private List<Jugador> listaJugadores = newArrayList;
	private String nombreDT;
	private String password;
	private double plata = 0.0;
	private int torneosDisponibles = 3;
	private int slots = 30;

	public DT(String _nombreDT, String _password){
        this.nombreDT = _nombreDT;
        this.password = _password;
    }
	
	public DT(String _nombreDT){
        this.nombreDT = _nombreDT;
    }
	
	public void venderJugador(Jugador jugador, double precio) throws Exception {
		if (!listaJugadores.contains(jugador))
			throw new Exception("Ese jugador no es tuyo");

		incPlata(precio);
		removeJugador(jugador);
	}

	public void comprarJugador(Jugador jugador, double precio) throws Exception {
		if (plata < precio)
			throw new Exception("No tenés suficiente plata");

		if (getCantJugadores() == slots)
			throw new Exception("No hay slots disponibles");

		decPlata(precio);
		addJugador(jugador);
		jugador.setPrecioVenta(0);
	}

	private int getCantJugadores() {
		return listaJugadores.size();
	}

	private void addJugador(Jugador jugador) {
		listaJugadores.add(jugador);
	}

	private void removeJugador(Jugador jugador) {
		listaJugadores.remove(jugador);
	}

	public void comprarSlot() throws Exception {
		double precioSlot = Precios.getInstance().getPrecio("Slot");

		if (plata < precioSlot)
			throw new Exception("No tenés suficiente plata");

		slots++;
		decPlata(precioSlot);
	}

	private void incPlata(double monto) {
		plata += monto;
	}

	private void decPlata(double monto) {
		plata -= monto;
	}
	
	public void prestarPlata(DT otro, double monto ) throws Exception{
		if(plata >= monto){
			otro.plata += monto;
			plata -= monto;
		}
		else{
		throw new Exception("No tenés suficiente plata");
		}
	}
	
	// Impuestos
	public void incTorneos() {
		torneosDisponibles++;
	}

	public void decTorneos() {
		torneosDisponibles--;
	}

	public boolean getPagaImpuesto() {
		return torneosDisponibles == 0;
	}

	private List<Jugador> getJugadoresConImpuesto() {
//		return listaJugadores.filter[pagaImpuesto].toList;
		listaJugadores.stream().filter(jugador->jugador.getPagaImpuesto());
		return listaJugadores;
	}

	public void pagarImpuesto(List<Jugador> jugadoresAPagar) {
		
		for (Jugador jugador : jugadoresAPagar) {
			pagarImpuesto(jugador);
		}
//		jugadoresAPagar.forEach[pagarImpuesto]

		List<Jugador> jugadoresNoPagados = Arrays.asList(); 
		jugadoresNoPagados.addAll(getJugadoresConImpuesto());
		jugadoresNoPagados.removeAll(jugadoresAPagar);
		for (Jugador jugador : jugadoresNoPagados) {
			jugador.noSePago();
		}
//		jugadoresNoPagados.forEach[noSePago];

		if(torneosDisponibles != 0) { 
			decTorneos();
		}else torneosDisponibles = 3;
		if (jugadoresNoPagados.stream().filter(jugador -> jugador.getVecesNoPagadas() != 3).collect(Collectors.toList()).size() > 0) {
		} else {
//			var borrados = jugadoresNoPagados.filter[vecesNoPagadas == 3].toList;
			List<Jugador> borrados = jugadoresNoPagados.stream().filter(jugador->jugador.getVecesNoPagadas() == 3).collect(Collectors.toList());
			listaJugadores.removeAll(borrados);
			List<String> borradosNombres = borrados.stream().map(jugador -> jugador.getNombre()).collect(Collectors.toList());
//			LigaMaster.instance.guardarBase;
//			throw new UserException("Se han borrado los siguientes jugadores" + borradosNombres)
		}
	}

	private void pagarImpuesto(Jugador jugador) {
		decPlata(jugador.getImpuesto());
		jugador.pagar();
	}

	public List<Jugador> getListaJugadoresDeshabilitados() {
		listaJugadores.stream().filter(jugador->!jugador.getHabilitado());
		return listaJugadores;
	}
	public String getNombreDT() {
		return nombreDT;
	}
	
	public List<Jugador> getListaJugadores() {
		return listaJugadores;
	}
	public int getTorneosDisponibles() {
		return torneosDisponibles;
	}

	public String getNombreEquipo() {
		// TODO Auto-generated method stub
		return nombreEquipo;
	}
	
}
