package com.hanains.mysite.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardInfo;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UploadFileVo;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// Get Board_INFO_LIKE_WORK Count
	public int getBoardCount(String word) {

		int count = sqlSession.selectOne("board.selectBoardCountByLikeWord", "%" + word + "%");
		
		return count;
	}

	
	
	//Get OrderMaxNo By GroupNo
	public long getOrderMaxNoByGroupNo(long no){
	
		long count = sqlSession.selectOne("board.getOrderMaxNoByGroupNo", no);
		return count;
	}
	
	
	//update OrderNo and Depth
	
	public void updateOrderNoAndDepth(BoardVo vo){
		sqlSession.update("board.updateOrderNoAndDepth",vo);
	}
	
	
	// Get Board_INFO Count
	public int getBoardCount() {

		int count = sqlSession.selectOne("board.getBoardCount");
		
		return count;
	}

	public void updateBoard(BoardVo vo) {
		sqlSession.update("board.update", vo);

	}
	
	public Long getGroupMaxValue(){
		Long maxValue = sqlSession.selectOne("board.getGroupMaxValue");
		return maxValue;
	}

	// Delete Board_INFO
	public void deleteBoard(BoardVo vo) {
		
		sqlSession.delete("board.delete", vo);
		
	}


	// Update Count
	public void updateViewCount(BoardVo vo) {
		
		sqlSession.update("board.updateViewCount", vo );
		
	}

	// Get Board vo
	public BoardVo getBoardVo(BoardVo vo) {
		
		BoardVo board = sqlSession.selectOne("board.getBoardInfo", vo);
		
		return board;
	}

	

	public List<BoardInfo> getListByFaging(int pagingCount,
			int displayArticleCount, String word) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("word", "%" + word + "%");
		map.put("start", (pagingCount - 1) * displayArticleCount);
		map.put("end",pagingCount * displayArticleCount );
		
		List<BoardInfo> list = sqlSession.selectList("board.getBoardListByFagingWithWord", map);
		
		return list;

	}

	// Get List BoarndInfo By Count
	public List<BoardInfo> getListByFaging(int pagingCount,
			int displayArticleCount) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start",(pagingCount - 1) * displayArticleCount );
		map.put("end",pagingCount * displayArticleCount );
		
		List<BoardInfo> list =  sqlSession.selectList("board.getBoardInfoByPaging", map);
		
		
		return list;
	}


	// insert
	public long insert(BoardVo vo) {

		System.out.println(vo);
		sqlSession.insert("board.insert", vo);
		return vo.getNo();
		
	}
	
	public void insert(long boardNo, String fileName, String originFilename, String mineType){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardNo", boardNo);
		map.put("fileName", fileName);
		map.put("originFileName", originFilename);
		map.put("mineType", mineType);
		System.out.println(map);
		sqlSession.insert("board.insertFile", map);
		
	}
	

	public List<UploadFileVo> getUploadFileListByBoardNo(long no){
		
		List<UploadFileVo> list = sqlSession.selectList("board.getUpdateFileByBoardNo", no);
		return list;
	}
	
}
