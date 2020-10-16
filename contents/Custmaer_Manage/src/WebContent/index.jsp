<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
<link rel="stylesheet" href="docoTsubu_css.css" />
<script type="text/javascript" src="/docotsubu_13_UseDB/js/round_clock.js"></script>
<script type="text/javascript" src="/docotsubu_13_UseDB/js/img.js"></script>
</head>
<body onload="setInterval('drawOrigin()',10)">
<h1><img id="docoTsubuMark" src="img/docoTsubu_pink.PNG" onclick="changeSrc()">
どこつぶへようこそ</h1>
<form action="/docotsubu_13_UseDB/Login" method="post">
<span class="label">ユーザー名</span><input type="text" name="name"><br />
<span class="label">パスワード</span><input type="password" name="pass"><br />
<input class="submit" type="submit" value="ログイン">
</form>
<canvas id="clock" width="200px" height="200px"></canvas><br />
</body>
</html>