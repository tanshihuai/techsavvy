package project314;
//imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.BreakIterator;

//main class
public class RestaurantManagerUI extends JFrame
{
    //variables
    //private javax.swing.JTable jt1;
    JFrame menuFrame;
    JFrame updateFrame;
    JFrame createFrame;
    JFrame viewMenuFrame;
    JFrame deleteFrame;
    JButton viewButton;
    JButton createButton;
    JButton updateButton;
    JButton deleteButton;
    JButton logoutButton;
    JTable menuList;
    //Container contain;
    JTextField searchField;

    public RestaurantManagerUI(String username)
    {
        menuDisplayPage(username);
    }

    private void menuDisplayPage(String username)
    {
        //main frame
        menuFrame = new JFrame("Manager");
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setSize(500,500);
        menuFrame.getContentPane().setLayout(null);
        menuFrame.setVisible(true);

        //search box
        /*searchField = new JTextField();
        searchField.setBounds(145, 121, 131, 20);
        menuFrame.getContentPane().add(searchField);
        searchField.setColumns(10);*/

        //create, update and delete buttons
        viewButton = new JButton("View Menu");
        viewButton.setBounds(300, 230, 150, 20);
        menuFrame.getContentPane().add(viewButton);

        createButton = new JButton("Create new items");
        createButton.setBounds(300, 200, 150, 20);
        menuFrame.getContentPane().add(createButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(300, 170, 150, 20);
        menuFrame.getContentPane().add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 140, 150, 20);
        menuFrame.getContentPane().add(deleteButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(300, 110, 150, 20);
        menuFrame.getContentPane().add(logoutButton);

        //table

        /*String data[][] = {{"F1", "Nasi Lemak", "$2.00"},
                           {"F2", "Chicken Rice", "$3.00" }};
        String column[] = {"No.", "Name", "Price"};
        menuList = new JTable((TableModel) mc.viewItem());
        menuList.setBounds(30,40,200,300);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 366, 107);
        menuFrame.getContentPane().add(scrollPane);
        scrollPane.setViewportView(menuList);*/

        //actionListener for the buttons
        viewButton.addActionListener (e ->
        {
            if (e.getSource () == viewButton)
            {
                viewMenuUI();
            }
        });
        createButton.addActionListener (e ->
        {
            if (e.getSource () == createButton)
            {
                createItemUI();
            }
        });

        updateButton.addActionListener (e ->
        {
            if (e.getSource () == updateButton)
            {
                updateItemUI();
            }
        });
        deleteButton.addActionListener (e ->
        {
            if (e.getSource () == deleteButton)
            {
                deleteItemUI();
            }
        });
    }
    private void viewMenuUI() {
        viewMenuFrame = new JFrame("View Menu");
        viewMenuFrame.setResizable(false);
        viewMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewMenuFrame.setBounds(100, 100, 359, 369);
        viewMenuFrame.getContentPane().setLayout(null);
        viewMenuFrame.setVisible(true);
    }

    private void updateItemUI()
    {
        updateFrame = new JFrame("Update");
        updateFrame.setResizable(false);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setBounds(100, 100, 359, 369);
        updateFrame.getContentPane().setLayout(null);
        updateFrame.setVisible(true);

        JLabel itemNumber2 = new JLabel("Item No:");
        itemNumber2.setBounds(70, 114, 65, 31);
        itemNumber2.setFont(new Font("Serif", Font.PLAIN, 14));
        updateFrame.getContentPane().add(itemNumber2);

        JLabel itemName2 = new JLabel("Item Name:");
        itemName2.setFont(new Font("Serif", Font.PLAIN, 14));
        itemName2.setBounds(70, 155, 65, 31);
        updateFrame.getContentPane().add(itemName2);

        JLabel itemPrice2 = new JLabel("Item Price:");
        itemPrice2.setFont(new Font("Serif", Font.PLAIN, 14));
        itemPrice2.setBounds(70, 196, 65, 31);
        updateFrame.getContentPane().add(itemPrice2);

        JTextField itemNumber3 = new JTextField();
        itemNumber3.setBounds(145, 114, 65, 31);
        itemNumber3.setFont(new Font("Serif", Font.PLAIN, 14));
        updateFrame.getContentPane().add(itemNumber3);

        JTextField itemName3 = new JTextField();
        itemName3.setFont(new Font("Serif", Font.PLAIN, 14));
        itemName3.setBounds(145, 155, 65, 31);
        updateFrame.getContentPane().add(itemName3);

        JTextField itemPrice3 = new JTextField();
        itemPrice3.setFont(new Font("Serif", Font.PLAIN, 14));
        itemPrice3.setBounds(145, 196, 65, 31);
        updateFrame.getContentPane().add(itemPrice3);

        JButton updateButton1 = new JButton("Update");
        updateButton1.setBounds(125, 216, 150, 20);
        updateFrame.getContentPane().add(updateButton1);

        updateButton1.addActionListener (e ->
        {
            if (e.getSource () == updateButton1)
            {
                String itemNumber00 = itemNumber3.getText();
                String itemName00 = itemName3.getText();
                Double itemPrice00 = Double.valueOf(itemPrice3.getText());

                ManagerController mc = new ManagerController();
                mc.validateUpdate(itemNumber00, itemName00, itemPrice00);
            }
        });
    }

    private void deleteItemUI()
    {
        deleteFrame = new JFrame("Delete");
        deleteFrame.setResizable(false);
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setBounds(100, 100, 359, 369);
        deleteFrame.getContentPane().setLayout(null);
        deleteFrame.setVisible(true);

        JButton deleteButton1 = new JButton("Delete");
        deleteButton1.setBounds(125, 216, 150, 20);
        deleteFrame.getContentPane().add(deleteButton1);
    }

    private void createItemUI() {
        createFrame = new JFrame("Create new items");
        createFrame.setResizable(false);
        createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createFrame.setBounds(100, 100, 359, 369);
        createFrame.getContentPane().setLayout(null);
        createFrame.setVisible(true);

        JLabel itemNumber = new JLabel("Item No:");
        itemNumber.setBounds(70, 114, 65, 31);
        itemNumber.setFont(new Font("Serif", Font.PLAIN, 14));
        createFrame.getContentPane().add(itemNumber);

        JLabel itemName = new JLabel("Item Name:");
        itemName.setFont(new Font("Serif", Font.PLAIN, 14));
        itemName.setBounds(70, 155, 65, 31);
        createFrame.getContentPane().add(itemName);

        JLabel itemPrice = new JLabel("Item Price:");
        itemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        itemPrice.setBounds(70, 196, 65, 31);
        createFrame.getContentPane().add(itemPrice);

        JTextField itemNumber1 = new JTextField();
        itemNumber1.setBounds(145, 114, 65, 31);
        itemNumber1.setFont(new Font("Serif", Font.PLAIN, 14));
        createFrame.getContentPane().add(itemNumber1);

        JTextField itemName1 = new JTextField();
        itemName1.setFont(new Font("Serif", Font.PLAIN, 14));
        itemName1.setBounds(145, 155, 65, 31);
        createFrame.getContentPane().add(itemName1);

        JTextField itemPrice1 = new JTextField();
        itemPrice1.setFont(new Font("Serif", Font.PLAIN, 14));
        itemPrice1.setBounds(145, 196, 65, 31);
        createFrame.getContentPane().add(itemPrice1);

        JButton addButton = new JButton("Add");
        addButton.setBounds(125, 216, 150, 20);
        createFrame.getContentPane().add(addButton);

        addButton.addActionListener (e ->
        {
            if (e.getSource () == addButton)
            {
                String itemNumber0 = itemNumber1.getText();
                String itemName0 = itemName1.getText();
                Double itemPrice0 = Double.valueOf(itemPrice1.getText());

                ManagerController mc = new ManagerController();
                mc.ifExist(itemNumber0, itemName0, itemPrice0);
            }
        });
    }

   /* private void displayWarning()
    {
        displayWarningFrame = new JFrame("Delete");
        displayWarningFrame.setResizable(false);
        displayWarningFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayWarningFrame.setBounds(100, 100, 359, 369);
        displayWarningFrame.getContentPane().setLayout(null);
    }

    //main method

    public static void main(String[] args) {
            //new LoginUI();
            try {
                //create instance of the displayPage
                new RestaurantManagerUI();

            } catch (Exception e) {
                //handle exception
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }*/
}