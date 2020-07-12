package master;

import java.util.List;

class Jugador {
	
	// Info SoFIFA
	private int id = 0;
	private String nombre = "";
	private String nacionalidad = "";
	private List<String> newArrayList;
	private List<String> posiciones = newArrayList;
	private int nivel = 0;
	private int potencial = 0;
	
	// Info GrondoMaster
	private int lesion = 0;
	private boolean habilitado = true;
	private double precioVenta = 0;
	private int vecesNoPagadas = 0;

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

}