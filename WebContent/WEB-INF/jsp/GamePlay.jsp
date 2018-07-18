<%@page import="java.util.ArrayList, model.GameData" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/hover.css" rel="stylesheet" media="all">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/index.css">
<meta charset="UTF-8">
<title>ゲーム画面</title>
</head>
<body>
    <ul>
        <li class="button hvr-sweep-to-right"> ${account.userName}さん</li>
        <li class="button hvr-sweep-to-right"><a href="/Quiz2/LoginServlet">ログアウト</a></li>
    </ul>

<p>Q<span id="span">1</span>/10</p>
<p id="star"></p>
<p id="question"></p>
<div id="otin">
<p id="result"></p>
</div>
<div class="main1">
   <p class="button hvr-grow" id="maru">
     <button type="button" id="marubtn"  class="on" value="1">○</button>
   </p>
   <p class="button hvr-grow" id="batsu">
     <button type="button"  id="batsubtn" class="on" value="0">×</button>
   </p>
</div>
<div class="main2">
<p id="end"></p>
<button type="button" id="nextbtn" class="off" style="display:none;">次へ</button>
<button type="button" id="resultbtn" class="off" style="display:none" onclick="location.href='/Quiz2/ResultServlet'">結果画面へ</button>
</div>
<script>
window.onload = function(){

    // 要素を取得
    let questionNum = 1;
    let dif;
    // 問題数カウント
    const span = document.getElementById("span");
    // 難易度
    const star = document.getElementById("star");
    // ○ボタン
    const maru = document.getElementById("marubtn");
    // ×ボタン
    const batsu = document.getElementById("batsubtn");
    // 問題文
    const question = document.getElementById("question");
    //「次へ」ボタン
    const nextQ = document.getElementById("nextbtn");
    // 結果
    const result = document.getElementById("result");
    // 終了メッセージ
    const end = document.getElementById("end");

    //イベントリスナーをセット
    maru.addEventListener("click", judgement, false);
    batsu.addEventListener("click", judgement, false);
    nextQ.addEventListener("click", nextQuestion, false);

    // １問目を表示
    getData();

    // 問題内容の読み込み
    function getData(){
        const req = new XMLHttpRequest();

        // 難易度を判別し、star設定
        if(Number(span.innerHTML) < 4){
            dif = "★☆☆☆";
        }else if(Number(span.innerHTML) < 7){
            dif = "★★☆☆";
        }else if(Number(span.innerHTML) < 10){
            dif = "★★★☆";
        }else{
            dif = "★★★★";
        }

        // 本文
        req.onreadystatechange = function(){
            if(req.readyState == 4 && req.status == 200){
                star.textContent = dif;
                console.log(JSON.parse(req.response));
                question.textContent = JSON.parse(req.response);
            }
        }
        req.open("GET","/Quiz2/GameServlet?action=getData&questionNum="+questionNum);
        req.setRequestHeader('Content-Type', 'application/x-www-form-urlencorded');
        req.send(null);
    }

    // 正誤判定
    function judgement(){
        const req = new XMLHttpRequest();

        req.onreadystatechange = function(){
            if(req.readyState == 4 && req.status == 200){
                actionResultListener(JSON.parse(req.response));
                ActionHidListener(marubtn);
                ActionHidListener(batsubtn);
                if(Number(span.innerHTML) < 10){
                    ActionHidListener(nextbtn);
                    questionNum++;
                }else{
                    ActionHidListener(resultbtn);
                    end.textContent = "終了！";
                }
            }
        }
        console.log(questionNum);
        req.open("GET","/Quiz2/CheckAnswerServlet?answer="+this.value + "&questionNum="+questionNum);
        req.setRequestHeader('Content-Type', 'application/x-www-form-urlencorded');
        req.send(null)
    }

    // 押されたボタンの判定
    function actionResultListener(check){
        if(check == 1){
            result.textContent = "正解!!";
        }else{
            result.textContent = "不正解...";
        }
    }

    //次の問題の表示等
    function nextQuestion(){
        span.innerHTML = Number(span.innerHTML) + 1;
        getData();
        result.textContent = "";
        ActionHidListener(marubtn);
        ActionHidListener(batsubtn);
        ActionHidListener(nextbtn);
    }

    // 表示・非表示
    function ActionHidListener(hid){
        if(hid.className == "off"){
            hid.className = "on";
            hid.setAttribute("style","display:block;");
        }else{
            hid.className = "off";
            hid.setAttribute("style","display:none;");
        }
    }
}
</script>
</body>
</html>
