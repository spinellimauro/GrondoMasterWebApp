package logic.entities;

import javax.persistence.*;

@Entity
@Table(name = "suceso")
public class Suceso {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @Column(name = "cantidad_goles")
    private int cantidadGoles;

    @Column(name = "cantidad_amarillas")
    private int cantidadAmarillas;

    @Column(name = "cantidad_rojas")
    private int cantidadRojas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getCantidadGoles() {
        return cantidadGoles;
    }

    public void setCantidadGoles(int cantidadGoles) {
        this.cantidadGoles = cantidadGoles;
    }

    public int getCantidadAmarillas() {
        return cantidadAmarillas;
    }

    public void setCantidadAmarillas(int cantidadAmarillas) {
        this.cantidadAmarillas = cantidadAmarillas;
    }

    public int getCantidadRojas() {
        return cantidadRojas;
    }

    public void setCantidadRojas(int cantidadRojas) {
        this.cantidadRojas = cantidadRojas;
    }

}
