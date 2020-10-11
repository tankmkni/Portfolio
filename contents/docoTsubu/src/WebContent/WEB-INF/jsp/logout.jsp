<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>

<link rel="stylesheet" href="docoTsubu_css.css">
<script type="text/javascript" src="/docotsubu_13_UseDB/js/round_clock.js"></script>
<script type="text/javascript" src="/docotsubu_13_UseDB/js/img.js"></script>
</head>
<body onload="setInterval('drawCosumoClockLike()',10)">
<h1><img id="docoTsubuMark" src="img/docoTsubu_pink.PNG" onclick="changeSrc()">
どこつぶログアウト</h1>
<P>ログアウトしました。</P>
<a href="/docotsubu_13_UseDB">TOPへ</a>

<div id="cosumoClock_box">
<canvas id="cosumoClock" width="200px" height="200px"></canvas>
<p id="digitalClock" onclick="changeSecondColorPattarn()"></p>
</div>

</body>
<script type="text/javascript">getDigitalClock()</script>
</html>