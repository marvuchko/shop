-- Roles

MERGE INTO ROLE KEY(ID) 
VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ADMIN', 'Can perform all actions.', 10);

MERGE INTO ROLE KEY(ID) 
VALUES (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'EMPLOYEE', 'Can manage shop item data.', 20);

MERGE INTO ROLE KEY(ID) 
VALUES (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'USER', 'Can purchase shop items.', 10);

-- ADMIN

MERGE INTO SHOP_USER KEY(ID) 
VALUES (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Root', null, 'Admin', '$2a$10$x960zPz4RFa06BCgb4idoeuYSkvTXFsxpOTONUak8KPPoi5uYteW.', 'admin');

MERGE INTO USER_HAS_ROLE KEY(ID_SHOP_USER, ID_ROLE) VALUES (1, 1);

-- SHOP ITEMS

MERGE INTO SHOP_ITEM KEY(ID) 
VALUES(1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'A drink.', 'https://target.scene7.com/is/image/Target/GUEST_1d1acfbf-d627-4cee-a1ba-0853508cc4ff?wid=488&hei=488&fmt=pjpeg','Coca Cola', 10);

MERGE INTO SHOP_ITEM KEY(ID)
VALUES(2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Newest model of Apple mobile phone.', 'https://cdn.dxomark.com/wp-content/uploads/2018/07/apple_iphonex-1024x768.jpg','Iphone X', 1100);