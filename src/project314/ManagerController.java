package project314;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ManagerController
{
    public void getItem(JTable menuList)
    {
        new Menu().getItem(menuList);
    }


    public boolean ifExist(String itemNumber, String itemName, double itemPrice)
    {
        boolean input = new Menu().createItem(itemNumber, itemName, itemPrice);
        return input;
    }

    /*public void ifExist(String itemNumber, String itemName, double itemPrice)
    {
//        new Menu().createItem(itemNumber, itemName, itemPrice);
//        itemNumber = "";
//        itemName = "";
//        itemPrice = 0;
//
//        if (itemNumber == "")
//        {
//            return true;
//        }
//        return false;
//        boolean input = new Menu().createItem(itemNumber, itemName, itemPrice);
//        return input;
        new Menu().createItem(itemNumber, itemName, itemPrice);

    }*/

    public void deleteItem(String itemNumber)
    {
        new Menu().deleteItem(itemNumber);
    }

    public void updateItem(String itemNumber, String itemName, Double itemPrice)
    {
        new Menu().updateItem(itemNumber, itemName, itemPrice);
    }

}