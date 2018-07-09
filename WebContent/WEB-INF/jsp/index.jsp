<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/hover.css" rel="stylesheet" media="all">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/index.css">
<title>トップページ</title>
</head>
<body>
    <ul>
        <li class="button hvr-sweep-to-right"> ${account.userName}さん</li>
        <li class="button hvr-sweep-to-right"><a href="/Quiz2/LoginServlet"></a>ログアウト</li>
    </ul><h1>Secure Life</h1>
<p>説明</p>
<form>
    <input id="pochi" class="button hvr-grow" type="button" value="ゲームスタート" onclick="location.href='/Quiz2/GameServlet?action=start'">
    <input id="pochi" class="button hvr-grow" type="button" value="ランキング" onclick="location.href='/Quiz2/RankingServlet'">
</form>
</body>
</html>
