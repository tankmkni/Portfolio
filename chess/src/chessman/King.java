package chessman;

public class King extends Chessman {


	//	コンストラクタ
	public King( int player ) {
		super( player, "King" , '♚' );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動可能かを確認するメソッド
	@Override
	public boolean moveJudg( Chessman[][] board, int[] intInput, int count ) {

		this.setMoveImpossible( false );	//	下記のif文に当てはまらなければ移動可

			//	全駒共通の移動制限の確認(super,superクラス(Chessman)のメソッドを呼び出し、trueかfalseを受け取る）
		if(this.generalMoveCheck( board, intInput, count )) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)


			//	King独自の移動制限の確認（一歩しか動けないので、縦(Y)か横(X)の移動量が１より大きかったら移動不可）
			//	（Math.abs(引数)メソッドは、絶対値を取得するメソッド（正数は正数のまま、負数は正数に変換））
		}else if( Math.abs( intInput[ 3 ] - intInput[ 0 ] ) > 1 || Math.abs( intInput[ 4 ] - intInput[ 1 ] ) > 1 ) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)
		}

		this.castlingCheck( board, intInput );	// キャスリングの確認

		if( this.isCastling() ) {									//	キャスリングが可能だったら
			this.setMoveImpossible( false );						//	移動は不可能ではない
		}

		if( this.getFirstMove() == 0 && !( this.isMoveImpossible() ) ){	//	一手目で移動不可じゃない場合
			this.setFirstMove( count );							//	一手目確認の解除
		}

		return this.isMoveImpossible();
	}

//----------------------------------------------------------------------------------------------------------------------

 	// キャスリングの確認
	public void castlingCheck( Chessman[][] board, int[] intInput ) {

			//	１手目で、横の移動量が２マスだったら
			//	Math.abs(引数)メソッドは、絶対値を取得するメソッド（正数は正数のまま、負数は正数に変換））
		if( this.getFirstMove() == 0 && Math.abs( intInput[ 3 ] - intInput[ 0 ] ) == 2 ) {

				//	移動の方向がプラス(positive)かマイナス(negative)かを取得
				//	（Math.signum(引数)メソッドは、引数が + か - かを返すメソッド）
				// 	（正数 = 1.0 , 0 = 0 , 負数 = -1.0（int型ではない））
			int negativePositive = (int)Math.signum( intInput[ 3 ] - intInput[ 0 ] );
			int column = 7;	//	列を指定する変数	初期値は 7
			int loop = 4;		//	ループ回数を指定する変数	初期値は 4

				//	移動方向がマイナスだった時は、変数を変更
			if( negativePositive < 0 ) {
					column = 0;
					loop = 3;
			}

				//	キャスリングする側のルークの確認用の変数
			Chessman rookSide = board[ intInput[ 1 ] ][ column ];

				//	キャスリングする側の端にルークがいて
			if( rookSide != null && rookSide.getName() == "Rook" ) {

					//	そのルークがまだ一度も動いていなければ
				if( rookSide.getFirstMove() == 0 ) {

					this.setCastling( true );		//	キャスリングを認める(true)

						//	キャスリングのルート上に駒があるか確認するループ
					for( int i = 1; i < loop; i++ ) {

							//	ルート上に駒があったら
						if( board[ intInput[ 1 ]][ 3 + i * negativePositive ] != null ) {

							this.setCastling( false );		//	キャスリングの許可を取り消す(false)
						}
					}
				}
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------

	//	不使用（プロトタイプ）
	public boolean castlingCheckProto(Chessman[][] board,int[] intInput){

		// キャスリングの確認
		if( this.getFirstMove() == 0 && Math.abs( intInput[ 3 ] - intInput[ 0 ] ) == 2 ){
			if( Math.signum( intInput[ 3 ] - intInput[ 0 ] ) > 0 ){
				if( board[ intInput[ 1 ] ][ 7 ] != null && board[ intInput[ 1 ]][ 7 ].getName() == "Rook" ){
					if( board[ intInput[ 1 ] ][ 7 ].getFirstMove() == 0 ){
						this.setCastling( true );
						for( int i = 1; i < 4; i++ ){
							if( board[ intInput[ 1 ] ][ 3 + i ] != null ){
								this.setCastling(false);
							}
						}
					}
				}
			}else {
				if( board[ intInput[ 1 ] ][ 0 ] != null && board[ intInput[ 1 ] ][ 0 ].getName() == "Rook" ){
					if( board[ intInput[ 1 ] ][ 0 ].getFirstMove() == 0 ){
						this.setCastling( true );
						for( int i = 2; i > 0; i-- ){
							if( board[ intInput[ 1 ] ][ 3 - i ] != null ){
								this.setCastling( false );
							}
						}
					}
				}
			}
		}


		return false;

	}
}
