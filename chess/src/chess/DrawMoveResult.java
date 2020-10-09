package chess;

import chessman.Chessman;

//	移動前後の盤を描く(Draw)クラス（移動の結果(result))
public class DrawMoveResult extends Draw{

	//	フィールド

	final int SHEETS = 2;			//	表記する盤の枚数
	final String SPACE = "\t\t";	//	表示の間の空間の広さを決める定数

//----------------------------------------------------------------------------------------------------------------------

	//	コンストラクタ
	public DrawMoveResult() {
		super();
	}

//----------------------------------------------------------------------------------------------------------------------

	//チェス盤を指定した枚数表記するメソッド
	public void draw( String turn, Chessman[][][] board ) {

		System.out.print("\n" + this.SPACE );			//	盤上部と左端の空白確保

			//	表記する盤の枚数分のループ
		for( int i = 0; i < SHEETS; i++ ) {
			this.rowOfMark( turn, board[ 0 ].length );	//	列マーク(a～h)の行を表記
			System.out.print( this.SPACE + "\t" );				//	スペースの確保
		}

		System.out.print("\n\n");						//	改行

		this.boardDraw( turn, board );					//	移動前後の盤の表記
	}

//----------------------------------------------------------------------------------------------------------------------

	//	列番号の行を表記するメソッド
	public void rowOfMark( String turn, int range ) {

		range--;										//	受け取った範囲から１を引く

		if( turn.equals( "先手" ) ) {					//	先手だったら
			for( int i = range; i >= 0; i-- ) {			//	盤の列の数（８回）だけ” 降 ”順で回すループ
				this.columnMark( i );					//	列記号の表記
			}
		}else {											//	後手だったら
			for( int i = 0; i <= range; i++ ) {			//	盤の列の数（８回）だけ” 昇 ”順で回すループ
				this.columnMark( i );					//	列記号の表記
			}
		}
		System.out.print( this.SPACE );					//	スペースの確保
	}

//----------------------------------------------------------------------------------------------------------------------

	//	行を手番側の向きで表記するメソッド
	public void boardDraw( String turn, Chessman[][][] board ) {

		int range = board[ 0 ].length - 1;

		if( turn.equals( "先手" ) ) {				//	先手の場合
			for( int i = range; i >= 0; i-- ) {		//	盤の列の数（８回）だけ” 降 ”順で回すループ
				this.rows( board, i, turn );		//	盤の枚数分の行の表記
			}
		}else {										//	後手の場合
			for( int i = 0; i <= range; i++ ) {		//	盤の列の数（８回）だけ” 昇 ”順で回すループ
				this.rows( board, i, turn );		//	盤の枚数分の行の表記
			}
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	行を盤の枚数分表記するメソッド
	public void rows( Chessman[][][] board, int index, String turn ) {

		System.out.print( this.SPACE );				//	スペースの確保

		this.row( board[ 1 ], index, turn );		//	移動”前 ”の１行を表記

		this.supace( index );						//	４、５行目に矢印を入れてスペースを確保

		this.row( board[ 0 ], index, turn );		//	移動”後 ”の１行を表記

		System.out.println("");						//	改行
	}

//----------------------------------------------------------------------------------------------------------------------

	//	１行を表記するメソッド
	public void row( Chessman[][] board, int index, String turn ) {

		int range = board[ 0 ].length - 1;

		if( turn.equals( "先手" ) ) {				//	先手の場合
			for( int i = range; i >= 0; i-- ) {		//	盤の列の数（８回）だけ”降 ”順で回すループ
				this.classify( board, index, i );	//	マス目の表記
			}
		}else {										//	後手の場合
			for( int i = 0; i <= range; i++ ) {		//	盤の列の数（８回）だけ”昇 ”順で回すループ
				this.classify( board, index, i );	//	マス目の表記
			}
		}

		this.rowMark( index );						//	行記号の表記
	}

//----------------------------------------------------------------------------------------------------------------------

	@Override
	//行記号の表記メソッド(改行なし）
	public void rowMark( int index ) {
		System.out.print( "  " + getMarkColorSentence() + this.MARK[ 1 ][ index ] + "\u001b[0m");
	}

//----------------------------------------------------------------------------------------------------------------------

	//	４，５行目に矢印を入れてスペースを表記するメソッド
	public void supace( int index ) {

		String center = null;		//	盤の間に表記する記号を代入する変数

		if( index == 3 || index == 4) {		//	行数で分岐
			center = "→";		//	４，５行目だったら矢印を代入
		}else {
			center = "  ";		//	その他だったらスペースを代入
		}
		System.out.print( this.SPACE + center + this.SPACE );		//	スペース確保
	}
}