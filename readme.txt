

					  MySite 구현.
		   
		   									      정기남
		   							
  	※ BOARD테이블에서 ORDER_NO, GROUP_NO, DEPTH 칼럼 추가.
  		ALTER TABLE WEBDB.BOARD
   			ADD (GROUP_NO NUMBER (10, 2) );
   
		ALTER TABLE WEBDB.BOARD
   			ADD (ORDER_NO NUMBER (10, 2) );   
   
		ALTER TABLE WEBDB.BOARD
   			ADD (DEPTH NUMBER (10, 2) ); 
  	
  	※ MEMBER 테이블과 BOARD 테이블간의 조인값을 처리하기 위한 BoardInfo 클래스 추가.
  	
  	※파일 첨부 기능 추가로 인해 UploadFile table 추가.
  	  -> upload_file.sql 폴더에 있는  테이블생성, 시퀀스 생성 쿼리문을 통해 테이블과 시퀀스 생성.

  	※파일을 관리하기 위해 UploadFileVo 추가.
  	
   							
   			
   							