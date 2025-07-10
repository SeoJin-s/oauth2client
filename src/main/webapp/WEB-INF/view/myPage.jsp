<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MyPage</title>
  <script>
    function logoutAll() {
      // 1. 새 창으로 카카오 로그아웃
      window.open("https://accounts.kakao.com/logout", "_blank", "width=500,height=600");

      // 2. 조금 기다렸다가 내 로그인 페이지로 이동
      setTimeout(() => {
        window.location.href = "/logout";
      }, 1500); // 필요시 조절 가능
    }
  </script>
</head>
<body>
  <h1>MyPage</h1>
  <hr>

<%
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
    String client = null;
    if (auth instanceof OAuth2AuthenticationToken) {
      client = ((OAuth2AuthenticationToken) auth).getAuthorizedClientRegistrationId();
    }
%>
    ${loginUsername}님 반갑습니다. (창을 닫으면 로그아웃이 됩니다)<br>
    ROLE : ${loginRole}
    <br><br>

<%
    if ("kakao".equals(client)) {
%>
    <!-- ✅ 카카오 로그아웃 후 → Spring Security 로그아웃 트리거 -->
    <button onclick="logoutAll()">카카오 완전 로그아웃</button>
<%
    } else {
%>
    <form action="<%= request.getContextPath() %>/logout" method="post">
      <button type="submit">로그아웃</button>
    </form>
<%
    }
  } else {
%>
    <a href="/login">로그인 하러가기</a>
<%
  }
%>

</body>
</html>
