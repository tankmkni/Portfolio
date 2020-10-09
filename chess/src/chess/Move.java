package chess;

import chessman.Chessman;

public class Move {

	//	フィールド
	final String[] METHOD = {"castling","enPassant","normal"};		//	移動法(method)の種類
	private String whatDid;											//	どの移動法を実行したかを代入する変数
	private boolean promotion;										//	プロモーション条件の有無を代入する変数

//----------------------------------------------------------------------------------------------------------------------


	//	アクセサメソッド
	public String getWhatDid() {
		return whatDid;
	}
	public boolean isPromotion() {
		return promotion;
	}
	public void setWhatDid(String whatDid) {
		this.whatDid = whatDid;
	}
	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}

//----------------------------------------------------------------------------------------------------------------------


	//	条件によって駒の移動法を分岐するメソッド
	public Chessman[][] move( Chessman[][] board, int[] inted ) {

		this.setPromotion( false );		//	プロモーション条件をリセット

			//	キャスリングの条件に合えばキャスリングをする
		if ( board[ inted[ 1 ] ][ inted[ 0 ] ].isCastling() ) {
			board = this.castling( board, inted );
			this.setWhatDid( this.METHOD[ 0 ] );

			//	アンパッサンの条件に合えばアンパッサンをする
		} else if ( board[ inted[ 1 ] ][ inted[ 0 ] ].getEnPassant() != 0 ) {
			board = this.enPassant( board, inted );
			this.setWhatDid( this.METHOD[ 1 ] );

			//	それ以外なら通常の移動をする
		} else {
			board = this.normal( board, inted );
			this.setWhatDid( this.METHOD[ 2 ] );

				//	プロモーションの条件に合えば
			if( board[ inted[ 4 ] ][ inted[ 3 ] ].getName().equals( "Pawn" ) && inted[ 4 ] % 7 == 0 ) {
					//	移動させた駒がポーンで、盤の端まで進んだら（移動先が０行目か７行目かで判断）
					//	（０～７の行番号で、７で割った余りが０になるのは、０と７だけ）

				this.setPromotion( true );		//	プロモーション条件をtrueに
			}
		}

		return board;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	通常の移動をするメソッド
	public Chessman[][] normal( Chessman[][] board, int[] inted ) {

		board[ inted[ 4 ] ][ inted[ 3 ] ] = board[ inted[ 1 ] ][ inted[ 0 ] ]; 		//	移動先に移動前の駒を代入
		board[ inted[ 1 ] ][ inted[ 0 ] ] = null; 									//	移動前のマスを空にする

		return board;
	}

	//----------------------------------------------------------------------------------------------------------------------

	//	キャスリングの移動をするメソッド
	public Chessman[][] castling( Chessman[][] board, int[] inted ) {

		int colmun;													//	キャスリングをする側の列の添え字を代入する変数
		int direction = (int) Math.signum( inted[ 3 ] - inted[ 0 ] );	//	キャスリングの方向(direction)を決める変数
			//	（Math.signum(引数)メソッドは、引数が正数なら 1.0 を、0 なら 0 を、負数なら -1.0 を返す(float型?)）

			//	クイーンサイドキャスリングの場合
		if ( direction > 0 ) {
			colmun = 7;	//	クイーンルークの列の添え字

			//	キングサイドキャスリングの場合
		} else {
			colmun = 0;	//	キングルークの列の添え字
		}

		board[ inted[ 4 ] ][ inted[ 3 ] ] = board[ inted[ 1 ] ][ inted[ 0 ] ];	//	キングの移動

			//	ルークの移動（移動したキングの反対側の隣へ移動させる）
		board[ inted[ 1 ] ][ inted[ 0 ] + direction ] = board[ inted[ 1 ] ][ colmun ];

		board[ inted[ 1 ] ][ inted[ 0 ] ] = null;						//	キングの移動前のマスを空にする
		board[ inted[ 1 ] ][   colmun   ] = null;						//	ルークの移動前のマスを空にする

		board[ inted[ 4 ] ][ inted[ 3 ] ].setCastling( false );			//	キングのキャスリングの権利を取り消す

		return board;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	アンパッサンの移動をするメソッド
	public Chessman[][] enPassant( Chessman[][] board, int[] inted ) {

		board[ inted[ 4 ] ][ inted[ 3 ] ] = board[ inted[ 1 ] ][ inted[ 0 ] ];	//	移動先に移動する駒を代入

		board[ inted[ 1 ] ][ inted[ 0 ] ] = null;				//	移動前のマスを空にする
		board[ inted[ 1 ] ][ inted[ 3 ] ] = null;				//	アンパッサンされた駒のマスを空にする

		board[ inted[ 4 ] ][ inted[ 3 ] ].setEnPassant( 0 );	//	移動させたポーンのアンパッサンの権利を取り消す

		return board;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動させる駒の移動判定をさせるメソッド
	public boolean moveJudg( Chessman[][] board, int[] inted, int count ) {

		return board[ inted[ 1 ] ][ inted[ 0 ] ].moveJudg( board, inted, count );
	}
}