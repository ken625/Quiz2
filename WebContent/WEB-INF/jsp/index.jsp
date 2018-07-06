<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h1>ゲームタイトル</h1>
<p>説明</p>
<form>
<input type="button" value="ゲームスタート" onclick="location.href='/Quiz2/GameServlet?action=start'">
<input type="button" value="ランキング" onclick="location.href='/Quiz2/RankingServlet'">
</form>
</body>
</html>