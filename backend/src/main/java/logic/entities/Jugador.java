package logic.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jugador")
public class Jugador {

	Jugador() {
		habilitado = true;
	}
	
	// Info SoFIFA
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;

	private String nacionalidad;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Posicion.class)
	@JoinTable(name = "jugador_posicion", joinColumns = { @JoinColumn(name = "jugador_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "posicion_id", referencedColumnName = "id")})
	private List<String> posiciones;

	private int nivel;
	private int potencial;
	
	// Info GrondoMaster
	private int lesion;
	private boolean habilitado;

	@Column(name = "precio_venta")
	private double precioVenta;

	@Column(name = "cantidad_no_pagada")
	private int vecesNoPagadas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dt_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DT dt;

	// Impuestos
	
	public double getImpuesto() {
		return Precios.getInstance().getPrecio(this) * (Precios.getInstance().getPrecio("Impuesto") / 100);
	}
	
	public void noSePago() {
		vecesNoPagadas++;
		habilitado = false;
	}
	
	public void pagar() {
		vecesNoPagadas = 0;
		habilitado = true;
	}
	
	public boolean getPagaImpuesto() {
		return nivel > 82;
	}

	// Mercado

	public double getPrecioMaquina() {
		return Precios.getInstance().getPrecio(this);
	}
	
	
	public DT getPropietario() {
		return LigaMaster.getInstance().getPropietario(this);
	}
	
	private String getPropietarioNombre() {
		return LigaMaster.getInstance().getPropietario(this).getNombreDT();
	}

	// Actualizar Stats
//	public void update() {
//		val instance = Jsoup.connect("http://sofifa.com/player/" + id+ "?hl=es-ES").userAgent("Mozilla").post;
//		
//		val ratings = instance.select("td.text-center > span.label");
//		nivel = Integer::parseInt(ratings.get(0).text);
//		potencial = Integer.parseInt(ratings.get(1).text);
//		
//		val data = instance.select( "div.meta > span" );
//		nacionalidad = data.select("a > span").attr("title");
//		posiciones = newArrayList( data.select("span > span").map[text]);
//	}

	// Lesion

	public boolean estaLesionado() {
		return lesion > 0;
	}

	public void incLesion() {
		lesion++;
	}

	public void decLesion() {
		lesion--;
	}
	
	public boolean getHabilitado(){
		return habilitado;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getVecesNoPagadas() {
		return vecesNoPagadas;
	}

	public void setPrecioVenta(int i) {
		precioVenta = i;
	}

	public int getNivel() {
		return nivel;
	}

	public int getId() {
		return id;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<String> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<String> posiciones) {
		this.posiciones = posiciones;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPotencial() {
		return potencial;
	}

	public void setPotencial(int potencial) {
		this.potencial = potencial;
	}

	public int getLesion() {
		return lesion;
	}

	public void setLesion(int lesion) {
		this.lesion = lesion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public void setVecesNoPagadas(int vecesNoPagadas) {
		this.vecesNoPagadas = vecesNoPagadas;
	}

	public DT getDt() {
		return dt;
	}

	public void setDt(DT dt) {
		this.dt = dt;
	}
}