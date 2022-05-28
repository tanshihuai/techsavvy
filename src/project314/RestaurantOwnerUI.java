package project314;
//imports
import javax.swing.*;
import java.awt.*;

//main class
public class RestaurantOwnerUI extends JFrame {
    JFrame ownerFrame;

    JButton ownerViewButton1;
    JButton ownerViewButton2;
    JButton ownerViewButton3;
    JButton ownerLogoutButton;

    public RestaurantOwnerUI() {
        ownerDisplayPage();
    }

    private void ownerDisplayPage() {
        //main frame
        ownerFrame = new JFrame("Owner");
        ownerFrame.setResizable(false);
        ownerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ownerFrame.setSize(700, 500);
        ownerFrame.getContentPane().setBackground(Color.darkGray);
        ownerFrame.getContentPane().setLayout(null);
        ownerFrame.setVisible(true);

        ownerViewButton1 = new JButton("View Daily Report");
        ownerViewButton1.setBounds(200, 90, 150, 35);
        ownerViewButton1.setBorder(BorderFactory.createLoweredBevelBorder());
        ownerViewButton1.setBackground(Color.WHITE);
        ownerViewButton1.setForeground(Color.BLACK);
        ownerFrame.getContentPane().add(ownerViewButton1);

        ownerViewButton2 = new JButton("View Weekly Report");
        ownerViewButton2.setBounds(200, 130, 150, 35);
        ownerViewButton2.setBorder(BorderFactory.createLoweredBevelBorder());
        ownerViewButton2.setBackground(Color.WHITE);
        ownerViewButton2.setForeground(Color.BLACK);
        ownerFrame.getContentPane().add(ownerViewButton2);

        ownerViewButton3 = new JButton("View Monthly Report");
        ownerViewButton3.setBounds(200, 170, 150, 35);
        ownerViewButton3.setBorder(BorderFactory.createLoweredBevelBorder());
        ownerViewButton3.setBackground(Color.WHITE);
        ownerViewButton3.setForeground(Color.BLACK);
        ownerFrame.getContentPane().add(ownerViewButton3);

        ownerLogoutButton = new JButton("Logout");
        ownerLogoutButton.setBounds(200, 210, 150, 35);
        ownerLogoutButton.setBorder(BorderFactory.createLoweredBevelBorder());
        ownerLogoutButton.setBackground(Color.WHITE);
        ownerLogoutButton.setForeground(Color.BLACK);
        ownerFrame.getContentPane().add(ownerLogoutButton);

        ownerLogoutButton.addActionListener(e ->
        {
            if (e.getSource() == ownerLogoutButton) {
                new LoginUI();
                ownerFrame.dispose();
            }
        });
    }
}
