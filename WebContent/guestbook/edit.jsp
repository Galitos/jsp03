<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/include/jquery-3.4.1.min.js"></script>

<script>
function update() {
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
	
	//폼데이터를 받을 주소 지정
	document.form1.action="/guestbook_servlet/update.do";
	//폼데이터를 서버에 제출
	document.form1.submit();
}

</script>
</head>
<body>
<h2>방명록 편집</h2>
<form name="form1"  method="post">
<table border="1" style="width:500px">
	
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name" value="${dto.name}" size="20"></td>		
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" id="emil" value="${dto.email}" size="40"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" id="passwd" value="${dto.passwd}" size="40"></td>		
	</tr>
	<tr>
		<td colspan="2" align="center">
		<textarea row="5" cols="55" 
		name="content">${dto.content}</textarea></td>
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="hidden" name="idx" value="${dto.idx}">
			<input type="button" value="수정" onclick="update()">
			<input type="button" value="삭제">
		</td>
	</tr>
</table>
</form>
</body>
</html>