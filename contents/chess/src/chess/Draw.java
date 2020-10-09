package chess;

import chessman.Chessman;

//	チェス盤を市松模様(CheckeredPattern)で描く(Draw)クラス
public class Draw{

	//	フィールド
	final String SPACE = "\t\t\t\t\t\t";
	final char[][] MARK = {{'h','g','f','e','d','c','b','a','-'},{'1','2','3','4','5','6','7','8','-'}};
											//	↑列と行の記号（mark）を格納する配列
	public int[] colorNominate;			//	色番号の候補(Nominate)

	public String player1ColorSentence;		//	プレイヤー１の色指定文
	public String player2ColorSentence;		//	プレイヤー２の色指定文
	public String backColor1Sentence;		//	盤の色１の色指定文
	public String backColor2Sentence;		//	盤の色２の色指定文
	public String markColorSentence;		//	行と列記号の色指定文

//----------------------------------------------------------------------------------------------------------------------

	//	コンストラクタ
	public Draw() {
		this.colorNominate = new int[] {97, 91, 100, 47, 33, 107};

		setPlayer1ColorSentence( getColorNominate( 0 ) );
		setPlayer2ColorSentence( getColorNominate( 1 ) );
		setBackColor1Sentence( getColorNominate( 2 ) );
		setBackColor2Sentence( getColorNominate( 3 ) );
		setMarkColorSentence( getColorNominate( 4 ), getColorNominate( 5 ) );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター
	public int[] getColorNominate() {
		return colorNominate;
	}
	public int getColorNominate( int index ) {
		return colorNominate[ index ];
	}

	public String getPlayer1ColorSentence() {
		return player1ColorSentence;
	}
	public String getPlayer2ColorSentence() {
		return player2ColorSentence;
	}
	public String getBackColor1Sentence() {
		return backColor1Sentence;
	}
	public String getBackColor2Sentence() {
		return backColor2Sentence;
	}
	public String getMarkColorSentence() {
		return markColorSentence;
	}

	//	セッター
	public void setColorNominate( int[] colorNominate ) {
		for( int i = 0; i < colorNominate.length; i++ ) {
			this.colorNominate[ i ] = colorNominate[ i ];
		}
	}
	public void setColorNominate( int index, int element ) {
		this.colorNominate[ index ] = element;
	}
	public void setColorNominate( int[] color, int index1, int index2 ) {
		this.colorNominate[ index1 ] = color[ 0 ];
		this.colorNominate[ index2 ] = color[ 1 ];
	}

	public void setPlayer1ColorSentence( int color ) {
		this.player1ColorSentence = "\u001b[" + color + "m";
	}
	public void setPlayer2ColorSentence( int color ) {
		this.player2ColorSentence = "\u001b[" + color + "m";
	}
	public void setBackColor1Sentence( int color ) {
		this.backColor1Sentence = "\u001b[" + color + "m";
	}
	public void setBackColor2Sentence( int color ) {
		this.backColor2Sentence = "\u001b[" + color + "m";
	}
	public void setMarkColorSentence( int color1, int color2 ) {
		this.markColorSentence = "\u001b[" + color1 + "m\u001b[" + color2 + "m";
	}

//----------------------------------------------------------------------------------------------------------------------

	//チェス盤を表記するメソッド
	public void draw( String turn, Chessman[][] board ) {

		System.out.print("\n" + this.SPACE + " ");		//	盤上部と左端の空白確保

		switch( turn ) {	//	手番によって並べ方を反転

			case "先手":
				this.drawPlayer1( board );		//	先手の場合
					break;
			case "後手":
				this.drawPlayer2( board );		//	後手の場合
		}
	}

//------------------------------------------------- ---------------------------------------------------------------------

	//	先手番の時の表記メソッド(ループを”降順”で回す)
	public void drawPlayer1( Chessman[][] board ) {

		for( int i = board.length - 1; i >= 0; i-- ) {		//	盤の列の数（８回）だけ”降順”で回すループ
			this.columnMark( i );							//	列記号の表記
		}

		System.out.println("\n");							//	改行

		for( int i = board[0].length - 1; i >= 0; i-- ) {	//	盤の行の数（８回）だけ”降順”で回すループ

			System.out.print( this.SPACE + "\u001b[0m ");

			for( int j = board.length - 1; j >= 0; j-- ) {	//	盤の列の数（８回）だけ”降順”で回すループ
				this.classify( board, i, j );				//	マス目の表記
			}

			this.rowMark(i);		//	行記号の表記
		}
	}

	//	後手番の時の表記メソッド(ループを”昇順”で回す)
	public void drawPlayer2( Chessman[][] board ){

		for( int i = 0; i < board.length; i++ ) {			//	盤の列の数（８回）だけ”昇順”で回すループ
			this.columnMark( i );							//	列記号の表記
		}

		System.out.println("\n");							//	改行

		for( int i = 0; i < board[0].length; i++ ) {		//	盤の行の数（８回）だけ”昇順”回すループ

			System.out.print( this.SPACE + "\u001b[0m ");

			for( int j = 0; j < board.length; j++ ) {		//	盤の列の数（８回）だけ”昇順”回すループ
				this.classify( board, i, j );				//	マス目の表記
			}

			this.rowMark( i );		//	行記号の表記
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	配列要素内の駒の有無とどちらの駒かの違いで表記するものを変えるメソッド
	public void classify( Chessman[][] board, int index1, int index2 ) {

		Chessman inBoard = board[ index1 ][ index2 ];						//	boardの１マスの中身
		String pieceCoior = "";											//	駒の色指定文
		String squarColor = this.squarColorSentence( index1, index2 );		//	マス目(squar)の色指定文
		String inSquar = "\u001b[0m";										//	表記するマスの中身と色を戻す文

			//	配列内の駒の有無による分岐
		if( inBoard == null ) {		//	駒が無ければ

			inSquar = "　" + inSquar;	//	スペースを代入

		}else {						//	駒がある場合

			inSquar = inBoard.getMark() + inSquar;		//	駒のマークを代入

			switch( inBoard.getPlayer() ) {		//	どちらのプレイヤーの駒かによる分岐

				case 1 :		//	先手の場合	先手の駒の色を代入
					pieceCoior = this.getPlayer1ColorSentence();
						break;
				case 2 :		//	後手の場合	後手の駒の色を代入
					pieceCoior = this.getPlayer2ColorSentence();
			}
		}
		System.out.print( pieceCoior + squarColor  + inSquar );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	マス目(square)の色を決めるメソッド
	public String squarColorSentence( int index1, int index2 ) {

		String squarColor;		//	マス目の色を指定する文を代入する変数

			//	縦(index1)と横(index2)を足した数が偶数か奇数かでマスの色を決定
		if( ( index1 + index2 ) % 2 == 0 ) {
			squarColor = this.getBackColor1Sentence();	//	偶数なら色１
		}else {
			squarColor = this.getBackColor2Sentence();	//	奇数なら色２
		}
		return squarColor;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	列記号の表記メソッド
	public void columnMark( int index ) {
		System.out.print( getMarkColorSentence() + " " + this.MARK[ 0 ][ index ] + "\u001b[0m");
	}

	//	行記号の表記メソッド
	public void rowMark( int index ) {
		System.out.println( "  " + getMarkColorSentence() + this.MARK[ 1 ][ index ] + "\u001b[0m");
	}

//----------------------------------------------------------------------------------------------------------------------

	//	駒と盤の色を指定するメソッド
	public void colorSet( int[] color ) {

		this.setColorNominate( color );
		this.setPlayer1ColorSentence( this.getColorNominate( 3 ) );
		this.setPlayer2ColorSentence( this.getColorNominate( 2 ) );
		this.setBackColor1Sentence( this.getColorNominate( 1 ) );
		this.setBackColor2Sentence( this.getColorNominate( 0 ) );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	駒の色を指定するメソッド
	public void pieceColorSet( int[] color ) {

		this.setColorNominate( color, 0, 1 );
		this.setPlayer1ColorSentence( this.getColorNominate( 1 ) );
		this.setPlayer2ColorSentence( this.getColorNominate( 0 ) );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	盤の色を指定するメソッド
	public void boardColorSet( int[] color ) {

		this.setColorNominate( color, 2, 3 );
		this.setBackColor1Sentence( this.getColorNominate( 3 ) );
		this.setBackColor2Sentence( this.getColorNominate( 2 ) );
	}
}
