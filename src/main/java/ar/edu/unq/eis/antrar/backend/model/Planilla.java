/**
 * 
 */
package ar.edu.unq.eis.antrar.backend.model;

import java.util.ArrayList;

/**
 * @author Peter
 *
 */
public class Planilla {
	
	
	private String fecha;
	private ArrayList<Viaje> listaDeViajes;
	
	
	public Planilla(String fecha, ArrayList<Viaje> listaDeViajes) {
		this.fecha = fecha;
		this.listaDeViajes = listaDeViajes;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public ArrayList<Viaje> getListaDeViajes() {
		return listaDeViajes;
	}


	public void setListaDeViajes(ArrayList<Viaje> listaDeViajes) {
		this.listaDeViajes = listaDeViajes;
	} 
	 public void  agregarViaje(Viaje viaje){
		 
		 this.listaDeViajes.add(viaje);
	 }
	

}
