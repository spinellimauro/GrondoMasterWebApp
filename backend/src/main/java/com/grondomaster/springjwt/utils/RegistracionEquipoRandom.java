package com.grondomaster.springjwt.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.grondomaster.springjwt.repository.SoFifaRepository;

public class RegistracionEquipoRandom {
	
	private static Integer nivel_maximo = 78;
	private static Integer nivel_mimino = 67;
	private static Integer edad_maxima = 35;
	private static Integer edad_minima = 20;
	private static List<Integer> arqueros = Arrays.asList(0);
	private static List<Integer> defensores = Arrays.asList(2,3,5,7,8);
	private static List<Integer> mediocampistas = Arrays.asList(10,12,14,16,18);
	private static List<Integer> delanteros = Arrays.asList(20,21,22,23,25,27);
	
	public static Integer returnAge() {
		Random age = new Random();
		return age.nextInt((edad_maxima - edad_minima) + 1 ) + edad_minima;
	}

	public static Integer returnLevel() {
		Random level = new Random();
		return level.nextInt((nivel_maximo - nivel_mimino) + 1 ) + nivel_mimino;
	}
	
	public static Integer getAlignment(String alineacion) {
//	1) 3-5-2
//	2) 3-4-3
//	3) 4-3-3
//	4) 4-2-4
//	5) 4-5-1
//	6) 5-2-3
//	7) 5-4-1
//	8) 5-3-2
		String alingment = "4-3-3";
		
		for(int i=0;i<=3;i++) {
			if(i==1) {
				returnPosition(Integer.parseInt(alingment.substring(0,1)));
			}
			if(i==2) {
				returnPosition(Integer.parseInt(alingment.substring(2,3)));
			}
			if(i==3) {
				returnPosition(Integer.parseInt(alingment.substring(4,5)));
			}
		}
		return 1;
	}
	
	public static Integer returnPosition(Integer alineacion) {
		Integer posicion = 0;
			if(alineacion == 0) {
				 int index = new Random().nextInt(defensores.size());
			     posicion = defensores.get(index);
			}else if(alineacion == 1){
				 int index = new Random().nextInt(mediocampistas.size());
			     posicion = mediocampistas.get(index);
			}else if(alineacion == 2){
				 int index = new Random().nextInt(delanteros.size());
			     posicion = delanteros.get(index);
			}else {
				 int index = new Random().nextInt(arqueros.size());
			     posicion = arqueros.get(index);
			}
				
		return posicion;
	}
}
