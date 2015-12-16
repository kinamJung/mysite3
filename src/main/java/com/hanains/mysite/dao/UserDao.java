package com.hanains.mysite.dao;


import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hanains.mysite.exception.RepositoryException;
import com.hanains.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void setPassword(UserVo vo)
	{
		sqlSession.update("user.updatePassword",vo);
	}
	
	public String getPassword(UserVo vo)
	{

		String password = sqlSession.selectOne("user.getPasswordByNo",vo);
		return password;
	}
	
	public UserVo get( UserVo vo ) throws RepositoryException {
		
		UserVo userVo =  sqlSession.selectOne("user.getByEmailAndPassword", vo);
	
		return userVo;
	}
	
	public UserVo get( String email, String password ) throws RepositoryException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo =  sqlSession.selectOne("user.getByEmailAndPassword", map);
	
		return userVo;
	}

	public UserVo get(Long no){
		
		UserVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;
	}
	
	public UserVo get(String email){
		UserVo vo = sqlSession.selectOne("user.getUserByEmail",email);
		return vo;
	}
	
	public void insert( UserVo vo ) {
		
		sqlSession.insert("user.insert",vo);
		
	}
}