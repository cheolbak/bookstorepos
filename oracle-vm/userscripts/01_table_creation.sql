CREATE USER C##POS_USER
IDENTIFIED BY secret;
/

GRANT CONNECT, RESOURCE, DBA TO C##POS_USER;
/

CONNECT C##POS_USER/secret
/

CREATE TABLE pos_book
(
  pos_book_isbn          CHAR(13)      NOT NULL,
  pos_book_title         VARCHAR2(150),
  pos_book_author        VARCHAR2(150),
  pos_book_publisher     VARCHAR2(150),
  pos_book_release_date  DATE         ,
  pos_book_msrp          NUMBER(7)     DEFAULT 0 NOT NULL,
  pos_book_discount_rate NUMBER(2)     DEFAULT 0 NOT NULL,
  pos_book_point_rate    NUMBER(2)     DEFAULT 0 NOT NULL,
  pos_book_stock         NUMBER(5)     DEFAULT 0 NOT NULL,
  pos_book_insert_date   DATE          DEFAULT SYSDATE NOT NULL,
  pos_book_modify_date   DATE          DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_book PRIMARY KEY (pos_book_isbn)
);
/

CREATE TABLE pos_customer
(
  pos_customer_id          NUMBER       NOT NULL,
  pos_customer_name        VARCHAR2(20) NOT NULL,
  pos_customer_tel         CHAR(12)     NOT NULL,
  pos_customer_point       NUMBER       DEFAULT 0 NOT NULL,
  pos_customer_total_price  NUMBER       DEFAULT 0 NOT NULL,
  pos_customer_create_date DATE         DEFAULT SYSDATE NOT NULL,
  pos_customer_modify_date DATE         DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_customer PRIMARY KEY (pos_customer_id)
);
/

CREATE SEQUENCE SEQ_pos_customer
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER SEQ_TRG_pos_customer
BEFORE INSERT ON pos_customer
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_customer.NEXTVAL
  INTO: NEW.pos_customer_id
  FROM DUAL;
END;
/

CREATE TABLE pos_entry
(
  pos_entry_id          NUMBER        NOT NULL,
  pos_entry_password    VARCHAR2(255) NOT NULL,
  pos_entry_modify_date DATE          DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_entry PRIMARY KEY (pos_entry_id)
);
/

CREATE TABLE pos_sell
(
  pos_sell_id     NUMBER    NOT NULL,
  pos_book_isbn   CHAR(13)  NOT NULL,
  pos_customer_id NUMBER    NOT NULL,
  pos_sell_count  NUMBER(5) DEFAULT 1 NOT NULL,
  pos_sell_price  NUMBER DEFAULT 0 NOT NULL,
  pos_sell_date   DATE      DEFAULT SYSDATE NOT NULL,
  pos_sell_used_point  NUMBER DEFAULT 0 NOT NULL,
  CONSTRAINT PK_pos_sell PRIMARY KEY (pos_sell_id)
);
/

CREATE SEQUENCE SEQ_pos_sell
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER SEQ_TRG_pos_sell
BEFORE INSERT ON pos_sell
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_sell.NEXTVAL
  INTO: NEW.pos_sell_id
  FROM DUAL;
END;
/

CREATE TABLE pos_stock
(
  pos_stock_id     NUMBER    NOT NULL,
  pos_book_isbn    CHAR(13)  NOT NULL,
  pos_stock_amount NUMBER(5) DEFAULT 1 NOT NULL,
  pos_stock_date   DATE      DEFAULT SYSDATE NOT NULL,
  CONSTRAINT PK_pos_stock PRIMARY KEY (pos_stock_id)
);
/

CREATE SEQUENCE SEQ_pos_stock
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER SEQ_TRG_pos_stock
BEFORE INSERT ON pos_stock
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_stock.NEXTVAL
  INTO: NEW.pos_stock_id
  FROM DUAL;
END;
/

ALTER TABLE pos_sell
  ADD CONSTRAINT FK_pos_customer_TO_pos_sell
    FOREIGN KEY (pos_customer_id)
    REFERENCES pos_customer (pos_customer_id);
/

ALTER TABLE pos_sell
  ADD CONSTRAINT FK_pos_book_TO_pos_sell
    FOREIGN KEY (pos_book_isbn)
    REFERENCES pos_book (pos_book_isbn);
/

ALTER TABLE pos_stock
  ADD CONSTRAINT FK_pos_book_TO_pos_stock
    FOREIGN KEY (pos_book_isbn)
    REFERENCES pos_book (pos_book_isbn);
/
