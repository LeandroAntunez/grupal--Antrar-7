package ar.edu.unq.eis.antrar.backend.model;

import javax.persistence.*;

@Entity(name = "Clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id_Cliente")
	private int id;

	@Column(name="Razon_Social")
	private String razonSocial;

	@Column(name="CUIT")
	private long cuit;

	@Column(name="Domicilio")
	private String domicilio;
	
	public Cliente(){
			
	}
	
	public Cliente(String nombre, long cuit, String domicilio) {
		this.razonSocial = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
	}


	public String getNombre() {
		return razonSocial;
	}
	public void setNombre(String nombre) {
		this.razonSocial = nombre;
	}
	public long getCuit() {
		return cuit;
	}
	public void setCuit(int cuit) {
		this.cuit = cuit;
	}


	@Override
	public String toString() {
		return "Cliente " + id +  razonSocial ;
	}
}
