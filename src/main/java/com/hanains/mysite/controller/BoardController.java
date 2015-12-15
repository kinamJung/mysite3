package com.hanains.mysite.controller;

import java.util.List;

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
import com.hanains.mysite.vo.BoardInfo;
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
	public String delete(@ModelAttribute BoardVo vo){
		boardService.delete(vo);
		return "redirect:/board/";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(@ModelAttribute BoardVo vo, Model model){
		
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
		
		System.out.println(board);
		
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
		
		System.out.println(vo);
		
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
	
	/*@RequestMapping("/comment")
	public String comment(@ModelAttribute BoardVo vo, Model model){
		
		//답글 리스트 뽑아오기
		List<BoardVo> list = boardService.getBoardListByGroup(vo.getGroupNo());
		
		for(BoardVo t : list){
			System.out.println(t);
		}
		
		model.addAttribute("groupNo", list.get(0).getGroupNo());
		model.addAttribute("list", list);
		
		return "/board/comment";
	}*/
	
	@RequestMapping("/commentInsert")
	public String commentInsert(@ModelAttribute BoardVo vo, Model model){
		
		System.out.println(vo);
		
		boardService.insertComment(vo, model);
	
		
		return "redirect:/board/";
	}
	
	
}







