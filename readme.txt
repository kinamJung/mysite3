

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

  	※ 파일을 관리하기 위해 UploadFileVo 추가.
  	※ AuthLoginInterceptor.preHandle에서 response.sendRedirect( request.getContextPath() + "/" ) 부분에서 
  	request.getContextPath()만 리다이렉트할 경우 세션이 사라지는 문제 발생. 이를 getContextPath에 /를 붙여 문제 해결.
   							
   			
   							