package project314;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Order {
    //variables
    private String itemNumber;
    private String itemName;
    private double itemPrice;


    //default constructor
    public Order() {

    }

    public Order(String itemNumber, String itemName, double itemPrice) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public void getItem(JTable menuList) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[8][3]; // [rows][columns]

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root", "abdulhameed13");
            /*ps = c.prepareStatement("Select * from menu");
            rs = ps.executeQuery();*/
            Statement st = c.createStatement();
            String sql = "Select * from menu";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String itemNumber = rs.getString("itemNumber");
                String itemName = rs.getString("itemName");
                String itemPrice = rs.getString("itemPrice");

                //new Menu(itemNumber, itemName,itemPrice);
                String values[] = {itemNumber, itemName, itemPrice};
                DefaultTableModel dtm = (DefaultTableModel) menuList.getModel();
                dtm.addRow(values);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
