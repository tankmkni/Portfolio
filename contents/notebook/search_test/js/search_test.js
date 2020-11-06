function search_test(){ /* URLの値を取得し、加工して表示 */
	
	// URLの取得
	var url = location.href.split("/").slice(-1)[0];

	var divTag = document.getElementById("URL");
	var str = "&lt;iframe&gt; の src の取得："
		+ "location.href.split('/').slice(-1)[0]<br/>"
		+ "&emsp;&emsp;➡ 「 <code>" + url + "</code> 」";
	divTag.innerHTML = str;




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
	var splited = subst.split('&');

	str += ".split('&')"
	divTag.innerHTML += str + " ➡「 <code>" + splited + "</code> 」";




	// 「=」で分割
	for (i = 0; i < splited.length; i++) {
		var sent = str + "[" + i + "].split('=') ➡「 <code>"

			+ splited[i].split('=') 

			+ "</code> 」</div>"
		divTag.innerHTML += sent;
	}




	// URLのアンカーの取得
	var urlHash = location.hash;

	divTag.innerHTML += "<div>location.hash ➡「 <code>" + urlHash + "</code> 」</div>";
}