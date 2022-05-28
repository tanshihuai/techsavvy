package project314;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdminController {
    public void getUsers(JTable userAdminUsersList)
    {
        new Credentials().getUsers(userAdminUsersList);
    }

    public boolean ifExist(String serialno, String fname, String lname, String username, String password, String email, String role)
    {
        boolean input = new Credentials().createUser(serialno, fname, lname, username, password, email, role);
        return input;
    }

    public void updateUser(String serailno, String fname, String lname, String username, String password, String email, String role)
    {
        new Credentials().updateUser(serailno, fname, lname, username, password, email, role);
    }

    public void deleteUser(String serialno)
    {
        new Credentials().deleteUser(serialno);
    }
}
