<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,
            com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
            java.util.*,
            java.io.*" 
%>  
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String savePath = "C:/FileUpload/filestorage";
	String encType = "UTF-8";
	int maxSize = 20*1024*1024;
	
	try {
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy());
		
		String name = multi.getParameter("name");
		String subject = multi.getParameter("subject");
		
		out.println("작성자: "+ name + "<br>");
		out.println("제목: "+ subject + "<br>");
		out.println("<br>");
		
		Enumeration files = multi.getFileNames();
		
		while(files.hasMoreElements()){
			String file = (String)files.nextElement();
			String filename = multi.getFilesystemName(file);
			out.println("업로드된 파일명: "+filename+"<br>");
		}
		
	}catch(Exception e){
		System.out.println(e);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>