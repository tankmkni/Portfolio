<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.User
				,model.Mutter
				,java.util.List" %>
<% 	User user = (User)session.getAttribute( "loginUser" );
	List<Mutter> mutterList = (List<Mutter>)request.getAttribute( "mutterList" );
	String errorMsg = (String)request.getAttribute( "errorMsg" );%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メイン画面</title>

<link rel="stylesheet" href="docoTsubu_css.css">
<script type="text/javascript" src="/docotsubu_13_UseDB/js/round_clock.js"></script>
<script type="text/javascript" src="/docotsubu_13_UseDB/js/img.js"></script>
</head>
<body onload="setInterval('drawDocoTsubuClock()',10)">

<h1>
<img id="docoTsubuMark" src="img/docoTsubu_pink.PNG" onclick="changeSrc()">
		どこつぶメイン

<div id="clock_box" onclick="switchMotion()" ondblclick="randomClockHandColor()">
<img id="secondMark" src="img/docoTsubu_pink.PNG">
<canvas id="docoTsubuClock" width="200px" height="200px"></canvas>
</div>

</h1>

<div id="box">
<div id="operat">
<p><span class="name"><%= user.getName() %></span>　さん、ログイン中<br/><br/>
<a href="/docotsubu_13_UseDB/Logout">ログアウト</a></p>
<p><a href="/docotsubu_13_UseDB/Main">更新</a></p>
<form action="/docotsubu_13_UseDB/Main" method="post">
<input class="mutter" type="text" name="mutter"><br/>
<input class="submit" type="submit" value="つぶやく">
</form>
<div id="ad" onclick="changeBackColor()">
<br />広<br />告<br />募<br />集<br />中<br /><br /></div>
</div>

<% if(errorMsg != null){ %>
	<p><%= errorMsg %></p>
<% } %>

<% if(mutterList != null){ %>
	<div id="mutterArea">
<% for(Mutter mutter : mutterList) {%>
	<div class="onePart">

	<div><span class="name"><%= mutter.getName() %></span> :
	<span id="text"><%= mutter.getText() %></span><br />
	<span id="date"><%= mutter.getDate() %></span></div>
	<button type="button" onclick="funcDelete( this.id )" id="<%= mutter.getId() %>">削 除</button>
	</div>
	<br/>
<% }} %>
	</div>
</div>
</body>
<script type="text/javascript">
        function funcDelete( id ) {

//			Stirng name = "<%--= user.getName() --%>";
//        	alert("user.getName() = <%--= user.getName() --%>");
//      	alert("name");


        //	if( user.getName() == mutterList[ id - 1 ].getName()){
	            if (confirm("このつぶやきを削除します。よろしいですか？")) {
	                var form = document.createElement("form");
	                form.setAttribute("action", "Main");
	                form.setAttribute("method", "post");
	                form.style.display = "none";
	                document.body.appendChild(form);
	                var input = document.createElement("input");
	                input.setAttribute("type","hidden");
	                input.setAttribute("name", "state");
	                input.setAttribute("value", id + "," + "<%= user.getName() %>" );

	                form.appendChild(input);
	                form.submit();
	            }
	    	//}else{
	    		//alert("名前が一致しないため削除できません");
	    	//}
        }

        function changeBackColor(){
			document.body.style.backgroundColor = randomColor(16);

        }
</script>
</html>