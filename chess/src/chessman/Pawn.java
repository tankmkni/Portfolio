package chessman;

public class Pawn extends Chessman {

	//	コンストラクタ
	public Pawn( int player ) {
		super( player, "Pawn", '♟' );

		switch ( player ) { //	手番によって進行方向を設定
			case 1: //	先手の場合
				this.setDirectionOfY( 1 ); //	進行方向が昇順
					break;
			case 2: //	後手の場合
				this.setDirectionOfY( -1 ); //	進行方向が降順
			}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動可能かを確認
	@Override
	public boolean moveJudg( Chessman[][] board, int[] intInput, int count ) {

		this.setMoveImpossible( false ); //	下記のif文に当てはまらなければ移動可

			//	Pawn独自の移動制限の確認（基本、前に一歩しか動けないが、条件によって特殊な動き方をする）
			//	一歩前進以外の移動は不可（縦の座標のズレが移動前後で１以外だったら）
		if ( intInput[ 4 ] != intInput[ 1 ] + this.getDirectionOfY() ) {
			this.setMoveImpossible( true ); //	trueが返ってきたら移動不可能(true)

			//	２列以上横への移動は不可
			//	（Math.abs(引数)メソッドは、絶対値を取得するメソッド（正数は正数のまま、負数は正数に変換））
		} else if ( Math.abs( intInput[ 3 ] - intInput[ 0 ] ) > 1 ){
			this.setMoveImpossible( true ); //	trueが返ってきたら移動不可能(true)

			//	斜め前を指定してそこに駒が無ければ移動不可
			//	(すでに一歩前進の確認は終了しているので、横の座標に変化があれば斜め前）
		} else if ( intInput[ 3 ] != intInput[ 0 ] && board[ intInput[ 4 ] ][ intInput[ 3 ] ] == null ){
			this.setMoveImpossible(true); //	trueが返ってきたら移動不可能(true)

				//	アンパッサンの条件(Conditions)に合うか
			if( enPassantConditions( board, intInput, count ) ) {
				this.setMoveImpossible( false ); //	trueが返ってきたら移動不可能ではない(false)
			}
		}

			//	初期配置からの移動は２歩進める
		if ( getFirstMove() == 0 && intInput[ 3 ] == intInput[ 0 ] ) { //	初期配置から同じ列に
			if ( intInput[ 4 ] == intInput[ 1 ] + this.getDirectionOfY() * 2 ){ 	//	２手進もうとして
				if ( board[ intInput[ 1 ] + this.getDirectionOfY() ][ intInput[ 0 ] ] == null ){ //	ルート上に駒がなければ
					this.setMoveImpossible( false ); 	//	全てtrueが返ってきたら移動不可能ではない(false)
				}
			}
		}

			//	全駒共通の移動制限の確認(super,superクラス(Chessman)のメソッドを呼び出し、trueかfalseを受け取る）
		if ( this.generalMoveCheck( board, intInput, count ) ) {
			this.setMoveImpossible( true ); //	trueが返ってきたら移動不可能(true)

			//	同じ列の移動で、移動先に駒がいたら移動不可
		} else if ( intInput[ 3 ] == intInput[ 0 ] && board[ intInput[ 4 ] ][ intInput[ 3 ] ] != null ) {
			this.setMoveImpossible( true ); //	trueが返ってきたら移動不可能(true)
		}

			//	一手目確認の解除
		if ( this.getFirstMove() == 0 && !( this.isMoveImpossible() ) ){ //	一手目で移動不可じゃない場合
			this.setFirstMove( count ); //	一手目確認の解除

				//	アンパッサンの権利を渡すかを判定
			if( Math.abs( intInput[ 4 ] - intInput[ 1 ] ) == 2 ){
				this.entitle( board, intInput, count ); 		//	アンパッサンの権利を渡す(entitle)メソッドの呼び出し
			}
		}

			//	trueかfalseを返す
		return this.isMoveImpossible();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	アンパッサンの権利を渡す(entitle)メソッド
	private void entitle( Chessman[][] board, int[] intInput, int count ) {

			//	アンパッサンする側(side)を代入する変数（１か－１）
		int side = 1;

			//	移動先の両隣にポーンがいるかを確認するループ（２回）
		for( int i = 0; i < 2; i++ ) {

			side = side * -1;											//	確認するサイドを反転させる

			int column = intInput[ 3 ] + side;							//	確認する列
			Chessman sideToBe = board[ intInput[ 4 ] ][ column ];		//	権利を渡される側(side to be)のマス

				//	サイドが盤の外だったら何もしない
			if( column < 0 || board.length <= column  ){
				continue;

				//	そのサイドに駒が有って、その駒がポーンで
			}else if ( sideToBe != null && sideToBe.getName() == "Pawn" ){

					//	それが自分の駒じゃなければ
				if( sideToBe.getPlayer() != this.getPlayer() ) {

						//	その駒にアンパッサンの権利を渡す( 1 か -1 )
					sideToBe.setEnPassant( side * -1 );

				}
			}
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	アンパッサンの条件(Conditions)を確認するメソッド
	public boolean enPassantConditions( Chessman[][] board, int[] intInput, int count){

		boolean criteria = false; //	基準(criteria)に合うかを代入する変数

			//	アンパッサンの権利があるかを確認
		if ( board[ intInput[ 1 ] ][ intInput[ 0 ] ].getEnPassant() != 0 ){

				//	アンパッサンされる駒が存在するかを確認
			if ( board[ intInput[ 1 ] ][ intInput[ 3 ] ] != null ){

					//	アンパッサンされるのはポーンであるかを確認
				if( board[ intInput[ 1 ] ][ intInput[ 3 ] ].getName() == "Pawn" ){

						//	現在のカウントの一手前がその駒の一手目なら
					if( board[ intInput[ 1 ] ][ intInput[ 3 ] ].getFirstMove() == --count ){ //	( count-- だとダメ)

						criteria = true;	//	全てtrueなら基準に合う(true)
					}
				}
			}
		}
		return criteria;
	}
}
