package project314;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {
    public void getItem(JTable menuList)
    {
        new Order().getItem(menuList);
    }
}
