package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.BoardInfo;
import com.hanains.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;

	public Model list(Model model) {

		List<BoardInfo> list = dao.getListByFaging(1,
				Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE);
		int tempSize = dao.getBoardCount();
		int pagingSize = (tempSize / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) + 1;
		if (tempSize % Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE == 0) {
			pagingSize = pagingSize - 1;
		}
		
		model.addAttribute("search", "");
		model.addAttribute("size", pagingSize);
		model.addAttribute("list", list);
		model.addAttribute("index", 1);

		return model;

	}

	public void delete(BoardVo vo) {
		dao.deleteBoard(vo);
	}
	
	public void insert(BoardVo vo) {

		//새글인 경우 
		if( vo.getGroupNo() == 0 &&
				vo.getOrderNo() == 0 && vo.getDepth() == 0){
			long maxValue = 0;
			int boardCount = dao.getBoardCount();
			if( boardCount == 0 ){
				maxValue = 0;
			}else{
				maxValue = dao.getGroupMaxValue();
			}
			
			vo.setGroupNo(maxValue+1);
			vo.setOrderNo(vo.getOrderNo()+1);
			vo.setDepth(vo.getDepth()+1);
			
			dao.insert(vo);
		}else{ // 코멘트인 경우
		
			long tempOrderNo = dao.getOrderMaxNoByGroupNo(vo.getGroupNo());
			vo.setOrderNo(vo.getOrderNo()+1); // 부모글에 orderNo와 depth 1증가
			vo.setDepth(vo.getDepth()+1);
			if( vo.getOrderNo() <= tempOrderNo ){
				dao.updateOrderNoAndDepth(vo); // 1단계 증가
			}
			//해당 위치에 글 삽입.
			dao.insert(vo);			
		}
		
	}

	public void updateView(BoardVo vo) {
		dao.updateViewCount(vo);
	}

	public BoardVo view(BoardVo vo) {
		BoardVo board = dao.getBoardVo(vo);
		return board;
	}

	public void updateBoard(BoardVo vo) {
		dao.updateBoard(vo);
	}
	
	public Model search(String keyword, Model model) {

		int pagingIndex = 1; // 현재 페이지를 가리킬 변수
		List<BoardInfo> list = null;

		// 한 페이지에 보이는 게시글 수
		int onePagingShowBoard = -1;
		int boaodCount = 0; // 필터링된 board 총 개수
		if (keyword.equals("")) {
			System.out.println("[info]SearchAction...equals");
			list = dao.getListByFaging(pagingIndex,
					Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE);
			boaodCount = dao.getBoardCount();
			onePagingShowBoard = (boaodCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) + 1;
		} else {
			list = dao.getListByFaging(pagingIndex,
					Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE, keyword);
			boaodCount = dao.getBoardCount(keyword);
			onePagingShowBoard = (boaodCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) + 1;
		}

		if (boaodCount % Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE == 0) {
			onePagingShowBoard = onePagingShowBoard - 1;
		}

		model.addAttribute("search", keyword);
		model.addAttribute("list", list);
		model.addAttribute("size", onePagingShowBoard);
		model.addAttribute("index", pagingIndex);

		return model;
	}

	public Model viewPaging(int index, String keyword,
			Model model) {

		List<BoardInfo> list = null;
		// Paging 처리
		int paging = -1;
		int totCount = 0;
		if (keyword.equals("")) {
			System.out.println("[info]DisplayPaginAction-equal");
			list = dao.getListByFaging(index,
					Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE);
			totCount = dao.getBoardCount();
			paging = (totCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) + 1;

		} else {
			System.out.println("[info]DisplayPaginAction-NotEqual");
			list = dao.getListByFaging(index,
					Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE, keyword);
			totCount = dao.getBoardCount(keyword);
			paging = (totCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) + 1;

		}

		// 한 페이지에 보이는 게시글 수
		if (totCount % Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE == 0) {
			paging = paging - 1;
		}

		model.addAttribute("index", index);
		model.addAttribute("search", keyword);		
		model.addAttribute("size", paging);
		model.addAttribute("list", list);
		
		
		return model;
	}
	
	//트랜잭션 필요할거 같네
	public boolean insertComment(BoardVo vo, Model model){
		
		
		
		return false;
	}
	
}
