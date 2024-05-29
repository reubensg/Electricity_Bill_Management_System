//package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, cancel, signup;
    JTextField username, password;
    JLabel l1;
    Choice logginin;

    Login() {

        super("Login Page");
    
        getContentPane().setBackground(Color.WHITE);
        setBounds(450, 150, 700, 400);
        setLayout(null);

        l1 = new JLabel();
        l1.setBounds(200, 200, 150, 30);
        add(l1);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 40, 100, 20);
        add(lblusername);

        username = new JTextField();
        username.setBounds(400, 40, 150, 20);
        add(username);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 80, 100, 20);
        add(lblpassword);

        password = new JTextField();
        password.setBounds(400, 80, 150, 20);
        add(password);

        //JLabel loggininas = new JLabel("Loggin in as");
        //loggininas.setBounds(300, 100, 100, 20);
        //add(loggininas);

        //logginin = new Choice();
        //logginin.add("Admin");
        //logginin.add("Customer");
       // logginin.setBounds(400, 100, 150, 20);
        // logginin.addItemListener(this);
       // add(logginin);

       login = new JButton("Login");
       login.setBackground(Color.BLACK);
       login.setForeground(Color.WHITE);
       login.setBounds(330, 160, 100, 30);
       login.addActionListener(this);
       add(login);

        cancel = new JButton("Customer");
        cancel.setBackground(Color.BLACK);
       cancel.setForeground(Color.WHITE);
        cancel.setBounds(450, 160, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        //signup = new JButton("Signup");
        //signup.setBounds(380, 200, 100, 20);
        //signup.addActionListener(this);
        //add(signup);

        setSize(600, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String susername = username.getText().toString();
        String spassword = password.getText().toString();
       // String User = logginin.getSelectedItem().toString();
       // String user = User.toLowerCase();

        try {
            if (ae.getSource() == login) {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
                Statement stm = con.createStatement();
                String query = "select * from login where username = '" + susername + "' and password = '" + spassword
                        + "' and user = 'admin'";

                ResultSet rs = stm.executeQuery(query);

                if (rs.next()) 
                {
                    l1.setText("Login Success");
                    new Admindb();
                    //setVisible(false);
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    l1.setText("Login not Success");
                    username.setText("");
                    password.setText("");
                }
                rs.close();
                stm.close();
                con.close();

            } else if (ae.getSource() == cancel) {
                new Afront();
                setVisible(false);

            } //else if (ae.getSource() == signup) {
                //setVisible(false);

               // new Signup();
            //}

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}