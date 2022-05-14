package project314;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;

public class CustomerUI {
    JFrame orderFrame;
    JTable menuList;
    DefaultTableModel model;

    public static void main(String[] args)
    {
        new CustomerUI();
    }

    public CustomerUI()
    {
        orderDisplayPage();
    }

    private void orderDisplayPage() {
        orderFrame = new JFrame("Order");
        orderFrame.setResizable(false);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setSize(400, 400);
        orderFrame.getContentPane().setLayout(null);
        orderFrame.setVisible(true);

        model = new DefaultTableModel();
        model.addColumn("itemNumber");
        model.addColumn("itemName");
        model.addColumn("itemPrice");
        menuList = new JTable(model);
        menuList.setBounds(30,40,200,300);
        menuList.setRowSelectionAllowed(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 366, 107);
        orderFrame.getContentPane().add(scrollPane);
        CustomerController cc = new CustomerController();
        cc.getItem(menuList);
        scrollPane.setViewportView(menuList);
    }
}
