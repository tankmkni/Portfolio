package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Mutter;

public class MutterDAO {

	//フィールド(データベース接続に使用する情報)
    /** JDBCドライバ名 */
//    private static final String JDBC_DRIVER_NAME = "h2-1.4.200.jar";

//	private final String JDBC_URL = "jdbc:h2:tcp://localhost/d:/data/docoTsubu";

	//自宅ではこれで作動(組み込みモード)（2020/07/15）
//	private final String JDBC_URL_MY_HOME = "jdbc:h2:d:/data/docotsubu";
//	private final String JDBC_URL = "jdbc:h2:d:/data/docotsubu";

	// dynabookでのアドレス(2020/08/20)
	// 組み込みモード(絶対パス)
//	private final String JDBC_URL = "jdbc:h2:C:\\Users\\tankm\\OneDrive\\ドキュメント\\topia\\data_docoTubu\\docoTubu";
//	private final String JDBC_URL = "jdbc:h2:C:\\Users\\tankm\\OneDrive\\ドキュメント\\topia\\data_docoTubu\\docoTsubu_topia";
	// 組み込みモード(相対パス)(2020/08/23)
	private final String JDBC_URL = "jdbc:h2:~\\OneDrive\\ドキュメント\\topia\\data_docoTubu\\docoTubu";
	// サーバーモード(2020/08/23)
//	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/OneDrive\\ドキュメント\\topia\\data_docoTubu\\docoTubu";


	//とぴあではこれで作動(serverモード)（2020/07/15）
//	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Documents/data/docoTsubu";

//	とぴあではこれで作動(組み込みモード)（2020/07/17）
//	private final String JDBC_URL = "jdbc:h2:C:\\Users\\am\\Documents\\data\\docoTsubu";


	private final String DB_USER = "sa";
	private final String DB_PASS = "";


	//データベース編集
	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<>();
//		System.out.println( "First mutterList.size() = " + mutterList.size() );

//		boolean roop = true;	/***このroopは、6/29追加***/
//		int count = 0;			/***このcountは、6/29追加***/
//
//		while(roop) {			/***このwhileは、6/29追加***/
//
//			count++;			/***このcount++は、6/29追加***/

			//データベース接続

				try ( Connection conn = DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS ) ){


//					System.out.println("test 7/15 10:10");

					//SELECT文の準備
					String sql = "SELECT ID, NAME, TEXT, DATE, FROM MUTTER ORDER BY ID DESC";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					//SELECTを実行
					ResultSet rs = pStmt.executeQuery();

					//SELECT文の結果をArrayListに格納
					while(rs.next()) {
						int id = rs.getInt("id");
//						System.out.println( "test id = " + id );
						String userName = rs.getString("name");
//						System.out.println( "test userName = " + userName );
						String text = rs.getString("text");
						String date = rs.getString("date");
						Mutter mutter = new Mutter(id,userName,text,date);
						mutterList.add(mutter);
					}

//					roop = false;

				}catch(SQLException e) {
//					System.out.println("NG count = " + count);
					e.printStackTrace();
		//			return null;  /***このnullは、6/29コメントアウト***/
					/***このif()は、7/15追加***/
//					if(count > 5) {
//						System.out.println( "Last mutterList.size() = " + mutterList.size() );
						return null;
//					}
				}

				/***このif()は、6/29追加***/
//				if(count > 10) {
//					roop = false;
//				}
//			} catch (ClassNotFoundException e1) {
//				// TODO 自動生成された catch ブロック
//				e1.printStackTrace();
//			}
//		}
//		System.out.println( "Last mutterList.size() = " + mutterList.size() );
//		System.out.println( "Last count = " + count );
		return mutterList;
	}

	//つぶやきの追加
	public boolean create(Mutter mutter) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//INSERT文の準備（idは自動連番なので記載しなくてもよい）
			String sql = "INSERT INTO MUTTER(NAME,TEXT,DATE) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1,mutter.getName());
			pStmt.setString(2,mutter.getText());
			pStmt.setString(3,mutter.getDate());

			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//2020/7/15追加
	public boolean remove( String id ) {

		//データベースへの接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

			//SELECT文を準備
			String sql = "DELETE FROM MUTTER WHERE ID=? LIMIT 1";
//			String sql = "DELETE FROM MUTTER WHERE ID=" + id + " LIMIT 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, String.valueOf( id ));
			pStmt.setString(1, id );

			int result = pStmt.executeUpdate();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
			System.out.println( simpleDateFormat.format(new Date()) + " : remove() result = " + result );

			return (result == 1);

		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
