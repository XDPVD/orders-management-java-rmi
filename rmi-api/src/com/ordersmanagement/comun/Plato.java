package com.ordersmanagement.comun;

import java.io.Serializable;

public class Plato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id_comida;
	
	private String nombre;
	
	// TODO: Class of image attribute of plato class
	
	public Plato(int id_comida, String nombre) {
		this.id_comida = id_comida;
		this.nombre = nombre;
		// TODO: Image
	}

	public int getId_comida() {
		return id_comida;
	}

	public String getNombre() {
		return nombre;
	}

	
	
}
