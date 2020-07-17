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

		jugadoresOfrecidos.stream().forEach(jugador -> {
			try {
				dtReceptor.comprarJugador(jugador, 0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//
			}
		});
		
		jugadoresOfrecidos.stream().forEach(jugador -> {
			try {
				dtOfertante.venderJugador(jugador, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//
			}
		});
	
		LigaMaster.getInstance().mercado.listaOfertas.removeAll(LigaMaster.getInstance().mercado.getOfertasRecibidas(jugadorOfertado));
	}

	public void rechazar() {
		LigaMaster.getInstance().mercado.listaOfertas.remove(this);
	}
}
