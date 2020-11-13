function test(){	// 親要素のボタン操作で子要素の値を操作

	// <iframe>要素 [object HTMLIFrameElement] の取得
	var iframeElem = document.getElementById( "elem" );
	
	// 【 今回の主役 】<iframe>内の要素 [object window] の取得
	var innerElem = iframeElem.contentWindow.document;
	
	// <iframe>内のtarget要素 [object HTMLDivElement] の取得
	var target = innerElem.getElementById( "target" );

	// target要素を操作
	target.innerHTML = "親要素の操作で、子要素の表示内容を変更";
	target.setAttribute( "style","color: red;" );
	
	// <iframe>の高さを調整
	setHeight( iframeElem );
}