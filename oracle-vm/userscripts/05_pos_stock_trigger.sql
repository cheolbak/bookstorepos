CONNECT C##POS_USER/secret

CREATE OR REPLACE TRIGGER SEQ_TRG_pos_stock
BEFORE INSERT ON pos_stock
REFERENCING NEW AS NEW FOR EACH ROW
BEGIN
  SELECT SEQ_pos_stock.NEXTVAL
  INTO: NEW.pos_stock_id
  FROM DUAL;
END;
/