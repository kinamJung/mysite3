<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="com.hanains.mysite.vo.UserVo"%>

<% 
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board/search" method="post">
					 <input
						type="hidden" name="index" value="${param.index }"> <input
						type="hidden" name="size" value="${size }"> <input
						type="text" id="kwd" name="search" value="${search}"> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set value="${fn:length(list) }" var="count"></c:set>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${vo.articleSequence }</td>
							<td><a
								href="${pageContext.request.contextPath}/board/view?no=${vo.no}">${vo.title}</a>
							</td>
							<td>${vo.name}</td>
							<td>${vo.viewCount}</td>
							<td>${vo.regDate}</td>
							<td><c:if test="${ vo.memberNo eq authUser.no }">
									<a href="${pageContext.request.contextPath}/board/delete?no=${vo.no}" class="del"><font
										color="black">삭제</font></a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test="${ index-1 != 0 }">
							<li class="pg-prev"><a
								href="${pageContext.request.contextPath}/board/viewpaging?index=${index-1}&search=${search}">◀
									이전</a></li>

						</c:if>

						<c:forEach begin="1" end="${size}" varStatus="status">
							<c:choose>
								<c:when test="${size >= status.count}">
									<li><a
										href='${pageContext.request.contextPath}/board/viewpaging?index=${status.index}&search=${search}'>
											<!-- Color To Paging Index --> <c:choose>
												<c:when test="${ status.index eq param.index }">
													<font color="red">${status.index}</font>
												</c:when>
												<c:when test="${ status.index eq index }">
													<font color="red">${status.index}</font>
												</c:when>
												<c:otherwise>
														${status.index}
												</c:otherwise>
											</c:choose>
									</a></li>
								</c:when>
								<c:otherwise>
									<!-- 게시물이 없는 페이지는 disable -->
									<li class="disable">${status.index }</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${ index != size }">
							<li class="pg-next"><a
							href="${pageContext.request.contextPath}/board/viewpaging?index=${index+1}&search=${search}">다음
								▶</a></li>
						</c:if>
						
					</ul>
				</div>
				<c:if test="${ not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/writeForm?no=${authUser.no}" id="new-book">글쓰기</a>
					</div>
				</c:if>

			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>