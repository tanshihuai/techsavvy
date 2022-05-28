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

    public void getItem(JTable managerMenuList)
    {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
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
                DefaultTableModel dtm = (DefaultTableModel) managerMenuList.getModel();
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

    public void updateItem(String itemNumber, String itemName, double itemPrice)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
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

    }

    public boolean createItem(String itemNumber, String itemName, double itemPrice)
    {
        Connection c = null;
        PreparedStatement ps = null;
        boolean input = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
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
    }
}