package com.ordersmanagement.comun;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDetailsDTO implements Serializable {
	
	/**
	 * Ver antes PedidoCrearDTO...
	 * 
	 * PedidoDetailsDTO
	 * Esta clase sirve mas para crear objetos que contengan la informacion de los pedidos  que vengan del backend.
	 * 
	 * Solo hay getters.
	 * 
	 * La diferencia principal que hay es la aparacion del atributo id_pedido.
	 * esto se puede solicitar mediante getId_pedido()
	 * 
	 * Ver luego LineaPedidoDTO.java
	 * 
	 */
	private static final long serialVersionUID = 4231L;

	private int id_pedido;
	
	private String nombre_persona;
	
	private boolean delivery;
	
	private Integer dni;
	
	private String direccion;
		
	private Integer celular;
	
	private Float pago_pendiente;
	
	private LocalDateTime fecha_pedido; 
	private String estado_pedido;
	private LocalDateTime fecha_terminado;
	
	private boolean archivado;
	
	private List<LineaPedidoDTO> lineasPedido;  
			
	public PedidoDetailsDTO(int id_pedido, String nombre_persona, boolean delivery, 
			LocalDateTime fecha_pedido, String estado_pedido, LocalDateTime fecha_terminado) 
	{
		this.id_pedido = id_pedido;
		
		this.nombre_persona = nombre_persona;
		this.delivery = delivery;
		
		this.fecha_pedido = fecha_pedido;
		this.estado_pedido = estado_pedido;
		this.fecha_terminado = fecha_terminado;
		
		this.lineasPedido = new ArrayList<LineaPedidoDTO>();
		
	}
	
	public PedidoDetailsDTO( int id_pedido, String nombre_persona, boolean delivery,
			LocalDateTime fecha_pedido, String estado_pedido, LocalDateTime fecha_terminado,
			Integer dni, String direccion, Integer celular, float pago_pendiente) 
	{
		
		this(id_pedido, nombre_persona, delivery,fecha_pedido,estado_pedido,fecha_terminado);
		
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

	public int getId_pedido() {
		return id_pedido;
	}

	public LocalDateTime getFecha_pedido() {
		return fecha_pedido;
	}

	public String getEstado_pedido() {
		return estado_pedido;
	}
	
	public boolean isArchivado() {
		return this.archivado;
	}

	public LocalDateTime getFecha_terminado() {
		return fecha_terminado;
	}
	
}
