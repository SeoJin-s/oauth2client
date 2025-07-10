<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Home</h1>
	<hr>
	
	<c:if test="${loginUsername == 'anonymousUser'}">
		<a href="/login">로그인</a>
	</c:if>
	
	<c:if test="${loginUsername != 'anonymousUser'}">
		${loginUsername}(${loginRole})님 반갑습니다.(창을 닫으면 로그아웃이 됩니다)<br>
		<a href="/myPage">MyPage</a>
	</c:if>
	
	
</body>
</html>
