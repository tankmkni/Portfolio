function search_test(){ /* URLの値を取得し、加工して表示 */
	
	// URLの取得
	var url = location.href.split("/");
	var divTag = document.getElementById("URL");
	divTag.innerHTML += "「 <code>" + url[ url.length - 1 ] + "</code> 」";

	// URLのパラメータの取得
	var urlParam = location.search;
	divTag = document.getElementById("1");
	divTag.innerHTML = "location.search ➡「 <code>" + urlParam + "</code> 」";
	
	// URLのアンカーの取得
	var urlHash = location.hash;
	divTag = document.getElementById("2");
	divTag.innerHTML = "location.hash ➡「 <code>" + urlHash + "</code> 」";
	
	// URLのパラメータを取得(substring(1)で一文字目の「?」を除去)
	var subst = location.search.substring(1);
	divTag = document.getElementById("3");
	divTag.innerHTML = "location.search.substring(1) ➡「 <code>" + subst + "</code> 」";

	// 「&」が含まれている場合は「&」で分割
	var splited = location.search.substring(1).split('&');
	divTag = document.getElementById("4");
	divTag.innerHTML = "location.search.substring(1).split('&') ➡「 <code>" + splited + "</code> 」";
	
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
	divTag.innerHTML = "variable names ➡「 <code>" + variables + "</code> 」";
	
	divTag = document.getElementById("6");
	divTag.innerHTML = "values ➡「 <code>" + values + "</code> 」";
}