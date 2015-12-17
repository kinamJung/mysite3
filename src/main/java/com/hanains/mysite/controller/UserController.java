package com.hanains.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/loginform")
	public String loginform(@ModelAttribute UserVo vo){
	
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
	
	@Auth
	@RequestMapping("/updateform")
	public String updateForm(){
		
		return "/user/updateform";
		
	}
	
	@RequestMapping("/loginfail")
	public String loginFormRetry(){
		return "/user/loginform_retry";
	}
	
	@RequestMapping("/update")
	public String updatePassword(@ModelAttribute UserVo vo,
								 @RequestParam(value="uptPassword",required=true, defaultValue="") String uptPassword,
								 Model model){
		String path = "";
		
		boolean isSuccess = userService.updatePassword(vo, uptPassword);
		
		//성공 여부에 따라 페이지를 다르게 보여준다.
		if( isSuccess == true){
			path =  "redirect:/";
		}else
		{
			path =  "/user/updateform_retry";
		}
		
		
		return path;
	}
}
