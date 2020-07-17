package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "transferencia")
public class Transferencia {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*@Column(name = "dt_comprador")
	String dtCompra;

	@Column(name = "dt_vendedor")
	String dtVende;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_comprador_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dtComprador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_vendedor_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dtVendedor;

	double monto;

	/*@Column(name = "jugador_comprado")
	String jugadorComprado;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jugador_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Jugador jugador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

/*	public String getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(String dtCompra) {
		this.dtCompra = dtCompra;
	}

	public String getDtVende() {
		return dtVende;
	}

	public void setDtVende(String dtVende) {
		this.dtVende = dtVende;
	}*/

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

/*	public String getJugadorComprado() {
		return jugadorComprado;
	}

	public void setJugadorComprado(String jugadorComprado) {
		this.jugadorComprado = jugadorComprado;
	}*/

	public DT getDtComprador() {
		return dtComprador;
	}

	public void setDtComprador(DT dtComprador) {
		this.dtComprador = dtComprador;
	}

	public DT getDtVendedor() {
		return dtVendedor;
	}

	public void setDtVendedor(DT dtVendedor) {
		this.dtVendedor = dtVendedor;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
