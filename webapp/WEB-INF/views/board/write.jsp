<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String no = request.getParameter("no");
%>

<html>
<head>
<title>MySite</title>

<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(function() {
		$('#board-write-form').submit(function() {

			var title = $("#title").val();
			var content = $("textarea").val();

			if(title == ""){
				alert("제목을 입력하세요.");
				return false;
			}
			
			if(content.length < 10){
				alert("10자 이상 입력하세요.");
				return false;
			}
			
			return true;
		});
	});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="board-write-form" class="board-form" method="post"
					action="${pageContext.request.contextPath}/board/write"
					enctype="multipart/form-data">
					<input type="hidden" name="groupNo" value="${boardVo.groupNo}">
					<input type="hidden" name="orderNo" value="${boardVo.orderNo }">
					<input type="hidden" name="depth" value="${boardVo.depth }">

					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" id="title" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">첨부파일</td>
							<td>
							<input type="file" name="uploadFile"> 
							<font size="1"> 5MB제한</font><br> 
							<input type="file" name="uploadFile">
							</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content"></textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/">취소</a>
						 <input type="submit" value="등록">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>