import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Report extends JFrame {

	private JPanel contentPane;
	 public Connection con;
	 private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
		
		
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
		setBounds(100, 100, 814, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Report = new JLabel("Report");
		Report.setHorizontalAlignment(SwingConstants.CENTER);
		Report.setFont(new Font("Wide Latin", Font.BOLD | Font.ITALIC, 24));
		Report.setBounds(137, 43, 449, 55);
		contentPane.add(Report);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(168, 156, 449, 266);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NO", "product_name", "price", "quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(82);
		scrollPane.setViewportView(table);
		
		JButton report = new JButton("Report");
		report.setFont(new Font("Tahoma", Font.BOLD, 12));
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					ResultSet rs=null;
					PreparedStatement st=con.prepareStatement("select * from report");
					rs = st.executeQuery();
					while (rs.next()) {
						
						String no1 = Integer.toString(rs.getInt("no"));
						String product1 = rs.getString("product_name");
						String price1 = rs.getString("price");	
						String quantity1 = rs.getString("quantity");
						
						
						
						String data [] = {no1,product1,price1,quantity1};
						
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
		report.setBounds(429, 457, 85, 21);
		contentPane.add(report);
		
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HomePage ad = new HomePage();
				ad.setSize(1000,1000);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();
				
				
			}
		});
		Back.setFont(new Font("Tahoma", Font.BOLD, 12));
		Back.setBounds(524, 457, 85, 21);
		contentPane.add(Back);
		
		JLabel lblNewLabel = new JLabel("Report till today");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 108, 368, 38);
		contentPane.add(lblNewLabel);
	}
}
