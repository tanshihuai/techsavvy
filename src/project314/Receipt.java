package project314;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;

public class Receipt
{
    //variables
    private String itemNumber;
    private String itemName;
    private double itemPrice;
    private int qty;
    private int orderID;
    private double totalAmount;
    private int receiptItemsID;

    // gets all the items
    //private List orders;

    //default constructor
    public Receipt() {

    }

    public Receipt(int orderID, double totalAmount, int receiptItemsID, String itemNumber, String itemName, double itemPrice, int qty) {
        this.orderID = orderID;
        this.totalAmount = totalAmount;
        this.receiptItemsID = receiptItemsID;
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.qty = qty;
    }

    public void getItem(JTable staffMenuList) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root", "mysql123");
            Statement st = c.createStatement();
            String sql = "SELECT * FROM receiptitems";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int receiptItemsID = Integer.parseInt(rs.getString("receiptItemsID"));
                int orderID = Integer.parseInt(rs.getString("orderID"));
                String itemNo = rs.getString("itemNumber");
                String itemNama = rs.getString("itemName");
                double itemValue = Double.parseDouble(rs.getString("itemPrice"));
                String quantity = rs.getString("qty");

                 String values[] = {String.valueOf(receiptItemsID), String.valueOf(orderID), itemNo, itemNama,
                        String.valueOf(itemValue), quantity};
                DefaultTableModel dtm = (DefaultTableModel) staffMenuList.getModel();
                dtm.addRow(values);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteOrder(int orderID)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");

            Statement st = c.createStatement();
            String sql = "DELETE FROM receipt WHERE orderID ='"+ orderID + "'";
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

    public void editInOrder(int receiptItemsID, int orderID, String itemNumber, String itemName, double itemPrice, int qty)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            Statement st = c.createStatement();
            String sql = "Update receiptitems set qty = '"+ qty + "'WHERE receiptItemsID ='"+ receiptItemsID+ "'";
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

}
