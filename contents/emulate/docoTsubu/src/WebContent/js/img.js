//img関連

var imgSrc = ['docoTsubu_pink.PNG','IMG_0219.PNG','alf.png'];
var srcSelect = 0;

//imgSrcのセレクト番号を変更
function changeSrcSelect(){
	srcSelect++;
	if( srcSelect >= imgSrc.length ){
		srcSelect = 0;
	}
}

//imgSrcを変更
function changeSrc(){
	changeSrcSelect();
	var src = "img/" + imgSrc[ srcSelect ];
	var object = document.getElementById("docoTsubuMark");
	object.setAttribute( "src" , src );
}