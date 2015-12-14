<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<% 	
	pageContext.setAttribute("crlf", "\r\n"); 
%>
<!doctype html>
<html>
<head>
<title>MySite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/gs" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td>

							<td>
								<c:choose>
									<c:when test="${not empty authUser }">
										<label> ${authUser.name } </label>
										<input type="hidden" name="name" value="${authUser.name }">
									</c:when>
									<c:otherwise>
										<input type="text" name="name">
									</c:otherwise>

								</c:choose>
							</td>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li><c:set var="count" value="${fn:length(list)}" /> <c:forEach
							items="${list}" var="vo" varStatus="status">
							<table width=510 border=1>
								<tr>
									<td>${count-status.index}</td>
									<td>${vo.name}</td>
									<td>${vo.date }</td>
									<td><a href="${pageContext.request.contextPath}/gs?no=${vo.no}&a=deleteForm">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${fn:replace(vo.message,crlf,'<br/>')}</td> 
								</tr>
							</table>
						</c:forEach> <br></li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>