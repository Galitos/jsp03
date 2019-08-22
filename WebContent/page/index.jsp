<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../include/jquery-3.4.1.min.js"></script>
<script>
$(function(){
	list("1");
});
function list(page){
	$.ajax({
		url: "/page_servlet/list.do",
		data: "curPage="+page,
		success: function(result) {
			$("#result").html(result);
		}		
	});
}
</script>
</head>
<body>
<h2>페이지 나누기</h2>
<div id="result"></div>
</body>
</html>