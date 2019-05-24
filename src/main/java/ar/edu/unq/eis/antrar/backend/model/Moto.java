package ar.edu.unq.eis.antrar.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name= "Tabla_de_Motos")
public class Moto implements Serializable

{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@Column (name="Codigo")
	private long codigo;
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "DNI")
	private long dni;
	@Column(name ="Nomres")
	private String nombre;
	@Column(name ="Apellido")
	private String  apellido;
	
	@Column(name ="Fec__Nac")
	private Date fechaNacimineto;
	

	public Moto(){
		
	}
	public Moto(String nombre, String apellido, long dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	
	}
	public Moto(String nombre, String apellido, long dni, Date fechaDeNacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimineto = fechaDeNacimiento;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public long getDni() {
		return dni;
	}
	
	public void setDni(long dni) {
		this.dni = dni;
	}
	
	public Date getFechaDeNacimiento() {
		return fechaNacimineto;
	}
	
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaNacimineto = fechaDeNacimiento;
	}

	@Override
	public String toString() {
		return   getNombre() + " " + getApellido() ;
	}
	
}
