package chess;

import chessman.Chessman;

public class Board {

	//	フィールド
	private Chessman[][][] board;		//	２枚のチェス盤(移動前後それぞれの８×８の情報を格納)

//----------------------------------------------------------------------------------------------------------------------

	//	コンストラクタ
	public Board() {
		this.board  = new Chessman[ 2 ][ 8 ][ 8 ];
		//	一つ目 = 移動前後それぞれの盤（０ = 移動後の盤、１ = 移動前の盤）
		//	二つ目 = 盤の行
		//	三つ目 = 盤の列
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター

	//	ボード全体を返す
	public Chessman[][][] getBoard() {
		return this.board;
	}
	//	盤の片方を返す
	public Chessman[][] getBoard( int index ) {
		return this.board[ index ];
	}
	//	盤の１マスの駒だけを返す
	public Chessman getBoard( int[] intInput, int index1, int index2 ) {
		return this.board[ 0 ][ intInput[ index1 ] ][ intInput[ index2 ] ];
	}
	public Chessman getBoard( int index1, int index2, int index3 ) {
		return this.board[ index1 ][ index2 ][ index3 ];
	}


	//	セッター

	//	ボード全体を書き換える
	public void setBoard( Chessman[][][] board ) {
		this.board = board;
	}
	//	ボードの片側を書き換える
	public void setBoard( Chessman[][] board ) {
		this.board[ 0 ] = board;
	}
	//	ボードの１マスを書き換える
	public void setBoard( int index1, int index2, int index3, Chessman element ) {
		this.board[ index1 ][ index2 ][ index3 ] = element;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	処理前の盤をコピーをしてから返すメソッド
	public Chessman[][] before() {
		this.copy();
		return this.getBoard( 0 );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	インプット値から、動く駒(mover)を返すメソッド
	public Chessman mover( int[] intInput ) {
		return this.getBoard( intInput, 1, 0 );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	インプット値から、動いた駒(moved)を返すメソッド
	public Chessman moved( int[] intInput ) {
		return this.getBoard( intInput, 4, 3 );
	}

//----------------------------------------------------------------------------------------------------------------------

	// 一枚目の盤の駒を二枚目の盤にコピーする
	public void copy() {
		for( int i = 0; i < 8; i++ ) {
			for( int j = 0; j < 8; j++) {
				this.setBoard( 1, i, j, this.getBoard( 0, i, j ) );
			}
		}
	}
}