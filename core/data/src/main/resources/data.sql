-- Roles

INSERT INTO ROLE (ID, CAPTION, DESCRIPTION, CREATED_AT, UPDATED_AT, DISCOUNT_BY_ROLE)
VALUES (1,'ADMIN', 'Can perform all actions.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO ROLE (ID, CAPTION, DESCRIPTION, CREATED_AT, UPDATED_AT, DISCOUNT_BY_ROLE)
VALUES (2, 'EMPLOYEE', 'Can manage shop item data.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 20);

INSERT INTO ROLE (ID, CAPTION, DESCRIPTION, CREATED_AT, UPDATED_AT, DISCOUNT_BY_ROLE)
VALUES (3, 'USER', 'Can purchase shop items.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 0);

INSERT INTO ROLE (ID, CAPTION, DESCRIPTION, CREATED_AT, UPDATED_AT, DISCOUNT_BY_ROLE)
VALUES (4,'AFFILIATE', 'Can purchase shop items.', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 10);

-- ADMIN

INSERT INTO SHOP_USER (ID, FIRST_NAME, LAST_NAME, PASSWORD, USERNAME, CREATED_AT, UPDATED_AT, IMAGE_URL)
VALUES (1, 'Root', 'Admin', '$2a$10$x960zPz4RFa06BCgb4idoeuYSkvTXFsxpOTONUak8KPPoi5uYteW.', 'admin', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), null);

INSERT INTO USER_HAS_ROLE (ID_SHOP_USER, ID_ROLE) VALUES (1, 1);

-- SHOP ITEMS

INSERT INTO SHOP_ITEM (ID, DESCRIPTION, IMAGE_URL, NAME, PRICE, CREATED_AT, UPDATED_AT, TYPE)
VALUES(1, 'A drink.', 'https://target.scene7.com/is/image/Target/GUEST_1d1acfbf-d627-4cee-a1ba-0853508cc4ff?wid=488&hei=488&fmt=pjpeg','Coca Cola', 10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'GROCERIES');

INSERT INTO SHOP_ITEM (ID, DESCRIPTION, IMAGE_URL, NAME, PRICE, CREATED_AT, UPDATED_AT, TYPE)
VALUES(2, 'Newest model of Apple mobile phone.', 'https://cdn.dxomark.com/wp-content/uploads/2018/07/apple_iphonex-1024x768.jpg','Iphone X', 1100, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'OTHER');