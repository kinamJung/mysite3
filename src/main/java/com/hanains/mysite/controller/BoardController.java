package com.hanains.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;












import org.springframework.web.multipart.MultipartFile;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.annotation.AuthUser;
import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.BoardInfo;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;
import com.hanains.mysite.vo.UploadFileVo;
import com.hanains.mysite.vo.UserVo;

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
	
	@Auth
	@RequestMapping("/writeForm")
	public String writeForm(@ModelAttribute BoardVo vo, Model model){
		
		model.addAttribute("board", vo);
		
		return "/board/write";
	}
	
	@RequestMapping("/write")
	public String write(@AuthUser UserVo authUser,
						@ModelAttribute BoardVo vo,
						@RequestParam( "uploadFile" ) MultipartFile multipartFile[]){
		
		vo.setMemberNo(authUser.getNo());
	
		boardService.insert(vo, multipartFile);
		
		return "redirect:/board/";
	}
	
	
	@RequestMapping("/view")	
	public String boardView(@ModelAttribute BoardVo vo,
							@RequestParam(value="search",required=true,defaultValue="")String search,
							@RequestParam(value="index",required=true, defaultValue="1" )int index,
							Model model){
		
		//update view Count
		boardService.updateView(vo);
		
		//select board vo
		Map<String, Object> map = new HashMap<String, Object>();
		map = boardService.view(vo);
		
		BoardVo board = (BoardVo)map.get("board");
		List<UploadFileVo> list = (List<UploadFileVo>)map.get("uploadFile");
		
		//파일 이름에 경로 추가. // view.jsp에 파일을 다운받게 하기 위해
		for(UploadFileVo file : list){
			file.setFileName( Common.FILE_MAPPING_URL + file.getFileName());		
		}
	
		model.addAttribute("search", search);
		model.addAttribute("index", index);
		model.addAttribute("boardVo", board);
		model.addAttribute("uploadFileList", list);
		return "/board/view";
		
	}
	
	@Auth
	@RequestMapping("/modifyform")
	public String modifyForm(@ModelAttribute BoardVo vo, Model model){
		
	
		Map<String, Object> map = new HashMap<String, Object>();
		map = boardService.view(vo);
		
		BoardVo board = (BoardVo)map.get("board");
		
		model.addAttribute("boardVo", board);
		
		return "/board/modify";
	}
	
	@RequestMapping("/update")
	public String update( @AuthUser UserVo authUser,
						  @ModelAttribute BoardVo vo, Model model){
		
		vo.setMemberNo(authUser.getNo());
		boardService.updateBoard(vo);
		
		model.addAttribute("boardVo", vo);
		return "/board/view";
		
	}
	@RequestMapping("/viewpaging")
	public String viewPaging(@RequestParam(value="index", required=true, defaultValue="1")int index,
							
							@RequestParam(value="search", required=true, defaultValue="")String keyword,
							Model model){
		model = boardService.viewPaging(index, keyword, model);
		return "/board/list";		
	}

}







