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
	@JoinColumn(name = "equipo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Equipo equipo;


	// Impuestos

//	public double getImpuesto() {
//		return Precios.getInstance().getPrecio(this) * (Precios.getInstance().getPrecio("Impuesto") / 100);
//	}
//
//	public void noSePago() {
//		vecesNoPagadas++;
//		habilitado = false;
//	}
//
//	public void pagar() {
//		vecesNoPagadas = 0;
//		habilitado = true;
//	}
//
//	public boolean getPagaImpuesto() {
//		return nivel > 82;
//	}

	// Mercado

//	public double getPrecioMaquina() {
//		return Precios.getInstance().getPrecio(this);
//	}
//
//
//	public DT getPropietario() {
//		return LigaMaster.getInstance().getPropietario(this);
//	}
//
//	private String getPropietarioNombre() {
//		return LigaMaster.getInstance().getPropietario(this).getNombreDT();
//	}

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

//	public boolean estaLesionado() {
//		return lesion > 0;
//	}
//
//	public void incLesion() {
//		lesion++;
//	}
//
//	public void decLesion() {
//		lesion--;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
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

	public int getNivel() {
		return nivel;
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

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getVecesNoPagadas() {
		return vecesNoPagadas;
	}

	public void setVecesNoPagadas(int vecesNoPagadas) {
		this.vecesNoPagadas = vecesNoPagadas;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}