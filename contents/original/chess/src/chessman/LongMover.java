package chessman;

public abstract class LongMover extends Chessman {

	// フィールド

	private boolean obstacle;		//	ルート上に障害物(obstacle)があるかどうかを判定する変数

//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター
	public boolean isObstacle() {
		return obstacle;
	}
	//	セッター
	public void setObstacle( boolean obstacle ) {
		this.obstacle = obstacle;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	コンストラクタ
	public LongMover( int player, String name, char mark ) {
		super( player, name, mark );
		this.setObstacle( false );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ルート上の障害物（駒）の有無を確認する抽象メソッド
	public abstract boolean routeCheck( Chessman[][] board,int[] intInput );

}
