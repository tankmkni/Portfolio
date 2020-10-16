<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやき結果画面</title>
<link rel="stylesheet" href="docoTsubu_css.css">
<script type="text/javascript" src="/docotsubu_13_UseDB/js/img.js"></script>
</head>
<body onload="setInterval('drawOrigin()',10)">
<h1><img id="docoTsubuMark" src="img/docoTsubu_pink.PNG" onclick="changeSrc()">
どこつぶ削除結果</h1>
<p>${ message }</p>
<a href="/docotsubu_13_UseDB/Main">つぶやき投稿・閲覧へ</a>
<div>
<canvas id="clock" width="200px" height="200px"></canvas><br />
<script type="text/javascript" src="/docotsubu_13_UseDB/js/round_clock.js"></script>
</div>
</body>
</html>