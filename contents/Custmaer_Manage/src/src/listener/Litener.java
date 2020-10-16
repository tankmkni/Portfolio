package listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Litener
 *
 */
@WebListener
public class Litener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public Litener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {
//		//現在時刻の表示法を指定するインスタンスを生成してアプリケーションスコープに保存（2020/07/18 追加）
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
		System.out.println( simpleDateFormat.format(new Date()) + " : Listener が実行されました" );
    }

}
