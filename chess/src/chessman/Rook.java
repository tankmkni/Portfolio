package chessman;

public class Rook extends LongMover {


	//	コンストラクタ
	public Rook(int player) {
		super( player, "Rook" , '♜' );
	}

	//	移動可能かを確認
	@Override
	public boolean moveJudg( Chessman[][] board, int[] intInput, int count ){

		this.setMoveImpossible( false );	//	移動が不可能(Impossible)ではない(false)で初期化
		this.moveAmountSet( intInput );		//	移動の量(amount)と方向(direction)を変数に代入
										//	(super.super(Chessman)クラスのメソッド)


			//	全駒共通の移動制限の確認(super,superクラス(Chessman)のメソッドを呼び出し、trueかfalseを受け取る）
		if(this.generalMoveCheck( board, intInput, count ) ) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)

			//	Rook独自の移動制限の確認（縦か横だけの移動でなかったら移動不可）
			//	（縦か横だけの移動は縦(Y)と横(X)の変化が片方だけなので、両方に変化があれば縦横の移動ではないと判断できる）
		}else if( intInput[ 1 ] != intInput[ 4 ] && intInput[ 0 ] != intInput[ 3 ]){
			this.setMoveImpossible(true);		//	縦か横だけの移動でなかったら移動不可能(true)

			//	移動ルート上の障害物（駒）の有無を確認（自身のrouteCheckメソッドを呼び出し、trueかfalseを受け取る）
		}else if(this.routeCheck( board,intInput ) ) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)
		}

			//	一手目の確認を解除するか否かを確認
		if( this.getFirstMove() == 0 && !( this.isMoveImpossible() ) ){	//	一手目で移動不可じゃない場合
			this.setFirstMove( count );				//	一手目確認の解除
		}

			//	移動が不可能(Impossible)かどうかを返す
		return this.isMoveImpossible();

	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動ルート上の障害物(駒)の有無を確認するメソッド
	@Override
	public boolean routeCheck( Chessman[][] board,int[] intInput ){

		this.setObstacle(false);		//	障害物(Obstacle)無しで初期化


		if( intInput[ 1 ] == intInput[ 4 ] ) {			//	縦の移動だった場合

				//	縦の移動のルート上の駒の有無を確認するループ
			for( int i = 1; i < getAmountOfX(); i++ ) {
				if( board[ intInput[ 1 ] ][ intInput[ 0 ] + i * this.getDirectionOfX() ] != null ) {
													//	二つ目(縦)の添え字を増減させて移動ルート上の駒の有無を確認
					this.setObstacle( true );		//	ルート上に駒があったら障害物(Obstacle)有り
				}
			}
		}else if( intInput[ 0 ] == intInput[ 3 ] ) {		//	横の移動だった場合

				//	横の移動のルート上の駒の有無を確認するループ
			for( int i = 1; i < getAmountOfY(); i++ ) {
				if( board[ intInput[ 1 ] + i * this.getDirectionOfY() ][ intInput[ 0 ] ] != null ) {
													//	一つ目(横)の添え字を増減させて移動ルート上の駒の有無を確認
					this.setObstacle( true );		//	ルート上に駒があったら障害物(Obstacle)有り
				}
			}
		}

			//	trueかfalseを返す
		return this.isObstacle();
	}
}