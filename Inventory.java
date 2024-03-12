import java.awt.EventQueue;
import java.awt.*;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Inventory extends JFrame {

	
	public JTextField txtPd;
	Connection con;
	ResultSet rs;
	JLabel j1, j2, j3, j4; 
	public JTable table;
	String Total;
	private JTextField qty;
	
	public static void main(String[] args) throws NumberFormatException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inventory() {
		try 
		
		{

			Class.forName("org.postgresql.Driver"); 
			con = DriverManager.getConnection("jdbc:postgresql://localhost/shop","postgres","postgres"); 
			} 

		catch(Exception e) 

		{ 
			System.out.println("Error"+e.getMessage());
		} 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 530);
		
		JLabel Inventory = new JLabel("Inventory");
		Inventory.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 38));
		Inventory.setBounds(194, 27, 366, 56);
		getContentPane().add(Inventory);
		
		txtPd = new JTextField();
		txtPd.setBounds(194, 121, 112, 30);
		getContentPane().add(txtPd);
		txtPd.setColumns(10);
		
		JButton Add = new JButton("Add");
		Add.setFont(new Font("Tahoma", Font.BOLD, 12));
		Add.setBounds(337, 121, 96, 26);
		getContentPane().add(Add);
		
		JLabel lblNewLabel = new JLabel("Product_name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(76, 119, 112, 30);
		getContentPane().add(lblNewLabel);
		getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 206, 483, 212);
		getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Product_name", "price", "quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage ad = new HomePage();
				ad.setSize(900,550);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();
			}
		});
		Back.setFont(new Font("Tahoma", Font.BOLD, 12));
		Back.setBounds(512, 447, 85, 21);
		getContentPane().add(Back);
		
		JButton Bill = new JButton("Bill");
		Bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bill ad = new Bill();
				ad.setSize(900,550);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();
			
			}
		});
		Bill.setFont(new Font("Tahoma", Font.BOLD, 12));
		Bill.setBounds(410, 448, 85, 21);
		getContentPane().add(Bill);
		
		JLabel lblQuantity = new JLabel("quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantity.setBounds(76, 166, 112, 30);
		getContentPane().add(lblQuantity);
		
		qty = new JTextField();
		qty.setColumns(10);
		qty.setBounds(194, 166, 112, 30);
		getContentPane().add(qty);
		setVisible(true);
		
		
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println(qty.getText());
				
				try {
					
					String quantity;
					int QTY=0;
					String pd;
					PreparedStatement st=con.prepareStatement("select * from stock");
					rs = st.executeQuery();
					
					while (rs.next()) {
						
						String no = Integer.toString(rs.getInt("no"));
						String product = rs.getString("product_name");
						String price = rs.getString("price");	
						int qy = rs.getInt("quantity");
						if(qty.getText().isEmpty() || txtPd.getText().isEmpty()) {
							
							JOptionPane.showMessageDialog(null,"enter a product name and quantity!!");
							Inventory ad = new Inventory();
							ad.setSize(850,550);
							ad.setResizable(false);
							ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							ad.setVisible(true);
							dispose();
							break;
						}
						else if(QTY>qy){
							JOptionPane.showMessageDialog(null,"Quantity is out of stock");
								txtPd.setText("");
								qty.setText("");
								Inventory ad = new Inventory();
								ad.setSize(900,550);
								ad.setResizable(false);
								ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								ad.setVisible(true);
								dispose();
								break;
						}
						
						else if(!(qty.getText().isEmpty() || txtPd.getText().isEmpty())) {
						
							 quantity=(String)qty.getText();
							 QTY=Integer.parseInt(quantity);
							 pd = txtPd.getText();
							System.out.println(quantity);
						
						
						
						 if (product.equals(txtPd.getText()) || (QTY>qy)) {	
						
						String data [] = {no,product,price,quantity};
						
						DefaultTableModel tb1 = (DefaultTableModel)table.getModel();
						
						tb1.addRow(data);
						
						PreparedStatement st1=con.prepareStatement("UPDATE stock SET quantity=quantity-? WHERE product_name=?");
						st1.setInt(1,QTY);
						st1.setString(2,pd);
						st1.executeUpdate(); 
					
						
						int num = 0;
						PreparedStatement getMax = con.prepareStatement("select max(no) from bill");
						ResultSet rs1 = getMax.executeQuery();
						while(rs1.next()){
							num = rs1.getInt("max");
						}
						
						PreparedStatement ps1 = con.prepareStatement("insert into bill values(?,?,?,?)");
						ps1.setInt(1,num+1);
						ps1.setString(2,product);
						ps1.setString(3,price);
						ps1.setString(4, quantity);
						ps1.executeUpdate();
						
						
						int num1 = 0;
						PreparedStatement getMax1 = con.prepareStatement("select max(no) from report");
						ResultSet rs2 = getMax1.executeQuery();
						while(rs2.next()){
							num1 = rs2.getInt("max");
						}
						
						int cnt=0; 
						ArrayList<String>arr = new ArrayList<String>();
						String temp =null;
						Statement ps3 = con.createStatement();
						ResultSet rs3 = ps3.executeQuery("select * from report");
						while(rs3.next()) {
						 temp=rs3.getString("product_name");
						arr.add(temp);
						cnt++;
						
						}
						System.out.println(arr);
						System.out.println(cnt);
						
						if(arr.contains(product)){
							System.out.println("123");

							PreparedStatement st5=con.prepareStatement("UPDATE report SET quantity=quantity+? WHERE product_name=?");
							st5.setInt(1,QTY);
							st5.setString(2,product);
							st5.executeUpdate(); 
							
						}
						else {
							PreparedStatement ps2 = con.prepareStatement("insert into report values(?,?,?,?)");
							ps2.setInt(1,num1+1);
							ps2.setString(2,product);
							ps2.setString(3,price);
							ps2.setInt(4, QTY);
							ps2.executeUpdate();
							
						}
						}
						}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

				
			}
		});	
		}
	}

