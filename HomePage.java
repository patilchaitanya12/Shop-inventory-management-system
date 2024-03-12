import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private static final long serialVersionUID = -5522324045791735228L;
	private JPanel contentPane;
	public static void main(String[] args) {
		new HomePage();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HomePage");
		lblNewLabel.setFont(new Font("Wide Latin", Font.PLAIN, 49));
		lblNewLabel.setBounds(165, 10, 482, 45);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Inventory");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory ad = new Inventory();
				ad.setSize(750,600);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();

			}
		});
		btnNewButton.setBounds(97, 140, 132, 45);
		contentPane.add(btnNewButton);
		
		JButton btnStock = new JButton("Stock");
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock ad = new Stock();
				ad.setSize(900,600);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();
			
			}
		});
		btnStock.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnStock.setBounds(97, 212, 132, 45);
		contentPane.add(btnStock);
		
		JButton btnBill = new JButton("Report");
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report ad = new Report();
				ad.setSize(1000,1000);
				ad.setResizable(false);
				ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ad.setVisible(true);
				dispose();
			}
		});
		btnBill.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnBill.setBounds(97, 286, 132, 45);
		contentPane.add(btnBill);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(ABORT);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnLogout.setBounds(617, 334, 132, 34);
		contentPane.add(btnLogout);
		
		
		ImageIcon background_img =new ImageIcon("Home.png");
		Image img=background_img.getImage();
		Image temp_img=img.getScaledInstance(350,250,Image.SCALE_SMOOTH);
		background_img=new ImageIcon(temp_img);
		JLabel background = new JLabel(background_img);
		background.setBounds(239, 140, 377, 227);
		contentPane.add(background);
	}
}
