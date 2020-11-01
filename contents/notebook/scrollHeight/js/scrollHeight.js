// 【今回の主題】内包要素の高さを取得し、親要素の高さに指定する
function setHeight( tag ){
	tag.style.height = tag.contentWindow.document.body.scrollHeight + "px";
}


// 自身の高さを表示する
function displayHeight( tag , target ){

/*	引数「tag」の中身はタグ内から this で渡され
*	今回は [object Window] か [object HTMLIFrameElement] のどちらか
*
*	[object Window] = <body>タグから
*	[object HTMLIFrameElement] = <iframe>タグから
*
*	tagが[object Window]だった場合、
*	tag.scrollHeight の返り値は「undefined」になる
*/

	var value = tag.scrollHeight;

	if( value == undefined ){
		value = document.body.scrollHeight;
	}
	
	document.getElementById( target ).innerHTML += value + "px";
}
