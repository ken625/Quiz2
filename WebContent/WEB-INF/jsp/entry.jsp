<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/default.css">
    <meta charset="UTF-8">
    <title>新規登録</title>
</head>
<body>
    <div id="login">
        <form action="/Quiz2/EntryServlet" method="post">
            新しいユーザー名:
            <div class="center">
                <input type="text" name="userName">
            </div><br>
            新しいパスワード:
            <div class="center">
                <input type="password" name="pass">
            </div><br>
            <div class="center">
                <input type="submit" value="作成" id="new_submit">
            </div>
        </form>
    </div>
</body>
</html>
