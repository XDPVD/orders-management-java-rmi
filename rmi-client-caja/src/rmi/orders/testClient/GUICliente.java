package rmi.orders.testClient;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ordersmanagement.comun.LineaPedidoDTO;
import com.ordersmanagement.comun.PedidoCrearDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class GUICliente extends JFrame{

	private JPanel panel;
	private JTextField txtName, txtAddress, txtDni, txtMobile, txtBalance, txtDate, txtIdFood, txtQuantity;
	JRadioButton rdbtnDeliveryYes, rdbtnDeliveryNo;
	private JButton btnAddOrder, btnSendOrder;
	private DefaultTableModel modelOrderLine, modelFoodList;
	private JTable tableOrderLine, tableFoodList;
	JScrollPane scrollOrderLine, scrollFoodList; 


	/**
	 * Launch the application.
	 */

	public GUICliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(200, 200, 850, 700);
		setLocationRelativeTo(null);
		startComponents();
		setVisible(true);
	}
		
	public void startComponents() {
		setPanel();
		setLabel();
		setText();
		setRadioButtons();
		setButton();
		setTable();
	}
		
	public void setPanel() {
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
	}
		
	public void setLabel() {
		
		//-------------------------------------------------------------- ORDER ----------------------------------------------------------------------
		
		JLabel lblOrder = new JLabel("Pedido");
		lblOrder.setBounds(150, 30, 115, 30);
		lblOrder.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblOrder);
		
		JLabel lblName = new JLabel("Nombre del Cliente:");
		lblName.setBounds(40, 90, 115, 14);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblName);
		
		JLabel lblDelivery = new JLabel("Delivery:");
		lblDelivery.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDelivery.setBounds(40, 120, 115, 14);
		lblName.setAlignmentX(RIGHT_ALIGNMENT);
		panel.add(lblDelivery);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDNI.setBounds(40, 150, 115, 14);
		lblName.setAlignmentX(RIGHT_ALIGNMENT);
		panel.add(lblDNI);
		
		JLabel lblAddress = new JLabel("Direccion:");
		lblAddress.setBounds(40, 180, 115, 14);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblAddress);
		
		JLabel lblMobile = new JLabel("Celular:");
		lblMobile.setBounds(40, 210, 115, 14);
		lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblMobile);
		
		JLabel lblBalance = new JLabel("Saldo Pendiente:");
		lblBalance.setBounds(40, 240, 115, 14);
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblBalance);
		
		JLabel lblDate = new JLabel("Fecha de Pedido:");
		lblDate.setBounds(40, 270, 115, 14);
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDate);		
		
		
		//------------------------------------------------------ ORDER LINE ----------------------------------------------------------------------
		
		JLabel lblOrderLine = new JLabel("Linea de Pedido");
		lblOrderLine.setBounds(105, 320, 240, 30);
		lblOrderLine.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblOrderLine);
		
		JLabel lblIdFood = new JLabel("Id de Comida:");
		lblIdFood.setBounds(40, 380, 90, 14);
		lblIdFood.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblIdFood);
        
        JLabel lblQuantity = new JLabel("Cantidad:");
        lblQuantity.setBounds(40, 410, 90, 14);
        lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(lblQuantity);
		//------------------------------------------------------ FOOD LIST ----------------------------------------------------------------------
		
		JLabel lblFoodList = new JLabel("Lista de Comida");
		lblFoodList.setBounds(490, 30, 220, 30);
		lblFoodList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblFoodList);
	}
	
	public void setText() {		
		
		//-------------------------------------------------------------- ORDER ----------------------------------------------------------------------
		
		txtName = new JTextField();
		txtName.setBounds(160, 87, 150, 20);
		txtName.setColumns(10);
		panel.add(txtName);
		
		txtDni = new JTextField();
		txtDni.setBounds(160, 147, 150, 20);
		txtDni.setColumns(10);
		panel.add(txtDni);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(160, 177, 150, 20);
		panel.add(txtAddress);
		
		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		txtMobile.setBounds(160, 207, 150, 20);
		panel.add(txtMobile);
		
		txtBalance = new JTextField();
		txtBalance.setColumns(10);
		txtBalance.setBounds(160, 237, 150, 20);
		panel.add(txtBalance);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(160, 267, 150, 20);
		panel.add(txtDate);
		
		//-------------------------------------------------------------- ORDER-FOOD ----------------------------------------------------------------------
		
		 txtIdFood = new JTextField();
		 txtIdFood.setColumns(10);
		 txtIdFood.setBounds(135, 377, 86, 20);
	     panel.add(txtIdFood);
	        
	     txtQuantity = new JTextField();
	     txtQuantity.setColumns(10);
	     txtQuantity.setBounds(135, 407, 86, 20);
	     panel.add(txtQuantity);
	}
	
	public void setButton() {
		
		//------------------------------------------------------ ORDER LINE ----------------------------------------------------------------------
		
		 
		btnAddOrder = new JButton("Agregar");
		btnAddOrder.setBounds(238, 374, 110, 50);
		panel.add(btnAddOrder);
		
		btnSendOrder = new JButton("Enviar Pedido");
		btnSendOrder.setBounds(100, 600, 210, 40);
		panel.add(btnSendOrder);
		
	}
	
	public void setTable() {
		
		//------------------------------------------------------ ORDER LINE ----------------------------------------------------------------------
		
		modelOrderLine = new DefaultTableModel();
		modelOrderLine.addColumn("Id de Comida");
		modelOrderLine.addColumn("Cantidad");
        
        String []comida01={"01","2"};  
        String []comida02={"02","3"};
        modelOrderLine.addRow(comida01);
        modelOrderLine.addRow(comida02);
        
        tableOrderLine = new JTable(modelOrderLine);
        tableOrderLine.setBounds(34, 480, 350, 100);
        panel.add(tableOrderLine);
        
         scrollOrderLine = new JScrollPane(tableOrderLine,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollOrderLine.setBounds(40, 440, 340, 150);
        panel.add(scrollOrderLine);
		
		
		
		
		//------------------------------------------------------ FOOD LIST ----------------------------------------------------------------------
		
        modelFoodList = new DefaultTableModel();
		modelFoodList.addColumn("Id de Comida");
		modelFoodList.addColumn("Nombre");
		modelFoodList.addColumn("Imagen");
        
        String []comida1={"01","Pan con tortilla","*inserte pan con tortilla*"};
        String []comida2={"02","Pan con tortilla","*inserte pan con tortilla*"};
        modelFoodList.addRow(comida1);
        modelFoodList.addRow(comida2);
        
        tableFoodList = new JTable(modelFoodList);
        tableFoodList.setBounds(400, 120, 350, 600);
        panel.add(tableFoodList);
        
        scrollFoodList = new JScrollPane(tableFoodList,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFoodList.setBounds(405, 89, 395, 521);
        panel.add(scrollFoodList);        
	}
	
	public void setRadioButtons() {
		
		//------------------------------------------------------ ORDER ----------------------------------------------------------------------
		
		rdbtnDeliveryYes = new JRadioButton("Si");
		rdbtnDeliveryYes.setBounds(170, 117, 50, 23);
        panel.add(rdbtnDeliveryYes);
        
        rdbtnDeliveryNo = new JRadioButton("No");
        rdbtnDeliveryNo.setBounds(230, 117, 50, 23);
        panel.add(rdbtnDeliveryNo);
        
        ButtonGroup groupDelivery = new ButtonGroup();
		groupDelivery.add(rdbtnDeliveryYes);
		groupDelivery.add(rdbtnDeliveryNo);
	}
	
	public PedidoCrearDTO getPedidoGUI() {
		PedidoCrearDTO pedido = null;
		
		if(rdbtnDeliveryYes.isSelected()) {
			if(txtAddress.getText().equals("")
			|| txtDni.getText().equals("")
			|| txtMobile.getText().equals("")
			|| txtBalance.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos");
			}
			else {
				pedido = new PedidoCrearDTO(txtName.getText(), false,
												Integer.parseInt(txtDni.getText()),
											   txtAddress.getText(), 
											   Integer.parseInt(txtMobile.getText()),
											   Float.parseFloat(txtBalance.getText()));
			}
		}
		else if(rdbtnDeliveryNo.isSelected()) {
			pedido = new PedidoCrearDTO(txtName.getText(), false);
		}
		
		return pedido;

	}
	
	public LineaPedidoDTO getLineaPedidoGUI() {
		LineaPedidoDTO lineaPedido = null;
		if(txtIdFood.getText().equals("")
		  || txtQuantity.getText().equals("")) {
			lineaPedido = new LineaPedidoDTO(Integer.parseInt(txtIdFood.getText()),
											Integer.parseInt(txtQuantity.getText()));
		}
		return lineaPedido;
	}
	
	public JButton getBtnAddOrder() {
		return btnAddOrder;
	}
	
	public JButton getBtnSendOrder() {
		return btnSendOrder;
	}
	
	public void addAL(ActionListener action) {
		btnAddOrder.addActionListener(action);
		btnSendOrder.addActionListener(action);
	}
	
}
