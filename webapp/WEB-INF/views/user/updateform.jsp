<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty authUser }">
	<c:redirect url="/main"></c:redirect>
</c:if>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">
				
				<form id="join-form" name="updateForm" method="post" action="${pageContext.request.contextPath}/user">
					<input type="hidden" name="a" value="modify">
					<input type="hidden" name="no" value="${authUser.no}" >
					
					<h1>회원정보 수정</h1>
					
					<label class="block-label" for="name">이름
					
					<font size="3"> &nbsp;${authUser.name }</font></label>
					
					<label class="block-label" for="email">이메일
					
					<font size="3"> &nbsp;${authUser.email }</font>
					</label>
					<br>
					<label class="block-label">현재 패스워드</label>
					<input name="password" type="password" value="">
					
					<label class="block-label">바꿀 패스워드</label>
					<input name="uptPassword" type="password" value="">
					
					<input type="submit" value="확인">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>