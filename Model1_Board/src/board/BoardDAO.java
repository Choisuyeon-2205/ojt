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
	
	//게시글 번호 조건에 맞는 해당 게시글만 검색하는 메소드
	public BoardDO getBoard(BoardDO boardDO) {
		System.out.println("==> getBoard() 처리됌");
		
		BoardDO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			//[중요] 해당 게시글의 조회수(cnt)를 1 증가 시킨다.
			String UPDATE_CNT = "update board set cnt=cnt+1 where seq=?";
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
			//그런 다음 해당 게시글 가져오기
			String BOARD_GET = "select * from board where seq=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDO();
				
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return board;
	} //end getBoard() ===============================
	
	
	//게시글 수정 처리 메소드
	public int updateBoard(BoardDO boardDO) {
		System.out.println("==> updateBoard() 처리됌!");
		
		int result=0;
		try {
			conn = JDBCUtil.getConnection();
			
			String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
			
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getContent());
			pstmt.setInt(3, boardDO.getSeq());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		return result;
	}
	
	//게시글 삭제 처리 메소드
	public int deleteBoard(BoardDO boardDO) {
		System.out.println("==> deleteBoard() 처리됌!");
		
		int result=0;
		try {
			conn = JDBCUtil.getConnection();
			
			String BOARD_DELETE = "delete from board where seq=?";
			
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, boardDO.getSeq());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		return result;
	}
	
	//게시글 입력 처리 메소드
	public int insertBoard(BoardDO boardDO) {
		System.out.println("==> insertBoard() 처리됌!");
		
		int result=0;
		try {
			conn = JDBCUtil.getConnection();
			
			//NVL 함수는 값이 null인 경우 지정값을 출력한다. => NVL("값", "지정값")
			//nvl: 게시글이 첫번째로 들어가면 null이 들어가기 때문에 0으로 처리한 다음, +1을 해준다.
			String BOARD_INSERT = "insert into board(seq,title,writer,content) values ((select nvl(max(seq),0)+1 from board),?,?,?)";
			
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		return result;
	}
}
