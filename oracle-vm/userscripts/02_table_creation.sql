CONNECT C##POS_USER/secret

CREATE TABLE pos_book
(
  pos_book_isbn          CHAR(13)                      NOT NULL,
  pos_book_title         VARCHAR2(255),
  pos_book_author        VARCHAR2(150),
  pos_book_publisher     VARCHAR2(150),
  pos_book_release_date  DATE         ,
  pos_book_msrp          NUMBER(7)     DEFAULT 0       NOT NULL,
  pos_book_discount_rate NUMBER(2)     DEFAULT 0       NOT NULL,
  pos_book_point_rate    NUMBER(2)     DEFAULT 0       NOT NULL,
  pos_book_stock         NUMBER(5)     DEFAULT 0       NOT NULL,
  pos_book_insert_date   DATE          DEFAULT SYSDATE NOT NULL,
  pos_book_modify_date   DATE          DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_book PRIMARY KEY (pos_book_isbn)
);

CREATE TABLE pos_customer
(
  pos_customer_id          NUMBER                       NOT NULL,
  pos_customer_name        VARCHAR2(100)                NOT NULL,
  pos_customer_tel         CHAR(11)                     NOT NULL,
  pos_customer_point       NUMBER       DEFAULT 0       NOT NULL,
  pos_customer_total_price NUMBER       DEFAULT 0       NOT NULL,
  pos_customer_create_date DATE         DEFAULT SYSDATE NOT NULL,
  pos_customer_modify_date DATE         DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_customer PRIMARY KEY (pos_customer_id)
);

CREATE TABLE pos_entry
(
  pos_entry_id          NUMBER                        NOT NULL,
  pos_entry_password    VARCHAR2(255)                 NOT NULL,
  pos_entry_modify_date DATE          DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_entry PRIMARY KEY (pos_entry_id)
);

CREATE TABLE pos_sell
(
  pos_sell_id          NUMBER                    NOT NULL,
  pos_book_isbn        CHAR(13)                  NOT NULL,
  pos_customer_id      NUMBER                    NOT NULL,
  pos_sell_count       NUMBER(5) DEFAULT 1       NOT NULL,
  pos_sell_price       NUMBER    DEFAULT 0       NOT NULL,
  pos_sell_date        DATE      DEFAULT SYSDATE NOT NULL,
  pos_sell_used_point  NUMBER    DEFAULT 0       NOT NULL,
  CONSTRAINT PK_pos_sell PRIMARY KEY (pos_sell_id)
);

CREATE TABLE pos_stock
(
  pos_stock_id     NUMBER                    NOT NULL,
  pos_book_isbn    CHAR(13)                  NOT NULL,
  pos_stock_amount NUMBER(5) DEFAULT 1       NOT NULL,
  pos_stock_date   DATE      DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_stock PRIMARY KEY (pos_stock_id)
);

CREATE SEQUENCE SEQ_pos_customer
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_pos_sell
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_pos_stock
START WITH 1
INCREMENT BY 1;

INSERT INTO pos_customer (pos_customer_id, pos_customer_name, pos_customer_tel)
VALUES (0, '비회원', '00000000000');

INSERT INTO pos_entry (pos_entry_id, pos_entry_password)
VALUES (0, '$2a$10$n6sPufGH.rKPt92hgOvy5eyDUZojHHhv7wlo1N4oXZOJOwYhFl1Uy');

COMMIT;