import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;


public class Updateinfo extends JFrame implements ActionListener 
{
    JButton update;
    JTextField password;
    JPanel panel;
    
String sconsumerid;
    Updateinfo(String sconsumerid)
    {
        this.sconsumerid=sconsumerid;

        setBounds(400,200,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Update Password",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel lblpassword = new JLabel("Enter New Password");
        lblpassword.setBounds(140,40 , 150, 20);
       lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);

        password = new JTextField();
        password.setBounds(300, 40, 150, 20);
        panel.add(password);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(240, 90, 100, 25);
        update.addActionListener(this);
        panel.add(update);

        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae)
    {
        String spassword=password.getText();
        
        if(ae.getSource()==update)
        {
            try
            {
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
                Statement stm = con.createStatement();
                String query="update login set password='"+spassword+"' where consumerid='"+sconsumerid+"'";
                //String query1="delete from customer where consumerid='"+sconsumerid+"'";

                stm.executeUpdate(query);
                //stm.executeUpdate(query1);

                JOptionPane.showMessageDialog(null, "Password Updated");
                setVisible(false);
               

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
        Updateinfo obj=new Updateinfo("");
    }
}