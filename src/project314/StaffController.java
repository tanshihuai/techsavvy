package project314;

import javax.swing.*;

public class StaffController
{
    public void getItem(JTable staffMenuList)
    {
        new Receipt().getItem(staffMenuList);
    }

    public void getId(JTable viewOrders)
    {
        new Receipt().getId(viewOrders);
    }

    public void editOrder(int receiptItemsID, int orderID, String itemNumber, String itemName, double itemPrice, int qty)
    {
        new Receipt().editInOrder(receiptItemsID, orderID, itemNumber, itemName, itemPrice, qty);
    }

    public void deleteOrder(int orderID)
    {
        new Receipt().deleteOrder(orderID);
    }

}
