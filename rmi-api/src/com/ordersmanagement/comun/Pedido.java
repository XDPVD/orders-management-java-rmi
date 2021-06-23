package com.ordersmanagement.comun;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4231L;

	
	
	private String nombre_persona;
	
	private Date fecha_pedido;
	
	private Date fecha_terminado;
	
	private String estado_pedido;
	
	private boolean delivery;
	
	private int dni;
	
	private String direccion;
		
	private int celular;
	
	private float pago_pendiente;
	
	private List<LineaPedido> lineasPedido;  
			
	public Pedido( 
			String nombre_persona, 
			Date fecha_pedido, 
			Date fecha_terminado, 
			String estado_pedido, 
			boolean delivery,
			int dni,
			String direccion, 
			int celular, 
			float pago_pendiente
	) {
		this.nombre_persona = nombre_persona;
		this.fecha_pedido = fecha_pedido;
		this.fecha_terminado = fecha_terminado;
		this.estado_pedido = estado_pedido;
		this.delivery = delivery;
		
		this.lineasPedido = new ArrayList<LineaPedido>();
		
		if(delivery) this.setDescDelivery(dni, direccion, celular, pago_pendiente);
		
	}
	
	public void setDescDelivery(int dni, String direccion, 
			int celular, float pago_pendiente) {
		
		this.dni = dni;
		this.direccion = direccion;
		this.celular = celular;
		this.pago_pendiente = pago_pendiente;
		
	}
	
	public void anadirLineaPedido(LineaPedido linea) {
		lineasPedido.add(linea);
	}
	
	public List<LineaPedido> getLineaPedido (){
		return this.lineasPedido;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public Date getFecha_pedido() {
		return fecha_pedido;
	}


	public Date getFecha_terminado() {
		return fecha_terminado;
	}


	public String getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(String nuevoEstado) {
		this.estado_pedido = nuevoEstado;
	}

	public boolean isDelivery() {
		return delivery;
	}


	public int getDni() {
		return dni;
	}

	public String getDireccion() {
		return direccion;
	}


	public int getCelular() {
		return celular;
	}

	public float getPago_pendiente() {
		return pago_pendiente;
	}
	
}
