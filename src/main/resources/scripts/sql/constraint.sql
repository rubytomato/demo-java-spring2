ALTER TABLE item ADD CONSTRAINT FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE;

ALTER TABLE item_stock ADD CONSTRAINT FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE;
ALTER TABLE item_stock ADD CONSTRAINT FOREIGN KEY (location_id) REFERENCES location(id) ON DELETE SET NULL;

ALTER TABLE user_order ADD CONSTRAINT FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE SET NULL;
ALTER TABLE user_order ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE user_review ADD CONSTRAINT FOREIGN KEY (user_order_id) REFERENCES user_order(id) ON DELETE SET NULL;
ALTER TABLE user_review ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;

/*
ALTER TABLE item DROP FOREIGN KEY "<name>";
ALTER TABLE item_stock DROP FOREIGN KEY "<name>";
ALTER TABLE user_order DROP FOREIGN KEY "<name>";
ALTER TABLE user_review DROP FOREIGN KEY "<name>";
*/