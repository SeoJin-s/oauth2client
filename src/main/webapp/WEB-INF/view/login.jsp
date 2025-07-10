<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>소셜 로그인</title>
  <style>
    body {
      font-family: sans-serif;
      background-color: #f0f0f0;
      padding: 40px;
      display: flex;
      justify-content: center;
    }

    .social-buttons {
      display: flex;
      flex-direction: column;
      gap: 12px;
      width: 250px;
    }

    .social-buttons a {
      display: flex;
      align-items: center;
      justify-content: center;
      text-decoration: none;
      padding: 12px;
      font-size: 16px;
      font-weight: bold;
      border-radius: 4px;
      color: white;
      transition: background-color 0.2s ease;
    }

    .facebook {
      background-color: #3b5998;
    }

    .google {
      background-color: #dd4b39;
    }

    .naver {
      background-color: #1ec800;
    }

    .kakao {
      background-color: #ffe812;
      color: #3c1e1e;
    }

    .social-buttons a:hover {
      opacity: 0.9;
    }
  </style>
</head>
<body>
  <div class="social-buttons">
    <a href="/oauth2/authorization/google" class="google">Google 로그인</a>
	<a href="/oauth2/authorization/naver" class="naver">네이버 로그인</a>
	<a href="/oauth2/authorization/kakao" class="kakao">Kakao 로그인</a>
  </div>
</body>
</html>
