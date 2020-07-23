--전체 통계

-- 초기화

delete from pos_stock;
delete from pos_sell;
delete from pos_book;
delete from pos_customer;
commit;

drop sequence SEQ_POS_CUSTOMER;
CREATE SEQUENCE  "C##POS_USER"."SEQ_POS_CUSTOMER"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
drop sequence SEQ_POS_SELL;
CREATE SEQUENCE  "C##POS_USER"."SEQ_POS_SELL"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
drop sequence SEQ_POS_STOCK;
CREATE SEQUENCE  "C##POS_USER"."SEQ_POS_STOCK"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
commit;

drop trigger SEQ_TRG_pos_customer;
CREATE OR REPLACE TRIGGER SEQ_TRG_pos_customer
BEFORE INSERT ON pos_customer
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_customer.NEXTVAL
  INTO: NEW.pos_customer_id
  FROM DUAL;
END;
/
drop trigger SEQ_TRG_pos_sell;
CREATE OR REPLACE TRIGGER SEQ_TRG_pos_sell
BEFORE INSERT ON pos_sell
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_sell.NEXTVAL
  INTO: NEW.pos_sell_id
  FROM DUAL;
END;
/
drop trigger SEQ_TRG_pos_stock;
CREATE OR REPLACE TRIGGER SEQ_TRG_pos_stock
BEFORE INSERT ON pos_stock
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_stock.NEXTVAL
  INTO: NEW.pos_stock_id
  FROM DUAL;
END;
/
commit;

-- 책 만들기

insert into pos_book(pos_book_isbn, POS_BOOK_TITLE, POS_BOOK_AUTHOR, POS_BOOK_PUBLISHER,
POS_BOOK_RELEASE_DATE) values('9788968481475','이것이 자바다','신용권','한빛미디어','2015-01-15');
insert into pos_book(pos_book_isbn, POS_BOOK_TITLE, POS_BOOK_AUTHOR, POS_BOOK_PUBLISHER,
POS_BOOK_RELEASE_DATE) values('9791162241875','혼자 공부하는 자바','신용권','한빛미디어','2019-06-10');
commit;

-- 사람 만들기(32명)
  
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test1','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test2','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test3','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test4','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test5','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test6','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test7','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test8','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test9','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test10','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test11','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test12','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test13','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test14','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test15','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test16','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test17','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test18','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test19','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test20','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test21','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test22','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test23','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test24','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test25','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test26','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test27','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test28','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test29','01000000000');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('test30','01000000000');

insert into pos_customer(pos_customer_name, pos_customer_tel)
values('customer','01099999999');
insert into pos_customer(pos_customer_name, pos_customer_tel)
values('customer2','01099999999');
commit;

-- 판매량 만들기

-- customer(31),isbn(9788968481475) 기준 최근 6달, 6주

insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,3,'2020-02-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,6,'2020-03-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,9,'2020-04-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,6,'2020-05-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,12,'2020-06-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,10,'2020-07-01');

insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,1,'2020-06-15');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,2,'2020-06-22');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,1,'2020-06-29');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,3,'2020-07-06');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,2,'2020-07-13');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9788968481475',31,4,'2020-07-20');

-- customer(32),isbn(9791162241875) 기준 최근 6달, 6주

insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,10,'2020-02-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,8,'2020-03-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,6,'2020-04-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,8,'2020-05-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,2,'2020-06-01');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,25,'2020-07-01');

insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,8,'2020-06-15');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,7,'2020-06-22');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,1,'2020-06-29');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,10,'2020-07-06');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,3,'2020-07-13');
insert into pos_sell(pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
values('9791162241875',32,4,'2020-07-20');
commit;


