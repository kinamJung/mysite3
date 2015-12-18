drop table UPLOAD_FILE;
drop sequence upload_file_no_seq;

CREATE TABLE UPLOAD_FILE
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


commit;