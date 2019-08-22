<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>방명록</h2>
<!-- 검색폼 -->
<form method="post" action="/guestbook_servlet/search.do">
<select name="searchKey">
<c:choose>
	<c:when test="${searchKey == 'name'}">
		<option value="name" selected>이름</option>
		<option value="content" >내용</option>
		<option value="name_content">이름+내용</option>
	</c:when>
	<c:when test="${searchKey == 'content'}">
		<option value="name" >이름</option>
		<option value="content" selected>내용</option>
		<option value="name_content">이름+내용</option>
	</c:when>
	<c:when test="${searchKey == 'name_content'}">
		<option value="name" >이름</option>
		<option value="content" >내용</option>
		<option value="name_content" selected>이름+내용</option>
	</c:when>
	<c:otherwise>
		<option value="name" selected>이름</option>
		<option value="content" >내용</option>
		<option value="name_content" >이름+내용</option>
	</c:otherwise>
</c:choose>
	
</select>
<input type="text" name="search" value="${search}">
<input type="submit" value="검색">
</form>

<!-- 버튼을 클릭하면 write.jsp로 이동 -->
<input type="button" value="글쓰기" onclick="location.href='/guestbook/write.jsp'">

${list.size()}개의 글이 있습니다<br>
<c:forEach var="dto" items="${list}">
<form action="/guestbook_servlet/passwd_check.do" method="post">
<input type="hidden" name="idx" value="${dto.idx}">
<table border="1" style="width:600px;">
	<tr>
		<td>이름</td>
		<td>${dto.name}</td>
		<td>날짜</td>
		<td>${dto.post_date}</td>		
	</tr>
	<tr>
		<td>이메일</td>
		<td colspan="3">${dto.email}</td>	
	</tr>
	<tr><td colspan="4">${dto.content}</td></tr>
	<tr>
		<td colspan="4">
			<input type="password" name="passwd">
			<input type="submit" value="수정/삭제">
		</td>
	</tr>	
</table>
</form>
</c:forEach>
</body>
</html>