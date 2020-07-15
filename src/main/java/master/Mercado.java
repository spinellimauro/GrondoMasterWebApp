package master;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mercado {
	List<Oferta> listaOfertas = Arrays.asList();
	List<Transferencia> listaTraspasos = Arrays.asList();
	
	
	
	public List<Oferta> getOfertasRecibidas(Jugador jugador) {
		return listaOfertas.stream().filter(oferta -> oferta.jugadorOfertado.equals(jugador)).collect(Collectors.toList());
	}
	
	public List<Oferta> getOfertasRecibidas(DT dt) {
		return listaOfertas.stream().filter(oferta -> oferta.dtReceptor.equals(dt)).collect(Collectors.toList());
	}

	public List<Oferta> getOfertasEnviadas(DT dt) {
		return listaOfertas.stream().filter(oferta -> oferta.dtOfertante.equals(dt)).collect(Collectors.toList());
	}
	
	public void agregarTransferencia(Transferencia transferencia){
		listaTraspasos.add(transferencia);
	}
}
