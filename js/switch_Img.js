//	main_imgのファイル名の配列
var fileNames = new Array("daytime","evening");

//	text-shadowの色の配列
var shadowColors = new Array("blue","orange");

//	配列の添え字を指定するグローバル変数
var specifyIndex;



function setValue(){
/*	text-shadowと画像をセットする関数
----------------------------------------------------------------------------------------------------------*/
	setImage();
	setTextShadow();
}


function setImage(){
/*	画像をセットする関数
----------------------------------------------------------------------------------------------------------*/
	var imgSrc = "img/" + fileNames[specifyIndex] + ".jpg";
	var tag = document.getElementById("main_img");
	tag.setAttribute("src", imgSrc);
}


function setTextShadow(){
/*	text-shadowをセットする関数
----------------------------------------------------------------------------------------------------------*/
	var shadowColor = shadowColors[specifyIndex];
	var tag = document.getElementById("img_box");
	tag.style.textShadow =
		"  2px  2px 10px " + shadowColor +
		",-2px  2px 10px " + shadowColor +
		", 2px -2px 10px " + shadowColor +
		",-2px -2px 10px " + shadowColor ;
}


function timeZone(){
/*	時間帯によって画像を切り替える関数
----------------------------------------------------------------------------------------------------------*/
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


function switchImg(){
/*	画像をクリックすると画像を切り替える関数
----------------------------------------------------------------------------------------------------------*/
	specifyIndex++;
	if( specifyIndex >= fileNames.length ){
		specifyIndex = 0;
	}
	setValue(); 
}
