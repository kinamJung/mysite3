

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
  	
   							
   			
   							