package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(HttpSession session , @ModelAttribute UserVo vo){
 
		UserVo authUser =  userService.login(vo);		
		System.out.println(authUser);
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session ){
 
		session.removeAttribute("authUser");
		session.invalidate();		
		return "redirect:/";
	}
	@RequestMapping("/loginform")
	public String loginform(@ModelAttribute UserVo vo){
		
		
		System.out.println("hello world");
		return "/user/loginform";
	}
	
	
	@RequestMapping("/joinform")
	public String joinform(){
		System.out.println("hello world");
		return "/user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){

		userService.join(vo);
		
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/join/joinsuccess")
	public String joinSuccess(){

		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loinform")
	public String loinform(){
		
		return "/user/loinform";
	}
	
}
