package project314;
//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//main class
public class LoginUI extends JFrame
{
    //variables
    JFrame frame;
    JButton loginButton;
    JLabel userLabel;
    JLabel passLabel;
    JLabel check;
    JTextField userField;
    JPasswordField passField;

    //main method
    public static void main(String[] args)
    {
        new LoginUI();

    }
    public LoginUI()
    {
        displayPage();
    }

    //method to display the login page
    private void displayPage()
    {
        frame = new JFrame("Login");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,400);
        frame.getContentPane().setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(70, 114, 65, 31);
        userLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        frame.getContentPane().add(userLabel);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        passLabel.setBounds(70, 155, 65, 31);
        frame.getContentPane().add(passLabel);

        userField = new JTextField();
        userField.setBounds(145, 121, 131, 20);
        frame.getContentPane().add(userField);
        userField.setColumns(10);

        passField = new JPasswordField();
        passField.setBounds(145, 162, 131, 20);
        frame.getContentPane().add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(97, 248, 150, 31);
        frame.getContentPane().add(loginButton);

        JButton makeOrderButton = new JButton ("Make Order");
        makeOrderButton.setBounds(97, 278, 150, 31);
        frame.getContentPane().add(makeOrderButton);

        /*makeOrderButton.addActionListener(e ->
        {
            if (e.getSource() == makeOrderButton)
            {
                LoginController lc = new LoginController();
                lc.getItem();
            }*/

        //to print error messages
        JLabel check = new JLabel("");
        check.setFont(new Font("Serif", Font.PLAIN, 12));
        check.setForeground(Color.RED);
        check.setBounds(10, 299, 323, 20);
        frame.getContentPane().add(check);
        frame.setVisible(true);

        //actionListener (onSubmit())
        loginButton.addActionListener(e ->
        {
            if (e.getSource() == loginButton)
            {
                String username = userField.getText();
                String password = String.valueOf(passField.getPassword());

                //loginFailed()
                LoginController lc = new LoginController();
                if (!lc.isFilled(username, password))
                {
                    check.setText("Please enter your Username/Password!");
                } else if (!lc.validateLogin(username, password))
                {
                    check.setText("Invalid Username/Password entered.");
                } else
                {
                    //loginSuccess();
                    String role = lc.checkRole(username);
                    loginSuccess(role, username);
                }
            }
        });

    }

    private void loginSuccess(String role, String username)
    {
        switch (role)
        {
            case "userAdmin":
            {
                new UserAdminUI();
                frame.dispose();
                break;
            }
            case "staff":
            {
                new RestaurantStaffUI();
                frame.dispose();
                break;
            }
            case "manager":
            {
                new RestaurantManagerUI(username);
                frame.dispose();
                break;
            }
            case "owner":
            {
                new RestaurantOwnerUI();
                frame.dispose();
                break;
            }
        }
    }
}
