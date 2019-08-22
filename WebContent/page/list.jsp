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
<table border="1" width="300px">
	<tr>
		<th>사번</th>
		<th>이름</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.empno}</td>
		<td>${row.ename}</td>
	</tr>
</c:forEach>
	<!-- 페이지 네비게이션 -->
	<tr>
		<td colspan="2" align="center">
<c:if test="${curBlock > 1}">
	<a href="#" onclick="list('${prevPage}')">[이전]</a>
</c:if>		
<c:forEach var="i" begin="${blockStart}" end="${blockEnd}">
	<a href="#" onclick="list('${i}')">${i}</a>&nbsp;
</c:forEach>
<c:if test="${curBlock < totBlock}">
	<a href="#" onclick="list('${nextPage}')">[다음]</a>
</c:if>				
		</td>
	</tr>
</table>
</body>
</html>