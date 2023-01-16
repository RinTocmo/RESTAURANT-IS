import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RestaurantIS extends JFrame { 
	private JPanel contentPane; 
	private Image img_resto = new ImageIcon(LoginFrame.class.getResource("res/resto.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); 
	
	//private Image img_menu = new ImageIcon(LoginFrame.class.getResource("res/menu.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); 
	//private Image img_add = new ImageIcon(LoginFrame.class.getResource("res/add.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); 
	//private Image img_del = new ImageIcon(LoginFrame.class.getResource("res/del.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); 
	//private Image img_out = new ImageIcon(LoginFrame.class.getResource("res/Signout.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try { 
					RestaurantIS frame = new RestaurantIS(); 
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
	public RestaurantIS() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(265, 0, 435, 400); 
		setUndecorated(true); 
		setLocationRelativeTo(null);
		contentPane = new JPanel(); 
		contentPane.setBackground(new Color(240, 248, 255)); 
		setContentPane(contentPane); 
		contentPane.setLayout(null); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblIconResto = new JLabel(""); 
		lblIconResto.setBounds(0, 0, 435, 159);
		contentPane.add(lblIconResto);
		lblIconResto.setHorizontalTextPosition(SwingConstants.CENTER); 
		lblIconResto.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconResto.setIcon(new ImageIcon(img_resto));
		
		JButton btnMeal = new JButton("MEALS");
		btnMeal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Meals meal = new Meals();
				meal.show();
				
				dispose();
			}
		});
		btnMeal.setForeground(new Color(240, 248, 255));
		btnMeal.setFont(new Font("Arial", Font.BOLD, 19));
		btnMeal.setBorder(null);
		btnMeal.setBackground(new Color(0, 128, 128));
		btnMeal.setBounds(74, 189, 117, 90);
		contentPane.add(btnMeal);
		
		JButton btnDrinks = new JButton("DRINKS");
		btnDrinks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Drinks drink = new Drinks();
				drink.show();
				
				dispose();
			}
		});
		btnDrinks.setForeground(new Color(240, 248, 255));
		btnDrinks.setFont(new Font("Arial", Font.BOLD, 19));
		btnDrinks.setBorder(null);
		btnDrinks.setBackground(new Color(0, 128, 128));
		btnDrinks.setBounds(243, 189, 118, 90);
		contentPane.add(btnDrinks);
		
		JButton btnSignout = new JButton("LOGOUT");
		btnSignout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?")==0) {
					LoginFrame lFrame = new LoginFrame();
					lFrame.setVisible(true);
					RestaurantIS.this.dispose();
				}
			}
		});
		btnSignout.setForeground(new Color(240, 248, 255));
		btnSignout.setFont(new Font("Arial", Font.BOLD, 19));
		btnSignout.setBorder(null);
		btnSignout.setBackground(new Color(0, 128, 128));
		btnSignout.setBounds(132, 307, 168, 46);
		contentPane.add(btnSignout);
		
		JLabel lblnResto = new JLabel("KAHIT ANO RESTO");
		lblnResto.setForeground(new Color(0, 128, 128));
		lblnResto.setFont(new Font("Arial", Font.BOLD, 14));
		lblnResto.setHorizontalAlignment(SwingConstants.CENTER);
		lblnResto.setBounds(0, 142, 435, 14);
		contentPane.add(lblnResto);
		
		JLabel lblXres = new JLabel("X");
		lblXres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you user you want to close this application?", "Confirmation", JOptionPane.YES_OPTION)==0) {
					RestaurantIS.this.dispose();
				}
				else {
					RestaurantIS.this.show();
				}				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblXres.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblXres.setForeground(Color.BLACK);
			}
		});
		lblXres.setHorizontalAlignment(SwingConstants.CENTER);
		lblXres.setFont(new Font("Arial", Font.BOLD, 14));
		lblXres.setBounds(397, 0, 38, 31);
		contentPane.add(lblXres);
		
		
	}


}
