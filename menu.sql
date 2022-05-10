USE project314;

CREATE TABLE menu (itemNumber varchar(5) NOT NULL, itemName varchar(20) NOT NULL, itemPrice float(2), PRIMARY KEY(itemNumber));

INSERT INTO menu VALUE ("F1", "Chicken Rice", 4.50), ("F2", "Nasi Lemak", 4.00), ("F3", "Mee Goreng", 5.00),
					   ("B1", "Coffee", 1.50), ("B2", "Milo", 1.70), ("B3", "Bandung", 2.00);