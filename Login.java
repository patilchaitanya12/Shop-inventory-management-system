import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
class  Login extends JFrame implements ActionListener
{
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	
	public static void main(String[] args)
	{
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setSize(400,350);
		panel.setBackground(new Color(0,0,0,60));
		panel.setBounds(400,175,400,350);
		
		JFrame frame=new JFrame();
		frame.setTitle("Inventory Login");
		frame.setSize(1200,900);
		frame.setLayout (null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		ImageIcon background_img =new ImageIcon("inventory-management.png");
		Image img=background_img.getImage();
		Image temp_img=img.getScaledInstance(900,600,Image.SCALE_SMOOTH);
		background_img=new ImageIcon(temp_img);
		JLabel background=new JLabel("",background_img, JLabel.CENTER);
		background.add(panel);
		background.setBounds(0,0,1200,700);
		frame.add(background);
		frame.setVisible(true);
		
		userLabel=new JLabel("UserName");
		userLabel.setBounds(50,25,100,25);
		userLabel.setFont(new Font("Courier New",Font.BOLD,15));
		panel.add(userLabel);
		
		userText=new JTextField();
		userText.setBounds(50,55,300,50);
		userText.setBackground(new  Color(204,204,204));
		panel.add(userText);
		
		passwordLabel=new JLabel("Password");
		passwordLabel.setBounds(40,120,100,25);
		passwordLabel.setFont(new Font("Courier New",Font.BOLD,15));
		panel.add(passwordLabel);
		
		passwordText=new JPasswordField();
		passwordText.setBounds(50,150,300,50);
		passwordText.setBackground(new  Color(204,204,204));
		panel.add(passwordText);
		
		button=new JButton("Login");
		button.setBounds(125,230,100,50);
		button.setBackground(new Color(160,182,45));
		button.addActionListener(new Login());
		panel.add(button);
		
		//frame.setVisible(true);
		
		success=new JLabel("",JLabel.CENTER);
		success.setBounds(50,275,300,30);
		success.setFont(new Font("Arial",Font.BOLD,15));
		panel.add(success);
		
		//frame.setVisible(true); 
	}



	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) 
	{
		String user=userText.getText();
		String password=passwordText.getText();
		if(e.getSource()==button) {
		if(user.equals("username") && password.equals("123456"))
		{
			System.out.println("1");
			success.setText("Login successful!");
			HomePage ad = new HomePage();
			ad.setSize(900,550);
			ad.setResizable(false);
			ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ad.setVisible(true);
			dispose();
		}
		else
		{
			success.setText("Username or Password is mismatch");
		}
		
		}
	}
	
}