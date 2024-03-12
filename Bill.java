import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class Bill extends JFrame {

	public JPanel contentPane;
	public JScrollPane scrollPane1;
	private JTable table;
	Connection con;
	ResultSet rs;
	int total=0;
	public JTextField amount;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Bill() {
		
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
		setBounds(100, 100, 871, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bill");
		lblNewLabel.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 44));
		lblNewLabel.setBounds(296, 10, 197, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Inventory ad = new Inventory();
				ad.setSize(750,550);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(563, 450, 101, 32);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 130, 513, 286);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"no", "product_name", "price", "quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnNewButton_1 = new JButton("Click to generate Bill");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					PreparedStatement st=con.prepareStatement("select * from Bill");
					rs = st.executeQuery();
					while (rs.next()) {
						
						String no = Integer.toString(rs.getInt("no"));
						String product = rs.getString("product_name");
						String price = rs.getString("price");	
						String quantity = rs.getString("quantity");
						
						
						
						String data [] = {no,product,price,quantity};
						
						DefaultTableModel tb1 = (DefaultTableModel)table.getModel();
						
						tb1.addRow(data);
						
						int p = Integer.parseInt(price);
						int q = Integer.parseInt(quantity);
						total=total+(p*q);
						String Gtotal =""+total;
						amount.setText(Gtotal);
						
					}
					st = con.prepareStatement("delete from bill");
					st.executeUpdate();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(321, 95, 160, 32);
		contentPane.add(btnNewButton_1);
		
		JLabel TOTAL = new JLabel("Total amount");
		TOTAL.setFont(new Font("Tahoma", Font.BOLD, 12));
		TOTAL.setBounds(242, 468, 101, 32);
		contentPane.add(TOTAL);
		
		amount = new JTextField();
		amount.setFont(new Font("Tahoma", Font.BOLD, 12));
		amount.setEditable(false);
		amount.setBounds(344, 476, 96, 19);
		contentPane.add(amount);
		amount.setColumns(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(91);
	}
}
