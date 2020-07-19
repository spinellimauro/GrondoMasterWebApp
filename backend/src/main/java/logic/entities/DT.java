package logic.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dt")
public class DT {

	public DT(String _nombreDT, String _password){
		this.nombre = _nombreDT;
		this.password = _password;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_equipo")
	Equipo equipo;

	private String nombre;

	private String password;

	@Column(name = "dinero")
	private double plata = 0.0;

	@Column(name = "torneos_disponibles")
	private int torneosDisponibles = 3;

	private int slots = 30;


	//	public void venderJugador(Jugador jugador, double precio) throws Exception {
//		if (!listaJugadores.contains(jugador))
//			throw new Exception("Ese jugador no es tuyo");
//
//		incPlata(precio);
//		removeJugador(jugador);
//	}
//
//	public void comprarJugador(Jugador jugador, double precio) throws Exception {
//		if (plata < precio)
//			throw new Exception("No ten�s suficiente plata");
//
//		if (getCantJugadores() == slots)
//			throw new Exception("No hay slots disponibles");
//
//		decPlata(precio);
//		addJugador(jugador);
//		jugador.setPrecioVenta(0);
//	}
//
//	private int getCantJugadores() {
//		return listaJugadores.size();
//	}
//
//	private void addJugador(Jugador jugador) {
//		listaJugadores.add(jugador);
//	}
//
//	private void removeJugador(Jugador jugador) {
//		listaJugadores.remove(jugador);
//	}
//
//	public void comprarSlot() throws Exception {
//		double precioSlot = Precios.getInstance().getPrecio("Slot");
//
//		if (plata < precioSlot)
//			throw new Exception("No ten�s suficiente plata");
//
//		slots++;
//		decPlata(precioSlot);
//	}
//
//	public void incPlata(double monto) {
//		plata += monto;
//	}
//
//	public void decPlata(double monto) {
//		plata -= monto;
//	}
//
//	public void prestarPlata(DT otro, double monto ) throws Exception{
//		if(plata >= monto){
//			otro.plata += monto;
//			plata -= monto;
//		}
//		else{
//			throw new Exception("No ten�s suficiente plata");
//		}
//	}
//
//	// Impuestos
//	public void incTorneos() {
//		torneosDisponibles++;
//	}
//
//	public void decTorneos() {
//		torneosDisponibles--;
//	}
//
//	public boolean getPagaImpuesto() {
//		return torneosDisponibles == 0;
//	}
//
//	private List<Jugador> getJugadoresConImpuesto() {
////		return listaJugadores.filter[pagaImpuesto].toList;
//		listaJugadores.stream().filter(jugador->jugador.getPagaImpuesto());
//		return listaJugadores;
//	}
//
//	public void pagarImpuesto(List<Jugador> jugadoresAPagar) {
//
//		for (Jugador jugador : jugadoresAPagar) {
//			pagarImpuesto(jugador);
//		}
////		jugadoresAPagar.forEach[pagarImpuesto]
//
//		List<Jugador> jugadoresNoPagados = Arrays.asList();
//		jugadoresNoPagados.addAll(getJugadoresConImpuesto());
//		jugadoresNoPagados.removeAll(jugadoresAPagar);
//		for (Jugador jugador : jugadoresNoPagados) {
//			jugador.noSePago();
//		}
////		jugadoresNoPagados.forEach[noSePago];
//
//		if(torneosDisponibles != 0) {
//			decTorneos();
//		}else torneosDisponibles = 3;
//		if (jugadoresNoPagados.stream().filter(jugador -> jugador.getVecesNoPagadas() != 3).collect(Collectors.toList()).size() > 0) {
//		} else {
////			var borrados = jugadoresNoPagados.filter[vecesNoPagadas == 3].toList;
//			List<Jugador> borrados = jugadoresNoPagados.stream().filter(jugador->jugador.getVecesNoPagadas() == 3).collect(Collectors.toList());
//			listaJugadores.removeAll(borrados);
//			List<String> borradosNombres = borrados.stream().map(jugador -> jugador.getNombre()).collect(Collectors.toList());
////			LigaMaster.instance.guardarBase;
////			throw new UserException("Se han borrado los siguientes jugadores" + borradosNombres)
//		}
//	}
//
//	private void pagarImpuesto(Jugador jugador) {
//		decPlata(jugador.getImpuesto());
//		jugador.pagar();
//	}
//
//	public List<Jugador> getListaJugadoresDeshabilitados() {
//		listaJugadores.stream().filter(jugador->!jugador.getHabilitado());
//		return listaJugadores;
//	}
//	public String getNombreDT() {
//		return nombreDT;
//	}
//
//	public List<Jugador> getListaJugadores() {
//		return listaJugadores;
//	}
//	public int getTorneosDisponibles() {
//		return torneosDisponibles;
//	}
//
//	public String getNombreEquipo() {
//		// TODO Auto-generated method stub
//		return nombreEquipo;
//	}
//	// Comparacion
//	@Override
//		public equals(Object obj) {
//			if(obj == null) return false;
//			if(!DT.isAssignableFrom(obj.class)) return false;
//
//			DT otroDT = obj as DT;
//			nombreDT.equals(otroDT.nombreDT);
//		}
//
//		override hashCode() {
//			super.hashCode;
//		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public int getTorneosDisponibles() {
		return torneosDisponibles;
	}

	public void setTorneosDisponibles(int torneosDisponibles) {
		this.torneosDisponibles = torneosDisponibles;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}
