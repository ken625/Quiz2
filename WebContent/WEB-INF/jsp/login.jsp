<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/default.css">
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>Secure Life</h1>
<div id="login">
<form action="/Quiz2/LoginServlet" method="post">
ユーザー名:<input type="text" name="userName"><br>
パスワード:<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
<input type="button" value="新規登録" onclick="location.href='/Quiz2/EntryServlet'">
</form>
</div>
</body>
</html>
