package com.hanains.mysite.service;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.BoardInfo;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UploadFileVo;

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

		int endPage = (1 + Common.PAGE_BLOCK_SIZE) - 1;
		model.addAttribute("startpage", 1);
		model.addAttribute("endpage", endPage);

		
		return model;

	}

	public void delete(BoardVo vo) {
		dao.deleteBoard(vo);
	}

	@Transactional
	public void insert(BoardVo vo, MultipartFile multipartFile[]) {

		// 새글에 추가될 no
		Long no = 0L;

		// 새글인 경우
		if (vo.getGroupNo() == 0 ) {
			long maxValue = 0;
			int boardCount = dao.getBoardCount();
			if (boardCount == 0) {
				maxValue = 0;
			} else {
				maxValue = dao.getGroupMaxValue();
			}
			// 새글의 GroupNo, orderNo, depth 세팅
			vo.setGroupNo(maxValue + 1);
			vo.setOrderNo(1L);
			vo.setDepth(1L);
			
			System.out.println(vo);
			
			no = dao.insert(vo);
		} else { // 코멘트인 경우

			long tempOrderNo = dao.getOrderMaxNoByGroupNo(vo.getGroupNo());
			vo.setOrderNo(vo.getOrderNo() + 1); // 부모글에 orderNo와 depth 1증가
			vo.setDepth(vo.getDepth() + 1);
			if (vo.getOrderNo() <= tempOrderNo) {
				dao.updateOrderNoAndDepth(vo); // 1단계 증가
			}
			// 해당 위치에 글 삽입.
			no = dao.insert(vo);
		}

		// 첨부파일 처리
		for (MultipartFile multiFile : multipartFile) {

			if (multiFile.isEmpty() == false) {

				String fileOriginalName = multiFile.getOriginalFilename();
				String extName = fileOriginalName.substring(
						fileOriginalName.lastIndexOf(".") + 1,
						fileOriginalName.length());

				String saveFileName = genSaveFileName(extName);
				// 파일 쓰기
				writeFile(multiFile, Common.FILE_PATH, saveFileName);

				// insert To DB
				dao.insert(no, saveFileName, fileOriginalName, extName);
			}
		}

	}

	private void writeFile(MultipartFile file, String path, String fileName) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path + fileName);
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private String genSaveFileName(String extName) {

		Calendar calendar = Calendar.getInstance();
		String fileName = "";

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

	public void updateView(BoardVo vo) {
		dao.updateViewCount(vo);
	}

	public Map<String, Object> view(BoardVo vo) {
		BoardVo board = dao.getBoardVo(vo);
		List<UploadFileVo> uploadfileList = dao.getUploadFileListByBoardNo(vo
				.getNo());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("uploadFile", uploadfileList);

		return map;
	}

	public void updateBoard(BoardVo vo) {
		dao.updateBoard(vo);
	}

	public Model viewPaging(int index, String keyword, Model model) {

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

		// 해당 인덱스가 존재하는 처음페이지와 끝페이지를 구함.
		int temp = (index / Common.PAGE_BLOCK_SIZE);

		if (index % (Common.PAGE_BLOCK_SIZE) == 0) {
			temp = temp - 1;
		}

		int startPage = (temp * Common.PAGE_BLOCK_SIZE) + 1;
		int endPage = startPage + Common.PAGE_BLOCK_SIZE - 1;

		model.addAttribute("startpage", startPage);
		model.addAttribute("endpage", endPage);

		return model;
	}

}
