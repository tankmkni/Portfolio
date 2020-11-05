function swapSize(){	// iframeの縦と横のサイズを入れ替える
	var tag = document.getElementById("eight_queen");

	var width = tag.scrollWidth;
	var height = tag.scrollHeight;

	tag.style.width = height + "px";
	tag.style.height = width + "px";
}