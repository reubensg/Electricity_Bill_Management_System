
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;


public class Viewbill extends JFrame implements ActionListener
{

    Choice monthc;
    JButton status,pay;
    JLabel kseb;
    JLabel lconsumerid,lname,lmonth,lamt;
    JLabel l1,l2,l3,l4;
    String sconsumerid;
    String amount;
    JPanel panel;

    Viewbill(String sconsumerid)
    {
        this.sconsumerid=sconsumerid;
       
        setBounds(400, 200, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Make Payment",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel month = new JLabel("Select Month");
        month.setBounds(140,20 , 100, 20);
        month.setForeground(Color.GRAY);
        month.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(month);

        monthc = new Choice();
        monthc.add("January");
        monthc.add("February");
        monthc.add("March");
        monthc.add("April");
        monthc.add("May");
        monthc.add("June");
        monthc.add("July");
        monthc.add("August");
        monthc.add("September");
        monthc.add("October");
        monthc.add("November");
        monthc.add("December");
       monthc.setBounds(260, 20, 150, 20);
       panel.add(monthc);

       status = new JButton("Status");
        status.setBackground(Color.BLACK);
        status.setForeground(Color.WHITE);
        status.setBounds(220, 50, 100, 25);
        status.addActionListener(this);
        panel.add(status);

        kseb = new JLabel("KSEB");
        kseb.setBounds(250, 90, 100, 20);
        kseb.setForeground(Color.GRAY);
        kseb.setFont(new Font("Tahoma", Font.BOLD, 14));
        kseb.setVisible(false);
        panel.add(kseb);

        lconsumerid=new JLabel("Consumer ID: ");
        lconsumerid.setBounds(140, 130, 150, 20);
        lconsumerid.setForeground(Color.GRAY);
        lconsumerid.setFont(new Font("Tahoma", Font.BOLD, 14));
        lconsumerid.setVisible(false);
        panel.add(lconsumerid);

        lname=new JLabel("Name: ");
        lname.setBounds(140, 160, 150, 20);
        lname.setForeground(Color.GRAY);
        lname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lname.setVisible(false);
        panel.add(lname);

        lmonth=new JLabel("Status: ");
        lmonth.setBounds(140, 190, 150, 20);
        lmonth.setForeground(Color.GRAY);
        lmonth.setFont(new Font("Tahoma", Font.BOLD, 14));
        lmonth.setVisible(false);
       panel.add(lmonth);

        lamt=new JLabel("Amount: ");
        lamt.setBounds(140, 220, 150, 20);
        lamt.setForeground(Color.GRAY);
        lamt.setFont(new Font("Tahoma", Font.BOLD, 14));
        lamt.setVisible(false);
       panel.add(lamt);

        l1=new JLabel(sconsumerid);
        l1.setBounds(270,130 , 150, 20);
        l1.setForeground(Color.GRAY);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setVisible(false);
        panel.add(l1);

        l2=new JLabel();
        l2.setBounds(270,160 , 150, 20);
        l2.setForeground(Color.GRAY);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setVisible(false);
        panel.add(l2);

        l3=new JLabel();
        l3.setBounds(270,190 , 150, 20);
        l3.setForeground(Color.GRAY);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setVisible(false);
        panel.add(l3);

        l4=new JLabel();
        l4.setBounds(270,220 , 150, 20);
        l4.setForeground(Color.GRAY);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setVisible(false);
        panel.add(l4);

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(220, 250, 100, 25);
        pay.addActionListener(this);
        pay.setVisible(false);
        panel.add(pay);
        


       setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    { 
        String smonth=monthc.getSelectedItem().toString().toLowerCase();
        if(ae.getSource()==status)
        {
           
            //System.out.println(smonth);

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
                Statement stm = con.createStatement();
                String query="select * from customer where consumerid='"+sconsumerid+"' and  month='"+smonth+"'";
                ResultSet rs=stm.executeQuery(query);

                if(rs.next())
                {
                    kseb.setVisible(true);
                    lconsumerid.setVisible(true);
                    lname.setVisible(true);
                    lmonth.setVisible(true);
                    lamt.setVisible(true);
                    l1.setVisible(true);
                    l2.setVisible(true);
                    l3.setVisible(true);
                    l4.setVisible(true);
                    pay.setVisible(true);
                    l1.setText(rs.getString(1));
                    l2.setText(rs.getString(2));
                    l3.setText(rs.getString(7));
                    l4.setText(rs.getString(5));
                    amount=rs.getString(5);

                }
                else
                {
                    kseb.setVisible(false);
                    lconsumerid.setVisible(false);
                    lname.setVisible(false);
                    lmonth.setVisible(false);
                    lamt.setVisible(false);
                    l1.setVisible(false);
                    l2.setVisible(false);
                    l3.setVisible(false);
                    l4.setVisible(false);
                    pay.setVisible(false);
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
        else if(ae.getSource()==pay)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
                Statement stm = con.createStatement();
                String query1="update customer set amtdue='0',amtpaid='"+amount+"',status='paid' where consumerid='"+sconsumerid+"' and  month='"+smonth+"'";
                stm.executeUpdate(query1);

                JOptionPane.showMessageDialog(null, "Payment Successful");
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
        Viewbill obj=new Viewbill("");
    }
    
}
