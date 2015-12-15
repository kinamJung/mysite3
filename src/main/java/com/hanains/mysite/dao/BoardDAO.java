package com.hanains.mysite.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardInfo;
import com.hanains.mysite.vo.BoardVo;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// Get Board_INFO_LIKE_WORK Count
	public int getBoardCount(String word) {

		int count = sqlSession.selectOne("board.selectBoardCountByLikeWord", "%" + word + "%");
		
		return count;
	}

	// Get Board_INFO Count
	public int getBoardCount() {

		int count = sqlSession.selectOne("board.getBoardCount");
		
		return count;
	}

	public void updateBoard(BoardVo vo) {
		sqlSession.update("board.update", vo);

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
	public void insert(BoardVo vo) {

		System.out.println(vo);
		sqlSession.insert("board.insert", vo);
		
	}

}
