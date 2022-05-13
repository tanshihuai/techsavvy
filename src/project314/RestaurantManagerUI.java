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
public class RestaurantManagerUI extends JFrame
{
    //variables
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
    JTextField searchField;
    DefaultTableModel model;
    private int selectedRow;
    ManagerController mc;
    private String username;

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

        createButton = new JButton("Create new items");
        createButton.setBounds(250, 370, 100, 20);
        menuFrame.getContentPane().add(createButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(250, 340, 100, 20);
        menuFrame.getContentPane().add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 310, 100, 20);
        menuFrame.getContentPane().add(deleteButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(250, 280, 100, 20);
        menuFrame.getContentPane().add(logoutButton);

        logoutButton.addActionListener (e ->
        {
            if (e.getSource () == logoutButton)
            {
                new LoginUI();
                menuFrame.dispose();
            }
        });

        /*JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(250, 250, 100, 20);
        menuFrame.getContentPane().add(refreshButton);

        refreshButton.addActionListener (e ->
        {
            if (e.getSource () == refreshButton)
            {
                new RestaurantManagerUI(username);
                menuFrame.dispose();
            }
        });*/

        //table
        model = new DefaultTableModel();
        model.addColumn("itemNumber");
        model.addColumn("itemName");
        model.addColumn("itemPrice");
        menuList = new JTable(model);
        menuList.setBounds(30,40,200,300);
        menuList.setRowSelectionAllowed(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 366, 107);
        menuFrame.getContentPane().add(scrollPane);
        mc = new ManagerController();
        mc.getItem(menuList);
        scrollPane.setViewportView(menuList);



        //actionListener for the buttons
        createButton.addActionListener (e ->
        {
            if (e.getSource () == createButton)
            {
                createItemUI();
                menuFrame.dispose();
            }
        });

        updateButton.addActionListener (e ->
        {
            if (e.getSource () == updateButton)
            {
                selectedRow = menuList.getSelectedRow();
                if(selectedRow != -1)
                {
                    System.out.println(selectedRow);
                    updateItemUI();
                    menuFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to update");
                }
            }
        });

        deleteButton.addActionListener (e ->
        {
            if (e.getSource () == deleteButton)
            {
                //deleteItemUI();
                int selectedRow = menuList.getSelectedRow();
                if(selectedRow != -1)
                {
                    // remove selected row from the model
                    String itemNumber = model.getValueAt(selectedRow, 0).toString();
                    mc.deleteItem(itemNumber);
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        });
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
        String itemNumber =  model.getValueAt(selectedRow, 0).toString();
        itemNumber3.setText(itemNumber);
        itemNumber3.setEditable(false);
        updateFrame.getContentPane().add(itemNumber3);

        JTextField itemName3 = new JTextField();
        itemName3.setFont(new Font("Serif", Font.PLAIN, 14));
        itemName3.setBounds(145, 155, 65, 31);
        String currItemName = model.getValueAt(selectedRow, 1).toString();
        itemName3.setText(currItemName);
        updateFrame.getContentPane().add(itemName3);

        JTextField itemPrice3 = new JTextField();
        itemPrice3.setFont(new Font("Serif", Font.PLAIN, 14));
        itemPrice3.setBounds(145, 196, 65, 31);
        String currItemPrice = model.getValueAt(selectedRow, 2).toString();
        itemPrice3.setText(currItemPrice);
        updateFrame.getContentPane().add(itemPrice3);

        JButton updateButton1 = new JButton("Update");
        updateButton1.setBounds(125, 216, 150, 20);
        updateFrame.getContentPane().add(updateButton1);

        updateButton1.addActionListener (e ->
        {
            if (e.getSource () == updateButton1)
            {
                String itemName = itemName3.getText();
                double itemPrice = Double.parseDouble(itemPrice3.getText());
                mc.updateItem(itemNumber, itemName, itemPrice);
                updateFrame.dispose();
                new RestaurantManagerUI(username);

                JOptionPane.showMessageDialog(null, "Selected row updated successfully");

            }
        });
        model.fireTableDataChanged();
    }

    private void createItemUI()
    {
        createFrame = new JFrame("Create new items");
        createFrame.setResizable(false);
        createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createFrame.setBounds(100, 100, 359, 369);
        createFrame.getContentPane().setLayout(null);
        createFrame.setVisible(true);

        JLabel itemNumberr = new JLabel("Item No:");
        itemNumberr.setBounds(70, 114, 65, 31);
        itemNumberr.setFont(new Font("Serif", Font.PLAIN, 14));
        createFrame.getContentPane().add(itemNumberr);

        JLabel itemNamee = new JLabel("Item Name:");
        itemNamee.setFont(new Font("Serif", Font.PLAIN, 14));
        itemNamee.setBounds(70, 155, 65, 31);
        createFrame.getContentPane().add(itemNamee);

        JLabel itemPricee = new JLabel("Item Price:");
        itemPricee.setFont(new Font("Serif", Font.PLAIN, 14));
        itemPricee.setBounds(70, 196, 65, 31);
        createFrame.getContentPane().add(itemPricee);

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
                String itemNumber = itemNumber1.getText();
                String itemName = itemName1.getText();
                Double itemPrice = Double.valueOf(itemPrice1.getText());

                //mc.ifExist(itemNumber, itemName, itemPrice);
                if(mc.ifExist(itemNumber, itemName, itemPrice))
                {
                    JOptionPane.showMessageDialog(null, "Successfully added");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Item already exists");
                }
                createFrame.dispose();
                new RestaurantManagerUI(username);
            }

        });

    }

}