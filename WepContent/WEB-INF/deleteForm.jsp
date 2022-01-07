<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.javaex.vo.GuestBookVo" %>
<%@ page import = "com.javaex.dao.GuestBookDao" %>
    
<%
	int no = (int)request.getAttribute("no");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<form action="/guestbook2/gbc" method="get">
		<input type="hidden" name="no" value="<%=no%>">
		비밀번호 <input type="password" name="password">
		<button type="submit"> 확인 </button>
		<input type="hidden" name="action" value="delete">
	</form>
	
	<a href="/guestbook2/gbc?action=addList">메인으로 돌아가기</a>
</body>
</html>