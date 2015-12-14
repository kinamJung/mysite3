<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="navigation">
	<ul>
		<!-- menu값에 따라 class=selected 적용 -->
		
		<c:choose>
			<c:when test="${param.menu eq 'main'}">
				<li class="selected"><a href="${pageContext.request.contextPath}/">정기남</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook/">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/board/">게시판</a></li>
			</c:when>
			<c:when test="${param.menu eq 'guestbook'}">
				<li ><a href="${pageContext.request.contextPath}/">정기남</a></li>
				<li class="selected"><a href="${pageContext.request.contextPath}/guestbook/">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/board/">게시판</a></li>
			</c:when>
			<c:when test="${param.menu eq 'board'}">
				<li ><a href="${pageContext.request.contextPath}/">정기남</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook/">방명록</a></li>
				<li class="selected"><a href="${pageContext.request.contextPath}/board/">게시판</a></li>
			</c:when>
		</c:choose>


		
	</ul>
</div>