function setHeight( tag ){	/* 引数「tag」の内包要素の高さを取得し、「tag」の高さに指定する */
	tag.style.height = tag.contentWindow.document.body.scrollHeight + "px";
}

function setIframesHeight(){	/* 複数のiframeの高さをセットする */
	var iframes = document.getElementsByTagName("iframe");

	for( i = 0 ; i < iframes.length ; i++ ){
		setHeight( iframes[i] );
	}
}
