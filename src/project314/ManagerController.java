package project314;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ManagerController
{
    public void getItem(JTable managerMenuList)
    {
        new Menu().getItem(managerMenuList);
    }


    public boolean ifExist(String itemNumber, String itemName, double itemPrice)
    {
        boolean input = new Menu().createItem(itemNumber, itemName, itemPrice);
        return input;
    }

    public void deleteItem(String itemNumber)
    {
        new Menu().deleteItem(itemNumber);
    }

    public void updateItem(String itemNumber, String itemName, double itemPrice)
    {
        new Menu().updateItem(itemNumber, itemName, itemPrice);
    }

}