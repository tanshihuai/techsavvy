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

    JButton managerCreateButton;

    JButton managerUpdateButton;

    JButton managerDeleteButton;

    JButton managerLogoutButton;

    JTable managerMenuList;

    DefaultTableModel managerModel;

    private int managerSelectedRow;

    JScrollPane scrollPaneManager;
    ManagerController mc;

    private String username;

    JLabel uItemNo;

    JLabel uItemName;

    JLabel uItemPrice;

    JTextField updateItemNo;

    JTextField updateItemName;

    JTextField updateItemPrice;

    JButton menuUpdate;

    JLabel cItemNo;

    JLabel cItemName;

    JLabel cItemPrice;

    JTextField createItemNo;

    JTextField createItemName;

    JTextField createItemPrice;

    JButton addNewItem;

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
        managerFrame.getContentPane().setLayout(null);
        managerFrame.setVisible(true);

        managerCreateButton = new JButton("Create new items");
        managerCreateButton.setBounds(70, 80, 150, 35);
        managerCreateButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        managerCreateButton.setBackground(Color.WHITE);
        managerFrame.getContentPane().add(managerCreateButton);

        managerUpdateButton = new JButton("Update");
        managerUpdateButton.setBounds(70, 120, 150, 35);
        managerUpdateButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        managerUpdateButton.setBackground(Color.WHITE);
        managerFrame.getContentPane().add(managerUpdateButton);

        managerDeleteButton = new JButton("Delete");
        managerDeleteButton.setBounds(70, 160, 150, 35);
        managerDeleteButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        managerDeleteButton.setBackground(Color.WHITE);
        managerFrame.getContentPane().add(managerDeleteButton);

        managerLogoutButton = new JButton("Logout");
        managerLogoutButton.setBounds(70, 200, 150, 35);
        managerLogoutButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        managerLogoutButton.setBackground(Color.WHITE);
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
        managerModel.addColumn("itemNumber");
        managerModel.addColumn("itemName");
        managerModel.addColumn("itemPrice");
        managerMenuList = new JTable(managerModel);
        managerMenuList.setBounds(250,80,350,200);
        managerMenuList.setRowSelectionAllowed(true);
        managerMenuList.setBackground(Color.WHITE);
        scrollPaneManager = new JScrollPane();
        scrollPaneManager.setBounds(250,80,350,200);
        managerFrame.getContentPane().add(scrollPaneManager);
        mc = new ManagerController();
        mc.getItem(managerMenuList);
        scrollPaneManager.setViewportView(managerMenuList);

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

    private void createItemUI()
    {
        managerCreateFrame = new JFrame("Create new items");
        managerCreateFrame.setResizable(false);
        managerCreateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerCreateFrame.setBounds(100, 100, 359, 369);
        managerCreateFrame.getContentPane().setLayout(null);
        managerCreateFrame.setVisible(true);

        cItemNo = new JLabel("Item No:");
        cItemNo.setBounds(70, 90, 90, 31);
        cItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        managerCreateFrame.getContentPane().add(cItemNo);

        cItemName = new JLabel("Item Name:");
        cItemName.setFont(new Font("Serif", Font.BOLD, 14));
        cItemName.setBounds(70, 130, 90, 31);
        managerCreateFrame.getContentPane().add(cItemName);

        cItemPrice = new JLabel("Item Price:");
        cItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        cItemPrice.setBounds(70, 170, 90, 31);
        managerCreateFrame.getContentPane().add(cItemPrice);

        createItemNo = new JTextField();
        createItemNo.setBounds(145, 90, 65, 31);
        createItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        createItemNo.setBorder(BorderFactory.createLineBorder(Color.RED));
        managerCreateFrame.getContentPane().add(createItemNo);

        createItemName = new JTextField();
        createItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        createItemName.setBorder(BorderFactory.createLineBorder(Color.RED));
        createItemName.setBounds(145, 130, 65, 31);
        managerCreateFrame.getContentPane().add(createItemName);

        createItemPrice = new JTextField();
        createItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        createItemPrice.setBorder(BorderFactory.createLineBorder(Color.RED));
        createItemPrice.setBounds(145, 170, 65, 31);
        managerCreateFrame.getContentPane().add(createItemPrice);

        addNewItem = new JButton("Add");
        addNewItem.setBounds(110, 216, 150, 35);
        addNewItem.setBorder(BorderFactory.createLineBorder(Color.RED));
        addNewItem.setBackground(Color.WHITE);
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

    private void updateItemUI()
    {
        managerUpdateFrame = new JFrame("Update");
        managerUpdateFrame.setResizable(false);
        managerUpdateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerUpdateFrame.setBounds(100, 100, 359, 369);
        managerUpdateFrame.getContentPane().setLayout(null);
        managerUpdateFrame.setVisible(true);

        uItemNo = new JLabel("Item No:");
        uItemNo.setBounds(70, 90, 90, 31);
        uItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        managerUpdateFrame.getContentPane().add(uItemNo);

        uItemName = new JLabel("Item Name:");
        uItemName.setFont(new Font("Serif", Font.BOLD, 14));
        uItemName.setBounds(70, 130, 90, 31);
        managerUpdateFrame.getContentPane().add(uItemName);

        uItemPrice = new JLabel("Item Price:");
        uItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        uItemPrice.setBounds(70, 170, 90, 31);
        managerUpdateFrame.getContentPane().add(uItemPrice);

        updateItemNo = new JTextField();
        updateItemNo.setBounds(145, 90, 65, 31);
        updateItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        updateItemNo.setBorder(BorderFactory.createLineBorder(Color.RED));
        String itemNumber =  managerModel.getValueAt(managerSelectedRow, 0).toString();
        updateItemNo.setText(itemNumber);
        updateItemNo.setEditable(false);
        managerUpdateFrame.getContentPane().add(updateItemNo);

        updateItemName = new JTextField();
        updateItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        updateItemName.setBounds(145, 130, 65, 31);
        updateItemName.setBorder(BorderFactory.createLineBorder(Color.RED));
        String currItemName = managerModel.getValueAt(managerSelectedRow, 1).toString();
        updateItemName.setText(currItemName);
        managerUpdateFrame.getContentPane().add(updateItemName);

        updateItemPrice = new JTextField();
        updateItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        updateItemPrice.setBounds(145, 170, 65, 31);
        updateItemPrice.setBorder(BorderFactory.createLineBorder(Color.RED));
        String currItemPrice = managerModel.getValueAt(managerSelectedRow, 2).toString();
        updateItemPrice.setText(currItemPrice);
        managerUpdateFrame.getContentPane().add(updateItemPrice);

        menuUpdate = new JButton("Update");
        menuUpdate.setBounds(110, 216, 150, 35);
        menuUpdate.setBorder(BorderFactory.createLineBorder(Color.RED));
        menuUpdate.setBackground(Color.WHITE);
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


}