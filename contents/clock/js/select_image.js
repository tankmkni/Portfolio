function setImg(){
	var filename = location.search.split("=")[1];
	var fileSrc = "../img/" + filename + ".png";
	document.getElementById("secondMark").setAttribute( "src" , fileSrc );
}
