package filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//2020/07/19 新しく作製

@WebFilter("/*")//フィルター用のアノテーション
public class DocotsubuFilter implements Filter{

	//init() destroy() doFilter() ３つのクラスを実装しなければいけない
	public void init(FilterConfig fConfig)throws ServletException{}
	public void destroy() {}
	public void doFilter(ServletRequest request , ServletResponse response , FilterChain chain)
		throws IOException , ServletException{

		//文字コードの指定
		request.setCharacterEncoding("UTF-8");

		//現在時刻の表示法を指定するインスタンスを生成して表記
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
		System.out.println( simpleDateFormat.format(new Date()) + " : Filter.doFilter()の前処理が実行されました" );

		//この前に書けば前処理、後に書けば後処理
		chain.doFilter(request,response);

		//現在時刻の表記
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
		System.out.println( simpleDateFormat.format(new Date()) + " : Filter.doFilter()の後処理が実行されました" );

	}
}



