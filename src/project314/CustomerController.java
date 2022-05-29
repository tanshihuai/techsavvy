package project314;

import javax.swing.*;
import java.util.List;

public class CustomerController
{
    public void getItem(JTable customerMenuList)
    {
        new Order().getItem(customerMenuList);
    }

    public void getOrder(JTable foodCart)
    {
        new Order().getOrder(foodCart);
    }

    public void getPayment(JTable customerReceiptTable)
    {
        new Order().getPayment(customerReceiptTable);
    }


    public List<String[]> getOrders()
    {
        return new Order().getOrders();
    }

    public void addCart(int orderID, String itemNumber, String itemName, double itemPrice, int qty)
    {
        new Order().addToCart(orderID, itemNumber, itemName, itemPrice, qty);
    }

    public void editCart(String itemNumber, String itemName, double itemPrice, int qty)
    {
        new Order().editInCart(itemNumber, itemName, itemPrice, qty);
    }

    public void paymentDone(List<String[]> orders, float totalAmount)
    {
        new Order().paymentMethod(orders, totalAmount);
    }

    public int getOrderID()
    {
        return new Order().getOrderId();
    }

    public void getCartTable(JTable customerFoodCart)
    {
        new Order().newCartTable(customerFoodCart);
    }
}