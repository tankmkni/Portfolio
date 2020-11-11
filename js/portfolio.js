//	main_imgのファイル名の配列
const fileNames = new Array("daytime","evening");

//	text-shadowの色の配列
const shadowColors = new Array("blue","orange");

//	配列の添え字を指定するグローバル変数
var specifyIndex;

//マウスストーカーの数
const stalker_num = 10;


/*** onload時の処理 ***/
window.onload = function () {
	timeZone();				//時間帯によって画像を切り替える
	writeLastModified();	//最終更新日を表示
	drawMouseStalker();		//マウスストーカーを作る
	chaseMouse();			//↑にカーソルを追尾させる
}




/*** text-shadowと画像をセットする関数 ***/
function setValue(){
	setImage();
	setTextShadow();
}

/*** 画像をセットする関数 ***/
function setImage(){
	var imgSrc = "img/" + fileNames[specifyIndex] + ".jpg";
	var main_img = document.getElementById("main_img");
	main_img.setAttribute("src", imgSrc);
}

/*** text-shadowをセットする関数 ***/
function setTextShadow(){
	var shadowColor = shadowColors[specifyIndex];
	var portfolioTag = document.getElementById("portfolio");
	portfolioTag.style.textShadow =
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

/*** 画像を切り替える関数 ***/
function switchImg(){
	specifyIndex++;
	if( specifyIndex >= fileNames.length ){
		specifyIndex = 0;
	}
	setValue(); 
}




/*** iframeの縦と横のサイズを入れ替える関数 ***/
function swapSize(){
	var eight_queen = document.getElementById("eight_queen");

	var width = eight_queen.scrollWidth;
	var height = eight_queen.scrollHeight;

	eight_queen.style.width = height + "px";
	eight_queen.style.height = width + "px";
}




/*** マウスストーカー ***/

//マウスストーカー作成
function drawMouseStalker(){

	//マウスストーカー用のタグを作成
	document.body.innerHTML += "<div id='stalkers_box'></div>";
	for( i = 0 ; i < stalker_num ; i++ ){
		document.getElementById("stalkers_box").innerHTML += "<img src='img/ico/mouse_pointer.png'"
			+ "style='transition:transform " + (0.2 + (i/5)) + "s;'"	//ちょっと遅れてついてくるように
			+ "class='stalker' id='stalker" + i + "'>";
	}
}

//上記のタグをマウスに追従させる処理
function chaseMouse(){
	document.addEventListener('mousemove', function (e) {
		for( i = 0 ; i < stalker_num ; i++ ){
			const stalker_tag = document.getElementById("stalker" + i);
			if( stalker_tag != null ){
				stalker_tag.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
			}
		}
	});
}

//マウスストーカー削除
function removeMouseStalker(){
	const stalkers_box = document.getElementById("stalkers_box");
	stalkers_box.remove();
}

//マウスストーカーON/OFFの切り替え
function switchingStalker( tagId ){

	let stalkers_box = document.getElementById("stalkers_box");

	if( stalkers_box != null ){
		removeMouseStalker();
	}else{
		drawMouseStalker();
	}
}




/*** トグルスイッチ ***/
function switchingToggle( tagId ){

	//トグルスイッチをスライドさせる
	let elme = document.getElementById( tagId );
	elme.classList.toggle('clicked_toggle');

	//tagIdによって振り分ける
	switch( tagId ){
		case "toggle_stalker":
			switchingStalker( tagId );
			break;
		case "toggle_img":
			switchImg();
	}
}




/*** 最終更新日の表示 ***/
function writeLastModified(){

	var date = new Date(document.lastModified);
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();

	var lastModified = document.getElementById("lastModified");
	lastModified.innerHTML += y + " 年 " + m + " 月 " + d + " 日"
}




/*** ハンバーガーメニュー ***/
function switchingHam(){

	const ham = document.getElementById('ham');
	ham.classList.toggle('clicked_ham');

	const menu_wrapper = document.getElementById('menu_wrapper');
	menu_wrapper.classList.toggle('clicked_ham');
}