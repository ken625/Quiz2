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
    <p>あなたのスコアは${score}点でした。</p>
<table border="1">
<% for(int i = 0; i < 10; i++){%>
    <tr id=<%if(2 % i == 0){%><%="gu"%><%}else{%><%="ki"%><%}%>>
        <td rowspan="2">Q<%=i + 1%></td>
        <td colspan="2"><%=questionList[i].getText()%></td>
    </tr>
    <tr id=<%if(2 % i == 0){%><%="gu"%><%}else{%><%="ki"%><%}%>>
        <td><%= result[i].equals("1") ? "○" : "×"%></td>
        <td><%= questionList[i].getComment()%></td>
    </tr>
<%} %>
</table>
<div id="topbtn">
    <button class="button hvr-grow" type="button" onclick="location.href='/Quiz2/ToIndexServlet'">トップへ</button>
</div>
</body>
</html>
