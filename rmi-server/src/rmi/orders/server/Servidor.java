package rmi.orders.server;

import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ordersmanagement.comun.LineaPedidoDTO;
import com.ordersmanagement.comun.PedidoCrearDTO;
import com.ordersmanagement.comun.PlatoDetallesDTO;

import rmi.orders.api.IServidorCaja;
import rmi.orders.api.IServidorCocina;
import rmi.orders.api.IServidorMesa;

public class Servidor implements IServidorMesa, IServidorCaja, IServidorCocina{

	public Connection connection = null;

	@Override
	public void conectar() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
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
	public void mostrarTablas() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null; 
		stmt = connection.createStatement();
        rs = stmt.executeQuery(
         		"SHOW TABLES;"
         );
         System.out.println("Mostrar Tablas:");
         while(rs.next()) {
         	System.out.println(rs.getString("Tables_in_bwrcph0dvhr5vrn8m5ck"));
         }
	}
	
	@Override
	public String enviarTablas() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null; 
		String envio ="";
		stmt = connection.createStatement();
        rs = stmt.executeQuery(
         		"SHOW TABLES;"
         );
         while(rs.next()) {
         	envio=envio+rs.getString("Tables_in_bwrcph0dvhr5vrn8m5ck")+"\n";
         }
		return envio;
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
	public HashMap<Integer, PedidoCrearDTO> obtenerPedidosTerminados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int terminarPedido(int id_pedido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<Integer, PedidoCrearDTO> obtenerPedidosPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlatoDetallesDTO> obtenerPlatos() throws RemoteException, SQLException {
		System.out.println("--- Operacion obtenerPlatos ---");
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(
				"SELECT ID_COMIDA, NOMBRE, IMAGEN FROM PLATO;"
		);
		
		return writePlatosData(resultSet);
	}
	
	private List<PlatoDetallesDTO> writePlatosData(ResultSet result) throws SQLException {
		ArrayList<PlatoDetallesDTO> platos = new ArrayList<PlatoDetallesDTO>();
		
		while(result.next()) {
			int id_comida = result.getInt("ID_COMIDA");
			String nombre = result.getString("ID_COMIDA");
			Blob imagen = result.getBlob("IMAGEN");
			
			platos.add(new PlatoDetallesDTO(id_comida, nombre, null));
			
			// System.out.println("IMAGEN: " + imagen);
		}
		
		return platos;
	}
	

	@Override
	public int crearPedido(PedidoCrearDTO nuevoPedido) throws SQLException  {
		// TODO Auto-generated method stub
		if(nuevoPedido.getNumbersOfLines() == 0) return -1;
		
		if(nuevoPedido.isDelivery()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO PEDIDO(NOMBRE_PERSONA, DELIVERY, DNI, DIRECCION,"
							+ " CELULAR, PAGO_PENDIENTE, FECHA_PEDIDO, ESTADO_PEDIDO)"+ 
					"VALUES( ?, ?, ?, ?, ?, ?, ?, ?)"
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
			
			preparedStatement.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
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
			preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
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
		
		return 1;
		
	}
	
	

}
