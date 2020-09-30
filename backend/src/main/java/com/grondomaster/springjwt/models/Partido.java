package com.grondomaster.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partido")
public class Partido {
	
	public Partido(){
		numeroFecha = 0;
		terminado = false;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "numero_fecha")
	int numeroFecha;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipo_local_id")
	Equipo equipoLocal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipo_visitante_id")
	Equipo equipoVisitante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "torneo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Torneo torneo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "partido_id")
	List<Suceso> sucesos = new ArrayList<Suceso>();

	boolean terminado;

//	public boolean getJugoPartido(DT dt) {
//		return dtLocal.equals(dt) || dtVisitante.equals(dt);
//	}
//
//	// Goles
//	public String getScore() {
//		return golesLocal.size() + " - " + golesVisitante.size();
//	}
//
//	public int getGoles(Jugador jugador) {
//		List<Jugador> goles = Stream.concat(golesLocal.stream(), golesVisitante.stream())
//				.collect(Collectors.toList());
//		return Collections.frequency(goles.stream().collect(Collectors.toList()), jugador);
//	}
//
//	public void addGol(Jugador jugador) {
//		if(dtLocal.getListaJugadores().contains(jugador)) golesLocal.add(jugador); else golesVisitante.add(jugador);
//	}
//
//	public void removeGol(Jugador jugador) {
//		if(dtLocal.getListaJugadores().contains(jugador)) golesLocal.remove(jugador); else golesVisitante.remove(jugador);
//	}
//
//	// Amonestaciones
//	public void addAmarilla(Jugador jugador) {
//		if(getAmarillas(jugador) < 2 && getRojas(jugador) < 1) listaAmarillas.add(jugador);
//	}
//
//	public void removeAmarilla(Jugador jugador) {
//		listaAmarillas.remove(jugador);
//	}
//
//	public void addRoja(Jugador jugador) {
//		if(getRojas(jugador) < 1 && getAmarillas(jugador) < 2) listaRojas.add(jugador);
//	}
//
//	public void removeRoja(Jugador jugador) {
//		listaRojas.remove(jugador);
//	}
//
//	public boolean fueAmonestado(Jugador jugador) {
//		return (getAmarillas(jugador) == 1);
//	}
//
//	public boolean fueExpulsado(Jugador jugador) {
//		return (getAmarillas(jugador) == 2 || getRojas(jugador) == 1);
//	}
//
//	// Estadisticas - Jugador
//	public int getAmarillas(Jugador jugador) {
//		return Collections.frequency(listaAmarillas, jugador);
//	}
//
//	public int getRojas(Jugador jugador) {
//		return Collections.frequency(listaRojas, jugador);
//	}
//
//	// Estadisticas - DT
//	public int getPuntos(DT dt) {
//		if (dt.equals(dtLocal)) {
//			if (golesLocal.size() > golesVisitante.size())
//				return 3;
//			else if(golesLocal.size() < golesVisitante.size()) return 0; else return 1;
//		} else {
//			if (golesLocal.size() < golesVisitante.size())
//				return 3;
//			else if(golesLocal.size() > golesVisitante.size()) return 0; else return 1;
//		}
//	}
//
//	public int getGolesFavor(DT dt) {
//		if(dt.equals(dtLocal)) return golesLocal.size(); else return golesVisitante.size();
//	}
//
//	public int getGolesContra(DT dt) {
//		if(dt.equals(dtLocal)) return golesVisitante.size(); else return golesLocal.size();
//	}
//
//	// Listas
//	public List<Jugador> getSuspendidos() {
//		List<Jugador> listaJugadoresCompleta = Stream.concat(dtLocal.getListaJugadores().stream(), dtVisitante.getListaJugadores().stream())
//				.collect(Collectors.toList());
//		return listaJugadoresCompleta.stream().filter(jugador -> torneo.estaSuspendido(jugador, numeroFecha)).collect(Collectors.toList());
//	}
//
//	public List<Jugador> getLesionados() {
//		List<Jugador> listaJugadoresCompleta = Stream.concat(dtLocal.getListaJugadores().stream(), dtVisitante.getListaJugadores().stream())
//				.collect(Collectors.toList());
//		return listaJugadoresCompleta.stream().filter(jugador -> jugador.estaLesionado()).collect(Collectors.toList());
//	}
//
//	// Terminar Partido
//	public void terminarPartido() throws Exception {
//		if (terminado) {
//			throw new Exception("El partido ya termin�");
//		}
//
//		terminado = true;
//		getLesionados().stream().forEach(jugador -> jugador.decLesion());
//		dtLocal.incPlata(getPremio(dtLocal));
//		dtVisitante.incPlata(getPremio(dtVisitante));
//	}
//
//	public double getPremio(DT dt) {
//		if (getPuntos(dt) == 3)
//			return torneo.premios.getPremioEvento("Victoria") + torneo.premios.getPremioEvento("Gol") * ( getGolesFavor(dt) - getGolesContra(dt) );
//		else if (getPuntos(dt) == 1)
//			return torneo.premios.getPremioEvento("Empate");
//		else
//			return 0;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroFecha() {
		return numeroFecha;
	}

	public void setNumeroFecha(int numeroFecha) {
		this.numeroFecha = numeroFecha;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	public List<Suceso> getSucesos() {
		return sucesos;
	}

	public void setSucesos(List<Suceso> sucesos) {
		this.sucesos = sucesos;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}
}