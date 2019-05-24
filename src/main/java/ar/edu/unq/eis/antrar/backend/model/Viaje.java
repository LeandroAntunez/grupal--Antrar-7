package ar.edu.unq.eis.antrar.backend.model;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * @author Peter
 *
 *
 * Clase Viaje representa un viaje de solicitado por un cliente,
 * cada viaje se identificara con un numero de id, mas alla de que puedan existeir otros
 * elementos que lo identifiquen, la clase no esta completamente definir, se ira reificando a medida
 * que avance el proyecto.
 */

@Entity(name="Viajes")
public class Viaje {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="Id_Viaje")
	private int  id;
	
	@Column(name="cliente")
	private String cliente;
	
	@Column(name= "Moto")
	private String moto;
	
	@Column (name="Destino")
	private String destino;
	
	@Column(name="Viaje_con_lluvia")
	private Boolean lluvia;

	// Persistido por el LocalDateAttributeConverter
	private LocalDate fecha;
	
	@Override
	public String toString() {
		return  "Cliente: " + cliente + ", Moto = " + moto + ", Destino = " + destino;
	}


	public Viaje (){
		
	}


	public Viaje(String cliente, String moto, String destino, Boolean lluvia) {
		this.cliente = cliente;
		this.moto = moto;
		this.destino = destino;
		this.lluvia = lluvia;
	}

	public Viaje(String cliente, String moto, String destino, Boolean lluvia, LocalDate fecha) {
		this.cliente = cliente;
		this.moto = moto;
		this.destino = destino;
		this.lluvia = lluvia;
		this.fecha = fecha;
	}
	 public LocalDate getFecha() {
		return fecha;
	}
	 public void setFecha (LocalDate fecha){
		 this.fecha= fecha;
	 }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public String getMoto() {
		return moto;
	}


	public void setMoto(String moto) {
		this.moto = moto;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public Boolean getLluvia() {
		return lluvia;
	}


	public void setLluvia(Boolean lluvia) {
		this.lluvia = lluvia;
	}
	
	
	
	
	
	
}
