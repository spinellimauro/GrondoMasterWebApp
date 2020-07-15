package master;

import java.util.Arrays;
import java.util.List;

public class Oferta {
	DT dtOfertante;
	DT dtReceptor;
	double monto;
	
	Jugador jugadorOfertado;
	List<Jugador> jugadoresOfrecidos = Arrays.asList();

	public void aceptar() throws Exception {
		dtOfertante.comprarJugador(jugadorOfertado,monto);
		dtReceptor.venderJugador(jugadorOfertado,monto);

		jugadoresOfrecidos.stream().forEach(jugador -> dtReceptor.comprarJugador(jugador, 0));
		jugadoresOfrecidos.stream().forEach(jugador -> dtOfertante.venderJugador(jugador, 0));
	
		LigaMaster.getInstance().mercado.listaOfertas.removeAll(LigaMaster.getInstance().mercado.getOfertasRecibidas(jugadorOfertado));
	}

	public void rechazar() {
		LigaMaster.getInstance().mercado.listaOfertas.remove(this);
	}
}
