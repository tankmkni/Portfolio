package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
//		System.out.println("test 7/15 mutterList.size() from doGet = " + mutterList.size() );
		request.setAttribute("mutterList", mutterList);

		//ログインの確認
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");

		if( loginUser == null ) {
			response.sendRedirect( "/docotsubu_13_UseDB/" );
		}else {
//			for(Mutter mutter : mutterList) {
//				System.out.println("test 7/15 13:05 mutterDate = " + mutter.getDate());
//			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward( request , response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメーターの取得
//		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("mutter");

		//削除する場合以外はnull
		String states = request.getParameter("state");

		//if文の中から移動（2020/07/15）
		PostMutterLogic postMutterLogic = new PostMutterLogic();

		//入力チェック
		if( text != null && text.length() != 0 ) {

			//誰のつぶやきか確認（セッションスコープに保存されたユーザー情報を取得）
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");

			//つぶやきをつぶやきリストに追加
			Mutter mutter = new Mutter(loginUser.getName(),text);
//			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);

		//削除する場合
		}else if(states != null) {
			//指定されたIDのレコードを削除（2020/07/15 追加）
//			String id = request.getParameter( "id" );

			//stateの値を分割して配列に格納（2020/07/16 追加）
	        String param = request.getParameter("state");
	        String[] state = param.split(",");

			String message = postMutterLogic.remove( state );
			request.setAttribute( "message" , message );

			request.getRequestDispatcher( "/WEB-INF/jsp/removeResult.jsp" ).forward( request , response );
			return;

		}else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg","つぶやきが入力されていません");
		}

		//つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute( "mutterList" , mutterList );

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher( "/WEB-INF/jsp/main.jsp" );
		dispatcher.forward( request , response );
	}
}




//自力では・・・
/*//誰のつぶやきか確認
HttpSession session = request.getSession();
User loginUser = (User)session.getAttribute("loginUser");
String name = loginUser.getName();

//つぶやきを保存
Mutter mutter = new Mutter( name , text );


//つぶやきリストの取得
ServletContext application = this.getServletContext();
List<Mutter> mutterList = (List<Mutter>)application.getAttribute( "mutterList" );


//つぶやきリストの先頭につぶやきを追加
mutterList.add( 0 , mutter );*/