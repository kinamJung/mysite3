<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 	
	pageContext.setAttribute("crlf", "\r\n"); 
%>
<!DOCTYPE html>
<html>
<head>
<title>MySite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<!-- for문으로 출력 -->	<table class="tbl-ex">
					
					<tr>
						<th colspan="2">글보기  </th>
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">
					<tr>
						<td class="label">제목</td>
						
						<c:choose>
							<c:when test="${status.index != 0 }">
							<td>
								<img src="/mysite/assets/images/ico-reply.gif"> ${vo.title}
							</td>
							</c:when>
							<c:otherwise>
							<td>${vo.title}</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							${fn:replace(vo.content,crlf,'<br/>')}
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<form class="board-form" method="post" action="${pageContext.request.contextPath}/board/commentInsert" >
					<input type="hidden" name="no" value="${param.no}">
					<input type="hidden" name="groupNo" value="${groupNo}">
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">첨부파일</td>
							<td> <input type="file" name="uploadFile"> <font size="1"> 5MB제한</font> </td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content"></textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/">취소</a>
						 <input type="submit" value="확인">
					</div>
				</form>
				
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>