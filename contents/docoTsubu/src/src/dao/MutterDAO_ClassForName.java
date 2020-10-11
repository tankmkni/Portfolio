package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO_ClassForName {

	//フィールド(データベース接続に使用する情報)
    /** JDBCドライバ名 */
//    private static final String JDBC_DRIVER_NAME = "h2-1.4.200.jar";
    private static final String JDBC_DRIVER_NAME = "org.h2.Driver";

//	private final String JDBC_URL = "jdbc:h2:tcp://localhost/d:/data/docoTsubu";
//	private final String JDBC_URL = "jdbc:h2:d:/data/docoTsubu";

//	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Documents\\data\\docotsubu";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Documents/data/docotsubu";


	private final String DB_USER = "sa";
	private final String DB_PASS = "";

    /** DBへの接続 */
    protected Connection conn = null;


	//データベース編集
	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<>();
		System.out.println( "First mutterList.size() = " + mutterList.size() );

		boolean roop = true;
		int count = 0;

		while(roop) {

			count++;	/***このcountは、6/29追加***/

			//データベース接続
				try {

			        Class.forName(JDBC_DRIVER_NAME);
			        conn = DriverManager.getConnection( JDBC_URL, DB_USER, DB_PASS );
//			        conn.setAutoCommit(true);
					System.out.println("test 7/15 10:10");

					//SELECT文の準備
					String sql = "SELECT ID, NAME, TEXT, DATE, FROM MUTTER ORDER BY ID DESC";
//					String sql = "SELECT ID, NAME, TEXT, FROM MUTTER ORDER BY ID DESC";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					//SELECTを実行
					ResultSet rs = pStmt.executeQuery();

					//SELECT文の結果をArrayListに格納
					while(rs.next()) {
						int id = rs.getInt("id");
						System.out.println( "test id = " + id );
						String userName = rs.getString("name");
						System.out.println( "test userName = " + userName );
						String text = rs.getString("text");

//						String date = rs.getString("date");
//						Mutter mutter = new Mutter(id,userName,text,date);
						Mutter mutter = new Mutter(id,userName,text);
						mutterList.add(mutter);
					}

					roop = false;

				}catch(SQLException | ClassNotFoundException e) {
					System.out.println("NG count = " + count);
					e.printStackTrace();
		//			return null;  /***このnullは、6/29コメントアウト***/
					/***このif()は、7/15追加***/
					if(count > 5) {
						System.out.println( "Last mutterList.size() = " + mutterList.size() );
						return null;
					}
				}finally {
		            try {
//		            	pStmt.close();
				        if (conn != null) {
				            conn.close();
				            conn = null;
				        }
		            } catch (SQLException e) {

		            }
		        }

				/***このif()は、6/29追加***/
				if(count > 10) {
					roop = false;
				}
//			} catch (ClassNotFoundException e1) {
//				// TODO 自動生成された catch ブロック
//				e1.printStackTrace();
//			}
		}
		System.out.println( "Last mutterList.size() = " + mutterList.size() );
		System.out.println( "Last count = " + count );
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
}
