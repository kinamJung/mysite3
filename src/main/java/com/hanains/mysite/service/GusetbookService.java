package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.GuestBookDAO;
import com.hanains.mysite.vo.GuestBookVo;

@Service
public class GusetbookService {

	@Autowired
	private GuestBookDAO dao;
	
	public List<GuestBookVo> list(){
		
		List<GuestBookVo> list = dao.getList();
		return list;
		
	}
	
	public void delete(GuestBookVo vo){		
		dao.delete(vo);		
	}
	
	public void add(GuestBookVo vo){
		dao.insert(vo);
	}
	
}
