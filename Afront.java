import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Afront extends JFrame implements ActionListener 
{

    JButton admin,login;
    JLabel l1,lblconsumerid,lblpassword;
    JTextField consumerid,password;

    Afront()
    {
        super("Customer Login Page");
        getContentPane().setBackground(Color.WHITE);
        setBounds(450, 150, 700, 400);
        setLayout(null);

        JLabel lblconsumerid = new JLabel("Consumer ID");
        lblconsumerid.setBounds(300, 40, 100, 20);
        add(lblconsumerid);

        consumerid = new JTextField();
        consumerid.setBounds(400, 40, 150, 20);
        add(consumerid);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 80, 100, 20);
        add(lblpassword);

        password = new JTextField();
        password.setBounds(400, 80, 150, 20);
        add(password);

        login = new JButton("Login");
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setBounds(330, 160, 100, 30);
        login.addActionListener(this);
        
        add(login);

        admin = new JButton("Admin Page");
        admin.setBackground(Color.BLACK);
        admin.setForeground(Color.WHITE);
        admin.setBounds(450, 160, 100, 30);
        admin.addActionListener(this);
        add(admin);

        l1 = new JLabel();
        l1.setBounds(200, 200, 150, 30);
        add(l1);

        setSize(600, 400);
        setLocation(400, 200);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae)
    {

         String sconsumerid=consumerid.getText();
         String spassword=password.getText();
        try
        {
           if (ae.getSource() == login) 
           {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");

                Statement stm = con.createStatement();

                String query = "select * from login where consumerid = '" + sconsumerid + "' and password = '" + spassword
                        + "' and user = 'customer'";
                    

                ResultSet rs = stm.executeQuery(query);

                if (rs.next()) 
                {
                    l1.setText("Login Success");
                    String name=rs.getString(1);

                    new Customerdb(sconsumerid,name);
                    
                }
                 else 
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    l1.setText("Login not Success");

                   // sconsumerid.setText(" ");
//                      password.setText(" ");
                }
                rs.close();
                stm.close();
                con.close();
            }
            if(ae.getSource()==admin)
            {
                new Login();
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
        } catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    public static void main(String args[])
    {
        Afront obj=new Afront();
    }

}

