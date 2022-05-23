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

    JLabel usernameLabel;

    JLabel passwordLabel;

    JLabel checkLogin;

    JButton loginButton;

    JButton makeOrderButton;

    JTextField usernameField;

    JPasswordField passwordField;

    JFrame customerDetailsFrame;

    JTextField emailField;

    JTextField tableNoField;

    JLabel emailLabel;

    JLabel tableNoLabel;

    JButton loginContinueButton;

    LoginController lc;

    //main method
    public static void main(String[] args)
    {
        new LoginUI();
    }

    public LoginUI()
    {
        displayPage();
    }

    private void displayPage() {
        //the landing page details
        loginFrame = new JFrame("Login");
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(700, 500);
        loginFrame.getContentPane().setLayout(null);
        loginFrame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\vimal\\Desktop\\back.jpg")));

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 114, 100, 35);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        loginFrame.getContentPane().add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 155, 80, 35);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 18));
        loginFrame.getContentPane().add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(290, 121, 150, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.RED));
        loginFrame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(290, 162, 150, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
        loginFrame.getContentPane().add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(240, 230, 150, 35);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        loginButton.setBackground(Color.WHITE);
        loginFrame.getContentPane().add(loginButton);

        makeOrderButton = new JButton("Make Order");
        makeOrderButton.setBounds(240, 280, 150, 35);
        makeOrderButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        makeOrderButton.setBackground(Color.WHITE);
        loginFrame.getContentPane().add(makeOrderButton);

        makeOrderButton.addActionListener(e ->
        {
            if (e.getSource() == makeOrderButton) {
                customerDetailsFrame();
                loginFrame.dispose();
            }
        });

        //to print error messages
        checkLogin = new JLabel("");
        checkLogin.setFont(new Font("Serif", Font.PLAIN, 12));
        checkLogin.setForeground(Color.RED);
        checkLogin.setBounds(10, 299, 323, 20);
        loginFrame.getContentPane().add(checkLogin);
        loginFrame.setVisible(true);

        //actionListener (onSubmit())
        loginButton.addActionListener(e ->
        {
            if (e.getSource() == loginButton) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                //loginFailed()
                lc = new LoginController();
                if (!lc.isFilled(username, password)) {
                    checkLogin.setText("Please enter your Username/Password!");
                } else if (!lc.validateLogin(username, password)) {
                    checkLogin.setText("Invalid Username/Password entered.");
                } else {
                    //loginSuccess();
                    String role = lc.checkRole(username);
                    loginSuccess(role, username);
                }
            }
        });
    }

    private void customerDetailsFrame() {
        //to enter customer email add and table number
        customerDetailsFrame = new JFrame("Customers Detail");
        customerDetailsFrame.setResizable(false);
        customerDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerDetailsFrame.setBounds(100, 100, 359, 369);
        customerDetailsFrame.getContentPane().setLayout(null);
        customerDetailsFrame.setVisible(true);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(70, 114, 65, 31);
        emailLabel.setFont(new Font("Serif", Font.BOLD, 14));
        customerDetailsFrame.getContentPane().add(emailLabel);

        tableNoLabel = new JLabel("Table Number:");
        tableNoLabel.setBounds(70, 155, 80, 31);
        tableNoLabel.setFont(new Font("Serif", Font.BOLD, 14));
        customerDetailsFrame.getContentPane().add(tableNoLabel);

        emailField = new JTextField();
        emailField.setBounds(145, 121, 150, 30);
        emailField.setBorder(BorderFactory.createLineBorder(Color.RED));
        customerDetailsFrame.getContentPane().add(emailField);
        emailField.setColumns(10);

        tableNoField = new JTextField();
        tableNoField.setBounds(145, 162, 150, 30);
        tableNoField.setBorder(BorderFactory.createLineBorder(Color.RED));
        customerDetailsFrame.getContentPane().add(tableNoField);
        tableNoField.setColumns(10);

        loginContinueButton = new JButton("Continue");
        loginContinueButton.setBounds(97, 248, 150, 31);
        loginContinueButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        loginContinueButton.setBackground(Color.WHITE);
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
                    lc = new LoginController();
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
