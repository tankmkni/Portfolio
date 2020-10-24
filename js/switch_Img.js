//	main_imgのファイル名の配列(fn = file name)
var fn = new Array("daytime","evening");

//	text-shadowの色の配列(ca = colors array)
var ca = new Array("blue","orange");

//	配列の添え字を指定するグローバル変数
var ind;


/*	text-shadowと画像をセットする関数
----------------------------------------------------------------------------------------------------------*/
function setValue(){

	var sc = ca[ind];						//	shadow color
	var is = "img/" + fn[ind] + ".jpg";		//	img src

	var tag = document.getElementById("img_box");
	tag.style.textShadow = " 2px  2px 10px " + sc
						+ ",-2px  2px 10px " + sc
						+ ", 2px -2px 10px " + sc
						+ ",-2px -2px 10px " + sc;
	tag = document.getElementById("main_img");
	tag.setAttribute("src", is);
}

/*	時間帯によって画像を切り替える関数
----------------------------------------------------------------------------------------------------------*/
function timeZone(){

	//	時間の情報を取得
	var now = new Date();
	var hour = now.getHours();

	//	条件によってindexの値を変更
	if( hour < 6 || 16 < hour){
		ind = 1;
	}else{
		ind = 0;
	}
	setValue();
}

/*	画像をクリックすると画像を切り替える関数
----------------------------------------------------------------------------------------------------------*/
function switchImg(){
	ind++;
	if( ind == fn.length ){
		ind = 0;
	}
	setValue();
}
