package project314;

import javax.swing.*;

public class LoginController
{
    public boolean validateLogin(String username, String password)
    {
        boolean input = new Users().login(username, password);
        return input;
    }

    public String checkRole(String username)
    {
        String role = new Users().checkRole(username);
        return role;
    }

    public boolean ifExist(String email)
    {
        boolean input = new Users().newEmail(email);
        return input;
    }
}
