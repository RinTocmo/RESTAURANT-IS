import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class LoginFrame extends JFrame {
	
	private Image img_resto = new ImageIcon(LoginFrame.class.getResource("res/resto.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon(LoginFrame.class.getResource("res/user.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_pass = new ImageIcon(LoginFrame.class.getResource("res/pass.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passField;
	private JLabel lblIconUser;
	private JLabel lblIconPass;
	private JButton btnLoginButton;
	private JLabel lblWarning = new JLabel("");
	private JLabel lblRestoIcon;
	private JLabel lblRestoName;
	private JLabel lblLogExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.setLocation(-182, -172);
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(255, 255, 255));
		panelUser.setBorder(null);
		panelUser.setBounds(160, 172, 281, 40);
		contentPane.add(panelUser);
		panelUser.setLayout(null);
		
		//Username
		userField = new JTextField();
		userField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(userField.getText().equals("Username")) {
					userField.setText("");
				}
				else {
					userField.selectAll();
				}
			
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(userField.getText().equals("")) {
					userField.setText("Username");
				}			
			}
		});
		userField.setBorder(null);
		userField.setText("Username");
		userField.setBounds(10, 11, 170, 20);
		panelUser.add(userField);
		userField.setColumns(10);
		
		//User Icon
		lblIconUser = new JLabel("");
		lblIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUser.setBounds(235, 0, 46, 40);
		lblIconUser.setIcon(new ImageIcon(img_user));
		panelUser.add(lblIconUser);
		
		JPanel panelPass = new JPanel();
		panelPass.setBackground(new Color(255, 255, 255));
		panelPass.setLayout(null);
		panelPass.setBorder(null);
		panelPass.setBounds(160, 228, 281, 40);
		contentPane.add(panelPass);
		
		//Password
		passField = new JPasswordField();
		passField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(passField.getText().equals("Password")) {
					passField.setEchoChar('●');
					passField.setText("");
				}
				else {
					passField.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(passField.getText().equals("")) {
					passField.setText("Password");
					passField.setEchoChar((char)0);
				}
			}
		});
		passField.setBorder(null);
		passField.setEchoChar((char)0);
		passField.setText("Password");
		passField.setBounds(10, 11, 170, 20);
		panelPass.add(passField);
		
		//Pass Icon
		lblIconPass = new JLabel("");
		lblIconPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPass.setBounds(235, 0, 46, 40);
		lblIconPass.setIcon(new ImageIcon(img_pass));
		panelPass.add(lblIconPass);
		
		btnLoginButton = new JButton("LOGIN");
		btnLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(userField.getText().equals("admin") && passField.getText().equals("admin123")) {
					lblWarning.setText("");
					RestaurantIS rIS = new RestaurantIS();
					rIS.show();
					
					dispose();
					//JOptionPane.showMessageDialog(null, "Login Successful");
				}
				else if(userField.getText().equals("") || userField.getText().equals("Username") || 
						passField.getText().equals("") || passField.getText().equals("Password")) {
					lblWarning.setText("Please input the requirements!");
				}
				else {
					lblWarning.setText("Username and Password didn't match!");
				}
			}
		});
		
		//btnLoginButton.setBackground(new Color(169, 169, 169));
		btnLoginButton.setForeground(new Color(0, 128, 128));
		btnLoginButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnLoginButton.setBounds(250, 279, 99, 32);
		contentPane.add(btnLoginButton);
		
		lblWarning.setForeground(new Color(255, 0, 0));
		lblWarning.setFont(new Font("Arial", Font.BOLD, 9));
		lblWarning.setBounds(160, 159, 281, 14);
		contentPane.add(lblWarning);
		
		lblRestoIcon = new JLabel("");
		lblRestoIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoIcon.setBounds(0, 24, 600, 137);
		contentPane.add(lblRestoIcon);
		lblRestoIcon.setIcon(new ImageIcon(img_resto));
		

        lblRestoName = new JLabel("KAHIT ANO RESTO");
		lblRestoName.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoName.setForeground(new Color(112, 128, 144));
		lblRestoName.setFont(new Font("Arial", Font.BOLD, 14));
		lblRestoName.setBounds(0, 132, 600, 29);
		contentPane.add(lblRestoName);
		
		lblLogExit = new JLabel("X");
		lblLogExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you user you want to close this application?", "Confirmation", JOptionPane.YES_OPTION)==0) {
					LoginFrame.this.dispose();
				}
				else {
					LoginFrame.this.show();
				}				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblLogExit.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLogExit.setForeground(Color.BLACK);
			}
			
		});
		lblLogExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogExit.setFont(new Font("Arial", Font.BOLD, 14));
		lblLogExit.setBounds(563, 0, 37, 32);
		contentPane.add(lblLogExit);
		setLocationRelativeTo(null);
	}
}
