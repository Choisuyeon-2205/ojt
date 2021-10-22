package common;

import java.sql.*;

public class JDBCUtil {
   // H2 DB 연동에 관련 소스
   
   static final String driver = "org.h2.Driver";
   static final String url = "jdbc:h2:tcp://localhost/~/test";
   
   public static Connection getConnection() throws Exception{
	   
      try {
         Class.forName(driver);
         Connection con = DriverManager.getConnection(url, "sa", "0000");
         return con;
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      
	return null;
   }
   
   
   //METHOD OVERLOADING(메소드 오버로딩)
   //DML(insert, update, delete) 작업 종료 시 호출되는 메소드
   public static void close(PreparedStatement pstmt, Connection conn) {
	   if(pstmt != null) {
		   try {
			   if(!pstmt.isClosed()) pstmt.close();
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   } finally {
			   pstmt = null;
		   } 
	   }
	   if(conn != null) {
		   try {
			   if(!conn.isClosed()) conn.close();
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   } finally {
			   conn = null;
		   }
	   }
   }
   
   //select 작업 종료 시 호출되는 메소드
   public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
	   if(rs != null) {
		   try {
			   if(!rs.isClosed()) rs.close();
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   } finally {
			   rs = null;
		   } 
	   }
	   if(pstmt != null) {
		   try {
			   if(!pstmt.isClosed()) pstmt.close();
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   } finally {
			   pstmt = null;
		   } 
	   }
	   if(conn != null) {
		   try {
			   if(!conn.isClosed()) conn.close();
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   } finally {
			   conn = null;
		   }
	   }
   }
   
}