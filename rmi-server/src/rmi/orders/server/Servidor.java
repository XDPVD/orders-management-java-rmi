package rmi.orders.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import com.ordersmanagement.comun.Pedido;
import com.ordersmanagement.comun.Plato;

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
	public HashMap<Integer, Pedido> obtenerPedidosTerminados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int terminarPedido(int id_pedido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<Integer, Pedido> obtenerPedidosPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plato> obtenerPlatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearPedido(Pedido nuevoPedido) {
		// TODO Auto-generated method stub
		
	}
	
	

}
