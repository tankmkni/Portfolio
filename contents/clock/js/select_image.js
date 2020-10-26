function setImg(){
	var urlHash = location.hash.substring(1);
	var fileSrc = "../img/" + urlHash + ".png";
	document.getElementById("secondMark").setAttribute( "src" , fileSrc );
}
