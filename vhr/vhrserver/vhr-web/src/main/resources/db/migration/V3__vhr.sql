-- alter menu table , add field : sort_order

ALTER TABLE MENU ADD sortOrder INT(11) DEFAULT 0 COMMENT '排序';
