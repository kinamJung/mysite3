-- 글쓰기

INSERT INTO board
     VALUES (board_no_seq.NEXTVAL,
             '제목입니다.',
             '내용입니다.',
             2,
             0,
             SYSDATE);

-- 글보기

SELECT no,
       title,
       content,
       member_no
  FROM board
 WHERE no = 1;
 
 -- 해당 그룹에 맞는 글 보기
 SELECT no,
       title,
       content,
       member_no
  FROM board
 WHERE group_no = 2
 ORDER by order_no asc;

-- 조회수 늘리기

UPDATE board
   SET view_cnt = view_cnt + 1
 WHERE no = 1;

-- 글수정

UPDATE board
   SET title = '수정된 제목', content = '스정된 내용'
 WHERE no = 1;

-- 삭제

DELETE FROM board
      WHERE no = 1;

-- commit
COMMIT;

-- 리스트

  SELECT a.no,
         a.title,
         a.member_no,
         b.name AS member_name,
         a.view_cnt,
         TO_CHAR (a.reg_date, 'yyyy-mm-dd hh:mi:ss')
    FROM board a, MEMBER b
   WHERE a.member_no = b.no
ORDER BY a.reg_date DESC;

select count(*) as count from board where title like ?;

SELECT *
  FROM (SELECT A.*, ROWNUM AS RNUM, COUNT (*) OVER () AS TOTCNT, COUNT (*) OVER () - ROWNUM as articleSequence
          FROM (  SELECT a.NO,
                         a.TITLE,
                         a.CONTENT,
                         a.MEMBER_NO,
                         b.NAME,
                         a.VIEW_CNT,
                         a.REG_DATE,
						 a.ORDER_NO,
						 a.GROUP_NO,
						 a.DEPTH
                    FROM board a, MEMBER b
                   WHERE a.member_no = b.no
                ORDER BY a.reg_date DESC) A)
 WHERE RNUM > 0 AND RNUM <= 5;

--
select no, title, content, member_no, view_cnt, reg_date from BOARD b where b.TITLE like '%우와%';

SELECT *
  FROM (SELECT A.*, ROWNUM AS RNUM, COUNT (*) OVER () AS TOTCNT
          FROM (  SELECT a.NO,
                         a.TITLE,
                         a.CONTENT,
                         a.MEMBER_NO,
                         b.NAME,
                         a.VIEW_CNT,
                         a.REG_DATE

                    FROM board a, MEMBER b
                   WHERE a.member_no = b.no AND a.TITLE like '%우와%'
                ORDER BY a.reg_date DESC) A)
 WHERE RNUM > 0 AND RNUM <= 5;

--

-- 칼럼 추가
ALTER TABLE WEBDB.BOARD
   ADD ("group_no" NUMBER (10, 2) );
   
ALTER TABLE WEBDB.BOARD
   ADD ("order_no" NUMBER (10, 2) );   
   
ALTER TABLE WEBDB.BOARD
   ADD ("depth" NUMBER (10, 2) ); 

COMMIT;

SELECT COUNT (*) AS COUNT FROM board;