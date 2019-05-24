package ar.edu.unq.eis.antrar.backend.model;

public class Hora {

	
	private float precio;
	private String tipo; // hora  comun ,  hora con recargo 50%
						//	esto deberia   moultiblicar  el precio de la hora  por 1 o por 1.5
	
	
	public Hora(){
		
		
	}
	
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
