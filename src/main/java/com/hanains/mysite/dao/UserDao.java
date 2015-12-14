package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.hanains.mysite.exception.RepositoryException;
import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.UserVo;

@Repository
public class UserDao {

	
	private static final String UPDATE_USER_PASSWORD_QUERY = " update MEMBER SET PASSWORD = ? where no = ?";
	private static final String INSERT_USER_QUERY = " insert" +
													" into member" +
													" values ( member_no_seq.nextval, ?, ?, ?, ? )";
	
	private static final String SELECT_USER_INFO_QUERY = " select no, name, email" +
														"   from member" +
														"  where email=?"+
														"    and password=?";
	
	private static final String SELECT_PASSWORD_INFO_QUERY = "select password from member where no = ? ";
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			//1.드라이버 로딩
			Class.forName( Common.ORCLE_DRIVER );
		
			//2.커넥션 만들기(ORACLE DB)
			String dbURL  = Common.CONNECT_DB_URL;
			connection = DriverManager.getConnection( dbURL, Common.DB_USER, Common.DB_PASSWORD );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		}
		
		return connection;
	}
	
	
	public boolean setPassword(UserVo vo)
	{
		boolean retVal = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. DB Connection
			conn = getConnection();
			
			//2. prepare statment
			pstmt = conn.prepareStatement( UPDATE_USER_PASSWORD_QUERY );
			
			//3. binding
			pstmt.setString( 1, vo.getPassword() );
			pstmt.setLong( 2, vo.getNo() );
			
			//4. execute SQL
			pstmt.executeUpdate();
			retVal = true;
			
		} catch( SQLException ex ) {
			System.out.println( "sql error:" + ex );
			ex.printStackTrace();
		} finally {
			//5. clear resources
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
		return retVal;
	}
	
	public String getPassword(Long no)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String password = null;
		
		
		try{
			//1. get Connection
			conn = getConnection();
			
			//2. prepare statement
			pstmt = conn.prepareStatement( SELECT_PASSWORD_INFO_QUERY );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. execute SQL
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				password = rs.getString( 1 );
				
			}
			
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		} finally {
			//5. clear resources
			try{
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
		return password;
	}
	
	public UserVo get( String email, String password ) throws RepositoryException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVo vo = null;
		
		try{
			//1. get Connection
			conn = getConnection();
			
			//2. prepare statement
			pstmt = conn.prepareStatement( SELECT_USER_INFO_QUERY );
			
			//3. binding
			pstmt.setString( 1, email );
			pstmt.setString( 2, password );
			
			//4. execute SQL
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String email2 = rs.getString( 3 );
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email2);
			}
			
		} catch( SQLException ex ) {
			//log 처리
			System.out.println( "SQL Error:" + ex );
			throw new RepositoryException(ex.toString());
			
		} finally {
			//5. clear resources
			try{
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
		return vo;
	}

	public void insert( UserVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. DB Connection
			conn = getConnection();
			
			//2. prepare statment
			pstmt = conn.prepareStatement( INSERT_USER_QUERY );
			
			//3. binding
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getEmail() );
			pstmt.setString( 3, vo.getPassword() );
			pstmt.setString( 4, vo.getGender() );
			
			//4. execute SQL
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "sql error:" + ex );
			ex.printStackTrace();
		} finally {
			//5. clear resources
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
	}
}