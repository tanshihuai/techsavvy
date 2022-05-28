package project314;
//imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

//main class
public class RestaurantStaffUI extends JFrame {
    //variables
    JFrame staffFrame;
    JFrame staffEditFrame;
    JButton staffEditButton;
    JButton staffLogoutButton;
    JButton staffDeleteButton;

    DefaultTableModel staffModel;

    JTable staffMenuList;
    int staffSelectedRow;
    StaffController sc = new StaffController();

    public RestaurantStaffUI() {
        staffDisplayPage();
    }

    private void staffDisplayPage() {
        //main frame
        staffFrame = new JFrame("Staff");
        staffFrame.setResizable(false);
        staffFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        staffFrame.setSize(750, 500);
        staffFrame.getContentPane().setBackground(Color.darkGray);
        staffFrame.getContentPane().setLayout(null);
        staffFrame.setVisible(true);

        staffEditButton = new JButton("Edit Order");
        staffEditButton.setBounds(70, 120, 150, 35);
        staffEditButton.setBorder(BorderFactory.createLoweredBevelBorder());
        staffEditButton.setBackground(Color.WHITE);
        staffEditButton.setForeground(Color.BLACK);
        staffFrame.getContentPane().add(staffEditButton);

        staffDeleteButton = new JButton("Completed");
        staffDeleteButton.setBounds(70, 160, 150, 35);
        staffDeleteButton.setBorder(BorderFactory.createLoweredBevelBorder());
        staffDeleteButton.setBackground(Color.WHITE);
        staffDeleteButton.setForeground(Color.BLACK);
        staffFrame.getContentPane().add(staffDeleteButton);

        staffLogoutButton = new JButton("Logout");
        staffLogoutButton.setBounds(70, 200, 150, 35);
        staffLogoutButton.setBorder(BorderFactory.createLoweredBevelBorder());
        staffLogoutButton.setBackground(Color.WHITE);
        staffLogoutButton.setForeground(Color.BLACK);
        staffFrame.getContentPane().add(staffLogoutButton);

        staffEditButton.addActionListener (e ->
        {
            if (e.getSource () == staffEditButton)
            {
                staffSelectedRow = staffMenuList.getSelectedRow();
                if(staffSelectedRow != -1)
                {
                    System.out.println(staffSelectedRow);
                    editOrderUI();
                    staffFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit");
                }
            }
        });

        staffDeleteButton.addActionListener(e ->
        {
            if (e.getSource() == staffDeleteButton) {
                //deleteItemUI();
                int selectedRow = staffMenuList.getSelectedRow();
                if (selectedRow != -1) {
                    // remove selected row from the model
                    int orderID = Integer.parseInt(staffModel.getValueAt(selectedRow, 0).toString());
                    sc.deleteOrder(orderID);
                    staffModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        });

        staffLogoutButton.addActionListener(e ->
        {
            if (e.getSource() == staffLogoutButton) {
                new LoginUI();
                staffFrame.dispose();
            }
        });

        //table
        staffModel = new DefaultTableModel();
        staffModel.addColumn("Receipt ID");
        staffModel.addColumn("Order ID");
        staffModel.addColumn("Item No");
        staffModel.addColumn("Item Name");
        staffModel.addColumn("Item Price");
        staffModel.addColumn("Quantity");

        staffMenuList = new JTable(staffModel);
        staffMenuList.setBounds(250, 80, 470, 200);
        staffMenuList.setRowSelectionAllowed(true);
        staffMenuList.setBackground(Color.WHITE);
        staffMenuList.setForeground(Color.BLACK);
        JScrollPane scrollPaneManager = new JScrollPane();
        scrollPaneManager.setBounds(250, 80, 470, 200);
        staffFrame.getContentPane().add(scrollPaneManager);
        scrollPaneManager.setViewportView(staffMenuList);
        scrollPaneManager.setBackground(Color.WHITE);
        scrollPaneManager.setForeground(Color.BLACK);
        sc.getItem(staffMenuList);
    }

    public void editOrderUI()
    {
        staffEditFrame = new JFrame("Edit Item");
        staffEditFrame.setResizable(false);
        staffEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        staffEditFrame.setSize(700, 500);
        staffEditFrame.getContentPane().setBackground(Color.darkGray);
        staffEditFrame.getContentPane().setLayout(null);
        staffEditFrame.setVisible(true);

        JLabel eReceiptID = new JLabel("Receipt ID:");
        eReceiptID.setBounds(180, 80, 90, 31);
        eReceiptID.setFont(new Font("Serif", Font.BOLD, 14));
        eReceiptID.setForeground(Color.WHITE);
        staffEditFrame.getContentPane().add(eReceiptID);

        JLabel eOrderID = new JLabel("Order ID:");
        eOrderID.setBounds(180, 120, 90, 31);
        eOrderID.setFont(new Font("Serif", Font.BOLD, 14));
        eOrderID.setForeground(Color.WHITE);
        staffEditFrame.getContentPane().add(eOrderID);

        JLabel eItemNo = new JLabel("Item No:");
        eItemNo.setBounds(180, 160, 90, 31);
        eItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        eItemNo.setForeground(Color.WHITE);
        staffEditFrame.getContentPane().add(eItemNo);

        JLabel eItemName = new JLabel("Item Name:");
        eItemName.setBounds(180, 200, 90, 31);
        eItemName.setFont(new Font("Serif", Font.BOLD, 14));
        eItemName.setForeground(Color.WHITE);
        staffEditFrame.getContentPane().add(eItemName);

        JLabel eItemPrice = new JLabel("Item Price:");
        eItemPrice.setBounds(180, 240, 90, 31);
        eItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        eItemPrice.setForeground(Color.WHITE);
        staffEditFrame.getContentPane().add(eItemPrice);

        JLabel eQuantity = new JLabel("Quantity:");
        eQuantity.setBounds(180, 280, 90, 31);
        eQuantity.setFont(new Font("Serif", Font.BOLD, 14));
        eQuantity.setForeground(Color.WHITE);
        staffEditFrame.getContentPane().add(eQuantity);

        JTextField editRecieptID = new JTextField();
        editRecieptID.setBounds(290, 80, 200, 31);
        editRecieptID.setFont(new Font("Serif", Font.PLAIN, 14));
        editRecieptID.setForeground(Color.BLACK);
        editRecieptID.setBackground(Color.WHITE);
        String currReceiptID =  staffModel.getValueAt(staffSelectedRow, 0).toString();
        editRecieptID.setText(currReceiptID);
        editRecieptID.setEditable(false);
        staffEditFrame.getContentPane().add(editRecieptID);

        JTextField editOrderID = new JTextField();
        editOrderID.setBounds(290, 120, 200, 31);
        editOrderID.setFont(new Font("Serif", Font.PLAIN, 14));
        editRecieptID.setForeground(Color.BLACK);
        editRecieptID.setBackground(Color.WHITE);
        String currOrderID =  staffModel.getValueAt(staffSelectedRow, 1).toString();
        editOrderID.setText(currOrderID);
        editOrderID.setEditable(false);
        staffEditFrame.getContentPane().add(editOrderID);

        JTextField editItemNo = new JTextField();
        editItemNo.setBounds(290, 160, 200, 31);
        editItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        editItemNo.setForeground(Color.BLACK);
        editItemNo.setBackground(Color.WHITE);
        String curritemNumber =  staffModel.getValueAt(staffSelectedRow, 2).toString();
        editItemNo.setText(curritemNumber);
        editItemNo.setEditable(false);
        staffEditFrame.getContentPane().add(editItemNo);

        JTextField editItemName = new JTextField();
        editItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        editItemName.setBounds(290, 200, 200, 31);
        editItemName.setForeground(Color.BLACK);
        editItemName.setBackground(Color.WHITE);
        String currItemName = staffModel.getValueAt(staffSelectedRow, 3).toString();
        editItemName.setText(currItemName);
        editItemName.setEditable(false);
        staffEditFrame.getContentPane().add(editItemName);

        JTextField editItemPrice = new JTextField();
        editItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        editItemPrice.setBounds(290, 240, 200, 31);
        editItemPrice.setForeground(Color.BLACK);
        editItemPrice.setBackground(Color.WHITE);
        String currItemPrice = staffModel.getValueAt(staffSelectedRow, 4).toString();
        editItemPrice.setText(currItemPrice);
        editItemPrice.setEditable(false);
        staffEditFrame.getContentPane().add(editItemPrice);

        JTextField editQuantity = new JTextField();
        editQuantity.setFont(new Font("Serif", Font.PLAIN, 14));
        editQuantity.setBounds(290, 280, 200, 31);
        editQuantity.setForeground(Color.BLACK);
        editQuantity.setBackground(Color.WHITE);
        editQuantity.setBorder(BorderFactory.createLoweredBevelBorder());
        String currQuantity = staffModel.getValueAt(staffSelectedRow, 5).toString();
        editQuantity.setText(currQuantity);
        staffEditFrame.getContentPane().add(editQuantity);

        JButton customerEdit = new JButton("Edit");
        customerEdit .setBounds(220, 350, 150, 35);
        customerEdit.setBorder(BorderFactory.createLoweredBevelBorder());
        customerEdit.setForeground(Color.BLACK);
        customerEdit.setBackground(Color.WHITE);
        staffEditFrame.getContentPane().add(customerEdit);

        customerEdit .addActionListener (e ->
        {
            if (e.getSource () == customerEdit ) {
                int receiptid = Integer.parseInt(editRecieptID.getText());
                int orderid = Integer.parseInt(editQuantity.getText());
                String itemNumber = editItemNo.getText();
                String itemName = editItemName.getText();
                double itemPrice = Double.parseDouble(editItemPrice.getText());
                int qty = Integer.parseInt(editQuantity.getText());
                sc.editOrder(receiptid, orderid, itemNumber, itemName, itemPrice, qty);
                staffEditFrame.dispose();
                staffDisplayPage();
                JOptionPane.showMessageDialog(null, "Selected row updated successfully");
            }
        });
    }
}
