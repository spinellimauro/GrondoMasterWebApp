package logic.entities;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mercado")
public class Mercado {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "oferta_id")
	List<Oferta> listaOfertas = Arrays.asList();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "transferencia_id")
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
