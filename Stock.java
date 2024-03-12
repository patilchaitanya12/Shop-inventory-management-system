import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Stock extends JFrame {

	private JPanel contentPane;
	private JTextField t1,t2,t3;
	private JTable table;
	private Connection con;
	private JTextField textField;
	private JTextField textField_1;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock frame = new Stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("serial")
	public Stock() {
		
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
		setBounds(100, 100, 841, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Stock");
		lblNewLabel.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 42));
		lblNewLabel.setBounds(264, 35, 247, 38);
		contentPane.add(lblNewLabel);
		
		t1 = new JTextField();
		t1.setColumns(10);
		t1.setBounds(168, 180, 102, 19);
		contentPane.add(t1);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(168, 234, 102, 19);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(168, 283, 102, 19);
		contentPane.add(t3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product_name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(20, 177, 138, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(94, 231, 64, 19);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("quantity");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(76, 283, 82, 19);
		contentPane.add(lblNewLabel_1_1_2);
		
		JButton Insert = new JButton("Add Product");
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{  
					String Product_name=t1.getText(); 
					String Price=t2.getText();;
					String quantity=t3.getText();
					Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/shop","postgres","postgres"); 
					System.out.println("1");
					PreparedStatement st=conn.prepareStatement("insert into stock (product_name,price,quantity) values(?,?,?)"); 
					st.setString(1,Product_name); 
					st.setString(2,Price);
					int qty =Integer.parseInt(quantity);
					st.setInt(3,qty);
					st.executeUpdate(); 
					st.close(); 
					JOptionPane.showMessageDialog(null,"Inserted!!"); 
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
 
				
			}
		});
		Insert.setFont(new Font("Tahoma", Font.BOLD, 8));
		Insert.setBounds(76, 331, 97, 27);
		contentPane.add(Insert);
		
		JButton Display = new JButton("View Stock");
		Display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				ResultSet rs=null;
				try 
				{
					PreparedStatement st=con.prepareStatement("select * from stock");
					rs = st.executeQuery();
					while (rs.next()) 
					{
						String no = Integer.toString(rs.getInt("no"));
						String product = rs.getString("product_name");
						String price = rs.getString("price");	
						String quantity = rs.getString("quantity");
						
						
						System.out.print("2");
						
						String data [] = {no,product,price,quantity};
						
						DefaultTableModel tb1 = (DefaultTableModel)table.getModel();
						
						tb1.addRow(data);

						}						
					}
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		});
		Display.setFont(new Font("Tahoma", Font.BOLD, 10));
		Display.setBounds(181, 331, 102, 27);
		contentPane.add(Display);
		
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
		Back.setBounds(693, 435, 102, 27);
		contentPane.add(Back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 111, 517, 207);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Produc_name", "Price", "quantity"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		textField = new JTextField();
		textField.setBounds(539, 382, 123, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton Update = new JButton("Update");
		Update.setFont(new Font("Tahoma", Font.BOLD, 12));
		Update.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				ResultSet rs2=null;
				try 
				{ 
					String Product_name=textField_1.getText();
					String quantity=textField.getText();
					int QTY=Integer.parseInt(quantity);
					Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/shop","postgres","postgres"); 
					System.out.println(QTY);
					PreparedStatement st1=conn.prepareStatement("UPDATE stock SET quantity=quantity+? WHERE product_name=?");
					st1.setInt(1,QTY);
					st1.setString(2,Product_name);
					st1.executeUpdate(); 
					st1.close(); 
					JOptionPane.showMessageDialog(null,"Updated!!"); 
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		Update.setBounds(560, 439, 102, 21);
		contentPane.add(Update);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(539, 345, 123, 27);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Product_name");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_3.setBounds(391, 353, 138, 19);
		contentPane.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("quantity");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2_1.setBounds(447, 383, 82, 19);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JButton btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table.setModel(new DefaultTableModel(null,new String[] {
						"No", "Produc_name", "Price", "quantity"
				}));
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(181, 368, 102, 27);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Enter product details");
		lblNewLabel_1.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(55, 119, 215, 27);
		contentPane.add(lblNewLabel_1);
	}
}