<%@page import="model.GameData" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    GameData[] questionList = (GameData[])session.getAttribute("questionList");
    String[] result = (String[])session.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/ranking.css">
<link href="css/hover.css" rel="stylesheet" media="all">
<title>結果画面</title>
</head>
<body>
    <ul>
        <li class="button hvr-sweep-to-right"> ${account.userName}さん</li>
        <li class="button hvr-sweep-to-right"><a href="/Quiz2/LoginServlet">ログアウト</a></li>
    </ul>
    <p id="dispResult">あなたのスコアは<strong id="scoreTen">${score}点</strong>でした。</p>

<% for(int i = 0; i < 10; i++){%>
<div class="qContents">
	<p>Q<%=i + 1%>：
		<span class="<%=result[i].equals("1") ? "seikai" : "fuseikai"%>">
		<%= result[i].equals("1") ? "正解" : "不正解"%></span>
	</p>
	<p>問題：
	<span class="qTexts"><%=questionList[i].getText()%></span></p>
	<p>解説：
	<span class="qTexts"><%= questionList[i].getComment()%></span></p>
</div>
<%} %>
<div id="topbtn">
    <button class="button hvr-grow" type="button" onclick="location.href='/Quiz2/ToIndexServlet'">トップへ</button>
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
