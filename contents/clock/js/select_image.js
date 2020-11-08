function setImg(){
	var fileName = location.search.split("=")[1];
	var fileSrc = "../img/" + fileName + ".png";
	document.getElementById("secondMark").setAttribute( "src" , fileSrc );
}
