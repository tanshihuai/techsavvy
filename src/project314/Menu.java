package project314;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Menu
{
    //variables
    private String itemNumber;
    private String itemName;
    private double itemPrice;

    //default constructor
    public Menu()
    {


    }

    public Menu(String itemNumber, String itemName, double itemPrice)
    {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String[][] getItem()
    {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","abdulhameed13");
            ps = c.prepareStatement("Select * from menu");
            rs = ps.executeQuery();

            int i=0;
            while(rs.next())
            {
                for(int j=0;j<5;j++)
                {
                    //System.out.print(rs.getString(j+1));
                    data[i][j]=rs.getString(j+1);
                }
                //System.out.println();
                i=i+1;

            };

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return data;

    }

    public boolean updateItem(String itemNumber, String itemName, double itemPrice)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","abdulhameed13");
            ps = c.prepareStatement("UPDATE menu SET itemName, itemPrice = ? WHERE itemNumber = ?");
            ps.setString(1, itemNumber);
            ps.setString(2, itemName);
            ps.setDouble(3, itemPrice);
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                c.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return false;
    }

    private void viewItem(String itemNumber)
    {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","Passwordis123");
            ps = c.prepareStatement("Select itemName from menu where itemNumber = ?");
            ps.setString(1, itemNumber);
            rs = ps.executeQuery();

            String columns[] = { "itemNumber", "itemName", "itemPrice" };
            String data[][] = new String[8][3];

            int i = 0;
            while(rs.next())
            {
                this.itemName = rs.getString(1);
            };
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

    }

    public boolean createItem(String itemNumber, String itemName, double itemPrice)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","abdulhameed13");
            ps = c.prepareStatement("INSERT INTO menu (itemNumber, itemName, itemPrice) VALUES (?, ?, ?)");
            ps.setString(1, itemNumber);
            ps.setString(2, itemName);
            ps.setDouble(3, itemPrice);
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                c.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return false;
    }
}