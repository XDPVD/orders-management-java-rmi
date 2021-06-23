package com.ordersmanagement.comun;

import java.io.Serializable;

public class LineaPedidoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 132L;

	private int id_comida;
	
	private int cantidad;

	public LineaPedidoDTO(int id_comida, int cantidad) {
		this.id_comida = id_comida;
		this.cantidad = cantidad;
	}
	
	public int getId_comida() {
		return id_comida;
	}

	public int getCantidad() {
		return cantidad;
	}	
	
}
