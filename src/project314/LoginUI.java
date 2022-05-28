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
    JFrame loginFrame;
    JButton loginButton;
    JLabel userLabel;
    JLabel passLabel;
    JLabel check;
    JTextField usernameField;
    JPasswordField passwordField;
    JFrame customerDetailsFrame;
    JTextField emailField;
    JTextField tableNoField;

    //LoginController lc;


    //main method
    public static void main(String[] args)
    {
        new LoginUI();

    }
    public LoginUI()
    {
        displayPage();
    }

    public boolean isFilled(String username, String password)
    {
        boolean input = true;
        if (username.isBlank() || password.isBlank())
        {
            input = false;
        }
        return input;
    }

    //method to display the login page
    private void displayPage()
    {
        loginFrame = new JFrame("Login");
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(700,500);
        loginFrame.getContentPane().setLayout(null);
        loginFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\abdul\\Documents\\techsavvy2\\restaurant.jpg")));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 114, 100, 35);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        usernameLabel.setForeground(Color.BLACK);
        loginFrame.getContentPane().add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 155, 80, 35);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 18));
        passwordLabel.setForeground(Color.BLACK);
        loginFrame.getContentPane().add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(290, 121, 150, 30);
        usernameField.setBorder(BorderFactory.createLoweredBevelBorder());
        loginFrame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(290, 162, 150, 30);
        passwordField.setBorder(BorderFactory.createLoweredBevelBorder());
        loginFrame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(240, 230, 150, 35);
        loginButton.setBorder(BorderFactory.createLoweredBevelBorder());
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);
        loginFrame.getContentPane().add(loginButton);

        JButton makeOrderButton = new JButton ("Make Order");
        makeOrderButton.setBounds(240, 280, 150, 35);
        makeOrderButton.setBorder(BorderFactory.createLoweredBevelBorder());
        makeOrderButton.setBackground(Color.WHITE);
        makeOrderButton.setForeground(Color.BLACK);
        loginFrame.getContentPane().add(makeOrderButton);

        makeOrderButton.addActionListener (e ->
        {
            if (e.getSource () == makeOrderButton)
            {
                customerDetailsFrame();
                loginFrame.dispose();
            }
        });

        //to print error messages
        JLabel checkLogin = new JLabel("");
        checkLogin.setFont(new Font("Serif", Font.PLAIN, 12));
        checkLogin.setForeground(Color.BLACK);
        checkLogin.setBounds(10, 299, 323, 20);
        loginFrame.getContentPane().add(checkLogin);
        loginFrame.setVisible(true);

        //actionListener (onSubmit())
        loginButton.addActionListener(e ->
        {
            if (e.getSource() == loginButton)
            {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                //loginFailed()
                LoginController lc = new LoginController();
                if (!isFilled(username, password))
                {
                    checkLogin.setText("Please enter your Username/Password!");
                    checkLogin.setForeground(Color.BLACK);
                    checkLogin.setFont(new Font("Serif", Font.BOLD, 18));
                    checkLogin.setBounds(240, 320, 500, 35);
                } else if (!lc.validateLogin(username, password))
                {
                    checkLogin.setText("Invalid Username/Password entered.");
                    checkLogin.setForeground(Color.BLACK);
                    checkLogin.setFont(new Font("Serif", Font.BOLD, 18));
                    checkLogin.setBounds(240, 320, 500, 35);
                } else
                {
                    //loginSuccess();
                    String role = lc.checkRole(username);
                    loginSuccess(role, username);
                }
            }
        });
    }

    private void customerDetailsFrame() {
        customerDetailsFrame = new JFrame("Customers Detail");
        customerDetailsFrame.setResizable(false);
        customerDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerDetailsFrame.setSize(700, 500);
        customerDetailsFrame.getContentPane().setBackground(Color.darkGray);
        customerDetailsFrame.getContentPane().setLayout(null);
        customerDetailsFrame.setVisible(true);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 114, 100, 35);
        emailLabel.setFont(new Font("Serif", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        customerDetailsFrame.getContentPane().add(emailLabel);

        JLabel tableNoLabel = new JLabel("Table No:");
        tableNoLabel.setBounds(200, 155, 80, 35);
        tableNoLabel.setFont(new Font("Serif", Font.BOLD, 18));
        tableNoLabel.setForeground(Color.WHITE);
        customerDetailsFrame.getContentPane().add(tableNoLabel);

        emailField = new JTextField();
        emailField.setBounds(290, 121, 150, 30);
        emailField.setBorder(BorderFactory.createLoweredBevelBorder());
        customerDetailsFrame.getContentPane().add(emailField);
        emailField.setColumns(10);

        tableNoField = new JTextField();
        tableNoField.setBounds(290, 162, 150, 30);
        tableNoField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        customerDetailsFrame.getContentPane().add(tableNoField);
        tableNoField.setColumns(10);

        JButton loginContinueButton = new JButton("Continue");
        loginContinueButton.setBounds(240, 230, 150, 35);
        loginContinueButton.setBorder(BorderFactory.createLoweredBevelBorder());
        loginContinueButton.setBackground(Color.WHITE);
        loginContinueButton.setForeground(Color.BLACK);
        customerDetailsFrame.getContentPane().add(loginContinueButton);

        loginContinueButton.addActionListener (e ->
        {
            if (e.getSource () == loginContinueButton)
            {
                String emailAdd = emailField.getText();
                String tableNo = tableNoField.getText();
                if (tableNo.isBlank())
                {
                    //boolean input = false;
                    JOptionPane.showMessageDialog(null, "Please enter the table number");
                }
                else{
                    LoginController lc = new LoginController();
                    lc.ifExist(emailAdd);
                    new CustomerUI();
                    customerDetailsFrame.dispose();
                }
            }
        });
    }

    private void loginSuccess(String role, String username)
    {
        switch (role)
        {
            case "User Admin":
            {
                new UserAdminUI();
                loginFrame.dispose();
                break;
            }
            case "Staff":
            {
                new RestaurantStaffUI();
                loginFrame.dispose();
                break;
            }
            case "Manager":
            {
                new RestaurantManagerUI();
                loginFrame.dispose();
                break;
            }
            case "Owner":
            {
                new RestaurantOwnerUI();
                loginFrame.dispose();
                break;
            }
        }
    }
}
