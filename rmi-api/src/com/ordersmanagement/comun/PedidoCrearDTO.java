package com.ordersmanagement.comun;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoCrearDTO implements Serializable {
	
	/**
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
	
	public PedidoCrearDTO( String nombre_persona, boolean delivery,
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
