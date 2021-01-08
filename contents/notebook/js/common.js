/* 引数「tag」の内包要素の高さを取得し、「tag」の高さに指定する */
/* 2021.1.6 border/margin/padding幅も含めるように変更*/
/* 2021.1.8 ダイレクトにhtmlタグの高さを取得するように変更 */
function setHeight( tag ){
	let innerH = tag.contentWindow.document.children[0].offsetHeight;
	tag.style.height = innerH + "px"
}


/* 複数のiframeの高さをセットする */
function setIframesHeight(){
	var iframes = document.getElementsByTagName("iframe");

	for( i = 0 ; i < iframes.length ; i++ ){
		setHeight( iframes[i] );
	}
}
