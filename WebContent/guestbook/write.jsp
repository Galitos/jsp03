<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/include/jquery-3.4.1.min.js"></script>
<script >
function check() {
	var name=$("#name"); // id가 name인 태그
	var email=$("#email");
	var passwd=$("#passwd");
	var content=$("#content");
	if(name.val() ==""){ // 태그에 입력된 값이 없으면
		alert("이름을 입력하세요"); // 경고장 띄우기
		name.focus(); // 입력커서 표시
		return; //함수 종료
	}
	if(email.val() ==""){
		alert("이메일을 입력하세요");
		email.focus();
		return;
	}
	if(passwd.val() ==""){
		alert("비밀번호를 입력하세요");
		passwd.focus();
		return;
	}
	if(content.val() ==""){
		alert("내용을 입력하세요");
		content.focus();
		return;
	}
	document.form1.submit();//폼데이터를 서버에 제출
}
</script>
</head>
<body>
<h2>방명록 작성</h2>
<form name="form1" id="form1" method="post" action="/guestbook_servlet/insert.do">
<table border="1" style="width:500px">
	
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name" size="20"></td>		
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" id="emil" size="40"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" id="passwd" size="40"></td>		
	</tr>
	<tr>
		<td colspan="2" align="center"> <!-- colspan="셀의 갯수" 셀합치기 -->
		<textarea row="5" cols="55" name="content" id="content"></textarea></td>
	</tr>
	<tr align="center"> <!-- 가운데 정렬 -->
		<td colspan="2">
			<input type="button" value="확인" onclick="check()"> <!-- 폼데이터를 서버에 제출 -->
		</td>
	</tr>
</table>
</form>
</body>
</html>