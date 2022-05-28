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
    JFrame managerFrame;
    JFrame managerUpdateFrame;
    JFrame managerCreateFrame;

    JFrame managerViewMenuFrame;
    JFrame managerDeleteFrame;
    JButton managerViewButton;
    JButton managerCreateButton;
    JButton managerUpdateButton;
    JButton managerDeleteButton;
    JButton managerLogoutButton;
    JTable managerMenuList;

    JTextField managerSearchField;
    DefaultTableModel managerModel;

    private int managerSelectedRow;
    ManagerController mc;
    private String username;

    public RestaurantManagerUI()
    {
        menuDisplayPage();
    }

    private void menuDisplayPage()
    {
        //main frame
        managerFrame = new JFrame("Manager");
        managerFrame.setResizable(false);
        managerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerFrame.setSize(700,500);
        managerFrame.getContentPane().setBackground(Color.darkGray);
        managerFrame.getContentPane().setLayout(null);
        managerFrame.setVisible(true);

        managerCreateButton = new JButton("Create new items");
        managerCreateButton.setBounds(70, 80, 150, 35);
        managerCreateButton.setBorder(BorderFactory.createLoweredBevelBorder());
        managerCreateButton.setBackground(Color.WHITE);
        managerCreateButton.setForeground(Color.BLACK);
        managerFrame.getContentPane().add(managerCreateButton);

        managerUpdateButton = new JButton("Update");
        managerUpdateButton.setBounds(70, 120, 150, 35);
        managerUpdateButton.setBorder(BorderFactory.createLoweredBevelBorder());
        managerUpdateButton.setBackground(Color.WHITE);
        managerUpdateButton.setForeground(Color.BLACK);
        managerFrame.getContentPane().add(managerUpdateButton);

        managerDeleteButton = new JButton("Suspend");
        managerDeleteButton.setBounds(70, 160, 150, 35);
        managerDeleteButton.setBorder(BorderFactory.createLoweredBevelBorder());
        managerDeleteButton.setBackground(Color.WHITE);
        managerDeleteButton.setForeground(Color.BLACK);
        managerFrame.getContentPane().add(managerDeleteButton);

        managerLogoutButton = new JButton("Logout");
        managerLogoutButton.setBounds(70, 200, 150, 35);
        managerLogoutButton.setBorder(BorderFactory.createLoweredBevelBorder());
        managerLogoutButton.setBackground(Color.WHITE);
        managerLogoutButton.setForeground(Color.BLACK);
        managerFrame.getContentPane().add(managerLogoutButton);

        managerLogoutButton.addActionListener (e ->
        {
            if (e.getSource () == managerLogoutButton)
            {
                new LoginUI();
                managerFrame.dispose();
            }
        });

        //table
        managerModel = new DefaultTableModel();
        managerModel.addColumn("Item Number");
        managerModel.addColumn("Item Name");
        managerModel.addColumn("Item Price");
        managerMenuList = new JTable(managerModel);
        managerMenuList.setBounds(250,80,380,240);
        managerMenuList.setRowSelectionAllowed(true);
        managerMenuList.setBackground(Color.WHITE);
        managerMenuList.setForeground(Color.BLACK);
        JScrollPane scrollPaneManager = new JScrollPane();
        scrollPaneManager.setBounds(250,80,380,240);
        managerFrame.getContentPane().add(scrollPaneManager);
        mc = new ManagerController();
        mc.getItem(managerMenuList);
        scrollPaneManager.setViewportView(managerMenuList);
        scrollPaneManager.setBackground(Color.WHITE);
        scrollPaneManager.setForeground(Color.BLACK);

        //actionListener for the buttons
        managerCreateButton.addActionListener (e ->
        {
            if (e.getSource () == managerCreateButton)
            {
                createItemUI();
                managerFrame.dispose();
            }
        });

        managerUpdateButton.addActionListener (e ->
        {
            if (e.getSource () == managerUpdateButton)
            {
                managerSelectedRow = managerMenuList.getSelectedRow();
                if(managerSelectedRow != -1)
                {
                    System.out.println(managerSelectedRow);
                    updateItemUI();
                    managerFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to update");
                }
            }
        });

        managerDeleteButton.addActionListener (e ->
        {
            if (e.getSource () == managerDeleteButton)
            {
                //deleteItemUI();
                int selectedRow = managerMenuList.getSelectedRow();
                if(selectedRow != -1)
                {
                    // remove selected row from the model
                    String itemNumber = managerModel.getValueAt(selectedRow, 0).toString();
                    mc.deleteItem(itemNumber);
                    managerModel.removeRow(selectedRow);
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
        managerUpdateFrame = new JFrame("Update");
        managerUpdateFrame.setResizable(false);
        managerUpdateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerUpdateFrame.setSize(700, 500);
        managerUpdateFrame.getContentPane().setBackground(Color.darkGray);
        managerUpdateFrame.getContentPane().setLayout(null);
        managerUpdateFrame.setVisible(true);

        JLabel uItemNo = new JLabel("Item No:");
        uItemNo.setBounds(180, 90, 120, 31);
        uItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        uItemNo.setForeground(Color.WHITE);
        managerUpdateFrame.getContentPane().add(uItemNo);

        JLabel uItemName = new JLabel("Item Name:");
        uItemName.setFont(new Font("Serif", Font.BOLD, 14));
        uItemName.setBounds(180, 130, 120, 31);
        uItemName.setForeground(Color.WHITE);
        managerUpdateFrame.getContentPane().add(uItemName);

        JLabel uItemPrice = new JLabel("Item Price:");
        uItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        uItemPrice.setBounds(180, 170, 120, 31);
        uItemPrice.setForeground(Color.WHITE);
        managerUpdateFrame.getContentPane().add(uItemPrice);

        JTextField updateItemNo = new JTextField();
        updateItemNo.setBounds(260, 90, 200, 31);
        updateItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        updateItemNo.setBorder(BorderFactory.createLoweredBevelBorder());
        String itemNumber =  managerModel.getValueAt(managerSelectedRow, 0).toString();
        updateItemNo.setText(itemNumber);
        updateItemNo.setEditable(false);
        managerUpdateFrame.getContentPane().add(updateItemNo);

        JTextField updateItemName = new JTextField();
        updateItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        updateItemName.setBounds(260, 130, 200, 31);
        updateItemName.setBorder(BorderFactory.createLoweredBevelBorder());
        String currItemName = managerModel.getValueAt(managerSelectedRow, 1).toString();
        updateItemName.setText(currItemName);
        managerUpdateFrame.getContentPane().add(updateItemName);

        JTextField updateItemPrice = new JTextField();
        updateItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        updateItemPrice.setBounds(260, 170, 200, 31);
        updateItemPrice.setBorder(BorderFactory.createLoweredBevelBorder());
        String currItemPrice = managerModel.getValueAt(managerSelectedRow, 2).toString();
        updateItemPrice.setText(currItemPrice);
        managerUpdateFrame.getContentPane().add(updateItemPrice);

        JButton menuUpdate = new JButton("Update");
        menuUpdate.setBounds(220, 240, 150, 35);
        menuUpdate.setBorder(BorderFactory.createLoweredBevelBorder());
        menuUpdate.setBackground(Color.WHITE);
        menuUpdate.setForeground(Color.BLACK);
        managerUpdateFrame.getContentPane().add(menuUpdate);

        menuUpdate.addActionListener (e ->
        {
            if (e.getSource () == menuUpdate)
            {
                String itemName = updateItemName.getText();
                double itemPrice = Double.parseDouble(updateItemPrice.getText());
                if (itemName.isBlank())
                {
                    //boolean input = false;
                    JOptionPane.showMessageDialog(null, "Please update the item name");
                }
                else{
                    mc.updateItem(itemNumber, itemName, itemPrice);
                    managerUpdateFrame.dispose();
                    menuDisplayPage();
                    JOptionPane.showMessageDialog(null, "Selected row updated successfully");
                }
            }
        });
        managerModel.fireTableDataChanged();
    }

    private void createItemUI()
    {
        managerCreateFrame = new JFrame("Create new items");
        managerCreateFrame.setResizable(false);
        managerCreateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerCreateFrame.setSize(700, 500);
        managerCreateFrame.getContentPane().setBackground(Color.darkGray);
        managerCreateFrame.getContentPane().setLayout(null);
        managerCreateFrame.setVisible(true);

        JLabel cItemNo = new JLabel("Item No:");
        cItemNo.setBounds(180, 90, 120, 31);
        cItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        cItemNo.setForeground(Color.WHITE);
        managerCreateFrame.getContentPane().add(cItemNo);

        JLabel cItemName = new JLabel("Item Name:");
        cItemName.setFont(new Font("Serif", Font.BOLD, 14));
        cItemName.setBounds(180, 130, 120, 31);
        cItemName.setForeground(Color.WHITE);
        managerCreateFrame.getContentPane().add(cItemName);

        JLabel cItemPrice = new JLabel("Item Price:");
        cItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        cItemPrice.setBounds(180, 170, 120, 31);
        cItemPrice.setForeground(Color.WHITE);
        managerCreateFrame.getContentPane().add(cItemPrice);

        JTextField createItemNo = new JTextField();
        createItemNo.setBounds(260, 90, 200, 31);
        createItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        createItemNo.setBorder(BorderFactory.createLoweredBevelBorder());
        managerCreateFrame.getContentPane().add(createItemNo);

        JTextField createItemName = new JTextField();
        createItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        createItemName.setBorder(BorderFactory.createLoweredBevelBorder());
        createItemName.setBounds(260, 130, 200, 31);
        managerCreateFrame.getContentPane().add(createItemName);

        JTextField createItemPrice = new JTextField();
        createItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        createItemPrice.setBorder(BorderFactory.createLoweredBevelBorder());
        createItemPrice.setBounds(260, 170, 200, 31);
        managerCreateFrame.getContentPane().add(createItemPrice);

        JButton addNewItem = new JButton("Add");
        addNewItem.setBounds(220, 240, 150, 35);
        addNewItem.setBorder(BorderFactory.createLoweredBevelBorder());
        addNewItem.setBackground(Color.WHITE);
        addNewItem.setForeground(Color.BLACK);
        managerCreateFrame.getContentPane().add(addNewItem);

        addNewItem.addActionListener (e ->
        {
            if (e.getSource () == addNewItem)
            {
                String itemNumber = createItemNo.getText();
                String itemName = createItemName.getText();
                Double itemPrice = Double.valueOf(createItemPrice.getText());

                //mc.ifExist(itemNumber, itemName, itemPrice);
                if(mc.ifExist(itemNumber, itemName, itemPrice))
                {
                    JOptionPane.showMessageDialog(null, "Successfully added");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Item already exists");
                }
                managerCreateFrame.dispose();
                menuDisplayPage();
            }

        });

    }

}