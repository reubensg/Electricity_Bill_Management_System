//package program;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JButton create, back;
    Choice accountType;
    JTextField meter, username, name, password,consumerid,phone;

    Signup() {

        setBounds(400, 200, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel heading = new JLabel("Create Account");
        heading.setBounds(150, 30, 140, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);

      
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 70, 140, 20);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblusername);

        username = new JTextField();
        username.setBounds(260, 70, 150, 20);
        panel.add(username);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 100, 140, 20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(260, 100, 150, 20);
        panel.add(name);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 130, 140, 20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpassword);

        password = new JTextField();
        password.setBounds(260, 130, 150, 20);
        panel.add(password);

        JLabel lblconsumerid = new JLabel("Consumer ID");
        lblconsumerid.setBounds(100, 160, 140, 20);
        lblconsumerid.setForeground(Color.GRAY);
        lblconsumerid.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblconsumerid);

        consumerid = new JTextField();
        consumerid.setBounds(260, 160, 150, 20);
        panel.add(consumerid);

        JLabel lblphone = new JLabel("Phone No");
        lblphone.setBounds(100, 190, 140, 20);
        lblphone.setForeground(Color.GRAY);
        lblphone.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblphone);

        phone = new JTextField();
        phone.setBounds(260, 190, 150, 20);
        panel.add(phone);


        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140, 260, 120, 25);
        create.addActionListener(this);
        panel.add(create);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300, 260, 120, 25);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == create) 
        {

            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String sconsumerid =consumerid.getText();
            String sphone=phone.getText();
            if(sphone.length()==10)
            {

                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
                    Statement stm = con.createStatement();
                    String query1 = "select * from login where username='" + susername + "'or consumerid='"+sconsumerid+"'";
    
                     ResultSet rs = stm.executeQuery(query1);
                    if (rs.next()) 
                    {
                        JOptionPane.showMessageDialog(null, "Username/ConsumerID Already exists");

                    } 
                    else
                    {
                        String query,query2;
                        query = "insert into login(name,password,user,username,consumerid,phoneno)value('" + sname + "','" + spassword
                            + "','customer','" + susername + "','"+sconsumerid+"','"+sphone+"')";
                    
                    //query2="insert into customer(consumerid,name) value('"+sconsumerid+"','"+sname+"')";
                        stm.executeUpdate(query);
                    //stm.executeUpdate(query2);
                    
                        JOptionPane.showMessageDialog(null, "Account Created Successfully");
                        setVisible(false);
                    
                    }
                con.close();

                } 
                catch (ClassNotFoundException e) 
                {
                    System.out.println(e);
                } 
                catch (SQLException e) 
                {
                    System.out.println(e);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number","ERROR", JOptionPane.ERROR_MESSAGE);
            }

            
        }
        else if (ae.getSource() == back) 
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}