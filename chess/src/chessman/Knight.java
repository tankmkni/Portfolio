package chessman;

public class Knight extends Chessman {

	//	コンストラクタ
	public Knight( int player ) {
		super( player, "Knight" , '♞' );
	}

	//	移動可能かを確認
	@Override
	public boolean moveJudg( Chessman[][] board, int[] intInput, int count ) {

		this.setMoveImpossible( false );	//	移動が不可能(Impossible)ではない(false)で初期化
		this.moveAmountSet( intInput );	//	移動の量(amount)と方向(direction)を変数に代入
										//		(super(Chessman)クラスのメソッド)

			//	全駒共通の移動制限の確認(superクラス(Chessman)のメソッドを呼び出し、trueかfalseを受け取る）
		if( this.generalMoveCheck( board, intInput, count ) ){
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)

			//	Knigthは横(X)軸の移動の変化量が１か２でなければ移動不可
		}else if( getAmountOfX() != 1 && getAmountOfX() != 2 ){
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)

			//	Knigthは縦(Y)軸の移動の変化量が１か２でなければ移動不可
//		}else if( getAmountOfY() != 1 && getAmountOfY() != 2 ) {
//			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)
//			(↓で条件は絞れるので、x軸かy軸のどちらかを確認すれば良いことからコメントアウト)

			//	Knigthは横(X)軸と縦(Y)軸の移動の変化量の合計が３でなければ移動不可
		}else if( getAmountOfX() + getAmountOfY() != 3 ) {
			this.setMoveImpossible( true );		//	trueが返ってきたら移動不可能(true)
		}

			//	trueかfalseを返す
		return isMoveImpossible();
	}
}
