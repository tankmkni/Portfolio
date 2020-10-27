function search_test(){
	
	// URLのパラメータの取得
	var urlParam = location.search;
	var divTag = document.getElementById("1");
	divTag.innerHTML = "location.search ➡「 " + urlParam + " 」";
	
	// URLのアンカーの取得
	var urlHash = location.hash;
	divTag = document.getElementById("2");
	divTag.innerHTML = "location.hash ➡「 " + urlHash + " 」";
	
	// URLのパラメータを取得(substring(1)で一文字目の「?」を除去)
	var subst = location.search.substring(1);
	divTag = document.getElementById("3");
	divTag.innerHTML = "location.search.substring(1) ➡「 " + subst + " 」";

	// 「&」が含まれている場合は「&」で分割
	var splited = location.search.substring(1).split('&');
	divTag = document.getElementById("4");
	divTag.innerHTML = "location.search.substring(1).split('&') ➡「 " + splited + " 」";
	
	// パラメータを格納する用の配列を用意
	var variables = [];
	var values = [];

	// 用意した配列にパラメータを格納
	for (i = 0; i < splited.length; i++) {
		var paramItem = splited[i].split('=');
		variables[i] = paramItem[0];
		values[i] = paramItem[1];
	}

	divTag = document.getElementById("5");
	divTag.innerHTML = "variable names ➡「 " + variables + " 」";
	
	divTag = document.getElementById("6");
	divTag.innerHTML = "values ➡「 " + values + " 」";
}