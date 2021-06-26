package rmi.orders.cocinaClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ordersmanagement.comun.LineaPedidoDTO;
import com.ordersmanagement.comun.PedidoDetailsDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUICocina extends JFrame {

	private JPanel contentPane;
	private JButton btnActualizar, btnTerminar;
	private DefaultTableModel modelOrderLine;
	private JTable tableOrderLine;
	private JScrollPane scrollOrderLine;
	private JTextField txtTerminar;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public GUICocina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		setResizable(false);
		startComponents();
		setVisible(true);
	}
	
	public void startComponents() {
		setPanel();
		setTable();
		setLabel();
		setTextField();
		setButton();
		
	}
	
	public void setPanel() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void setLabel() {
		
		JLabel lblNewLabel = new JLabel("Linea de Pedidos Pendientes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(25, 42, 381, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblTerminar = new JLabel("Terminar Pedido:");
		lblTerminar.setBounds(51, 270, 104, 14);
		contentPane.add(lblTerminar);
		
	}
	
	public void setTextField() {
		txtTerminar = new JTextField();
		txtTerminar.setBounds(157, 267, 86, 20);
		contentPane.add(txtTerminar);
		txtTerminar.setColumns(10);
		
	}
	
	public void setButton() {
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(151, 320, 108, 40);
		contentPane.add(btnActualizar);

		btnTerminar = new JButton("Terminar");
		btnTerminar.setBounds(271, 266, 89, 23);
		contentPane.add(btnTerminar);
		
	}
	
	public void setTable() {
		
        tableOrderLine = new JTable(modelOrderLine);
        tableOrderLine.setBounds(51, 95, 350, 100);
        contentPane.add(tableOrderLine);
        
        scrollOrderLine = new JScrollPane(tableOrderLine,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollOrderLine.setBounds(51, 95, 340, 150);
        contentPane.add(scrollOrderLine);
        
	}

	
	public void addPedidos(List<PedidoDetailsDTO> listaPedidos) {
		modelOrderLine = new DefaultTableModel();
		modelOrderLine.addColumn("Id de Pedido");
		modelOrderLine.addColumn("Nombre Comida");
		modelOrderLine.addColumn("Cantidad");
        
		
		for(PedidoDetailsDTO pedido: listaPedidos) {
			for(LineaPedidoDTO lineaPedido: pedido.getLineasPedido()) {
				modelOrderLine.addRow(new Object[] {
						String.valueOf(pedido.getId_pedido()),
						lineaPedido.getNombre_comida(),
						String.valueOf(lineaPedido.getCantidad())
				});
			}
		}
		setTable();
	}
	
	public JButton getBtnActualizar() {
		return this.btnActualizar;
	}
	
	public JButton getBtnTerminar() {
		return this.btnTerminar;
	}
	
	public JTextField getTxtTerminar() {
		return this.txtTerminar;
	}
}
