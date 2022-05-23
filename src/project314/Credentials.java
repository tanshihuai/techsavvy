package project314;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Credentials {
    private String serialno;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String email;
    private String role;

    public Credentials()
    {

    }

    public Credentials(String fname, String lname, String username, String password, String email, String role)
    {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void getUsers(JTable userAdminUsersList)
    {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[][] data = new String[100][7]; // [rows][columns]

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","abdulhameed13");
            Statement st = c.createStatement();
            String sql = "Select * from users";
            rs = st.executeQuery(sql);

            while (rs.next())
            {
                String serialno = rs.getString("serialno");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");

                //new Menu(itemNumber, itemName,itemPrice);
                String values[] = {serialno, fname,lname,username,password,email,role };
                DefaultTableModel dtm = (DefaultTableModel) userAdminUsersList.getModel();
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

    public boolean createUser(String serialno, String fname, String lname, String username, String password, String email, String role)
    {
        Connection c = null;
        PreparedStatement ps = null;
        boolean input = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","abdulhameed13");
            Statement st = c.createStatement();
            String sql = "insert into users values('"+ serialno + "','"+ fname + "','"+ lname + "','" + username + "','"+ password + "','"+ email + "','" + role + "');";
            st.execute(sql);
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

    public void updateUser(String serialno, String fname, String lname, String username, String password, String email, String role) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314", "root", "abdulhameed13");
            Statement st = c.createStatement();
            String sql = "Update users set username = '" + username + "', password = '" + password + "', email = '" + email + "', role = '" + role + "' WHERE serialno ='" + serialno + "'";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public void deleteUser(String serialno)
    {
        Connection c = null;
        PreparedStatement ps = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","abdulhameed13");

            Statement st = c.createStatement();
            String sql = "DELETE FROM users WHERE serialno ='"+ serialno + "'";
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
