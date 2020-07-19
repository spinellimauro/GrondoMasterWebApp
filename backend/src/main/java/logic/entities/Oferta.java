package logic.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "oferta")
public class Oferta {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_ofertante_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private DT dtOfertante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_receptor_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private DT dtReceptor;

	private double monto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jugador_ofertado_id", referencedColumnName = "id", nullable = false)
	private Jugador jugadorOfertado;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Jugador.class)
	@JoinTable(name = "oferta_jugador", joinColumns = { @JoinColumn(name = "oferta_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "jugador_id", referencedColumnName = "id")})
	private List<Jugador> jugadoresOfrecidos;

//	public void aceptar() throws Exception {
//		dtOfertante.comprarJugador(jugadorOfertado,monto);
//		dtReceptor.venderJugador(jugadorOfertado,monto);
//
//		jugadoresOfrecidos.stream().forEach(jugador -> {
//			try {
//				dtReceptor.comprarJugador(jugador, 0);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				//
//			}
//		});
//
//		jugadoresOfrecidos.stream().forEach(jugador -> {
//			try {
//				dtOfertante.venderJugador(jugador, 0);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				//
//			}
//		});
//
//		LigaMaster.getInstance().mercado.listaOfertas.removeAll(LigaMaster.getInstance().mercado.getOfertasRecibidas(jugadorOfertado));
//	}
//
//	public void rechazar() {
//		LigaMaster.getInstance().mercado.listaOfertas.remove(this);
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DT getDtOfertante() {
		return dtOfertante;
	}

	public void setDtOfertante(DT dtOfertante) {
		this.dtOfertante = dtOfertante;
	}

	public DT getDtReceptor() {
		return dtReceptor;
	}

	public void setDtReceptor(DT dtReceptor) {
		this.dtReceptor = dtReceptor;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Jugador getJugadorOfertado() {
		return jugadorOfertado;
	}

	public void setJugadorOfertado(Jugador jugadorOfertado) {
		this.jugadorOfertado = jugadorOfertado;
	}

	public List<Jugador> getJugadoresOfrecidos() {
		return jugadoresOfrecidos;
	}

	public void setJugadoresOfrecidos(List<Jugador> jugadoresOfrecidos) {
		this.jugadoresOfrecidos = jugadoresOfrecidos;
	}
}
