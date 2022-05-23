package project314;

//imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;

//main class
public class UserAdminUI extends JFrame
{
    JFrame userAdminFrame;

    JButton userAdminCreateButton;

    JButton userAdminUpdateButton;

    JButton userAdminDeleteButton;

    JButton userAdminLogoutButton;

    DefaultTableModel credentialsModel;

    JTable userAdminUsersList;

    JScrollPane scrollPaneManager;

    private int userAdminSelectedRow;

    JFrame userAdminCreateFrame;

    JLabel cSerialNo;

    JLabel cFname;

    JLabel cLname;

    JLabel cUsername;

    JLabel cPass;

    JLabel cEmail;

    JLabel cRole;

    JTextField createSerialNo;

    JTextField createFname;

    JTextField createLname;

    JTextField createUsername;

    JTextField createPass;

    JTextField createEmail;

    JTextField createRole;

    JButton addNewUser;

    JFrame userAdminUpdateFrame;

    JLabel uSerialNo;

    JLabel uFname;

    JLabel uLname;

    JLabel uUsername;

    JLabel uPass;

    JLabel uEmail;

    JLabel uRole;

    JTextField updateSerialNo;

    JTextField updateFname;

    JTextField updateLname;

    JTextField updateUsername;

    JTextField updatePass;

    JTextField updateEmail;

    JTextField updateRole;

    JButton updateUser;

    UserAdminController uac;

    public UserAdminUI()
    {
        userAdminDisplayPage();
    }

    private void userAdminDisplayPage() {
        userAdminFrame = new JFrame("User Admin");
        userAdminFrame.setResizable(false);
        userAdminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userAdminFrame.setSize(700,500);
        userAdminFrame.getContentPane().setLayout(null);
        userAdminFrame.setVisible(true);

        userAdminCreateButton = new JButton("Create new users");
        userAdminCreateButton.setBounds(70, 80, 150, 35);
        userAdminCreateButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        userAdminCreateButton.setBackground(Color.WHITE);
        userAdminFrame.getContentPane().add(userAdminCreateButton);

        userAdminUpdateButton = new JButton("Update");
        userAdminUpdateButton.setBounds(70, 120, 150, 35);
        userAdminUpdateButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        userAdminUpdateButton.setBackground(Color.WHITE);
        userAdminFrame.getContentPane().add(userAdminUpdateButton);

        userAdminDeleteButton = new JButton("Delete");
        userAdminDeleteButton.setBounds(70, 160, 150, 35);
        userAdminDeleteButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        userAdminDeleteButton.setBackground(Color.WHITE);
        userAdminFrame.getContentPane().add(userAdminDeleteButton);

        userAdminLogoutButton = new JButton("Logout");
        userAdminLogoutButton.setBounds(70, 200, 150, 35);
        userAdminLogoutButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        userAdminLogoutButton.setBackground(Color.WHITE);
        userAdminFrame.getContentPane().add(userAdminLogoutButton);

        userAdminLogoutButton.addActionListener (e ->
        {
            if (e.getSource () == userAdminLogoutButton)
            {
                new LoginUI();
                userAdminFrame.dispose();
            }
        });

        credentialsModel = new DefaultTableModel();
        credentialsModel.addColumn("serailno");
        credentialsModel.addColumn("fname");
        credentialsModel.addColumn("lname");
        credentialsModel.addColumn("username");
        credentialsModel.addColumn("password");
        credentialsModel.addColumn("email");
        credentialsModel.addColumn("role");
        userAdminUsersList = new JTable(credentialsModel);
        userAdminUsersList.setBounds(250,80,350,200);
        userAdminUsersList.setRowSelectionAllowed(true);
        userAdminUsersList.setBackground(Color.WHITE);
        scrollPaneManager = new JScrollPane();
        scrollPaneManager.setBounds(250,80,350,200);
        userAdminFrame.getContentPane().add(scrollPaneManager);
        uac = new UserAdminController();
        uac.getUsers(userAdminUsersList);
        scrollPaneManager.setViewportView(userAdminUsersList);

        userAdminCreateButton.addActionListener (e ->
        {
            if (e.getSource () == userAdminCreateButton)
            {
                createUserUI();
                userAdminFrame.dispose();
            }
        });

        userAdminUpdateButton.addActionListener (e ->
        {
            if (e.getSource () == userAdminUpdateButton)
            {
                userAdminSelectedRow = userAdminUsersList.getSelectedRow();
                if(userAdminSelectedRow != -1)
                {
                    System.out.println(userAdminSelectedRow);
                    updateUserUI();
                    userAdminFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to update");
                }
            }
        });

        userAdminDeleteButton.addActionListener (e ->
        {
            if (e.getSource () == userAdminDeleteButton)
            {
                //deleteItemUI();
                int selectedRow = userAdminUsersList.getSelectedRow();
                if(selectedRow != -1)
                {
                    // remove selected row from the model
                    String username = credentialsModel.getValueAt(selectedRow, 0).toString();
                    uac.deleteUser(username);
                    credentialsModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        });
    }

    private void createUserUI() {
        userAdminCreateFrame = new JFrame("Create new user");
        userAdminCreateFrame.setResizable(false);
        userAdminCreateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userAdminCreateFrame.setBounds(100, 100, 400, 400);
        userAdminCreateFrame.getContentPane().setLayout(null);
        userAdminCreateFrame.setVisible(true);

        cSerialNo = new JLabel("Serial No:");
        cSerialNo.setBounds(70, 10, 90, 31);
        cSerialNo.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(cSerialNo);

        cFname = new JLabel("First Name:");
        cFname.setBounds(70, 50, 90, 31);
        cFname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(cFname);

        cLname = new JLabel("Last Name:");
        cLname.setBounds(70, 90, 90, 31);
        cLname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(cLname);

        cUsername = new JLabel("Username:");
        cUsername.setFont(new Font("Serif", Font.BOLD, 14));
        cUsername.setBounds(70, 130, 90, 31);
        userAdminCreateFrame.getContentPane().add(cUsername);

        cPass = new JLabel("Password:");
        cPass.setBounds(70, 170, 90, 31);
        cPass.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(cPass);

        cEmail = new JLabel("Email Address:");
        cEmail.setFont(new Font("Serif", Font.BOLD, 14));
        cEmail.setBounds(70, 210, 90, 31);
        userAdminCreateFrame.getContentPane().add(cEmail);

        cRole = new JLabel("Role:");
        cRole.setFont(new Font("Serif", Font.BOLD, 14));
        cRole.setBounds(70, 250, 90, 31);
        userAdminCreateFrame.getContentPane().add(cRole);

        createSerialNo= new JTextField();
        createSerialNo.setBounds(145, 10, 90, 31);
        createSerialNo.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createSerialNo);

        createFname = new JTextField();
        createFname.setBounds(145, 50, 90, 31);
        createFname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createFname);

        createLname = new JTextField();
        createLname.setBounds(145, 90, 90, 31);
        createLname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createLname);

        createUsername = new JTextField();
        createUsername.setFont(new Font("Serif", Font.BOLD, 14));
        createUsername.setBounds(145, 130, 90, 31);
        userAdminCreateFrame.getContentPane().add(createUsername);

        createPass = new JTextField();
        createPass.setBounds(145, 170, 90, 31);
        createPass.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createPass);

        createEmail = new JTextField();
        createEmail.setFont(new Font("Serif", Font.BOLD, 14));
        createEmail.setBounds(145, 210, 90, 31);
        userAdminCreateFrame.getContentPane().add(createEmail);

        createRole = new JTextField();
        createRole.setFont(new Font("Serif", Font.BOLD, 14));
        createRole.setBounds(145, 250, 90, 31);
        userAdminCreateFrame.getContentPane().add(createRole);

        addNewUser = new JButton("Add");
        addNewUser.setBounds(110, 296, 150, 35);
        addNewUser.setBorder(BorderFactory.createLineBorder(Color.RED));
        addNewUser.setBackground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(addNewUser);

        addNewUser.addActionListener (e ->
        {
            if (e.getSource () == addNewUser)
            {
                String serailNo = createSerialNo.getText();
                String fname = createFname.getText();
                String lname = createLname.getText();
                String username = createUsername.getText();
                String password = createPass.getText();
                String email = createEmail.getText();
                String role = createRole.getText();

                //mc.ifExist(itemNumber, itemName, itemPrice);
                if(uac.ifExist(serailNo, fname, lname, username, password, email, role))
                {
                    JOptionPane.showMessageDialog(null, "Successfully added");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "User already exists");
                }
                userAdminCreateFrame.dispose();
                userAdminDisplayPage();
            }

        });
    }

    private void updateUserUI() {
        userAdminUpdateFrame = new JFrame("Update");
        userAdminUpdateFrame.setResizable(false);
        userAdminUpdateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userAdminUpdateFrame.setBounds(100, 100, 400, 400);
        userAdminUpdateFrame.getContentPane().setLayout(null);
        userAdminUpdateFrame.setVisible(true);

        uSerialNo = new JLabel("Serial No:");
        uSerialNo.setBounds(70, 10, 90, 31);
        uSerialNo.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uSerialNo);

        uFname = new JLabel("First Name:");
        uFname.setBounds(70, 50, 90, 31);
        uFname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uFname);

        uLname = new JLabel("Last Name:");
        uLname.setBounds(70, 90, 90, 31);
        uLname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uLname);

        uUsername = new JLabel("Username:");
        uUsername.setBounds(70, 130, 90, 31);
        uUsername.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uUsername);

        uPass = new JLabel("Password:");
        uPass.setBounds(70, 170, 90, 31);
        uPass.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uPass);

        uEmail = new JLabel("Email Address:");
        uEmail.setBounds(70, 210, 90, 31);
        uEmail.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uEmail);

        uRole = new JLabel("Role:");
        uRole.setBounds(70, 250, 90, 31);
        uRole.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminUpdateFrame.getContentPane().add(uRole);

        updateSerialNo = new JTextField();
        updateSerialNo.setBounds(145, 10, 65, 31);
        updateSerialNo.setFont(new Font("Serif", Font.PLAIN, 14));
        updateSerialNo.setBorder(BorderFactory.createLineBorder(Color.RED));
        String serialNo =  credentialsModel.getValueAt(userAdminSelectedRow, 0).toString();
        updateSerialNo.setText(serialNo);
        updateSerialNo.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateSerialNo);

        updateFname = new JTextField();
        updateFname.setBounds(145, 50, 65, 31);
        updateFname.setFont(new Font("Serif", Font.PLAIN, 14));
        updateFname.setBorder(BorderFactory.createLineBorder(Color.RED));
        String fname =  credentialsModel.getValueAt(userAdminSelectedRow, 0).toString();
        updateFname.setText(fname);
        updateFname.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateFname);

        updateLname = new JTextField();
        updateLname.setBounds(145, 90, 65, 31);
        updateLname.setFont(new Font("Serif", Font.PLAIN, 14));
        updateLname.setBorder(BorderFactory.createLineBorder(Color.RED));
        String lname =  credentialsModel.getValueAt(userAdminSelectedRow, 1).toString();
        updateLname.setText(lname);
        updateLname.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateLname);

        updateUsername = new JTextField();
        updateUsername.setBounds(145, 130, 65, 31);
        updateUsername.setFont(new Font("Serif", Font.PLAIN, 14));
        updateUsername.setBorder(BorderFactory.createLineBorder(Color.RED));
        String currUsername =  credentialsModel.getValueAt(userAdminSelectedRow, 2).toString();
        updateUsername.setText(currUsername);
        //updateUsername.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateUsername);

        updatePass = new JTextField();
        updatePass.setBounds(145, 170, 65, 31);
        updatePass.setFont(new Font("Serif", Font.PLAIN, 14));
        updatePass.setBorder(BorderFactory.createLineBorder(Color.RED));
        String currPassword =  credentialsModel.getValueAt(userAdminSelectedRow, 3).toString();
        updatePass.setText(currPassword);
        //updatePass.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updatePass);

        updateEmail = new JTextField();
        updateEmail.setBounds(145, 210, 65, 31);
        updateEmail.setFont(new Font("Serif", Font.PLAIN, 14));
        updateEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
        String currEmail =  credentialsModel.getValueAt(userAdminSelectedRow, 4).toString();
        updateEmail.setText(currEmail);
        //updateEmail.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateEmail);

        updateRole = new JTextField();
        updateRole.setBounds(145, 250, 65, 31);
        updateRole.setFont(new Font("Serif", Font.PLAIN, 14));
        updateRole.setBorder(BorderFactory.createLineBorder(Color.RED));
        String currRole =  credentialsModel.getValueAt(userAdminSelectedRow, 5).toString();
        updateRole.setText(currRole);
        //updateRole.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateRole);

        updateUser = new JButton("Update");
        updateUser.setBounds(110, 296, 150, 35);
        updateUser.setBorder(BorderFactory.createLineBorder(Color.RED));
        updateUser.setBackground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(updateUser);

        updateUser.addActionListener (e ->
        {
            if (e.getSource () == updateUser)
            {
                String username = updateUsername.getText();
                String password = updatePass.getText();
                String email = updateEmail.getText();
                String role = updateRole.getText();
                if (username.isBlank()||password.isBlank()||email.isBlank()||role.isBlank())
                {
                    //boolean input = false;
                    JOptionPane.showMessageDialog(null, "Please update the user accordingly");
                }
                else{
                    uac.updateUser(serialNo, fname, lname, username, password, email, role);
                    userAdminUpdateFrame.dispose();
                    userAdminDisplayPage();
                    JOptionPane.showMessageDialog(null, "Selected row updated successfully");
                }
            }
        });
    }
}
