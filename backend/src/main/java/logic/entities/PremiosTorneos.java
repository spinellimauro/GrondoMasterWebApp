package logic.entities;

import java.util.Arrays;
import java.util.List;

public class PremiosTorneos {
		List<PremioEvento> listaEventos = Arrays.asList(
			new PremioEvento("Victoria", 2000.0),
			new PremioEvento("Gol", 1000.0),
			new PremioEvento("Empate", 1000.0)
		);
		
		List<PremioPosicion> listaPosiciones = Arrays.asList(
			new PremioPosicion(1, 40000.0),
			new PremioPosicion(2, 30000.0),
			new PremioPosicion(3, 20000.0),	
			new PremioPosicion(4, 10000.0)	
		);
		
		public double getPremioEvento(String string) {
			return listaEventos.stream().filter(evento -> evento.equals(string)).findFirst().get().getPremio();
		}
		
		public double getPremio(Integer _posicion) {
			return listaPosiciones.stream().filter(posicion -> posicion.equals(_posicion)).findFirst().get().getPremio();
		}
		
		public void crearPremio(double premio){
			listaPosiciones.add(new PremioPosicion(listaPosiciones.size() + 1, premio));
		}
		
		public int getCantPremios(){
			return listaPosiciones.size();
		}
		
		
	}

	class PremioEvento{
		String evento;
		double premio;

		public PremioEvento(String _evento, double _premio) {
			evento = _evento;
			premio = _premio;
		}
		
		public double getPremio() {
			return premio;
		}
	}
	
	class PremioPosicion{
		int posicion;
		double premio;

		public PremioPosicion(int _posicion, double _premio) {
			posicion = _posicion;
			premio = _premio;
		}
		
		public double getPremio() {
			return premio;
		}
	}