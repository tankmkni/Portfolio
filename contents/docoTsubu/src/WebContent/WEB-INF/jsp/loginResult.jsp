<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% User user = (User)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン結果画面</title>
<link rel="stylesheet" href="docoTsubu_css.css">
<script type="text/javascript" src="/docotsubu_13_UseDB/js/round_clock.js"></script>
<script type="text/javascript" src="/docotsubu_13_UseDB/js/img.js"></script>
</head>
<body onload="setInterval('drawCosumoClockLike()',10)">
<h1><img id="docoTsubuMark" src="img/docoTsubu_pink.PNG" onclick="changeSrc()">
どこつぶログイン</h1>
<% if( user != null ){%>
<p>ログインに成功しました</p>
<p>ようこそ　<span class="name">${loginUser.getName()}<%--= user.getName() --%></span>　さん</p>
<p>${count} 人目の訪問です</p>
<a href="/docotsubu_13_UseDB/Main">つぶやき投稿・閲覧へ</a>
<br />
<% }else{%>
<p>ログインに失敗しました</p>
<a href="/docotsubu_13_UseDB/">戻る</a>
<% } %>

<div id="cosumoClock_box">
<canvas id="cosumoClock" width="200px" height="200px"></canvas>
<p id="digitalClock" onclick="changeSecondColorPattarn()"></p>
</div>
</body>
</html>