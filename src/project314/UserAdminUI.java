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
    JFrame userAdminUpdateFrame;
    JFrame userAdminCreateFrame;
    JButton userAdminCreateButton;
    JButton userAdminUpdateButton;
    JButton userAdminDeleteButton;
    JButton userAdminLogoutButton;
    DefaultTableModel credentialsModel;
    JTable userAdminUsersList;
    private int userAdminSelectedRow;
    UserAdminController uac;
    public UserAdminUI()
    {
        userAdminDisplayPage();
    }

    private void userAdminDisplayPage() {
        userAdminFrame = new JFrame("User Admin");
        userAdminFrame.setResizable(false);
        userAdminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userAdminFrame.setSize(1000,500);
        userAdminFrame.getContentPane().setBackground(Color.darkGray);
        userAdminFrame.getContentPane().setLayout(null);
        userAdminFrame.setVisible(true);

        userAdminCreateButton = new JButton("Create new users");
        userAdminCreateButton.setBounds(70, 80, 150, 35);
        userAdminCreateButton.setBorder(BorderFactory.createLoweredBevelBorder());
        userAdminCreateButton.setBackground(Color.WHITE);
        userAdminCreateButton.setForeground(Color.BLACK);
        userAdminFrame.getContentPane().add(userAdminCreateButton);

        userAdminUpdateButton = new JButton("Update");
        userAdminUpdateButton.setBounds(70, 120, 150, 35);
        userAdminUpdateButton.setBorder(BorderFactory.createLoweredBevelBorder());
        userAdminUpdateButton.setBackground(Color.WHITE);
        userAdminUpdateButton.setForeground(Color.BLACK);
        userAdminFrame.getContentPane().add(userAdminUpdateButton);

        userAdminDeleteButton = new JButton("Suspend");
        userAdminDeleteButton.setBounds(70, 160, 150, 35);
        userAdminDeleteButton.setBorder(BorderFactory.createLoweredBevelBorder());
        userAdminDeleteButton.setBackground(Color.WHITE);
        userAdminDeleteButton.setForeground(Color.BLACK);
        userAdminFrame.getContentPane().add(userAdminDeleteButton);

        userAdminLogoutButton = new JButton("Logout");
        userAdminLogoutButton.setBounds(70, 200, 150, 35);
        userAdminLogoutButton.setBorder(BorderFactory.createLoweredBevelBorder());
        userAdminLogoutButton.setBackground(Color.WHITE);
        userAdminLogoutButton.setForeground(Color.BLACK);
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
        credentialsModel.addColumn("Serial No");
        credentialsModel.addColumn("Full Name");
        credentialsModel.addColumn("Last name");
        credentialsModel.addColumn("Username");
        credentialsModel.addColumn("Password");
        credentialsModel.addColumn("Email");
        credentialsModel.addColumn("Role");
        userAdminUsersList = new JTable(credentialsModel);
        userAdminUsersList.setBounds(250,80,700,280);
        userAdminUsersList.setRowSelectionAllowed(true);
        userAdminUsersList.setBackground(Color.WHITE);
        userAdminUsersList.setForeground(Color.BLACK);
        JScrollPane scrollPaneManager = new JScrollPane();
        scrollPaneManager.setBounds(250,80,700,280);
        userAdminFrame.getContentPane().add(scrollPaneManager);
        uac = new UserAdminController();
        uac.getUsers(userAdminUsersList);
        scrollPaneManager.setViewportView(userAdminUsersList);
        scrollPaneManager.setBackground(Color.WHITE);
        scrollPaneManager.setForeground(Color.BLACK);

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
                int selectedRow = userAdminUsersList.getSelectedRow();
                if(selectedRow != -1)
                {
                    // remove selected row from the model
                    String serialno = credentialsModel.getValueAt(selectedRow, 0).toString();
                    uac.deleteUser(serialno);
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
        userAdminCreateFrame.setSize(700, 500);
        userAdminCreateFrame.getContentPane().setBackground(Color.darkGray);
        userAdminCreateFrame.getContentPane().setLayout(null);
        userAdminCreateFrame.setVisible(true);

        JLabel cSerialNo = new JLabel("Serial No:");
        cSerialNo.setBounds(180, 40, 90, 31);
        cSerialNo.setFont(new Font("Serif", Font.BOLD, 14));
        cSerialNo.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cSerialNo);

        JLabel cFname = new JLabel("First Name:");
        cFname.setBounds(180, 80, 90, 31);
        cFname.setFont(new Font("Serif", Font.BOLD, 14));
        cFname.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cFname);

        JLabel cLname = new JLabel("Last Name:");
        cLname.setBounds(180, 120, 90, 31);
        cLname.setFont(new Font("Serif", Font.BOLD, 14));
        cLname.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cLname);

        JLabel cUsername = new JLabel("Username:");
        cUsername.setFont(new Font("Serif", Font.BOLD, 14));
        cUsername.setBounds(180, 160, 90, 31);
        cUsername.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cUsername);

        JLabel cPass = new JLabel("Password:");
        cPass.setBounds(180, 200, 90, 31);
        cPass.setFont(new Font("Serif", Font.BOLD, 14));
        cPass.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cPass);

        JLabel cEmail = new JLabel("Email Address:");
        cEmail.setFont(new Font("Serif", Font.BOLD, 14));
        cEmail.setBounds(180, 240, 90, 31);
        cEmail.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cEmail);

        JLabel cRole = new JLabel("Role:");
        cRole.setFont(new Font("Serif", Font.BOLD, 14));
        cRole.setBounds(180, 280, 90, 31);
        cRole.setForeground(Color.WHITE);
        userAdminCreateFrame.getContentPane().add(cRole);

        JTextField createSerialNo= new JTextField();
        createSerialNo.setBounds(290, 40, 200, 31);
        createSerialNo.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createSerialNo);

        JTextField createFname = new JTextField();
        createFname.setBounds(290, 80, 200, 31);
        createFname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createFname);

        JTextField createLname = new JTextField();
        createLname.setBounds(290, 120, 200, 31);
        createLname.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createLname);

        JTextField createUsername = new JTextField();
        createUsername.setFont(new Font("Serif", Font.BOLD, 14));
        createUsername.setBounds(290, 160, 200, 31);
        userAdminCreateFrame.getContentPane().add(createUsername);

        JTextField createPass = new JTextField();
        createPass.setBounds(290, 200, 200, 31);
        createPass.setFont(new Font("Serif", Font.BOLD, 14));
        userAdminCreateFrame.getContentPane().add(createPass);

        JTextField createEmail = new JTextField();
        createEmail.setFont(new Font("Serif", Font.BOLD, 14));
        createEmail.setBounds(290, 240, 200, 31);
        userAdminCreateFrame.getContentPane().add(createEmail);

        JTextField createRole = new JTextField();
        createRole.setFont(new Font("Serif", Font.BOLD, 14));
        createRole.setBounds(290, 280, 200, 31);
        userAdminCreateFrame.getContentPane().add(createRole);

        JButton addNewUser = new JButton("Add");
        addNewUser.setBounds(220, 350, 150, 35);
        addNewUser.setBorder(BorderFactory.createLoweredBevelBorder());
        addNewUser.setBackground(Color.WHITE);
        addNewUser.setForeground(Color.BLACK);
        userAdminCreateFrame.getContentPane().add(addNewUser);

        addNewUser.addActionListener (e ->
        {
            if (e.getSource () == addNewUser)
            {
                String serialno = createSerialNo.getText();
                String fname = createFname.getText();
                String lname = createLname.getText();
                String username = createUsername.getText();
                String password = createPass.getText();
                String email = createEmail.getText();
                String role = createRole.getText();

                if(uac.ifExist(serialno, fname, lname, username, password, email, role))
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
        userAdminUpdateFrame.setSize(700, 500);
        userAdminUpdateFrame.getContentPane().setBackground(Color.darkGray);
        userAdminUpdateFrame.getContentPane().setLayout(null);
        userAdminUpdateFrame.setVisible(true);

        JLabel uSerialNo = new JLabel("Serial No:");
        uSerialNo.setBounds(180, 40, 90, 31);
        uSerialNo.setFont(new Font("Serif", Font.BOLD, 14));
        uSerialNo.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uSerialNo);

        JLabel uFname = new JLabel("First Name:");
        uFname.setBounds(180, 80, 90, 31);
        uFname.setFont(new Font("Serif", Font.BOLD, 14));
        uFname.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uFname);

        JLabel uLname = new JLabel("Last Name:");
        uLname.setBounds(180, 120, 90, 31);
        uLname.setFont(new Font("Serif", Font.BOLD, 14));
        uLname.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uLname);

        JLabel uUsername = new JLabel("Username:");
        uUsername.setBounds(180, 160, 90, 31);
        uUsername.setFont(new Font("Serif", Font.BOLD, 14));
        uUsername.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uUsername);

        JLabel uPass = new JLabel("Password:");
        uPass.setBounds(180, 200, 90, 31);
        uPass.setFont(new Font("Serif", Font.BOLD, 14));
        uPass.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uPass);

        JLabel uEmail = new JLabel("Email Address:");
        uEmail.setBounds(180, 240, 90, 31);
        uEmail.setFont(new Font("Serif", Font.BOLD, 14));
        uEmail.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uEmail);

        JLabel uRole = new JLabel("Role:");
        uRole.setBounds(180, 280, 90, 31);
        uRole.setFont(new Font("Serif", Font.BOLD, 14));
        uRole.setForeground(Color.WHITE);
        userAdminUpdateFrame.getContentPane().add(uRole);

        JTextField updateSerialNo = new JTextField();
        updateSerialNo.setBounds(260, 40, 200, 31);
        updateSerialNo.setFont(new Font("Serif", Font.PLAIN, 14));
        updateSerialNo.setBorder(BorderFactory.createLoweredBevelBorder());
        String serialNo =  credentialsModel.getValueAt(userAdminSelectedRow, 0).toString();
        updateSerialNo.setText(serialNo);
        updateSerialNo.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateSerialNo);

        JTextField updateFname = new JTextField();
        updateFname.setBounds(260, 80, 200, 31);
        updateFname.setFont(new Font("Serif", Font.PLAIN, 14));
        updateFname.setBorder(BorderFactory.createLoweredBevelBorder());
        String fname =  credentialsModel.getValueAt(userAdminSelectedRow, 1).toString();
        updateFname.setText(fname);
        updateFname.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateFname);

        JTextField updateLname = new JTextField();
        updateLname.setBounds(260, 120, 200, 31);
        updateLname.setFont(new Font("Serif", Font.PLAIN, 14));
        updateLname.setBorder(BorderFactory.createLoweredBevelBorder());
        String lname =  credentialsModel.getValueAt(userAdminSelectedRow, 2).toString();
        updateLname.setText(lname);
        updateLname.setEditable(false);
        userAdminUpdateFrame.getContentPane().add(updateLname);

        JTextField updateUsername = new JTextField();
        updateUsername.setBounds(260, 160, 200, 31);
        updateUsername.setFont(new Font("Serif", Font.PLAIN, 14));
        updateUsername.setBorder(BorderFactory.createLoweredBevelBorder());
        String currUsername =  credentialsModel.getValueAt(userAdminSelectedRow, 3).toString();
        updateUsername.setText(currUsername);
        userAdminUpdateFrame.getContentPane().add(updateUsername);

        JTextField updatePass = new JTextField();
        updatePass.setBounds(260, 200, 200, 31);
        updatePass.setFont(new Font("Serif", Font.PLAIN, 14));
        updatePass.setBorder(BorderFactory.createLoweredBevelBorder());
        String currPassword =  credentialsModel.getValueAt(userAdminSelectedRow, 4).toString();
        updatePass.setText(currPassword);
        userAdminUpdateFrame.getContentPane().add(updatePass);

        JTextField updateEmail = new JTextField();
        updateEmail.setBounds(260, 240, 200, 31);
        updateEmail.setFont(new Font("Serif", Font.PLAIN, 14));
        updateEmail.setBorder(BorderFactory.createLoweredBevelBorder());
        String currEmail =  credentialsModel.getValueAt(userAdminSelectedRow, 5).toString();
        updateEmail.setText(currEmail);
        userAdminUpdateFrame.getContentPane().add(updateEmail);

        JTextField updateRole = new JTextField();
        updateRole.setBounds(260, 280, 200, 31);
        updateRole.setFont(new Font("Serif", Font.PLAIN, 14));
        updateRole.setBorder(BorderFactory.createLoweredBevelBorder());
        String currRole =  credentialsModel.getValueAt(userAdminSelectedRow, 6).toString();
        updateRole.setText(currRole);
        userAdminUpdateFrame.getContentPane().add(updateRole);

        JButton updateUser = new JButton("Update");
        updateUser.setBounds(220, 350, 150, 35);
        updateUser.setBorder(BorderFactory.createLoweredBevelBorder());
        updateUser.setBackground(Color.WHITE);
        updateUser.setForeground(Color.BLACK);
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
