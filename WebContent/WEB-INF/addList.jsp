<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.javaex.vo.GuestBookVo" %>    
<%
	List <GuestBookVo> guestBookList = (List<GuestBookVo>)request.getAttribute("gList");
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="/guestbook2/gbc" method="post">
		<input type="hidden" name="action" value="add">
		<table border="1" style="width:600px;">
			<colgroup>
				<col width="8%">
				<col width="38%">
				<col width="16%">
				<col width="38%">
			</colgroup>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"> <textarea name="content" cols="75" rows="5"></textarea> </td>
			</tr>
			<tr>
				<td colspan="4"> <button type="submit">확인</button> </td>
			</tr>
		</table>
	</form><br>
	
	<%for(GuestBookVo vo : guestBookList){ %>
		<table border="1" style="width:600px;">
			<colgroup>
				<col width="5%">
				<col width="25%">
				<col width="55%">
				<col width="15%">
			</colgroup>
			<tr>
				<td><%= vo.getNo() %></td>
				<td><%= vo.getName()%></td>
				<td><%= vo.getRegDate()%></td>
				<td><a href="/guestbook2/gbc?action=deleteForm&no=<%=vo.getNo()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4"><%= vo.getContent()%></td>
			</tr>
		</table>
		<br>
	<%} %>
</body>
</html>