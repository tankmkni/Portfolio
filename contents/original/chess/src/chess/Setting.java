package chess;

import chessman.Bishop;
import chessman.Chessman;
import chessman.King;
import chessman.Knight;
import chessman.Pawn;
import chessman.Queen;
import chessman.Rook;

public class Setting {

	//	フィールド

	final int[] COMMON_INDEX = {1,1,6,0,0,0,0,2};
		//	０と７番目の要素を各駒の引数(プレイヤー番号)と、自身の添え字を指定するのに共用(common)
		//	１と２番目の要素をポーンを並べる行を指定するのに使用
		//	３～６番目の要素は不使用

	//	選択できる駒の並べ方(arrangement)（最初の要素は説明文に使用）
	final String[] ARRANGEMENT = {"コマを並べるパターン","ノーマルの配置","シャッフルチェス","テスト用"};

	//	プロモーションさせる駒の種類（最初の要素は説明文に使用）
	final String[] PROMOTION = {"プロモーションさせる駒","クイーン","ビショップ","ナイト","ルーク"};

//----------------------------------------------------------------------------------------------------------------------

	//	駒の並べ方を選択するメソッド
	public Chessman[][] select( Chessman[][] board, int select ){

		switch( select ) {
			case 1:
				board = this.normal(board);
					break;
			case 2:
				board = this.shuffleChess(board);
					break;
			case 3:
				board = this.test(board);
		}
		return board;
	}


//----------------------------------------------------------------------------------------------------------------------

	//	テスト配置用メソッド
	public Chessman[][] test( Chessman[][] board ){

		//	プレイヤー1,2それぞれの駒を並べるループ（２回）
		//	for文のiを、board[][]の一つ目の添え字と、INDEX[]の添え字として共用する
		for ( int i = 0; i < 8; i += 7 ) {		//	iは0から始まり、７を足す。８以上で終了なので２回のループ

			//	ポーン以外の駒は、０と７の行に並べる
			board[ i ][ 0 ] = new Rook		( COMMON_INDEX[ i ] );
//			board[ i ][ 1 ] = new Knight	( COMMON_INDEX[ i ] );
//			board[ i ][ 2 ] = new Bishop	( COMMON_INDEX[ i ] );
			board[ i ][ 3 ] = new King		( COMMON_INDEX[ i ] );
//			board[ i ][ 4 ] = new Queen		( COMMON_INDEX[ i ] );
//			board[ i ][ 5 ] = new Bishop	( COMMON_INDEX[ i ] );
//			board[ i ][ 6 ] = new Knight	( COMMON_INDEX[ i ] );
			board[ i ][ 7 ] = new Rook		( COMMON_INDEX[ i ] );

		}

		for( int i = 0 ; i < board[1].length ; i++ ) {
			board[ 1 ][ i ] = new Pawn( 1 );
		}

		board [ 6 ][ 5 ] = new Pawn ( 1 );
		board [ 3 ][ 3 ] = new Pawn ( 2 );
		board [ 3 ][ 5 ] = new Pawn ( 2 );
		return board;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	初期配置に駒を並べるメソッド
	public Chessman[][] normal( Chessman[][] board ){

			//	プレイヤー1,2それぞれの駒を並べるループ（２回）
			//	for文のiを、board[][]の一つ目の添え字と、INDEX[]の添え字として共用する
		for ( int i = 0; i < 8; i += 7) {		//	iは0から始まり、７を足す。８以上で終了なので２回のループ

				//	ポーン以外の駒は０と７の行に並べる。インスタンス化の引数は１か２。
			board[ i ][ 0 ] = new Rook		( COMMON_INDEX[ i ] );
			board[ i ][ 1 ] = new Knight	( COMMON_INDEX[ i ] );
			board[ i ][ 2 ] = new Bishop	( COMMON_INDEX[ i ] );
			board[ i ][ 3 ] = new King		( COMMON_INDEX[ i ] );
			board[ i ][ 4 ] = new Queen		( COMMON_INDEX[ i ] );
			board[ i ][ 5 ] = new Bishop	( COMMON_INDEX[ i ] );
			board[ i ][ 6 ] = new Knight	( COMMON_INDEX[ i ] );
			board[ i ][ 7 ] = new Rook		( COMMON_INDEX[ i ] );

				//	ポーンを並べる列の数だけ回すループ(８回)(board[]の添え字は１か６)
			for( int j = 0 ; j < board [ COMMON_INDEX [ COMMON_INDEX [ i ] ] ].length ; j++ ) {

					//	ポーンは１と６の行(１つ目の添え字)に並べる
				board [ COMMON_INDEX [ COMMON_INDEX [ i ] ] ][ j ] = new Pawn ( COMMON_INDEX[ i ] );
			}
		}
		return board;
	}

//----------------------------------------------------------------------------------------------------------------------

	//初期配置にシャッフルチェスで駒を並べるメソッド
	public Chessman[][ ] shuffleChess( Chessman[][] board ){

			//	列と同じ数の配列を用意（要素を添え字として利用する）
		int[] pieceColmun = new int[ board.length ];

			//	配列の中に添え字となる要素を代入するループ
		for( int i = 0; i < pieceColmun.length; i++ ) {
			pieceColmun[ i ] = i;
		}

			//	プレイヤー1,2それぞれの駒を並べるループ（２回）
		for ( int i = 0; i < 8; i += 7 ) {

				//	各駒に渡す引数(プレイヤー番号)を指定する分岐
			int player = 0;
			switch( i ) {
				case 0:
					player = 1;
					 break;
				case 7:
					player = 2;
			}

				//	各駒を代入する列を指定する配列の要素をシャッフルする
			pieceColmun = pieceShuffle(pieceColmun);

			board[ i ][ pieceColmun[ 0 ] ] = new Rook( player );
			board[ i ][ pieceColmun[ 1 ] ] = new Knight( player );
			board[ i ][ pieceColmun[ 2 ] ] = new Bishop( player );
			board[ i ][ pieceColmun[ 3 ] ] = new King( player );
			board[ i ][ pieceColmun[ 4 ] ] = new Queen( player );
			board[ i ][ pieceColmun[ 5 ] ] = new Bishop( player );
			board[ i ][ pieceColmun[ 6 ] ] = new Knight( player );
			board[ i ][ pieceColmun[ 7 ] ] = new Rook( player );

				//	ポーンを並べる行を指定する分岐
			int pawnRow = 0;
			switch( player ) {
				case 1:
					pawnRow = 1;
						break;
				case 2:
					pawnRow = 6;
			}

				//	ポーンを並べる列の数だけ回すループ（８回）
			for( int j = 0 ; j < board[i].length ; j++ ) {

					//	ポーンは１と６の行に並べる
				board[ pawnRow ][ j ] = new Pawn( player );
			}
		}
				return board;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	駒をシャッフルするメソッド
	public int[] pieceShuffle( int[] pieceColmun ) {

			//	シャッフルをする回数
		int number = 10000;
		for( int i = 0; i < number; i++ ) {

				//	列の数（８回）だけ列の入れ替えを行うループ
			for( int j = 0; j < pieceColmun.length; j++ ) {
				int random = new java.util.Random().nextInt( pieceColmun.length );
				int tmp = pieceColmun[ j ];
				pieceColmun[ j ] = pieceColmun[ random ];
				pieceColmun[ random ] = tmp;
			}
		}
		return pieceColmun;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ポーンを昇進（promotion）させるメソッド
	public Chessman[][] promotion( Chessman[][ ] board, int[] index ,int type ) {

		Chessman promotion = null;
		int player = board[ index[ 4 ] ][ index[ 3 ] ].getPlayer();

			//	入力値によってインスタンス化するクラスを決定
		switch( type ) {
			case 1:
				promotion = new Queen( player );
					break;
			case 2:
				promotion = new Bishop( player );
					break;
			case 3:
				promotion = new Knight( player );
					break;
			case 4:
				promotion = new Rook( player );
		}

		board[ index[ 4 ] ][ index[ 3 ] ] = promotion;

		return board;
	}
}