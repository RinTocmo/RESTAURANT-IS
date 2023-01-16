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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Drinks extends JFrame {
    DefaultTableModel model2;
    JTable table2;
    String[][] data2;
    String[] column2 = {"NAME", "PRICE", "QUANTITY"};
    JFrame Drinks = new JFrame("MEALS");

    JTextField nameField = new JTextField();
    JTextField priceField = new JTextField();
    private JTextField quantityField;
    
    public Drinks() {
    	Drinks.getContentPane().setBackground(new Color(240, 248, 255));
    	Drinks.setTitle("DRINKS");
    	Drinks.setBackground(new Color(240, 248, 255));        
    	Drinks.setSize(700, 600);
    	Drinks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Drinks.setLocationRelativeTo(null);        
    	Drinks.getContentPane().setLayout(null);
        
        JLabel lblMenu2 = new JLabel("MENU");
        lblMenu2.setForeground(new Color(0, 128, 128));
        lblMenu2.setHorizontalAlignment(SwingConstants.CENTER);
        lblMenu2.setBounds(0, 50, 684, 40);
        Font font = new Font("Arial", Font.BOLD, 20);
        lblMenu2.setFont(new Font("Arial", Font.BOLD, 30));

        Drinks.getContentPane().add(lblMenu2);
        
        int length2 = 0;
        try {
            Scanner scn = new Scanner(new File("drinks.txt"));
            while(scn.hasNextLine()) {
                length2++;
                scn.nextLine();
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        data2 = new String[length2][3];
        
        int j = 0;
        try {
            Scanner scn = new Scanner(new File("drinks.txt"));
            while(scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] parts2 = line.split(" , ");
                data2[j][0] = parts2[0];
                data2[j][1] = parts2[1];
                data2[j][2] = parts2[2];
                j++;
            }
            scn.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }

        model2 = new DefaultTableModel(data2, column2);
        table2 = new JTable(model2);
        table2.setBackground(new Color(240, 248, 255));
        table2.setForeground(new Color(0, 0, 0));
        table2.setGridColor(new Color(0, 128, 128));
        table2.setBounds(50, 100, 600, 200);
        Drinks.getContentPane().add(table2);

        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBackground(new Color(240, 248, 255));
        scrollPane2.setBounds(50, 100, 600, 200);
        Drinks.getContentPane().add(scrollPane2);

        table2.setRowSelectionAllowed(true);
      
        JLabel DRINKS = new JLabel("ADD DRINKS");
        DRINKS.setForeground(new Color(0, 128, 128));
        DRINKS.setBounds(50, 300, 200, 40);
        Drinks.getContentPane().add(DRINKS);
        DRINKS.setFont(font);
       
        JLabel dNAME = new JLabel("Name:");
        dNAME.setForeground(new Color(0, 128, 128));
        dNAME.setHorizontalAlignment(SwingConstants.LEFT);
        dNAME.setFont(new Font("Arial", Font.BOLD, 16));
        dNAME.setBounds(50, 332, 100, 40);
        Drinks.getContentPane().add(dNAME);

        JLabel dPRICE = new JLabel("Price:");
        dPRICE.setForeground(new Color(0, 128, 128));
        dPRICE.setFont(new Font("Arial", Font.BOLD, 16));
        dPRICE.setHorizontalAlignment(SwingConstants.LEFT);
        dPRICE.setBounds(50, 380, 100, 40);
        Drinks.getContentPane().add(dPRICE);
      
        JLabel dQUANTITY = new JLabel("Quantity:");
        dQUANTITY.setForeground(new Color(0, 128, 128));
        dQUANTITY.setFont(new Font("Arial", Font.BOLD, 16));
        dQUANTITY.setBounds(50, 423, 100, 40);
        Drinks.getContentPane().add(dQUANTITY);
       
        nameField.setBounds(131, 340, 256, 29);
        Drinks.getContentPane().add(nameField);

        priceField.setBounds(131, 383, 131, 29);
        Drinks.getContentPane().add(priceField);
        
        quantityField = new JTextField();
        quantityField.setBounds(131, 431, 131, 29);
        Drinks.getContentPane().add(quantityField);
        quantityField.setColumns(10);
        
        JButton btndAdd = new JButton("Add Item");
        btndAdd.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String name2 = nameField.getText();
                String price2 = priceField.getText();
                String quantity2 = quantityField.getText();
                
                if(!name2.isEmpty() && !price2.isEmpty() && !quantity2.isEmpty()){
                	try{
                		File file2 = new File("drinks.txt");
                        FileWriter fileWriter2 = new FileWriter(file2,true);
                        fileWriter2.write(name2 + " , " + price2 + " , " + quantity2+ "\n");
                        JOptionPane.showMessageDialog(null, "A drink has been added");
                        fileWriter2.close();
                        Drinks.dispose();
                        new Drinks();
                	}
                	catch(Exception ex){
                		ex.printStackTrace();
                	}
                	nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                }
                else{
                	JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                }
        	}
        });
        btndAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btndAdd.setBounds(76, 499, 100, 34);
        Drinks.getContentPane().add(btndAdd);
        
        JButton btndDel = new JButton("Delete Item");
        btndDel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int row = table2.getSelectedRow();
                if(row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Delete", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        model2.removeRow(row);
                        try {
                            BufferedWriter bw2 = new BufferedWriter(new FileWriter("drinks.txt"));
                            for(int a = 0; a < table2.getRowCount(); a++) {
                                for(int b = 0; b < table2.getColumnCount(); b++) {
                                    bw2.write(table2.getValueAt(a, b).toString() + " , ");
                                }
                                bw2.newLine();
                            }
                            bw2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
        	}
        });
        btndDel.setFont(new Font("Arial", Font.BOLD, 14));
        btndDel.setBounds(193, 499, 124, 34);
        Drinks.getContentPane().add(btndDel);
        
        JButton btndBack = new JButton("Back");
        btndBack.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		RestaurantIS rIS = new RestaurantIS();
				rIS.setVisible(true);
        		Drinks.dispose();
                //new RestaurantIS();
        	}
        }); 
        new RestaurantIS();
        btndBack.setFont(new Font("Arial", Font.BOLD, 14));
        btndBack.setBounds(543, 499, 89, 34);
        Drinks.getContentPane().add(btndBack);
       
               
        Drinks.setVisible(true);
      
    
    }
}