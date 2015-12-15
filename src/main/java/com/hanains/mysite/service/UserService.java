package com.hanains.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo){
		 
		userDao.insert(vo);
		
	}
	
	public UserVo login(UserVo vo){
		UserVo authUser=  userDao.get(vo);
		return authUser;
	}
	
	public boolean updatePassword(UserVo vo, String uptPassword){
		
		boolean retVal = false;
		String chkPassword =  userDao.getPassword(vo);
		
		//사용자가 입력한 암호가 현재 암호와 같은경우에 바꾸고자 하는 암호가 공백이 아닐경우에만 프로세스 진행
		if(chkPassword.equals( vo.getPassword() ) == true
				&& uptPassword.equals("") == false){
			
			vo.setPassword(uptPassword);
			userDao.setPassword(vo);
			retVal = true;
		}
		
		return retVal;
		
	}
	
}
