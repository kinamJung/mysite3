package com.hanains.mysite.controller;

import javax.sound.midi.Track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;







import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("/")
	public String list(Model model){
		
		
		model = boardService.list(model);
		
		return "/board/list";
	}
	
	@RequestMapping( value = "/delete", method = RequestMethod.GET) 
	public String delete(@ModelAttribute GuestBookVo vo){
		boardService.delete(vo.getNo());
		return "redirect:/board/";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(@ModelAttribute GuestBookVo vo, Model model){
		
		model.addAttribute("no", vo.getNo());
		return "/board/write";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo vo){
		
		System.out.println(vo);
		
		boardService.insert(vo);
		
		return "redirect:/board/";
	}
	
	
	@RequestMapping("/view")
	public String boardView(@ModelAttribute BoardVo vo, Model model){
		
		System.out.println(vo);
		//update view Count
		boardService.updateView(vo);
		
		//select board vo
		BoardVo board = boardService.view(vo);
		
		model.addAttribute("boardVo", board);
		return "/board/view";
		
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(@ModelAttribute BoardVo vo, Model model){
		
		BoardVo board = boardService.view(vo);
		model.addAttribute("boardVo", board);
		
		return "/board/modify";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute BoardVo vo, Model model){
		
		System.out.println(vo);
		boardService.updateBoard(vo);
		
		model.addAttribute("boardVo", vo);
		return "/board/view";
		
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(value="search", required=true, defaultValue="" ) String keyword,
						 Model model){
		
		model = boardService.search(keyword, model);
		
		return "/board/list";
	}
	
	@RequestMapping("/viewpaging")
	public String viewPaging(@RequestParam(value="index", required=true, defaultValue="1")int index,
							
							@RequestParam(value="search", required=true, defaultValue="")String keyword,
							Model model){
		model = boardService.viewPaging(index, keyword, model);
		
		
		return "/board/list";
		
	}
	
}







