package chess;

public class Counter {

	//	フィールド
	final String[] TURN = {"後手","先手"};	//	手番
	private int count;						//	何手目かをカウント

//----------------------------------------------------------------------------------------------------------------------

	//	コンストラクタ
	public Counter() {
		this.setCount(0);
	}

//----------------------------------------------------------------------------------------------------------------------

	//	アクセサメソッド
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	countを１つ増やすメソッド
	public void upCount() {
		this.setCount( this.getCount() + 1 );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	一番最初の手番ではなければtrueを返すメソッド
	public boolean notFirstTurn() {
		boolean notFirstTurn = true;
		if( this.getCount() <= 1 ) {
			notFirstTurn = false;
		}
		return notFirstTurn;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	現在の手番(turn)を返すメソッド（先手か後手かを返す）
	public String turn() {		//	カウントをTURNの要素数(２)で割った余りがそのまま添え字として使える
		return this.TURN[ this.getCount() % this.TURN.length ];
	}

//----------------------------------------------------------------------------------------------------------------------

	//	現在の指し手(move)が何手目かを返すメソッド
	public int whatMove() {
		//	（将棋はカウント数がそのまま手数だが、チェスは先手後手それぞれ指して１手なのでそのままでは使えない）
		//	count数を２で割った商に、count数が偶数なら０、奇数なら１(count数を２で割った余り)が足された値を返す
		return ( this.getCount() / 2 ) + ( this.getCount() % 2 );
	}
}