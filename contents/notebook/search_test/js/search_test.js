function search_test(){ /* URLの値を取得し、加工して表示 */




	// URLの取得
	var url = location.href.split("/").slice(-1)[0];

	var str = "<div>&lt;iframe&gt; の src の取得："
		+ "location.href.split('/').slice(-1)[0]<br/>&emsp;&emsp;";
	var divTag = document.getElementById("URL");
	divTag.innerHTML = str + "➡ 「 <code>" + url + "</code> 」</div>";




	// URLのパラメータの取得
	var urlParam = location.search;

	str = "<div>location.search";
	divTag = document.getElementById("proc");
	divTag.innerHTML = str + " ➡「 <code>" + urlParam + "</code> 」</div>";




	// URLのパラメータを取得(substring(1)で一文字目の「?」を除去)
	var subst = location.search.substring(1);

	str += ".substring(1)";
	divTag.innerHTML += str + " ➡「 <code>" + subst + "</code> 」</div>";




	// 「&」が含まれている場合は「&」で分割
	var splitAmpersand = subst.split('&');

	str += ".split('&')"
	divTag.innerHTML += str + " ➡「 <code>" + splitAmpersand + "</code> 」</div>";




	// 「=」で分割
	for ( i = 0 ; i < splitAmpersand.length ; i++ ) {
		var splitEqual = splitAmpersand[i].split('=');
	
		var sent = str + "[" + i + "].split('=')";
		divTag.innerHTML += sent + " ➡「 <code>" + splitEqual + "</code> 」</div>";
	}




	// URLのアンカーの取得
	var urlHash = location.hash;

	divTag.innerHTML += "<div>location.hash ➡「 <code>" + urlHash + "</code> 」</div>";
}