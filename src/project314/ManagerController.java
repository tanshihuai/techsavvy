package project314;
import javax.swing.*;

public class ManagerController
{
    /*public void getItem(String itemNumber, String itemName, double itemPrice)
    {

    }*/

    public boolean validateUpdate(String itemNumber, String itemName, double itemPrice)
    {
        boolean input = new Menu().updateItem(itemNumber, itemName, itemPrice);
        return input;
    }

    public boolean ifExist(String itemNumber, String itemName, double itemPrice)
    {
        boolean input = new Menu().createItem(itemNumber, itemName, itemPrice);
        return input;
    }

    /*public void deleteItem(String itemNumber, String itemName, double itemPrice)
    {

    }*/
}