/* 引数「tag」の内包要素の高さを取得し、「tag」の高さに指定する */
/* 2021.1.6 border/margin/padding幅も含めるように変更*/
function setHeight( tag ){
	// 引数「tag」内の要素のbodyタグを取得
	let bodyTag = tag.contentWindow.document.body;
	
	// bodyタグのborder/paddingを含む高さを取得
	let bodyHeight = bodyTag.offsetHeight;
	
	// bodyタグのマージンの値を取得して追加（上下にあるので×２）
	bodyHeight += parseInt( getComputedStyle( bodyTag ).margin ) * 2;
	
	// 引数「tag」の高さをセット
	tag.style.height = bodyHeight + "px";
}


/* 複数のiframeの高さをセットする */
function setIframesHeight(){
	var iframes = document.getElementsByTagName("iframe");

	for( i = 0 ; i < iframes.length ; i++ ){
		setHeight( iframes[i] );
	}
}
