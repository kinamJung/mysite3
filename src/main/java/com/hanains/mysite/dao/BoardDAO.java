package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.BoardInfo;
import com.hanains.mysite.vo.BoardVo;

@Repository
public class BoardDAO {
	private static final String INSERT_BOARD_QUERY = " insert "
			+ " into board "
			+ " values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE )";

	private static final String SELECT_BOARD_QUERY = "select a.no, a.title, a.member_no, b.name "
			+ "as member_name, a.view_cnt, to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss') "
			+ "from board a, member b where a.member_no = b.no order by a.reg_date desc";

	private static final String SELECT_BOARD_INFO_QUERY = "select no, title, content, member_no from board where no=?";

	private static final String UPDATE_VIEW_COUNT_QUERY = "update board set view_cnt = view_cnt + 1 where no=?";
	private static final String DELETE_BOARD_INFO_QUERY = "delete from board where no = ?";
	private static final String SELECT_BOARD_COUNT_QUERY = " select count(*) as count from board";
	private static final String SELECT_BOARD_COUNT_LIKE_WORD_QUERY = "select count(*) as count from board where title like ?";
	
	private static final String SELECT_BOARD_INFO_PAGING_QUERY = " SELECT * "
																  + "FROM ( "
																  		+ "SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT "
																  		+ "FROM ("
																  			+ "SELECT a.NO, a.TITLE, a.CONTENT, a.MEMBER_NO, b.NAME, a.VIEW_CNT, a.REG_DATE "
																  			+ "from board a , member b "
																  			+ "where a.member_no = b.no order by a.reg_date desc )"
																  			+ "  A )  "
																  + "WHERE RNUM > ? AND RNUM <= ?";
	
	private static final String SELECT_BOARD_WORD_SEARCH_QUERY = " SELECT * "
																  + "FROM ( "
															  		+ "SELECT A.*, ROWNUM AS RNUM, COUNT(*) OVER() AS TOTCNT "
															  		+ "FROM ("
															  			+ "SELECT a.NO, a.TITLE, a.CONTENT, a.MEMBER_NO, b.NAME, a.VIEW_CNT, a.REG_DATE "
															  			+ "from board a , member b "
															  			+ "WHERE a.member_no = b.no AND a.TITLE like ? "
															  			+ "order by a.reg_date desc )"
															  			+ "  A )  "
															  	 + "WHERE RNUM > ? AND RNUM <= ?";
	
	private static final String UPDATE_BOARD_QUERY = "UPDATE board SET title = ?, content = ? WHERE no = ?";
	
	
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// Load Driver(Class Dynamic Loading)
			Class.forName(Common.ORCLE_DRIVER);

			// Connect DB
			String url = Common.CONNECT_DB_URL;
			conn = DriverManager.getConnection(url, Common.DB_USER, Common.DB_PASSWORD);

		} catch (ClassNotFoundException e) {
			System.out.println("[error] Fail Diver loading :" + e);
		}

		return conn;
	}
	//Get Board_INFO_LIKE_WORK Count
		public int getBoardCount(String word) {

			int count = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = getConnection();

				// Create Statement
				pstmt = conn.prepareStatement(SELECT_BOARD_COUNT_LIKE_WORD_QUERY);
				pstmt.setString(1, "%"+word+"%");
				
				// Execute SQL
				rs = pstmt.executeQuery(); // select문만 executeQuery, 이 외에는
												// executeUpdate
				
				while (rs.next()) {
					int index = 1;
					count = rs.getInt(index);
					break;
				}
			} catch (SQLException e) {
				System.out.println("[error] SQL :" + e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
		}
	
	//Get Board_INFO Count
	public int getBoardCount() {

		int count = -1;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Create Statement
			stmt = conn.createStatement();

			// Execute SQL
			rs = stmt.executeQuery(SELECT_BOARD_COUNT_QUERY); // select문만 executeQuery, 이 외에는
											// executeUpdate
			while (rs.next()) {
				int index = 1;
				count = rs.getInt(index);
				break;
			}
		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
	public boolean updateBoard( BoardVo vo )
	{
		
		boolean retVal = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(UPDATE_BOARD_QUERY);

			// binding
			int index = 1;
			pstmt.setString(index++, vo.getTitle());
			pstmt.setString(index++, vo.getContent());
			pstmt.setLong(index, vo.getNo());

			// Execute SQL
			pstmt.executeUpdate();
			retVal = true;

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return retVal;
		
		
	}
	
	//Delete Board_INFO
	public boolean deleteBoard(Long no) {
		boolean retVal = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(DELETE_BOARD_INFO_QUERY);

			// binding
			int index = 1;
			pstmt.setLong(index, no);

			// Execute SQL
			pstmt.executeUpdate();
			retVal = true;

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return retVal;
	}
	
	//Update Count
	public boolean updateViewCount(Long no) {
		boolean retVal = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(UPDATE_VIEW_COUNT_QUERY);

			// binding
			int index = 1;
			pstmt.setLong(index, no);

			// Execute SQL
			pstmt.executeUpdate();
			retVal = true;

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return retVal;
	}

	// Get Board vo
	public BoardVo getBoardVo(Long no) {

		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Create Statement
			pstmt = conn.prepareStatement(SELECT_BOARD_INFO_QUERY);

			// Binding
			pstmt.setLong(1, no);

			// Execute SQL
			rs = pstmt.executeQuery();

			int index = 1;
			while (rs.next()) {
				Long n = rs.getLong(index++);
				String title = rs.getString(index++);
				String content = rs.getString(index++);
				Long memberNo = rs.getLong(index);

				vo = new BoardVo(n, title, content, memberNo);
				break;
			}

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

		public List<BoardInfo> getListByFaging(int pagingCount, int displayArticleCount, String word)
		{
			List<BoardInfo> list = new ArrayList<BoardInfo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = getConnection();

				// Create Statement
				pstmt = conn.prepareStatement(SELECT_BOARD_WORD_SEARCH_QUERY);
				pstmt.setString(1, "%"+word+"%");
				pstmt.setInt(2, (pagingCount-1) * displayArticleCount);
				pstmt.setInt(3, pagingCount * displayArticleCount);
				
				
				// Execute SQL
				rs = pstmt.executeQuery();
				// executeUpdate
				while (rs.next()) {
					int index = 1;
					Long no = rs.getLong("no");
					String title = rs.getString("title");
					Long memberNo = rs.getLong("member_no");
					String memberName = rs.getString("name");
					Long viewCount = rs.getLong("view_cnt");
					String date = rs.getString("reg_date");
					int rNum = rs.getInt("rnum");
					int totCnt = rs.getInt("totcnt");

					BoardInfo vo = new BoardInfo(no, title, memberNo, memberName,
							viewCount, date);
					vo.setArticleSequence(totCnt-rNum+1);

					list.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("[error] SQL :" + e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
			
			
		}
	
	
		// Get List BoarndInfo By Count
		public List<BoardInfo> getListByFaging(int pagingCount, int displayArticleCount) {

			List<BoardInfo> list = new ArrayList<BoardInfo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = getConnection();

				// Create Statement
				pstmt = conn.prepareStatement(SELECT_BOARD_INFO_PAGING_QUERY);
				pstmt.setInt(1, (pagingCount-1) * displayArticleCount);
				pstmt.setInt(2, pagingCount * displayArticleCount);
				
				// Execute SQL
				rs = pstmt.executeQuery();
				// executeUpdate
				while (rs.next()) {
					int index = 1;
					Long no = rs.getLong("no");
					String title = rs.getString("title");
					Long memberNo = rs.getLong("member_no");
					String memberName = rs.getString("name");
					Long viewCount = rs.getLong("view_cnt");
					String date = rs.getString("reg_date");
					int rNum = rs.getInt("rnum");
					int totCnt = rs.getInt("totcnt");

					BoardInfo vo = new BoardInfo(no, title, memberNo, memberName,
							viewCount, date);
					vo.setArticleSequence(totCnt-rNum+1);

					list.add(vo);
				}
			} catch (SQLException e) {
				System.out.println("[error] SQL :" + e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
	
	// Get List
	public List<BoardInfo> getList() {

		List<BoardInfo> list = new ArrayList<BoardInfo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Create Statement
			stmt = conn.createStatement();

			// Execute SQL
			rs = stmt.executeQuery(SELECT_BOARD_QUERY);
			// executeUpdate
			while (rs.next()) {
				int index = 1;
				Long no = rs.getLong(index++);
				String title = rs.getString(index++);
				Long memberNo = rs.getLong(index++);
				String memberName = rs.getString(index++);
				Long viewCount = rs.getLong(index++);
				String date = rs.getString(index);

				BoardInfo vo = new BoardInfo(no, title, memberNo, memberName,
						viewCount, date);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// insert
	public void insert(BoardVo vo) {

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// Ready Statement
			pstmt = conn.prepareStatement(INSERT_BOARD_QUERY);

			// binding
			int index = 1;
			pstmt.setString(index++, vo.getTitle());
			pstmt.setString(index++, vo.getContent());
			pstmt.setLong(index, vo.getNo());

			// Execute SQL
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[error] SQL :" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
