package com.ordersmanagement.comun;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoCrearDTO implements Serializable {
	
	/**
	 * 
	 * getNumbersOfLines() -> devuelve las lineas de pedido que tiene el pedido (int)
	 * isDelivery() -> devuelve un booleano que indica si el pedido es para delivery o no
	 * getNombre_persona() -> devuelve el nombre de la persona que pidio el pedido
	 * getDNI() -> identificacion (int)
 	 * getDireccion() -> string
 	 * getCelular() -> int
 	 * getPagoPendiente() -> float
 	 * 
 	 * getLineasPedido() devuelve los objetos de LineaPedido
 	 * 
 	 * PedidoCrearDTO es una clase que instancia objetos que seran llevados como input al backend para que este 
 	 * puede crear un registro en la tabla PEDIDO con los atributos que contiene dicha clase.
 	 * Se puede ver que no hay los atributos de id_pedido, fecha_terminado, etc... Dedido a que esto solo se utilizara en le backend
 	 * y en la clase PedidoDetails 
 	 * 
 	 * El objeto DEBE ser construido al menos con los parametros nombre_persona y delivery (Primer constructor)
 	 * 
 	 * En caso el delivery sea verdadero, el objeto DEBE tener todos los parametros llenos. (Segundo constructor)
 	 * 
 	 * LineasPedido se establece luego de la invocacion del constructor, esto mediante el metodo addLineaPedido que 
 	 * permite el ingreso de un objeto de la clase LineaPedidoDTO.
 	 * 
 	 * Al ejecutar este comando, addLineaPedido() agrega un elemento adicional al arraylist interno que tiene el objeto PedidoCrearDTO.
 	 * 
 	 * El objeto deberia crearse (junto a sus lineaspedido) luego de dar click al boton submit del formulario. Luego
 	 * el formulario debera limpiarse.
 	 * 
	 */
	private static final long serialVersionUID = 4231L;

	private String nombre_persona;
	
	private boolean delivery;
	
	private Integer dni;
	
	private String direccion;
		
	private Integer celular;
	
	private Float pago_pendiente;
	
	private List<LineaPedidoDTO> lineasPedido;  
			
	public PedidoCrearDTO( String nombre_persona, boolean delivery) 
	{
		
		this.nombre_persona = nombre_persona;
		this.delivery = delivery;
		
		this.lineasPedido = new ArrayList<LineaPedidoDTO>();
		
	}
	
	public PedidoCrearDTO( 	String nombre_persona, boolean delivery, 
							Integer dni, String direccion, Integer celular, float pago_pendiente) 
	{
			
			this(nombre_persona, delivery);
			
			if(delivery) this.extraDescription(dni, direccion, celular, pago_pendiente);
			
	}
	
	private void extraDescription( int dni, String direccion, int celular, float pago_pendiente) {
		
		this.dni = dni;
		this.direccion = direccion;
		this.celular = celular;
		this.pago_pendiente = pago_pendiente;
		
	}
	


	public void anadirLineaPedido(LineaPedidoDTO linea) {
		lineasPedido.add(linea);
	}
	
	public Integer getNumbersOfLines() {
		return this.lineasPedido.size();
	}
	
	public boolean isDelivery() {
		return this.delivery;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public Integer getDni() {
		return dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getCelular() {
		return celular;
	}

	public Float getPago_pendiente() {
		return pago_pendiente;
	}

	public List<LineaPedidoDTO> getLineasPedido() {
		return lineasPedido;
	}
	
}
