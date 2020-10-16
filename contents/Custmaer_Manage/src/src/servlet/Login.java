package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//リクエストパラメーターの取得
//		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		//ユーザーインスタンスの生成
		User user = new User( name , pass );

		//ログイン処理（passの確認）
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute( user );

		//ログイン成功時の処理
		if( isLogin ) {
			HttpSession session = request.getSession();
			session.setAttribute( "loginUser" , user);

			//アプリケーションスコープに保存された訪問回数を増加
			ServletContext applecation = this.getServletContext();
			Integer count = (Integer)applecation.getAttribute("count");

			//スレッドの調停（排他制御処理）
			synchronized( count ) {
				count++;
				applecation.setAttribute("count", count);
			}
		}

		//ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward( request , response );
	}
//
//	private void alert(String string) {
//		// TODO 自動生成されたメソッド・スタブ
//
//	}

	public void init(ServletConfig config)throws ServletException{
		super.init(config);//super.classのinit()を実行する必要がある

		//訪問回数を数えるカウンターインスタンスを生成してアプリケーションスコープに保存（2020/07/17 追加）
		Integer count = 0;
		ServletContext application = config.getServletContext();
		application.setAttribute("count", count);
//
//		//現在時刻の表示法を指定するインスタンスを生成してアプリケーションスコープに保存（2020/07/18 追加）
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
//		application.setAttribute("simpleDateFormat" , simpleDateFormat );

		System.out.println( simpleDateFormat.format(new Date()) + " : Login.init()が実行されました count = " +  count );
	}

	public void destroy() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
		System.out.println( sdf.format(new Date()) + " : Login.destroy()が実行されました");
	}

}
