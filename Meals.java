import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import meals.add;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Meals extends JFrame {
    DefaultTableModel model;
    JTable table;
    String[][] data;
    String[] column = {"NAME", "PRICE", "QUANTITY"};
    JFrame meals = new JFrame("MEALS");

    JTextField nameText = new JTextField();
    JTextField priceText = new JTextField();
    private JTextField quantityText;
    
    public Meals() {
    	meals.getContentPane().setBackground(new Color(240, 248, 255));
    	meals.setBackground(new Color(240, 248, 255));        
    	meals.setSize(700, 600);
    	meals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	meals.setLocationRelativeTo(null);        
    	meals.getContentPane().setLayout(null);
        
        JLabel lblMenu = new JLabel("MENU");
        lblMenu.setForeground(new Color(0, 128, 128));
        lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenu.setBounds(0, 50, 684, 40);
        Font font = new Font("Arial", Font.BOLD, 20);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 30));

        meals.getContentPane().add(lblMenu);
        
        int length = 0;
        try {
            Scanner sc = new Scanner(new File("meals.txt"));
            while(sc.hasNextLine()) {
                length++;
                sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        data = new String[length][3];
        
        int i = 0;
        try {
            Scanner sc = new Scanner(new File("meals.txt"));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" , ");
                data[i][0] = parts[0];
                data[i][1] = parts[1];
                data[i][2] = parts[2];
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        model = new DefaultTableModel(data, column);
        table = new JTable(model);
        table.setBackground(new Color(240, 248, 255));
        table.setForeground(new Color(0, 0, 0));
        table.setGridColor(new Color(0, 128, 128));
        table.setBounds(50, 100, 600, 200);
        meals.getContentPane().add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(240, 248, 255));
        scrollPane.setBounds(50, 100, 600, 200);
        meals.getContentPane().add(scrollPane);

        table.setRowSelectionAllowed(true);
      
        JLabel MEALS = new JLabel("ADD MEALS");
        MEALS.setForeground(new Color(0, 128, 128));
        MEALS.setBounds(50, 300, 200, 40);
        meals.getContentPane().add(MEALS);
        MEALS.setFont(font);
       
        JLabel NAME = new JLabel("Name:");
        NAME.setForeground(new Color(0, 128, 128));
        NAME.setHorizontalAlignment(SwingConstants.LEFT);
        NAME.setFont(new Font("Arial", Font.BOLD, 16));
        NAME.setBounds(50, 332, 100, 40);
        meals.getContentPane().add(NAME);

        JLabel PRICE = new JLabel("Price:");
        PRICE.setForeground(new Color(0, 128, 128));
        PRICE.setFont(new Font("Arial", Font.BOLD, 16));
        PRICE.setHorizontalAlignment(SwingConstants.LEFT);
        PRICE.setBounds(50, 380, 100, 40);
        meals.getContentPane().add(PRICE);
      
        JLabel QUANTITY = new JLabel("Quantity:");
        QUANTITY.setForeground(new Color(0, 128, 128));
        QUANTITY.setFont(new Font("Arial", Font.BOLD, 16));
        QUANTITY.setBounds(50, 423, 100, 40);
        meals.getContentPane().add(QUANTITY);
       
        nameText.setBounds(131, 340, 256, 29);
        meals.getContentPane().add(nameText);

        priceText.setBounds(131, 383, 131, 29);
        meals.getContentPane().add(priceText);
        
        quantityText = new JTextField();
        quantityText.setBounds(131, 431, 131, 29);
        meals.getContentPane().add(quantityText);
        quantityText.setColumns(10);
        
        JButton btnAdd = new JButton("Add Item");
        btnAdd.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String name = nameText.getText();
                String price = priceText.getText();
                String quantity = quantityText.getText();
                
                if(!name.isEmpty() && !price.isEmpty() && !quantity.isEmpty()){
                	try{
                		File file = new File("meals.txt");
                        FileWriter fileWriter = new FileWriter(file,true);
                        fileWriter.write(name + " , " + price + " , " + quantity+ "\n");
                        JOptionPane.showMessageDialog(null, "A meal has been added");
                        fileWriter.close();
                        meals.dispose();
                        new Meals();
                	}
                	catch(Exception ex){
                		ex.printStackTrace();
                	}
                	nameText.setText("");
                    priceText.setText("");
                    quantityText.setText("");
                }
                else{
                	JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                }
        	}
        });
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.setBounds(76, 499, 100, 34);
        meals.getContentPane().add(btnAdd);
        
        JButton btnDel = new JButton("Delete Item");
        btnDel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int row = table.getSelectedRow();
                if(row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Delete", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        model.removeRow(row);
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("meals.txt"));
                            for(int i = 0; i < table.getRowCount(); i++) {
                                for(int j = 0; j < table.getColumnCount(); j++) {
                                    bw.write(table.getValueAt(i, j).toString() + " , ");
                                }
                                bw.newLine();
                            }
                            bw.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        	}
        });
        btnDel.setFont(new Font("Arial", Font.BOLD, 14));
        btnDel.setBounds(193, 499, 124, 34);
        meals.getContentPane().add(btnDel);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?")==0) {
					RestaurantIS rIS = new RestaurantIS();
					rIS.setVisible(true);
					meals.dispose();
								
				}
                
        	}
        	
        });
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBounds(543, 499, 89, 34);
        meals.getContentPane().add(btnBack);
       
               
        meals.setVisible(true);
      
    
    }
}
