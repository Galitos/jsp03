<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 컨드롤러로 이동
response.sendRedirect("/guestbook_servlet/list.do");
%>

</body>
</html>