<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.RankingLogic, model.Account" %>
<%@ page import="java.util.List" %>
<%
Account account = (Account) session.getAttribute("account");
List<Account> rankingList = (List<Account>) request.getAttribute("rankingList");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランキング</title>
</head>
<body>
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
	<div id="ranking">
		<p>あなたの順位は</p>
	</div>
</body>
</html>