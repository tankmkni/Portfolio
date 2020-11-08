//	main_imgのファイル名の配列
var fileNames = new Array("daytime","evening");

//	text-shadowの色の配列
var shadowColors = new Array("blue","orange");

//	配列の添え字を指定するグローバル変数
var specifyIndex;


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




/*** onload時の処理 ***/
window.onload = function () {
	
	//時間帯によって画像を切り替える
	timeZone();
	
	//マウスストーカー用のdivを取得
	const s0 = document.getElementById('s0'); 
	const s1 = document.getElementById('s1');
	const s2 = document.getElementById('s2');
	const s3 = document.getElementById('s3'); 
	const s4 = document.getElementById('s4'); 
	const s5 = document.getElementById('s5'); 
	const s6 = document.getElementById('s6'); 
	const s7 = document.getElementById('s7'); 
	const s8 = document.getElementById('s8'); 
	const s9 = document.getElementById('s9'); 

	//上記のdivタグをマウスに追従させる処理
	document.addEventListener('mousemove', function (e) {
		s0.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s1.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s2.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s3.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s4.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s5.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s6.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s7.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s8.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
		s9.style.transform = 'translate(' + e.clientX + 'px, ' + e.clientY + 'px)';
	});
}
