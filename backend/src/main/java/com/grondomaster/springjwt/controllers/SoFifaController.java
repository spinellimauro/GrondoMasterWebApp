package com.grondomaster.springjwt.controllers;

import com.grondomaster.springjwt.models.Jugador;
import com.grondomaster.springjwt.repository.SoFifaRepository;
import com.grondomaster.springjwt.utils.SharedMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/soFifa")
public class SoFifaController extends SharedMethods {

    @RequestMapping(value = {"/get-jugadores-by-search/{query}"}, method = { RequestMethod.GET })
    public ResponseEntity<?> searchPlayer(@PathVariable("query") String query)
    {
        try {
            List<Jugador> jugadores = SoFifaRepository.getJugadores(query);

            return ResponseEntity.ok(jugadores);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
