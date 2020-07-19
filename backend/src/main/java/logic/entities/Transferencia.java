package logic.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "transferencia")
public class Transferencia {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_comprador_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dtComprador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_vendedor_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dtVendedor;

	double monto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jugador_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Jugador jugador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

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
