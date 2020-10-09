package chessman;

public class Bishop extends LongMover {

	//	コンストラクタ
	public Bishop( int player ) {
		super( player, "Bishop" , '♝' );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動が可能かを確認
	@Override
	public boolean moveJudg( Chessman[][] board, int[] intInput, int count ) {

		this.setMoveImpossible( false );	//	移動が不可能(Impossible)ではない(false)で初期化
		this.moveAmountSet( intInput );	//	移動の量(amount)と方向(direction)を変数に代入
										//		(super.super(Chessman)クラスのメソッド)

			//	全駒共通の移動制限の確認(super,superクラス(Chessman)のメソッドを呼び出し、trueかfalseを受け取る）
		if(this.generalMoveCheck( board, intInput, count ) ) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)

			//	Bishop独自の移動制限の確認（縦(Y)と横(X)の移動の量(amount)が同じじゃなければ移動不可）
			//	（斜めの移動は縦(Y)と横(X)の変化量が等しくなるので、差があったら斜めではない移動だと判断できる）
		}else if( this.getAmountOfX() != this.getAmountOfY() ) {
			this.setMoveImpossible( true );		//	縦と横のそれぞれの移動量に差があったら移動不可能(true)

			//	移動ルート上の障害物(駒)の有無を確認（自身のルートチェックメソッドを呼び出し、trueかfalseを受け取る）
		}else if( this.routeCheck( board, intInput ) ) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)
		}

			//	上記のif文の結果で移動が不可能(Impossible)かどうかを返す
		return this.isMoveImpossible();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動ルート上の障害物(Obstacle)の有無を確認
	@Override
	public boolean routeCheck( Chessman[][] board,int[] intInput ) {

		this.setObstacle( false );		//	障害物(Obstacle)無しで初期化

			//	ルート上の駒の有無を確認するループ(回数は移動の量(amount)と同じ)
		for( int i = 1; i < this.getAmountOfX(); i++ ) {
			if( board[ intInput[ 1 ] + i * this.getDirectionOfY() ][ intInput[ 0 ] + i * this.getDirectionOfX() ] != null ) {
												//	添え字両方(縦,横)を増減させて移動ルート上の駒の有無を確認
				this.setObstacle( true );		//	ルート上に駒があったら障害物(Obstacle)有り
			}
		}
			//	trueかfalseを返す
		return this.isObstacle();
	}
}
