package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class BoardDAO {
	//DB 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//전체 게시글 목록 조회 메소드
	public List<BoardDO> getBoardList(String searchField, String searchText){
		System.out.println("==> getBoardList() 기능 처리됌!");
		
		List<BoardDO> boardList = new ArrayList<BoardDO>(); //가변 배열 객체 생성
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			/*
			 * [중요] 게시물 검색 시 => '제목' or '작성자'로 검색 조건 제시하는 SQL 문장을 어떻게 작성할 것 인가?
			 * 하나의 sql 문장을 두가지 용도로 사용
			 * 1. 검색 조건이 없을 때 => 전체 검색
			 * 2. 검색 조건이 있을 때 => 조건 검색
			 */
			String where = "";
			if( searchField != null && searchText != null ) {
				where = "where "+ searchField + " like '%" + searchText+"%'";
			}
			
			String Condition_SQL = "select * from board "+ where +" order by seq desc";

			pstmt = conn.prepareStatement(Condition_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDO board = new BoardDO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setWriter(rs.getString("WRITER"));
				board.setCnt(rs.getInt("CNT"));
				board.setRegdate(rs.getDate("REGDATE"));
				System.out.println("board:"+board);
				boardList.add(board);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return boardList;
	}
}
