package entities;

import java.util.Arrays;
import java.util.List;

final class Precios {
	private static Precios precios;
	private Precios() {
    }

	public static Precios getInstance() {
		if (precios == null) {
			precios = new Precios();
		} else {
			System.out.println(
					"No se puede crear el objeto porque ya existe un objeto de la clase LigaMaster");
		}

		return precios;
	}
	
	List<PrecioNivel> listaNiveles = Arrays.asList(
		new PrecioNivel(76, 10000.0),
		new PrecioNivel(77, 10000.0),
		new PrecioNivel(78, 20000.0),
		new PrecioNivel(79, 30000.0),
		new PrecioNivel(80, 40000.0),
		new PrecioNivel(81, 55000.0),
		new PrecioNivel(82, 70000.0),
		new PrecioNivel(83, 85000.0),
		new PrecioNivel(84, 95000.0),
		new PrecioNivel(85, 115000.0),
		new PrecioNivel(86, 130000.0),
		new PrecioNivel(87, 150000.0),
		new PrecioNivel(88, 180000.0),
		new PrecioNivel(89, 220000.0),
		new PrecioNivel(90, 250000.0),
		new PrecioNivel(91, 300000.0),
		new PrecioNivel(92, 400000.0),
		new PrecioNivel(93, 500000.0),
		new PrecioNivel(94, 550000.0)
	);

	List<PrecioEvento> listaEventos = Arrays.asList(
		new PrecioEvento("Slot", 10000.0),
		new PrecioEvento("Victoria", 2000.0),
		new PrecioEvento("Gol", 1000.0),
		new PrecioEvento("Empate", 1000.0),
		new PrecioEvento("Impuesto", 10.0),
		new PrecioEvento("PrecioMaquina", 4)	
	);

	public double getPrecio(Jugador jugador) {
		int nivelJugador = jugador.getNivel();
		if(nivelJugador > 76) {
			getPrecio(nivelJugador);
		}else { 
			getPrecio(76);
		}
		return nivelJugador;
	}

	public double getPrecio(int integer) {
		return listaNiveles.stream().filter(precioNivel -> precioNivel.getNivel() == integer).findFirst().get().getPrecio();
	}

	public double getPrecio(String string) {
		return listaEventos.stream().filter(evento -> evento.getEvento().equals(string)).findFirst().get().getPrecio();
	}

}

class PrecioNivel{
	int nivel;
	double precio;

	public PrecioNivel(int _nivel, double _precio){
        this.nivel = _nivel;
        this.precio = _precio;
    }
	
	public double getNivel() {
		return nivel;
	}
	
	public double getPrecio() {
		return precio;
	}
}

class PrecioEvento{
	String evento;
	double precio;

	public PrecioEvento(String _evento, double _precio){
        this.evento = _evento;
        this.precio = _precio;
    }
	
	public String getEvento() {
		return evento;
	}
	
	public double getPrecio() {
		return precio;
	}
}