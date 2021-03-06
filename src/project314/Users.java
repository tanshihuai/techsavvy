package project314;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class Users
{
    //variables
    private String username;
    private String password;
    private String role;

    //default constructor
    public Users()
    {

    }

    public Users(String username, String password, String role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public boolean login(String username, String password)
    {
        boolean input = true;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            ps = c.prepareStatement("Select username from users where username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (!rs.next())
            {
                input = false;
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
            try {
                c.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return input;
    }

    public String checkRole(String username)
    {
        String role = "";
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");
            ps = c.prepareStatement("Select Role from users where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next())
            {
                role = rs.getString(1);
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
            try {
                c.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return role;
    }

    public boolean newEmail(String email)
    {
        Connection c = null;
        PreparedStatement ps = null;
        boolean input = false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/project314","root","mysql123");

            Statement st = c.createStatement();
            String sql = "insert into customers values('"+ email + "');";
            st.executeUpdate(sql);
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

}