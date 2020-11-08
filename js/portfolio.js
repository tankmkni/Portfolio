//	main_imgのファイル名の配列
var fileNames = new Array("daytime","evening");

//	text-shadowの色の配列
var shadowColors = new Array("blue","orange");

//	配列の添え字を指定するグローバル変数
var specifyIndex;




/*** onload時の処理 ***/
window.onload = function () {
	timeZone();				//時間帯によって画像を切り替える
	drawMouseStalker();		//マウスストーカー
}




/*** text-shadowと画像をセットする関数 ***/
function setValue(){
	setImage();
	setTextShadow();
}


/*** 画像をセットする関数 ***/
function setImage(){
	var imgSrc = "img/" + fileNames[specifyIndex] + ".jpg";
	var tag = document.getElementById("main_img");
	tag.setAttribute("src", imgSrc);
}


/*** text-shadowをセットする関数 ***/
function setTextShadow(){
	var shadowColor = shadowColors[specifyIndex];
	var tag = document.getElementById("portfolio");
	tag.style.textShadow =
		"  2px  2px 10px " + shadowColor +
		",-2px  2px 10px " + shadowColor +
		", 2px -2px 10px " + shadowColor +
		",-2px -2px 10px " + shadowColor ;
}


/*** 時間帯によって画像を切り替える関数 ***/
function timeZone(){

	//	時間の情報を取得
	var hour = new Date().getHours();

	//	条件によってindexの値を変更
	if( hour < 6 || 16 < hour){
		specifyIndex = 1;
	}else{
		specifyIndex = 0;
	}
	setValue();
}


/*** 画像をクリックすると画像を切り替える関数 ***/
function switchImg(){
	specifyIndex++;
	if( specifyIndex >= fileNames.length ){
		specifyIndex = 0;
	}
	setValue(); 
}




/*** iframeの縦と横のサイズを入れ替える関数 ***/
function swapSize(){
	var tag = document.getElementById("eight_queen");

	var width = tag.scrollWidth;
	var height = tag.scrollHeight;

	tag.style.width = height + "px";
	tag.style.height = width + "px";
}




/*** マウスストーカー ***/
function drawMouseStalker(){

	//マウスストーカーの数
	const num = 10;

	//マウスストーカー用のタグを作成
	for( i = 0 ; i < num ; i++ ){
		document.body.innerHTML += "<img src='img/ico/mouse_pointer.png'"
			+ "style='transition:transform " + (0.1 + (i/10)) + "s;'"	//ちょっと遅れてついてくるように
			+ "class='stalker' id='stalker" + i + "'>";
	}

	//上記のタグをマウスに追従させる処理
	document.addEventListener('mousemove', function (e) {
		for( i = 0 ; i < num ; i++ ){
			const tag = document.getElementById("stalker" + i);
			tag.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		}
	});
}
