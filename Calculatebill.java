
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class Calculatebill extends JFrame implements ActionListener
{
    
    JTextField consumerid,unitsconsumed;
    JButton calculate;
    int amount;
    String name;
   Choice monthc;
    

    Calculatebill()
    {
        setBounds(400, 200, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Calculate Bill",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel lblconsumerid = new JLabel("Consumer ID");
        lblconsumerid.setBounds(100, 70, 140, 20);
        lblconsumerid.setForeground(Color.GRAY);
        lblconsumerid.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblconsumerid);

        consumerid = new JTextField();
        consumerid.setBounds(260, 70, 150, 20);
        panel.add(consumerid);

        JLabel lblunitconsumed = new JLabel("Units Consumed");
        lblunitconsumed.setBounds(100, 100, 140, 20);
        lblunitconsumed.setForeground(Color.GRAY);
        lblunitconsumed.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblunitconsumed);

        unitsconsumed = new JTextField();
        unitsconsumed.setBounds(260, 100, 150, 20);
        panel.add(unitsconsumed);

        JLabel month = new JLabel("Month");
        month.setBounds(100, 130, 100, 20);
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
       monthc.setBounds(260, 130, 150, 20);
       panel.add(monthc);




        calculate = new JButton("Calculate");
        calculate.setBackground(Color.BLACK);
        calculate.setForeground(Color.WHITE);
        calculate.setBounds(140, 180, 120, 25);
        calculate.addActionListener(this);
        panel.add(calculate);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        int flag=0;
        String sconsumerid=consumerid.getText();
        
        String sunitsconsumed=unitsconsumed.getText();
        int units=Integer.parseInt(sunitsconsumed);
       
        String smonth=monthc.getSelectedItem().toString().toLowerCase();

        if (units <= 100) 
        {
           amount=units*10;
        }
        else if (units <= 200) 
        {
            amount= (100*10)+ (units - 100)* 15;
        }
        else if (units <= 300) 
        {
            amount=(100 * 10)+ (100 * 15)+ (units - 200)* 20;
        }
        else if (units > 300) 
        {
            amount=(100 * 10)+ (100 * 15)+ (100 * 20)+ (units - 300)* 25;
        }

        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebms", "root", "1234");
            Statement stm = con.createStatement();
            String query2="select * from login where consumerid='"+sconsumerid+"'";
            ResultSet rs=stm.executeQuery(query2);

            if(rs.next())
            {
                name=rs.getString(1);
                flag=1;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Consumer Doesn't Exist");
               
            }
            rs.close();

            String query3="select * from customer where consumerid='"+sconsumerid+"'and month='"+smonth+"' ";
            ResultSet rs1=stm.executeQuery(query3);

            if(rs1.next())
            {
                JOptionPane.showMessageDialog(null, "Cannot Add");
            }
            else
            {
                if(flag==1)
                {
                    String query1 = "insert into customer(consumerid,name,energyconsumed,amtpaid,amtdue,month,status) value ('"+sconsumerid+"','"+name+"','"+sunitsconsumed+"','0','"+amount+"','"+smonth+"','not paid')";
                    stm.executeUpdate(query1);

                    JOptionPane.showMessageDialog(null, "Amount Calculated and Data Added");
                                setVisible(false);
                }
            }

        }
        catch (ClassNotFoundException e) 
        {
            System.out.println(e);
        } 
        catch (NumberFormatException e) 
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
        Calculatebill obj = new Calculatebill();
    }
}
