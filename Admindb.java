import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class Admindb extends JFrame implements ActionListener
{
    String atype;

    Admindb()
    {
        super("Admin Dashboard");
      
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/backd.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

       setBounds(400,200,700,400);

        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);

        JMenu master = new JMenu("Menu");
        master.setForeground(Color.BLUE);

        JMenuItem addcustomer = new JMenuItem("Add Customer");
        addcustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        addcustomer.setBackground(Color.WHITE);
        addcustomer.addActionListener(this);
        master.add(addcustomer);
        

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospaced", Font.PLAIN, 12));
        calculatebill.setBackground(Color.WHITE);
        calculatebill.addActionListener(this);
        master.add(calculatebill);



        JMenu info = new JMenu("Information");
        info.setForeground(Color.RED);

        /*JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        customerdetails.setBackground(Color.WHITE);
        customerdetails.addActionListener(this);
        info.add(customerdetails);*/

        

        JMenuItem deleteCustomer = new JMenuItem("Delete Customer");
        deleteCustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        deleteCustomer.setBackground(Color.WHITE);
        deleteCustomer.addActionListener(this);
        info.add(deleteCustomer);



        JMenu mexit = new JMenu("Exit");
        mexit.setForeground(Color.RED);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.PLAIN, 12));
        exit.setBackground(Color.WHITE);
        exit.addActionListener(this);
        mexit.add(exit);


        mb.add(master);
        mb.add(info);
        mb.add(mexit);
        
        setLayout(new FlowLayout());
        setVisible(true);
    }

   



    public void actionPerformed(ActionEvent ae)
    {
        String msg = ae.getActionCommand();

        if (msg.equals("Add Customer")) 
        {
            new Signup();
        }
        else if (msg.equals("Exit")) 
        {
            
            setVisible(false);
        }
        else if(msg.equals("Calculate Bill"))
        {
            new Calculatebill();
        }
        /*else if(msg.equals("Customer Details"))
        {
            new Customerdetails();
        }*/
        else if(msg.equals("Delete Customer"))
        {
            new Deletecustomer();
        }
    }
    public static void main(String args[])
    {
        Admindb obj=new Admindb();
    }

}




