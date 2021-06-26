package rmi.orders.server;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;

import rmi.orders.api.IServidorCocina;
import rmi.orders.api.IServidorMesa;
import rmi.orders.api.IServidorCaja;

import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ordersmanagement.comun.LineaPedidoDTO;
import com.ordersmanagement.comun.PedidoCrearDTO;
import com.ordersmanagement.comun.PedidoDetailsDTO;
import com.ordersmanagement.comun.PlatoDetallesDTO;

public class Servidor implements IServidorMesa, IServidorCaja, IServidorCocina{

	public Connection connection = null;

	@Override
	public void archivarPedido(int id_pedido) throws RemoteException, SQLException{
		// TODO marcarPedidos
		PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE PEDIDO SET ARCHIVADO ='1' WHERE ID_PEDIDO="+id_pedido
		);
		pstmt.executeUpdate();
		
		System.out.println("-- archivarPedido(+id_pedido+) BEGIN --");
		System.out.println("-- archivarPedido(+id_pedido+) END --");
	}
	
	@Override
	public void conectar() throws RemoteException {
		// TODO Auto-generated method stub
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        try {
            connection = DriverManager
                .getConnection("jdbc:mysql://bwrcph0dvhr5vrn8m5ck-mysql.services.clever-cloud.com:3306/bwrcph0dvhr5vrn8m5ck", "uz9kho774z7lvi7g", "1m39fvxAPqVNylU6JMiU");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        } 
	}
	
	@Override
	public void desconectar() throws RemoteException {
		// TODO Auto-generated method stub
		try
        {
            if(connection != null)
                connection.close();
            System.out.println("Connection closed !!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<PedidoDetailsDTO> obtenerPedidosTerminados() throws RemoteException, SQLException{
		this.conectar();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT * FROM PEDIDO WHERE ARCHIVADO=0 AND ESTADO_PEDIDO='terminado';"
		);
		this.desconectar();
		System.out.println("---- obtenerPedidosTerminados() BEGIN ----");
		System.out.println("---- obtenerPedidosTerminados() END ----");
		return writePedidosData(resultSet);
	}

	@Override
	public int terminarPedido(int id_pedido) throws RemoteException, SQLException {
		Date date = new Date();
		
		this.conectar();
		PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE PEDIDO SET ESTADO_PEDIDO ='terminado' WHERE ID_PEDIDO="+id_pedido
		);
		PreparedStatement pstmt2 = connection.prepareStatement(
				"UPDATE PEDIDO SET FECHA_TERMINADO =? WHERE ID_PEDIDO="+id_pedido
		);
		
		pstmt2.setTimestamp(1,new  java.sql.Timestamp(date.getTime()));
		
		pstmt.executeUpdate();
		pstmt2.executeUpdate();
		System.out.println("---- terminarPedido("+id_pedido+") BEGIN ----");
		System.out.println("---- terminarPedido("+id_pedido+") END ----");
		this.desconectar();
		return 0;
	}
	

	@Override
	public List<PedidoDetailsDTO> obtenerPedidosPendientes() throws RemoteException, SQLException {
		this.conectar();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT * FROM PEDIDO WHERE ARCHIVADO=0 AND ESTADO_PEDIDO='pendiente';"
		);
		this.desconectar();
		
		System.out.println("------ obtenerPedidosPendientes() ------");
		return writePedidosData(resultSet);
	}
	
	private List<PedidoDetailsDTO> writePedidosData(ResultSet result) throws RemoteException, SQLException {
		ArrayList<PedidoDetailsDTO> pedidos = new ArrayList<PedidoDetailsDTO>();
		this.conectar();
		while(result.next()) {
			//Llena los detalles del Pedido
			PedidoDetailsDTO detalles;
			int id_pedido = result.getInt("ID_PEDIDO");
			String nombre_persona = result.getString("NOMBRE_PERSONA");
			boolean delivery = result.getBoolean("DELIVERY");
			LocalDateTime fecha_pedido = result.getTimestamp("FECHA_PEDIDO").toLocalDateTime();
			String estado_pedido = result.getString("ESTADO_PEDIDO");
			
			LocalDateTime fecha_terminado;
			if (result.getTimestamp("FECHA_TERMINADO")!=null) {
				fecha_terminado = result.getTimestamp("FECHA_TERMINADO").toLocalDateTime();
			}
			else {
				fecha_terminado = null;
			}
			
			if(!delivery) {
				detalles = new PedidoDetailsDTO(id_pedido, nombre_persona, delivery, fecha_pedido, estado_pedido, fecha_terminado);
			}else {
				int dni = result.getInt("DNI");
				String direccion = result.getString("DIRECCION");
				int celular = result.getInt("CELULAR");
				float pago = result.getFloat("PAGO_PENDIENTE");
				detalles = new PedidoDetailsDTO(id_pedido, nombre_persona, delivery, fecha_pedido, estado_pedido, fecha_terminado,dni,direccion,celular,pago);
			}
			
			//Llena las LineasPedido de cada Pedido
			Statement statement = connection.createStatement();
			Statement statement2 = connection.createStatement();
			ResultSet result2 = statement.executeQuery(
					"SELECT * FROM LINEAPEDIDO WHERE ID_PEDIDO="+id_pedido
			);
			
			while(result2.next()) {
				
				int id_comida = result2.getInt("ID_COMIDA");
				
				int cantidad = result2.getInt("CANTIDAD");
				
				ResultSet result3 = statement2.executeQuery(
						"SELECT NOMBRE FROM PLATO WHERE ID_COMIDA="+id_comida
				);
				
				
				String nombre_comida;
				result3.next();
				nombre_comida = result3.getString("NOMBRE");	
				
				detalles.anadirLineaPedido(new LineaPedidoDTO(id_comida,cantidad, nombre_comida));
			}
			
			//Insertar Pedido a la Lista
			pedidos.add(detalles);
		}
		this.desconectar();
		System.out.println("------ devolviendo Pedidos END ------");
		return pedidos;
	}

	@Override
	public List<PlatoDetallesDTO> obtenerPlatos() throws RemoteException, SQLException {
		this.conectar();
		System.out.println("--- Operacion obtenerPlatos ---");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(
				"SELECT ID_COMIDA, NOMBRE, IMAGEN FROM PLATO;"
		);
		this.desconectar();
		System.out.println("--- Operacion obtenerPlatos END ---");
		return writePlatosData(resultSet);
		
	}
	
	private List<PlatoDetallesDTO> writePlatosData(ResultSet result) throws RemoteException, SQLException {
		ArrayList<PlatoDetallesDTO> platos = new ArrayList<PlatoDetallesDTO>();
		this.conectar();
		while(result.next()) {
			int id_comida = result.getInt("ID_COMIDA");
			String nombre = result.getString("NOMBRE");
			Blob imagen = result.getBlob("IMAGEN");
			
			platos.add(new PlatoDetallesDTO(id_comida, nombre, null));
			
			// System.out.println("IMAGEN: " + imagen);
		}
		this.desconectar();
		return platos;
	}
	

	@Override
	public int crearPedido(PedidoCrearDTO nuevoPedido) throws RemoteException, SQLException  {
		// TODO Auto-generated method stub
		this.conectar();
		if(nuevoPedido.getNumbersOfLines() == 0) return -1;
		
		Date currentDate = new Date();
		
		if(nuevoPedido.isDelivery()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO PEDIDO(NOMBRE_PERSONA, DELIVERY, DNI, DIRECCION,"
							+ " CELULAR, PAGO_PENDIENTE, FECHA_PEDIDO, ESTADO_PEDIDO, ARCHIVADO)"+ 
					"VALUES( ?, ?, ?, ?, ?, ?, ?, ?, 0)"
			);
			
			preparedStatement.setString(1, nuevoPedido.getNombre_persona());
			preparedStatement.setBoolean(2, nuevoPedido.isDelivery());
			
			if(nuevoPedido.getDni() != null) preparedStatement.setInt(3, nuevoPedido.getDni());
			else preparedStatement.setNull(3, java.sql.Types.INTEGER);
			
			preparedStatement.setString(4, nuevoPedido.getDireccion());
			
			if(nuevoPedido.getCelular() != null) preparedStatement.setInt(5, nuevoPedido.getCelular());
			else preparedStatement.setNull(5, java.sql.Types.INTEGER);
			
			if(nuevoPedido.getPago_pendiente() != null) preparedStatement.setFloat(6, nuevoPedido.getPago_pendiente());
			else preparedStatement.setNull(6, java.sql.Types.FLOAT);
			
			preparedStatement.setTimestamp(7, new java.sql.Timestamp(currentDate.getTime()));
			preparedStatement.setString(8, "pendiente");
			preparedStatement.executeUpdate();
			
		}
		else {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO PEDIDO(NOMBRE_PERSONA, DELIVERY, FECHA_PEDIDO, ESTADO_PEDIDO)"+ 
					"VALUES( ?, ?, ?, ? )"
			);
			
			preparedStatement.setString(1, nuevoPedido.getNombre_persona());
			preparedStatement.setBoolean(2, nuevoPedido.isDelivery());
			preparedStatement.setTimestamp(3, new java.sql.Timestamp(currentDate.getTime()));
			preparedStatement.setString(4, "pendiente");
			preparedStatement.executeUpdate();
		}
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(
				"SELECT LAST_INSERT_ID() as last_id FROM PEDIDO;"
		);
		
		resultSet.next();
		int lastId = resultSet.getInt("last_id");
		
		for(LineaPedidoDTO linea: nuevoPedido.getLineasPedido()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO LINEAPEDIDO(ID_PEDIDO, ID_COMIDA, CANTIDAD)"+ 
					"VALUES( ?, ?, ?)"
			);
			
			preparedStatement.setInt(1, lastId);
			preparedStatement.setInt(2, linea.getId_comida());
			preparedStatement.setInt(3, linea.getCantidad());
			preparedStatement.executeUpdate();
			
		}
		
		System.out.println("---- crearPedido() BEGIN ----");
		System.out.println(nuevoPedido.toString());
		System.out.println("---- crearPedido() END ----");
		this.desconectar();
		return 1;
		
	}

	
	
}
