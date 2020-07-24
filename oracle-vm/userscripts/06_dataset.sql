CONNECT C##POS_USER/secret

-- 책 만들기

INSERT INTO pos_book (pos_book_isbn, pos_book_title, pos_book_author, pos_book_publisher, pos_book_release_date, pos_book_msrp, pos_book_discount_rate, pos_book_point_rate)
VALUES ('9788968481475', '이것이 자바다', '신용권', '한빛미디어', TO_DATE('2015-01-15', 'YYYY-MM-DD'), 30000, 10, 5);
INSERT INTO pos_book (pos_book_isbn, pos_book_title, pos_book_author, pos_book_publisher, pos_book_release_date, pos_book_msrp, pos_book_discount_rate, pos_book_point_rate)
VALUES ('9791162241875', '혼자 공부하는 자바', '신용권', '한빛미디어', TO_DATE('2019-06-10', 'YYYY-MM-DD'), 24000, 10, 5);

-- 사람 만들기(32명)

INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test1', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test2', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test3', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test4', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test5', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test6', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test7', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test8', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test9', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test10', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test11', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test12', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test13', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test14', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test15', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test16', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test17', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test18', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test19', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test20', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test21', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test22', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test23', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test24', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test25', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test26', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test27', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test28', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test29', '01000000000');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('test30', '01000000000');

INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('customer', '01099999999');
INSERT INTO pos_customer (pos_customer_name, pos_customer_tel)
VALUES ('customer2', '01099999999');

-- 판매량 만들기

-- customer(31),isbn(9788968481475) 기준 최근 6달, 6주

INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 3, TO_DATE('2020-02-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 6, TO_DATE('2020-03-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 9, TO_DATE('2020-04-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 6, TO_DATE('2020-05-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 12, TO_DATE('2020-06-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 10, TO_DATE('2020-07-01', 'YYYY-MM-DD'));

INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 1, TO_DATE('2020-06-15', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 2, TO_DATE('2020-06-22', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 1, TO_DATE('2020-06-29', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 3, TO_DATE('2020-07-06', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 2, TO_DATE('2020-07-13', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9788968481475', 31, 4, TO_DATE('2020-07-20', 'YYYY-MM-DD'));

-- customer(32),isbn(9791162241875) 기준 최근 6달, 6주

INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 10, TO_DATE('2020-02-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 8, TO_DATE('2020-03-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 6, TO_DATE('2020-04-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 8, TO_DATE('2020-05-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 2, TO_DATE('2020-06-01', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 25, TO_DATE('2020-07-01', 'YYYY-MM-DD'));

INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 8, TO_DATE('2020-06-15', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 7, TO_DATE('2020-06-22', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 1, TO_DATE('2020-06-29', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 10, TO_DATE('2020-07-06', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 3, TO_DATE('2020-07-13', 'YYYY-MM-DD'));
INSERT INTO pos_sell (pos_book_isbn, pos_customer_id, pos_sell_count, pos_sell_date)
VALUES ('9791162241875', 32, 4, TO_DATE('2020-07-20', 'YYYY-MM-DD'));

COMMIT;