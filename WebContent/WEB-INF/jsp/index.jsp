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
    	<li class="button hvr-sweep-to-right"><a href="/Quiz2/LoginServlet">ログアウト</a></li>
        <li>${account.userName}さん</li>
    </ul><h1>Secure Life</h1>
<p>インターネットを利用する際に注意すべきセキュリティ上の問題や、<br>情報に関する時事問題を○×クイズ形式で学んでいくことができます。</p>
    <div id="pochi">
<form>
    <input class="button hvr-grow" type="button" value="ゲームスタート" onclick="location.href='/Quiz2/GameServlet?action=start'">
    <input class="button hvr-grow" type="button" value="ランキング" onclick="location.href='/Quiz2/RankingServlet'">
</form>
    </div>
<script>
window.onload = function(){

    document.onkeydown = function(e){
        e = (e)? e : window.event;
        if(e.ctrlKey){
        return false;
        }
    };

    document.oncontextmenu = function(){
        return false;
    };
}
</script>
</body>
</html>
