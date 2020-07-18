package logic.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "oferta")
public class Oferta {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dtOfertante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dtReceptor;
	
	double monto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jugador_id")
	Jugador jugadorOfertado;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "jugador_id")
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
