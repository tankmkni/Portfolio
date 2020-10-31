function test(){	// 親要素のボタン操作で子要素の値を操作

	// iframe要素の取得
	var iframeElem = document.getElementById( "elem" );
	
	// 【 今回の主役 】iframe内の要素の取得
	var innerElem = iframeElem.contentWindow.document;
	
	// target要素の取得
	var target = innerElem.getElementById( "target" );

	// iframe内のtarget要素を操作
	target.innerHTML = "親要素の操作で子要素を変更";
	target.setAttribute( "style","color: red;" );
}