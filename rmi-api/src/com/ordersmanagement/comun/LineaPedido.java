package com.ordersmanagement.comun;

import java.io.Serializable;

public class LineaPedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 132L;

	private int id_comida;
	
	private int cantidad;

	public int getId_comida() {
		return id_comida;
	}

	public int getCantidad() {
		return cantidad;
	}	
	
}
