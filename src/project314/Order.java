package project314;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Order {
    //variables
    private String itemNumber;
    private String itemName;
    private double itemPrice;
    private int qty;
    private int orderID;

    //default constructor
    public Order() {

    }

    public Order(int orderID, String itemNumber, String itemName, double itemPrice, int qty) {
        this.orderID = orderID;
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.qty = qty;
    }

    public void getItem(JTable customerMenuList) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root","mysql123");
            Statement st = c.createStatement();
            String sql = "Select * from menu";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String itemNumber = rs.getString("itemNumber");
                String itemName = rs.getString("itemName");
                String itemPrice = rs.getString("itemPrice");
                
                String values[] = {itemNumber, itemName, itemPrice};
                DefaultTableModel dtm = (DefaultTableModel) customerMenuList.getModel();
                dtm.addRow(values);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void getOrder(JTable foodCart) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root","mysql123");
            Statement st = c.createStatement();
            String sql = "Select * from cart";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String itemNumber = rs.getString("itemNumber");
                String itemName = rs.getString("itemName");
                String itemPrice = rs.getString("itemPrice");
                int qty = rs.getInt("Qty");
                
                String values[] = {itemNumber, itemName, itemPrice, String.valueOf(qty)};
                DefaultTableModel dtm = (DefaultTableModel) foodCart.getModel();
                dtm.addRow(values);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void getPayment(JTable foodCart) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root","mysql123");
            Statement st = c.createStatement();
            String sql = "Select * from cart";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String itemNumber = rs.getString("itemNumber");
                String itemName = rs.getString("itemName");
                String itemPrice = rs.getString("itemPrice");
                int qty = rs.getInt("Qty");

                String values[] = {itemNumber, itemName, itemPrice, String.valueOf(qty)};
                DefaultTableModel dtm = (DefaultTableModel) foodCart.getModel();
                dtm.addRow(values);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    public List<String[]> getOrders() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        ArrayList<String[]> orders = new ArrayList<String[]>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root","mysql123");
            Statement st = c.createStatement();
            String sql = "Select * from cart";
            rs = st.executeQuery(sql);



            while (rs.next()) {
                String itemNumber = rs.getString("itemNumber");
                String itemName = rs.getString("itemName");
                String itemPrice = rs.getString("itemPrice");
                int qty = rs.getInt("Qty");

                String values[] = {itemNumber, itemName, itemPrice, String.valueOf(qty)};
                orders.add(values);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return orders;
    }


    public void addToCart(int orderID, String itemNumber, String itemName, double itemPrice, int qty)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            Statement st = c.createStatement();
            String sql = "insert into cart values('"+ orderID + "','"+ itemNumber + "','"+ itemName + "','" + itemPrice + "','" + qty + "');";
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
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
    }
    
    public void paymentMethod(List<String[]> inOrders, float totalAmount)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            Statement st = c.createStatement();
            String sql = "TRUNCATE TABLE cart";
            st.execute(sql);
            
            c.setAutoCommit(false);
            // add to the receipt table,
            ps = c.prepareStatement("insert into receipt(totalAmount) values(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, totalAmount);
            ps.executeUpdate();

            int orderId = -1;
            // get generated keys
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = (int) generatedKeys.getLong(1);
            }
            
            // add to the receiptItems table,
            ArrayList<String[]> orders = new ArrayList<String[]>(inOrders);
            for (String[] order : orders)	{
                float itemPrice = Float.parseFloat(order[2]);
                int quantity = Integer.parseInt(order[3]);

                //{itemNumber, itemName, itemPrice, String.valueOf(qty)};
                ps = c.prepareStatement("insert into receiptItems(orderID, itemNumber, itemName, itemPrice, qty) values(?,?,?,?,?)");
                ps.setInt(1, orderId);
                ps.setString(2, order[0]);
                ps.setString(3, order[1]);
                ps.setFloat(4, Float.parseFloat(order[2]));
                ps.setInt(5, Integer.parseInt(order[3]));

                ps.executeUpdate();
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
        finally
        {
            try
            {
                c.commit();
                c.close();
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
    }

    public boolean newCartTable(JTable customerFoodCart)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");

            Statement st = c.createStatement();
            String sql = "CREATE TABLE cart (orderID integer, itemNumber varchar(5) NOT NULL, itemName varchar(20) NOT NULL, itemPrice decimal(4,2), qty integer , PRIMARY KEY(orderID));";
            st.executeUpdate(sql);
            
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
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public void editInCart(String itemNumber, String itemName, double itemPrice, int qty)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            Statement st = c.createStatement();
            String sql = "Update cart set qty = '"+ qty + "'WHERE itemNumber ='"+ itemNumber + "'";
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

    public int getOrderId (){
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            Statement st = c.createStatement();
            String sql = "select orderID from cart ORDER BY orderID DESC LIMIT 1;";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                int orderId = rs.getInt("orderID");
                return orderId + 1;
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
        return 0;
    }
}