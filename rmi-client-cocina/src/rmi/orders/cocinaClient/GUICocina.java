package rmi.orders.cocinaClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUICocina extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private DefaultTableModel modelOrderLine;
	private JTable tableOrderLine;
	private JScrollPane scrollOrderLine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICocina frame = new GUICocina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUICocina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTable();
		
		JLabel lblNewLabel = new JLabel("Linea de Pedidos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(73, 41, 287, 29);
		contentPane.add(lblNewLabel);
		
	}
	
	public void setTable() {
		modelOrderLine = new DefaultTableModel();
		modelOrderLine.addColumn("Id de Pedido");
		modelOrderLine.addColumn("Id de Comida");
		modelOrderLine.addColumn("Cantidad");
        
        tableOrderLine = new JTable(modelOrderLine);
        tableOrderLine.setBounds(51, 95, 350, 100);
        contentPane.add(tableOrderLine);
        
        scrollOrderLine = new JScrollPane(tableOrderLine,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollOrderLine.setBounds(51, 95, 340, 150);
        contentPane.add(scrollOrderLine);
        
	}
}
