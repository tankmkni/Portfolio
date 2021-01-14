/* 引数「tag」の内包要素の高さを取得し、「tag」の高さに指定する */
function setHeight( tag ){
	let innerH = tag.contentWindow.document.children[0].offsetHeight;
	tag.style.height = innerH + "px"
}