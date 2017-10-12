use toyStore;
show tables;

-- question 1 --

SELECT order_id, DATE_FORMAT(order_date,'%Y, %M %D') 
FROM orders ORDER BY order_date DESC;

-- question 2 --

SELECT CONCAT_WS(" ", `first_name`, `last_name`) AS customer, TIMESTAMPDIFF(YEAR,birthday,CURDATE()) 
AS age FROM account ORDER BY CONCAT_WS(" ", `first_name`, `last_name`);

-- question 3 --

SELECT CONCAT(first_name, ' ', last_name) AS Customer, RIGHT(email,9) AS 'Email Domain' 
FROM account WHERE EMAIL LIKE ('%yahoo.com') OR email LIKE ('%gmail.com') ORDER BY right(email,9) DESC;

-- question 4 --

SELECT gender AS Gender, FORMAT(COUNT(*) / (SELECT COUNT(*) FROM account) * 100,0)
AS '% Account Holders' FROM ACCOUNT WHERE gender = 'M' OR gender = 'F' GROUP BY GENDER;

-- question 5 --

SELECT DATE_FORMAT(order_date, '%m') AS 'Month', COUNT(*) AS 'No. Orders Placed'
FROM orders GROUP BY DATE_FORMAT(order_date, '%m') ORDER BY DATE_FORMAT(order_date, '%m');

-- question 6 --

SELECT (SELECT SUM(quantity) FROM lineitem) / (SELECT COUNT(DISTINCT order_id) FROM lineitem)
AS 'Average Qty Ordered Per Order';

-- question 7 --

SELECT cc_type AS 'Most Popular Credit Card' FROM orders GROUP BY cc_type ORDER BY COUNT(cc_type) DESC LIMIT 1;

-- question 8 --

SELECT CONCAT(first_name, " ", last_name) AS 'Account Holder', o.order_id AS OrderNo, order_date AS 'Order Date', p.product_id AS 'Product ID'
FROM account a JOIN orders o ON a.user_id = o.user_id JOIN lineitem l ON l.order_id = o.order_id JOIN item i ON i.item_id = l.item_id 
JOIN product p ON p.product_id = i.product_id WHERE series LIKE 'Fate/Stay Night' GROUP BY p.product_id;

-- question 9 --

CREATE VIEW genre_product_view AS SELECT genre AS 'Genre', name AS 'Product Name'
FROM product p JOIN xrefseriesgenre x on p.series = x.series ORDER BY genre;

-- question 10 --

SELECT state_province AS State, SUM(total_price) AS 'Total Price of Orders within State'
FROM account a JOIN orders o ON a.user_id = o.user_id GROUP BY state_province;

-- question 11 --

SELECT product_id AS 'Product ID', listprice AS 'List Price', unitprice AS 'Unit Price',
FORMAT(100 * (listprice - unitprice) / unitprice,0) AS 'Mark-up %' FROM item;

-- question 12 --

SELECT order_id AS 'Order No', order_date AS 'Order Date',
DATE_ADD(order_date, INTERVAL 3 DAY) AS 'Expected Delivery Date' FROM orders;


