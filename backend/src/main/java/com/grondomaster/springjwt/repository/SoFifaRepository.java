package com.grondomaster.springjwt.repository;

import com.grondomaster.springjwt.models.Equipo;
import com.grondomaster.springjwt.models.Jugador;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoFifaRepository {

    public static List<Jugador> getJugadores(String string) throws IOException {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            Document document = Jsoup.connect("http://sofifa.com/players?keyword=" + string + "&layout=2017desktop&hl=es-ES").userAgent("Mozilla").post();
            Thread.sleep(1000);
            Elements tabla = document.select("tbody > tr");

            for (int i = 0; i < tabla.size(); i++) { //first row is the col names so skip it.
                Element row = tabla.get(i);
                Elements cols = row.select("td");

                Jugador jugador = new Jugador();
                jugador.setNombre(cols.get(1).select("a").attr("data-tooltip"));
                jugador.setNacionalidad(cols.get(1).select("img").attr("title"));
                jugador.setNacionalidadCorta(jugador.getNacionalidad().substring(0,2).toLowerCase());
                jugador.setId(Integer.valueOf(cols.get(0).select("img").attr("id")));
                //jugador.posiciones = newArrayList(cols.get(2).select("span").map[text]);
                jugador.setNivel(Integer.parseInt(cols.get(3).text()));
                jugador.setPotencial(Integer.parseInt(cols.get(4).text()));

                jugadores.add(jugador);
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }

        return jugadores;
    }



    public static List<Equipo> getEquipos(String string) throws IOException {
        List<Equipo> equipos = new ArrayList<>();
        try {
            Document document = Jsoup.connect("http://sofifa.com/teams?keyword=" + string).userAgent("Mozilla").post();
            Elements tabla = document.select("tbody > tr > td > a[href*=team]");
            for (int i = 1; i < tabla.size(); i++) { //first row is the col names so skip it.
                Element row = tabla.get(i);
                Elements cols = row.select("td");

                Equipo equipo = new Equipo();
                equipo.setName(cols.get(1).select("a").text());

                equipos.add(equipo);
            }
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }

        return equipos;
    }

}
