
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;


public class Customerdb extends JFrame implements ActionListener 
{
    String sconsumerid,name;
    JLabel l1;
    JLabel l2,l3;
    Customerdb(String sconsumerid,String name)
    {
        super("Customer Dashboard");

        this.sconsumerid=sconsumerid;
        this.name=name;

        //System.out.println(sconsumerid);
        //ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/backd.jpg"));
        //Image i2 = i1.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
       // ImageIcon i3 = new ImageIcon(i2);
        //JLabel image = new JLabel(i3);
        //add(image);

       setBounds(400,200,700,400);

        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        //mb.setBackground(Color.RED);

        JMenu master = new JMenu("Menu");
        master.setForeground(Color.BLUE);

        JMenuItem viewbill = new JMenuItem("View Bills");
        viewbill.setFont(new Font("monospaced", Font.PLAIN, 12));
        viewbill.setBackground(Color.WHITE);
        viewbill.addActionListener(this);
        master.add(viewbill);



        JMenu info = new JMenu("Information");
        info.setForeground(Color.RED);

        JMenuItem updateinformation = new JMenuItem("Update Password");
        updateinformation.setFont(new Font("monospaced", Font.PLAIN, 12));
        updateinformation.setBackground(Color.WHITE);
        updateinformation.addActionListener(this);
        info.add(updateinformation);

        JMenuItem updatephone = new JMenuItem("Update Phone Number");
        updatephone.setFont(new Font("monospaced", Font.PLAIN, 12));
        updatephone.setBackground(Color.WHITE);
        updatephone.addActionListener(this);
        info.add(updatephone);

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

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(150, 135, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 250, 250);
        add(image);
        setLayout(null);

        l1 = new JLabel(name);
        l1.setBounds(240, 90, 200, 20);
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
   
        add(l1);
        setLayout(null);

        l2 = new JLabel("Consumer ID: "+sconsumerid);
        l2.setBounds(240, 120, 200, 20);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
    
        add(l2);

       // setLayout(new FlowLayout());
        setVisible(true);
        

    }
    public void actionPerformed(ActionEvent ae)
    {
        String msg = ae.getActionCommand();

        if(msg.equals("Exit"))
        {
            //new Afront();
            setVisible(false);
        }
         if(msg.equals("View Bills"))
        {
            new Viewbill(sconsumerid);
            //setVisible(false);
        }
        if(msg.equals("Update Password"))
        {
            new Updateinfo(sconsumerid);
        }
        if(msg.equals("Update Phone Number"))
        {
            new Updatephone(sconsumerid);
        }

    }

    public static void main(String args[])
    {
        Customerdb obj =new Customerdb("","");
    }
}
