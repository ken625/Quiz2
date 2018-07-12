<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.RankingLogic, model.Account" %>
<%@ page import="java.util.List" %>
<%
Account account = (Account) session.getAttribute("account");
List<Account> rankingList = (List<Account>) request.getAttribute("rankingList");
int myRanking = (int) request.getAttribute("myRanking");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/ranking.css">
<link href="css/hover.css" rel="stylesheet" media="all">
<meta charset="UTF-8">
<title>ランキング</title>
</head>
<body>
<div id="ranking">
    <p><% if(myRanking == 0) {%>ランキングに登録されていません<%
            }else { %>あなたの順位は<%= myRanking %>です<% } %>
    </p>
</div>
<div id="ranking_table">
    <table border="1">
        <tr>
            <th>ランキング</th>
            <th>ユーザー名</th>
            <th>スコア</th>
        </tr>
        <% for(int i = 0; i < rankingList.size(); i++) { %>
            <tr>
                <td><%= rankingList.get(i).getRanking() %></td>
                <td><%= rankingList.get(i).getUserName() %></td>
                <td><%= rankingList.get(i).getScore() %></td>
            </tr>
        <% } %>
    </table>
    </div>
    <div id="pochi">
        <input class="button hvr-grow" type="button" value="トップへ" onclick="location.href='/Quiz2/ToIndexServlet'">
    </div>
</body>
</html>
