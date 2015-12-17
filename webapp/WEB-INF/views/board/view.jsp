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
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">첨부파일</td>
						<td>
							<c:forEach items="${uploadFileList}" var="file" varStatus="status">
								<a href="${pageContext.request.contextPath }${file.fileName}" > ${ file.originFileName } </a><br>
							</c:forEach>
						</td>
					
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							${fn:replace(boardVo.content,crlf,'<br/>')}
							</div>
							<c:forEach items="${uploadFileList}" var="file" varStatus="status">
								<c:if test="${ file.mineType == 'jpg' || file.mineType == 'JPG' || file.mineType == 'png' || file.mineType == 'gif' }">
									<img src="${pageContext.request.contextPath }${file.fileName}">
								</c:if>
								<br>
							</c:forEach>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board/viewpaging?index=${index}&search=${search}">글목록</a>
					<c:if test="${ not empty authUser }">
						<a href="${pageContext.request.contextPath}/board/writeForm?groupNo=${boardVo.groupNo}&orderNo=${boardVo.orderNo}&depth=${boardVo.depth}">답글달기</a>
					</c:if>	
					<c:if test="${authUser.no eq boardVo.memberNo }">
						<a href="${pageContext.request.contextPath}/board/modifyform?no=${boardVo.no}&groupNo=${boardVo.groupNo}&depth=${boardVo.depth}&orderNo=${boardVo.orderNo}">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>