package project314;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

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

    public void getItem(JTable menuList)
    {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            /*ps = c.prepareStatement("Select * from menu");
            rs = ps.executeQuery();*/
            Statement st = c.createStatement();
            String sql = "Select * from menu";
            rs = st.executeQuery(sql);

            while (rs.next())
            {
                String itemNumber = rs.getString("itemNumber");
                String itemName = rs.getString("itemName");
                String itemPrice = rs.getString("itemPrice");

                //new Menu(itemNumber, itemName,itemPrice);
                String values[] = {itemNumber, itemName, itemPrice};
                DefaultTableModel dtm = (DefaultTableModel) menuList.getModel();
                dtm.addRow(values);
            }

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

    public void updateItem(String itemNumber, String itemName, Double itemPrice)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
//            ps = c.prepareStatement("UPDATE menu SET (itemName, itemPrice) = (?,?) WHERE itemNumber = ?");
//            ps.setString(1, itemName);
//            ps.setDouble(2, itemPrice);
//            ps.setString(3, itemNumber);
//            ps.executeUpdate();
            Statement st = c.createStatement();
            String sql = "Update menu set itemName = '"+ itemName + "', itemPrice = "+ itemPrice + " WHERE itemNumber ='"+ itemNumber + "'";
            st.execute(sql);
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
            try
            {
                c.close();
            }
            catch (SQLException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        //return false;
    }

    /*private boolean viewItem(String itemNumber)
    {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
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
        return false;

    }*/

    public boolean createItem(String itemNumber, String itemName, double itemPrice)
    {
        Connection c = null;
        PreparedStatement ps = null;
        boolean input = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
//            ps = c.prepareStatement("INSERT INTO menu (itemNumber, itemName, itemPrice) VALUES (?, ?, ?)");
//            ps.setString(1, itemNumber);
//            ps.setString(2, itemName);
//            ps.setDouble(3, itemPrice);
            Statement st = c.createStatement();
            String sql = "insert into menu values('"+ itemNumber + "','"+ itemName + "','" + itemPrice + "');";
            st.execute(sql);
            //ps.executeUpdate();
            input = true;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            input = false;
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }

        return input;


    }

    public void deleteItem(String itemNumber)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
//            ps = c.prepareStatement("DELETE FROM menu WHERE itemNumber = ?");

            Statement st = c.createStatement();
            String sql = "DELETE FROM menu WHERE itemNumber ='"+ itemNumber + "'";
            st.execute(sql);
//            ps.setString(1, itemNumber);
            //ps.setString(2, itemName);
            //ps.setDouble(2, itemPrice);
//            int result = ps.executeUpdate();
            //ps.executeUpdate();
//            System.out.println(rs.next());
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
            try
            {
                c.close();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
        //return false;
    }
}