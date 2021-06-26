package com.ordersmanagement.comun;

import java.io.Serializable;

public class LineaPedidoDTO implements Serializable {
	
	/**
	 * 
	 * LineaPedidoDTO
	 * La linea pedido se puede utilizar para la creacion del pedido y para la recuperacion de la informacion del pedido.
	 * 
	 * 1. Creacion
	 * Solo utilizar el primer constructor (id_comida, cantidad)
	 * 
	 * 2. Recuperacion o consulta
	 * Los atributos id_comida, nombre_comida y cantidad son valores no nulos, por lo tanto pueden solicitar los valores de ellos mediante
	 * los getters.
	 * 
	 */
	private static final long serialVersionUID = 132L;

	private int id_comida;
	
	private String nombre_comida;
	
	private int cantidad;
	

	public LineaPedidoDTO(int id_comida, int cantidad) {
		this.id_comida = id_comida;
		this.cantidad = cantidad;
	}
	
	public LineaPedidoDTO(int id_comida, int cantidad, String nombre_comida) {
		this.id_comida = id_comida;
		this.cantidad = cantidad;
		this.nombre_comida = nombre_comida;
	}
	
	public int getId_comida() {
		return id_comida;
	}

	public int getCantidad() {
		return cantidad;
	}	
	
	public String getNombre_comida() {
		return this.nombre_comida;
	}
	
	public String toString() {
		return "\t## LineaPedido\n"
				+ "\tNombre comida: " + this.nombre_comida + "\n"
				+ "\tCantidad: "+this.cantidad + "\n";
	}
	
	
}
