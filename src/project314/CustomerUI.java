package project314;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomerUI extends JFrame {
    JFrame customerFrame; //SHOW MENU
    JTable customerMenuList; //menu table
    JTable foodCart;
    DefaultTableModel customerModel; //menu model
    DefaultTableModel customerCartModel; //cart model
    DefaultTableModel customerReceiptModel; //receipt model

    JButton customerCartButton; //show cart table
    JButton customerAddFoodButton; //add food to cart
    JButton customerReceiptButton; //shows receipt or something
    JButton loginBackButton; // go back to login page
    JButton menuBackButton; //go back to menu page
    //JButton orderButton;
    JFrame customerCartFrame; //shows cart table
    JFrame customerAddItemFrame; //show frame to add qty

    JFrame customerEditItemFrame; //show frame to edit the qty
    JTable customerFoodCart; //the cart table
    JTable customerRecipetTable; //the receipt table
    private int customerSelectedRow; //is to add and update

    JFrame customerPaymentFrame; //to make payment

    JTextField addQuantity;
    JTextField addItemPrice;
    JTextField addItemName;
    JTextField addItemNo;
    JTextField orderIDtext;

    JLabel labelTotal;
    float total;

    CustomerController cc = new CustomerController(); //call CC class
    int value = cc.getOrderID();



    public static void main(String[] args)
    {
        new CustomerUI();

    }

    public CustomerUI()
    {
        orderDisplayPage();
    }

    private void orderDisplayPage() {

        customerFrame = new JFrame("Order");
        customerFrame.setResizable(false);
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.setSize(700, 500);
        customerFrame.getContentPane().setBackground(Color.darkGray);
        customerFrame.getContentPane().setLayout(null);
        customerFrame.setVisible(true);

        customerAddFoodButton = new JButton("Add");
        customerAddFoodButton.setBounds(70, 80, 150, 35);
        customerAddFoodButton.setBorder(BorderFactory.createLoweredBevelBorder());
        customerAddFoodButton.setBackground(Color.WHITE);
        customerAddFoodButton.setForeground(Color.BLACK);
        customerFrame.getContentPane().add(customerAddFoodButton);

        //is to add the food to cart
        customerAddFoodButton.addActionListener (e ->
        {
            if (e.getSource () == customerAddFoodButton)
            {
                cc.getCartTable(customerFoodCart);
                customerSelectedRow = customerMenuList.getSelectedRow();
                if(customerSelectedRow != -1)
                {
                    System.out.println(customerSelectedRow);
                    addItemUI();
                    customerFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to add");
                }
            }
        });

        customerCartButton = new JButton("Food Cart");
        customerCartButton.setBounds(70, 120, 150, 35);
        customerCartButton.setBorder(BorderFactory.createLoweredBevelBorder());
        customerCartButton.setBackground(Color.WHITE);
        customerCartButton.setForeground(Color.BLACK);
        customerFrame.getContentPane().add(customerCartButton);

        customerCartButton.addActionListener (e ->
        {
            if (e.getSource () == customerCartButton)
            {
                cartUI();
                customerFrame.dispose();
            }
        });

        customerReceiptButton = new JButton("Receipt");
        customerReceiptButton.setBounds(70, 120, 150, 35);
        customerReceiptButton.setBorder(BorderFactory.createLoweredBevelBorder());
        customerReceiptButton.setBackground(Color.WHITE);
        customerReceiptButton.setForeground(Color.BLACK);
        customerFrame.getContentPane().add(customerReceiptButton);

        loginBackButton = new JButton("Back");
        loginBackButton.setBounds(70, 160, 150, 35);
        loginBackButton.setBorder(BorderFactory.createLoweredBevelBorder());
        loginBackButton.setBackground(Color.WHITE);
        loginBackButton.setForeground(Color.BLACK);
        customerFrame.getContentPane().add(loginBackButton);

        loginBackButton.addActionListener (e ->
        {
            if (e.getSource () == loginBackButton)
            {
                new LoginUI();
                customerFrame.dispose();
            }
        });

        customerModel = new DefaultTableModel();
        customerModel.addColumn("Item Number");
        customerModel.addColumn("Item Name");
        customerModel.addColumn("Item Price");
        customerMenuList = new JTable(customerModel);
        customerMenuList.setBounds(250,80,400,200);
        customerMenuList.setBackground(Color.WHITE);
        customerMenuList.setForeground(Color.BLACK);
        customerMenuList.setRowSelectionAllowed(true);
        JScrollPane scrollPaneCustomer = new JScrollPane();
        scrollPaneCustomer.setBounds(250,80,400,200);
        scrollPaneCustomer.setForeground(Color.BLACK);
        scrollPaneCustomer.setBackground(Color.WHITE);
        customerFrame.getContentPane().add(scrollPaneCustomer);
        CustomerController cc = new CustomerController();
        cc.getItem(customerMenuList);
        scrollPaneCustomer.setViewportView(customerMenuList);
    }

    private void cartUI() {
        customerCartFrame = new JFrame("Food Cart");
        customerCartFrame.setResizable(false);
        customerCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerCartFrame.setSize(700, 500);
        customerCartFrame.getContentPane().setBackground(Color.darkGray);
        customerCartFrame.getContentPane().setLayout(null);

        JLabel orderID = new JLabel("Order ID: ");
        orderID.setBounds(70, 40, 90, 31);
        orderID.setFont(new Font("Serif", Font.BOLD, 14));
        orderID.setForeground(Color.WHITE);
        customerCartFrame.getContentPane().add(orderID);


        orderIDtext = new JTextField();
        int value = cc.getOrderID();
        orderIDtext.setText(String.valueOf(value));
        orderIDtext.setFont(new Font("Serif", Font.PLAIN, 14));
        orderIDtext.setBorder(BorderFactory.createLoweredBevelBorder());
        orderIDtext.setBounds(140, 40, 50, 35);
        orderIDtext.setBackground(Color.WHITE);
        orderIDtext.setForeground(Color.BLACK);
        customerCartFrame.getContentPane().add(orderIDtext);
        orderIDtext.setEditable(false);
        customerCartFrame.setVisible(true);

        customerCartModel = new DefaultTableModel();
        customerCartModel.addColumn("Item Number");
        customerCartModel.addColumn("Item Name");
        customerCartModel.addColumn("Item Price");
        customerCartModel.addColumn("Quantity");
        customerFoodCart = new JTable(customerCartModel);
        customerFoodCart.setBounds(250,80,400,200);
        customerFoodCart.setForeground(Color.BLACK);
        customerFoodCart.setBackground(Color.WHITE);
        customerFoodCart.setRowSelectionAllowed(true);

        JScrollPane scrollPaneCustomerCart = new JScrollPane();
        scrollPaneCustomerCart.setBounds(250,80,400,200);
        scrollPaneCustomerCart.setForeground(Color.BLACK);
        scrollPaneCustomerCart.setBackground(Color.WHITE);
        customerCartFrame.getContentPane().add(scrollPaneCustomerCart);

        cc.getOrder(customerFoodCart);
        scrollPaneCustomerCart.setViewportView(customerFoodCart);

        JButton makePaymentButton = new JButton("Payment");
        makePaymentButton.setBounds(70, 80, 150, 35);
        makePaymentButton.setBorder(BorderFactory.createLoweredBevelBorder());
        makePaymentButton.setBackground(Color.WHITE);
        makePaymentButton.setForeground(Color.BLACK);
        customerCartFrame.getContentPane().add(makePaymentButton);

        makePaymentButton.addActionListener (e ->
        {
            if (e.getSource () == makePaymentButton)
            {
                paymentPage();
                customerCartFrame.dispose();
            }
        });

        menuBackButton = new JButton("Back");
        menuBackButton.setBounds(70, 160, 150, 35);
        menuBackButton.setBorder(BorderFactory.createLoweredBevelBorder());
        menuBackButton.setBackground(Color.WHITE);
        menuBackButton.setForeground(Color.BLACK);
        customerCartFrame.getContentPane().add(menuBackButton);

        menuBackButton.addActionListener (e ->
        {
            if (e.getSource () == menuBackButton)
            {
                new CustomerUI();
                customerCartFrame.dispose();
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.setBounds(70, 120, 150, 35);
        editButton.setBorder(BorderFactory.createLoweredBevelBorder());
        editButton.setBackground(Color.WHITE);
        editButton.setForeground(Color.BLACK);
        customerCartFrame.getContentPane().add(editButton);

        editButton.addActionListener (e ->
        {
            if (e.getSource () == editButton)
            {
                customerSelectedRow = customerFoodCart.getSelectedRow();
                if(customerSelectedRow != -1)
                {
                    System.out.println(customerSelectedRow);
                    editItemUI();
                    customerCartFrame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit");
                }
            }
        });

    }

    private void addItemUI() {
        customerAddItemFrame = new JFrame("Add Item");
        customerAddItemFrame.setResizable(false);
        customerAddItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerAddItemFrame.setSize(700, 500);
        customerAddItemFrame.getContentPane().setBackground(Color.darkGray);
        customerAddItemFrame.getContentPane().setLayout(null);
        customerAddItemFrame.setVisible(true);

        JLabel aOrderNo = new JLabel("Order No:");
        aOrderNo.setBounds(180, 80, 90, 31);
        aOrderNo.setFont(new Font("Serif", Font.BOLD, 14));
        aOrderNo.setForeground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(aOrderNo);

        JLabel aItemNo = new JLabel("Item No:");
        aItemNo.setBounds(180, 120, 90, 31);
        aItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        aItemNo.setForeground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(aItemNo);

        JLabel aItemName = new JLabel("Item Name:");
        aItemName.setBounds(180, 160, 90, 31);
        aItemName.setFont(new Font("Serif", Font.BOLD, 14));
        aItemName.setForeground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(aItemName);

        JLabel aItemPrice = new JLabel("Item Price:");
        aItemPrice.setBounds(180, 200, 90, 31);
        aItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        aItemPrice.setForeground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(aItemPrice);

        JLabel aQuantity = new JLabel("Quantity:");
        aQuantity.setBounds(180, 240, 90, 31);
        aQuantity.setFont(new Font("Serif", Font.BOLD, 14));
        aQuantity.setForeground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(aQuantity);

        orderIDtext = new JTextField();
        int value = cc.getOrderID();
        orderIDtext.setText(String.valueOf(value));
        orderIDtext.setFont(new Font("Serif", Font.PLAIN, 14));
        orderIDtext.setBorder(BorderFactory.createLoweredBevelBorder());
        orderIDtext.setBounds(290, 80, 200, 31);
        orderIDtext.setForeground(Color.BLACK);
        orderIDtext.setBackground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(orderIDtext);
        orderIDtext.setEditable(false);
        customerAddItemFrame.setVisible(true);

        JTextField addItemNo = new JTextField();
        addItemNo.setBounds(290, 120, 200, 31);
        addItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        addItemNo.setBackground(Color.WHITE);
        addItemNo.setForeground(Color.BLACK);
        String itemNumber =  customerModel.getValueAt(customerSelectedRow, 0).toString();
        addItemNo.setText(itemNumber);
        addItemNo.setEditable(false);
        customerAddItemFrame.getContentPane().add(addItemNo);

        JTextField addItemName = new JTextField();
        addItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        addItemName.setBounds(290, 160, 200, 31);
        addItemNo.setBackground(Color.WHITE);
        addItemNo.setForeground(Color.BLACK);
        String currItemName = customerModel.getValueAt(customerSelectedRow, 1).toString();
        addItemName.setText(currItemName);
        addItemName.setEditable(false);
        customerAddItemFrame.getContentPane().add(addItemName);

        JTextField addItemPrice = new JTextField();
        addItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        addItemPrice.setBounds(290, 200, 200, 31);
        addItemNo.setBackground(Color.WHITE);
        addItemNo.setForeground(Color.BLACK);
        String currItemPrice = customerModel.getValueAt(customerSelectedRow, 2).toString();
        addItemPrice.setText(currItemPrice);
        addItemPrice.setEditable(false);
        customerAddItemFrame.getContentPane().add(addItemPrice);

        JTextField addQuantity = new JTextField();
        addQuantity.setFont(new Font("Serif", Font.PLAIN, 14));
        addQuantity.setBounds(290, 240, 200, 31);
        addItemNo.setBackground(Color.WHITE);
        addItemNo.setForeground(Color.BLACK);
        addQuantity.setBorder(BorderFactory.createLoweredBevelBorder());
        customerAddItemFrame.getContentPane().add(addQuantity);

        JButton customerAdd = new JButton("Add");
        customerAdd.setBounds(220, 350, 150, 35);
        customerAdd.setBorder(BorderFactory.createLoweredBevelBorder());
        customerAdd.setForeground(Color.BLACK);
        customerAdd.setBackground(Color.WHITE);
        customerAddItemFrame.getContentPane().add(customerAdd);

        customerAdd.addActionListener (e ->
        {
            if (e.getSource () == customerAdd)
            {
                String itemName = addItemName.getText();
                double itemPrice = Double.parseDouble(addItemPrice.getText());
                int qty = Integer.parseInt(addQuantity.getText());

                cc.addCart(value, itemNumber, itemName, itemPrice, qty);

                customerAddItemFrame.dispose();
                cartUI();

                JOptionPane.showMessageDialog(null, "Added successfully");
            }
        });

    }

    private void editItemUI() {
        customerEditItemFrame = new JFrame("Edit Item");
        customerEditItemFrame.setResizable(false);
        customerEditItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerEditItemFrame.setSize(700, 500);
        customerEditItemFrame.getContentPane().setBackground(Color.darkGray);
        customerEditItemFrame.getContentPane().setLayout(null);
        customerEditItemFrame.setVisible(true);

        JLabel eItemNo = new JLabel("Item No:");
        eItemNo.setBounds(180, 160, 90, 31);
        eItemNo.setFont(new Font("Serif", Font.BOLD, 14));
        eItemNo.setForeground(Color.WHITE);
        customerEditItemFrame.getContentPane().add(eItemNo);

        JLabel eItemName = new JLabel("Item Name:");
        eItemName.setBounds(180, 200, 90, 31);
        eItemName.setFont(new Font("Serif", Font.BOLD, 14));
        eItemName.setForeground(Color.WHITE);
        customerEditItemFrame.getContentPane().add(eItemName);

        JLabel eItemPrice = new JLabel("Item Price:");
        eItemPrice.setBounds(180, 240, 90, 31);
        eItemPrice.setFont(new Font("Serif", Font.BOLD, 14));
        eItemPrice.setForeground(Color.WHITE);
        customerEditItemFrame.getContentPane().add(eItemPrice);

        JLabel eQuantity = new JLabel("Quantity:");
        eQuantity.setBounds(180, 280, 90, 31);
        eQuantity.setFont(new Font("Serif", Font.BOLD, 14));
        eQuantity.setForeground(Color.WHITE);
        customerEditItemFrame.getContentPane().add(eQuantity);

        JTextField editItemNo = new JTextField();
        editItemNo.setBounds(290, 160, 200, 31);
        editItemNo.setFont(new Font("Serif", Font.PLAIN, 14));
        editItemNo.setForeground(Color.BLACK);
        editItemNo.setBackground(Color.WHITE);
        String itemNumber =  customerCartModel.getValueAt(customerSelectedRow, 0).toString();
        editItemNo.setText(itemNumber);
        editItemNo.setEditable(false);
        customerEditItemFrame.getContentPane().add(editItemNo);

        JTextField editItemName = new JTextField();
        editItemName.setFont(new Font("Serif", Font.PLAIN, 14));
        editItemName.setBounds(290, 200, 200, 31);
        editItemName.setForeground(Color.BLACK);
        editItemName.setBackground(Color.WHITE);
        String currItemName = customerCartModel.getValueAt(customerSelectedRow, 1).toString();
        editItemName.setText(currItemName);
        editItemName.setEditable(false);
        customerEditItemFrame.getContentPane().add(editItemName);

        JTextField editItemPrice = new JTextField();
        editItemPrice.setFont(new Font("Serif", Font.PLAIN, 14));
        editItemPrice.setBounds(290, 240, 200, 31);
        editItemPrice.setForeground(Color.BLACK);
        editItemPrice.setBackground(Color.WHITE);
        String currItemPrice = customerCartModel.getValueAt(customerSelectedRow, 2).toString();
        editItemPrice.setText(currItemPrice);
        editItemPrice.setEditable(false);
        customerEditItemFrame.getContentPane().add(editItemPrice);

        JTextField editQuantity = new JTextField();
        editQuantity.setFont(new Font("Serif", Font.PLAIN, 14));
        editQuantity.setBounds(290, 280, 200, 31);
        editQuantity.setBorder(BorderFactory.createLoweredBevelBorder());
        editQuantity.setForeground(Color.BLACK);
        editQuantity.setBackground(Color.WHITE);
        String currQuantity = customerCartModel.getValueAt(customerSelectedRow, 3).toString();
        editQuantity.setText(currQuantity);
        customerEditItemFrame.getContentPane().add(editQuantity);

        JButton customerEdit = new JButton("Edit");
        customerEdit .setBounds(220, 350, 150, 35);
        customerEdit.setBorder(BorderFactory.createLoweredBevelBorder());
        customerEdit.setForeground(Color.BLACK);
        customerEdit.setBackground(Color.WHITE);
        customerEditItemFrame.getContentPane().add(customerEdit);

        customerEdit .addActionListener (e ->
        {
            if (e.getSource () == customerEdit ) {
                String itemName = editItemName.getText();
                double itemPrice = Double.parseDouble(editItemPrice.getText());
                int qty = Integer.parseInt(editQuantity.getText());
                cc.editCart(itemNumber, itemName, itemPrice, qty);
                customerEditItemFrame.dispose();
                cartUI();

                JOptionPane.showMessageDialog(null, "Selected row updated successfully");
            }
        });

    }

    private void paymentPage() {
        customerPaymentFrame = new JFrame("Payment");
        customerPaymentFrame.setResizable(false);
        customerPaymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerPaymentFrame.setSize(700, 500);
        customerPaymentFrame.getContentPane().setBackground(Color.darkGray);
        customerPaymentFrame.getContentPane().setLayout(null);
        customerPaymentFrame.setVisible(true);

        customerReceiptModel = new DefaultTableModel();
        customerReceiptModel.addColumn("Item Number");
        customerReceiptModel.addColumn("Item Name");
        customerReceiptModel.addColumn("Item price");
        customerReceiptModel.addColumn("Quantity");
        customerRecipetTable = new JTable(customerReceiptModel);
        customerRecipetTable.setBounds(250,80,350,200);
        customerRecipetTable.setBackground(Color.WHITE);
        customerRecipetTable.setForeground(Color.BLACK);
        customerRecipetTable.setRowSelectionAllowed(true);

        JScrollPane scrollPaneCustomerReceiptCart = new JScrollPane();
        scrollPaneCustomerReceiptCart.setBounds(250,80,350,200);
        scrollPaneCustomerReceiptCart.setBackground(Color.WHITE);
        scrollPaneCustomerReceiptCart.setForeground(Color.BLACK);
        customerPaymentFrame.getContentPane().add(scrollPaneCustomerReceiptCart);

        cc.getPayment(customerRecipetTable);
        scrollPaneCustomerReceiptCart.setViewportView(customerRecipetTable);

        total = 0;
        labelTotal = new JLabel();
        labelTotal.setFont(new Font("Serif", Font.PLAIN, 24));
        labelTotal.setBounds(250, 204, 350, 200);


        // compute the total
        ArrayList<String[]> orders = (ArrayList) cc.getOrders();
        for (String[] order : orders)	{
            float itemPrice = Float.parseFloat(order[2]);
            int quantity = Integer.parseInt(order[3]);
            total += itemPrice * quantity;
        }

        labelTotal.setText(String.format("Total: %.2f", total));
        labelTotal.setForeground(Color.WHITE);
        customerPaymentFrame .getContentPane().add(labelTotal);


        JButton makePaymentButton = new JButton("Confirm Payment");
        makePaymentButton.setBounds(70, 80, 150, 35);
        makePaymentButton.setBorder(BorderFactory.createLoweredBevelBorder());
        makePaymentButton.setBackground(Color.WHITE);
        makePaymentButton.setForeground(Color.BLACK);
        customerPaymentFrame .getContentPane().add(makePaymentButton);

        makePaymentButton.addActionListener (e ->
        {
            if (e.getSource () == makePaymentButton)
            {
                cc.paymentDone(orders, total);
                customerPaymentFrame .dispose();
                cc.getCartTable(customerFoodCart);
                JOptionPane.showMessageDialog(null, "Payment Successful");
                customerCartFrame.dispose();
                orderDisplayPage();
            }
        });

    }
}
