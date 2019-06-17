-- Roles

MERGE INTO ROLE KEY(ID)
VALUES (1,'ADMIN', 'Can perform all actions.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

MERGE INTO ROLE KEY(ID)
VALUES (2, 'EMPLOYEE', 'Can manage shop item data.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 20);

MERGE INTO ROLE KEY(ID)
VALUES (3, 'USER', 'Can purchase shop items.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

MERGE INTO ROLE KEY(ID)
VALUES (3,'AFFILIATE', 'Can purchase shop items.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 10);

-- ADMIN

MERGE INTO SHOP_USER KEY(ID)
VALUES (1, 'Root', 'Admin', '$2a$10$x960zPz4RFa06BCgb4idoeuYSkvTXFsxpOTONUak8KPPoi5uYteW.', 'admin', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), null);

MERGE INTO USER_HAS_ROLE KEY(ID_SHOP_USER, ID_ROLE) VALUES (1, 1);

-- SHOP ITEMS

MERGE INTO SHOP_ITEM KEY(ID)
VALUES(1, 'A drink.', 'https://target.scene7.com/is/image/Target/GUEST_1d1acfbf-d627-4cee-a1ba-0853508cc4ff?wid=488&hei=488&fmt=pjpeg','Coca Cola', 10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'GROCERIES');

MERGE INTO SHOP_ITEM KEY(ID)
VALUES(2, 'Newest model of Apple mobile phone.', 'https://cdn.dxomark.com/wp-content/uploads/2018/07/apple_iphonex-1024x768.jpg','Iphone X', 1100, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'OTHER');