package com.ordersmanagement.comun;

import java.io.Serializable;

public class PlatoDetallesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id_comida;
	
	private String nombre;
	
	private byte[] imagen; 
	
	public PlatoDetallesDTO(int id_comida, String nombre, byte[] imagen) {
		this.id_comida = id_comida;
		this.nombre = nombre;
	
		//Copy of image array bytes
		if(imagen != null) System.arraycopy(imagen, 0 , this.imagen, 0 , imagen.length);
		
	}

	public int getId_comida() {
		return id_comida;
	}

	public String getNombre() {
		return nombre;
	}

	public byte[] getImagen() {
		return imagen;
	}
	
	
}
