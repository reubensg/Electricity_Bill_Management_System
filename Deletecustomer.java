import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;


public class Deletecustomer extends JFrame implements ActionListener 
{
    JButton delete;
    JTextField conusmerid;
    JPanel panel;
    

    Deletecustomer()
    {

        setBounds(400,200,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Delete Customer",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel lblconsumerid = new JLabel("Enter Consumer ID");
        lblconsumerid.setBounds(140,20 , 150, 20);
       lblconsumerid.setForeground(Color.GRAY);
        lblconsumerid.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblconsumerid);

        conusmerid = new JTextField();
        conusmerid.setBounds(300, 20, 150, 20);
        panel.add(conusmerid);

        delete = new JButton("Delete");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBounds(240, 50, 100, 25);
        delete.addActionListener(this);
        panel.add(delete);

        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae)
    {
        String sconsumerid=conusmerid.getText();
        
        if(ae.getSource()==delete)
        {
            try
            {
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
                Statement stm = con.createStatement();

                String query2="select * from customer where status='not paid' and consumerid='"+sconsumerid+"'";
                ResultSet rs=stm.executeQuery(query2);

                if(rs.next())
                {
                    JOptionPane.showMessageDialog(null, "Cannot Be Deleted: Amount Pending","WARNING", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    String query="delete from login where consumerid='"+sconsumerid+"'";
                    String query1="delete from customer where consumerid='"+sconsumerid+"'";
                    stm.executeUpdate(query);
                    stm.executeUpdate(query1);
                    JOptionPane.showMessageDialog(null, "Customer Data Deleted","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }

               

                
               

            }
            catch (ClassNotFoundException e) 
            {
                System.out.println(e);
            } 
            catch (SQLException e) 
            {
                System.out.println(e);
            } 
            catch (Exception e) 
            {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[])
    {
        Customerdetails obj=new Customerdetails();
    }
}