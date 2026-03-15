
INSERT INTO brand
(name, description, created_at, updated_at)
VALUES
    ('Apple','Apple electronics',now(),now()),
    ('Samsung','Samsung electronics',now(),now()),
    ('Logitech','Computer accessories',now(),now());


INSERT INTO category
(name, description, created_at, updated_at)
VALUES
    ('Laptops','All laptops',now(),now()),
    ('Accessories','Computer accessories',now(),now()),
    ('Phones','Smartphones',now(),now());


INSERT INTO shop
(name, description, created_at, updated_at, owner_id)
VALUES
    ('Tech Store','Electronics shop',now(),now(),3);



INSERT INTO product
(name, description, created_at, updated_at, price, stock, category_id, brand_id, shop_id)
VALUES
    ('MacBook Pro','Apple laptop',now(),now(),2400,10,1,1,1),

    ('Samsung Galaxy S24','Latest Samsung phone',now(),now(),1100,25,3,2,1),

    ('Logitech MX Master','Wireless mouse',now(),now(),120,50,2,3,1);



INSERT INTO product_image
(name, description, created_at, updated_at, product_id, image_url, is_main)
VALUES
    ('MacBook image','Main image',now(),now(),1,'https://example.com/macbook.jpg',true),

    ('Galaxy image','Main image',now(),now(),2,'https://example.com/galaxy.jpg',true),

    ('Mouse image','Main image',now(),now(),3,'https://example.com/mouse.jpg',true);



INSERT INTO address
(name, description, created_at, updated_at, user_id, country, city, street, postal_code)
VALUES
    ('Home Address','Customer home',now(),now(),2,'Poland','Warsaw','Main Street 10','00-001');



INSERT INTO cart
(name, description, created_at, updated_at, user_id)
VALUES
    ('John Cart','Shopping cart',now(),now(),2);


INSERT INTO cart_item
(name, description, created_at, updated_at, cart_id, product_id, quantity)
VALUES
    ('Mouse in cart','Cart item',now(),now(),1,3,1);


INSERT INTO orders
(user_id, status, total_price, created_at, address_id)
VALUES
    (2,'DELIVERED',2520,now(),1);


INSERT INTO order_item
(order_id, product_id, price, quantity)
VALUES
    (1,1,2400,1),
    (1,3,120,1);


INSERT INTO payment
(name, description, created_at, updated_at, order_id, amount, payment_method, status)
VALUES
    ('Order payment','Mock payment',now(),now(),1,2520,'CARD','PAID');



INSERT INTO review
(name, description, created_at, updated_at, product_id, user_id, rating, comment)
VALUES
    ('Great laptop','Amazing performance',now(),now(),1,2,5,'Best laptop I have owned'),

    ('Nice mouse','Very comfortable',now(),now(),3,2,4,'Great for work');

UPDATE product
SET average_rating = 5, review_count = 1
WHERE id = 1;

UPDATE product
SET average_rating = 4, review_count = 1
WHERE id = 3;


