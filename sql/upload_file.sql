CREATE TABLE WEBDB.UPLOAD_FILE
(
   NO                 NUMBER (10, 2) NOT NULL,
   BOARD_NO           NUMBER (10, 2) NOT NULL,
   FILE_NAME          VARCHAR2 (100) NOT NULL,
   ORIGIN_FILE_NAME   VARCHAR2 (100) NOT NULL,
   MINE_TYPE          VARCHAR2 (80) NOT NULL,
   PRIMARY KEY (NO)
);

CREATE SEQUENCE upload_file_no_seq
 START WITH     1
 INCREMENT BY   1
 MAXVALUE       99999999
 NOCACHE
 NOCYCLE;
 


-- insert
INSERT INTO UPLOAD_FILE VALUES (upload_file_no_seq.nextval, 3, '123345.jpg', 'abcd.jpg','jpg');

-- select
SELECT NO, FILE_NAME as fileName, ORIGIN_FILE_NAME as originFileName, MINE_TYPE as mineType 
from UPLOAD_FILE
WHERE BOARD_NO = 145;