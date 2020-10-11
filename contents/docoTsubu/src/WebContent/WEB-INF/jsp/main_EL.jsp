<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,model.Mutter,javax.servlet.http.HttpServletRequest" %>
<html>
<head>
<meta charset="UTF-8">
<title>メイン画面</title>

<link rel="stylesheet" href="docoTsubu_css.css">

</head>
<body>
<h1>どこつぶメイン</h1>
<p><c:out value="${ user.name }" /> さん、ログイン中
<a href="/docotsubu_13_UseDB/Logout">ログアウト</a></p>
<p><a href="/docotsubu_13_UseDB/Main">更新</a></p>


<form action="/docotsubu_13_UseDB/Main" method="post">
<input type="text" name="mutter">
<input class="submit" type="submit" value="つぶやく">
</form>

<c:if test="${ not empty erroMsg }">
	<p>${ errorMsg }</p>
</c:if>

<c:forEach var="mutter" items="${ mutterList }">
	<!-- <p>mutter.name : mutter.text<br/><span id="date"></span> -->
	<p><c:out value="${ mutter.name }"/> : <c:out value="${ mutter.text }"/><br/>
	<span id="date"><c:out value="${ mutter.date } }"/></span>
</c:forEach>

</body>
</html>