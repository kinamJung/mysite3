package com.hanains.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanains.mysite.service.GusetbookService;
import com.hanains.mysite.vo.GuestBookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GusetbookService guestbookService;
	
	@RequestMapping("/")
	public String list(Model model){
		
		List<GuestBookVo> list = guestbookService.list();
		
		model.addAttribute("list", list);
		
		return "/guestbook/list";
	}
	
	
	@RequestMapping(value="/deleteform/{no}", method= RequestMethod.GET)
	public String deleteForm(@PathVariable("no") Long no, Model model){
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo)
	{
		guestbookService.delete(vo);
		
		return "redirect:/guestbook/";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo vo){
		
		System.out.println(vo);
		
		guestbookService.add(vo);
		
		return "redirect:/guestbook/";
		
	}
	
	
}
