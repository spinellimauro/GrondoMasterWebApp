package com.grondomaster.springjwt.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class DT {

    public DT(String _nombreDT, String _apellido, String _username, String _password){
        this.setNombre(_nombreDT);
        this.setApellido(_apellido);
        this.setPassword(_password);
        this.setUsername(_username);
        this.setSlots(30);
        this.setPlata(0.0);
        this.setTorneosDisponibles(3);
        this.setEnabled(true);
    }

    public DT(){
        this.setSlots(30);
        this.setPlata(0.0);
        this.setTorneosDisponibles(3);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipo")
    Equipo equipo;

    private String nombre;

    private String apellido;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id") })
    private Set<Role> roles;

    @Column(name = "dinero")
    private double plata;

    @Column(name = "torneos_disponibles")
    private int torneosDisponibles;

    private int slots;

    private boolean enabled;

    private Date lastLogIn;

    private String username;


    //	public void venderJugador(Jugador jugador, double precio) throws Exception {
//		if (!listaJugadores.contains(jugador))
//			throw new Exception("Ese jugador no es tuyo");
//
//		incPlata(precio);
//		removeJugador(jugador);
//	}
//
//	public void comprarJugador(Jugador jugador, double precio) throws Exception {
//		if (plata < precio)
//			throw new Exception("No ten�s suficiente plata");
//
//		if (getCantJugadores() == slots)
//			throw new Exception("No hay slots disponibles");
//
//		decPlata(precio);
//		addJugador(jugador);
//		jugador.setPrecioVenta(0);
//	}
//
//	private int getCantJugadores() {
//		return listaJugadores.size();
//	}
//
//	private void addJugador(Jugador jugador) {
//		listaJugadores.add(jugador);
//	}
//
//	private void removeJugador(Jugador jugador) {
//		listaJugadores.remove(jugador);
//	}
//
//	public void comprarSlot() throws Exception {
//		double precioSlot = Precios.getInstance().getPrecio("Slot");
//
//		if (plata < precioSlot)
//			throw new Exception("No ten�s suficiente plata");
//
//		slots++;
//		decPlata(precioSlot);
//	}
//
//	public void incPlata(double monto) {
//		plata += monto;
//	}
//
//	public void decPlata(double monto) {
//		plata -= monto;
//	}
//
//	public void prestarPlata(DT otro, double monto ) throws Exception{
//		if(plata >= monto){
//			otro.plata += monto;
//			plata -= monto;
//		}
//		else{
//			throw new Exception("No ten�s suficiente plata");
//		}
//	}
//
//	// Impuestos
//	public void incTorneos() {
//		torneosDisponibles++;
//	}
//
//	public void decTorneos() {
//		torneosDisponibles--;
//	}
//
//	public boolean getPagaImpuesto() {
//		return torneosDisponibles == 0;
//	}
//
//	private List<Jugador> getJugadoresConImpuesto() {
////		return listaJugadores.filter[pagaImpuesto].toList;
//		listaJugadores.stream().filter(jugador->jugador.getPagaImpuesto());
//		return listaJugadores;
//	}
//
//	public void pagarImpuesto(List<Jugador> jugadoresAPagar) {
//
//		for (Jugador jugador : jugadoresAPagar) {
//			pagarImpuesto(jugador);
//		}
////		jugadoresAPagar.forEach[pagarImpuesto]
//
//		List<Jugador> jugadoresNoPagados = Arrays.asList();
//		jugadoresNoPagados.addAll(getJugadoresConImpuesto());
//		jugadoresNoPagados.removeAll(jugadoresAPagar);
//		for (Jugador jugador : jugadoresNoPagados) {
//			jugador.noSePago();
//		}
////		jugadoresNoPagados.forEach[noSePago];
//
//		if(torneosDisponibles != 0) {
//			decTorneos();
//		}else torneosDisponibles = 3;
//		if (jugadoresNoPagados.stream().filter(jugador -> jugador.getVecesNoPagadas() != 3).collect(Collectors.toList()).size() > 0) {
//		} else {
////			var borrados = jugadoresNoPagados.filter[vecesNoPagadas == 3].toList;
//			List<Jugador> borrados = jugadoresNoPagados.stream().filter(jugador->jugador.getVecesNoPagadas() == 3).collect(Collectors.toList());
//			listaJugadores.removeAll(borrados);
//			List<String> borradosNombres = borrados.stream().map(jugador -> jugador.getNombre()).collect(Collectors.toList());
////			LigaMaster.instance.guardarBase;
////			throw new UserException("Se han borrado los siguientes jugadores" + borradosNombres)
//		}
//	}
//
//	private void pagarImpuesto(Jugador jugador) {
//		decPlata(jugador.getImpuesto());
//		jugador.pagar();
//	}
//
//	public List<Jugador> getListaJugadoresDeshabilitados() {
//		listaJugadores.stream().filter(jugador->!jugador.getHabilitado());
//		return listaJugadores;
//	}
//	public String getNombreDT() {
//		return nombreDT;
//	}
//
//	public List<Jugador> getListaJugadores() {
//		return listaJugadores;
//	}
//	public int getTorneosDisponibles() {
//		return torneosDisponibles;
//	}
//
//	public String getNombreEquipo() {
//		// TODO Auto-generated method stub
//		return nombreEquipo;
//	}
//	// Comparacion
//	@Override
//		public equals(Object obj) {
//			if(obj == null) return false;
//			if(!DT.isAssignableFrom(obj.class)) return false;
//
//			DT otroDT = obj as DT;
//			nombreDT.equals(otroDT.nombreDT);
//		}
//
//		override hashCode() {
//			super.hashCode;
//		}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public int getTorneosDisponibles() {
        return torneosDisponibles;
    }

    public void setTorneosDisponibles(int torneosDisponibles) {
        this.torneosDisponibles = torneosDisponibles;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> authorities) {
        this.roles = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(Date lastLogIn) {
        this.lastLogIn = lastLogIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
