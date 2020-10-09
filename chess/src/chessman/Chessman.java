package chessman;

public abstract class Chessman {

	//フィールド
	private int player;				//	1,2
	private String name;				//	King,Queen,Bishop,Knight,PreviousRook,Pawm
	private char mark;					//	Ｋ，Ｑ，Ｂ，Ｎ，Ｒ，Ｐ
	private boolean moveImpossible;	//	移動の可能か不可能(Impossible)か
	private int amountOfX;				//	横(x)軸の移動の量(amount)を代入する変数
	private int amountOfY;				//	縦(y)軸の移動の量(amount)を代入する変数
	private int directionOfX;			//	横(x)軸の移動の方向(direction)がマイナスの時に-1が代入される変数
	private int directionOfY;			//	縦(y)軸の移動の方向(direction)がマイナスの時に-1が代入される変数
	private int firstMove;				//	一手目の確認,Kingとrookとpawnのみ使用
	private boolean castling;			//	キャスリング(castling,入城)の宣言用,kingとrookのみ使用
	private int enPassant;				//	アンパッサン(enPassant)の処理で使用,pawnのみ使用

//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター
	public int getPlayer() {
		return player;
	}
	public String getName() {
		return name;
	}
	public char getMark() {
		return mark;
	}
	public boolean isMoveImpossible() {
		return moveImpossible;
	}
	public int getAmountOfX() {
		return amountOfX;
	}
	public int getAmountOfY() {
		return amountOfY;
	}
	public int getDirectionOfX() {
		return directionOfX;
	}
	public int getDirectionOfY() {
		return directionOfY;
	}
	public int getFirstMove() {
		return firstMove;
	}
	public boolean isCastling() {
		return this.castling;
	}
	public int getEnPassant() {
		return enPassant;
	}

	//	セッター
	public void setPlayer( int player ) {
		this.player = player;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public void setMark( char mark ) {
		this.mark = mark;
	}
	public void setMoveImpossible( boolean moveImpossible ) {
		this.moveImpossible = moveImpossible;
	}
	public void setAmountOfX( int amountOfX ) {
		this.amountOfX = amountOfX;
	}
	public void setAmountOfY( int amountOfY ) {
		this.amountOfY = amountOfY;
	}
	public void setDirectionOfX( int directionOfX ) {
		this.directionOfX = directionOfX;
	}
	public void setDirectionOfY( int directionOfY ) {
		this.directionOfY = directionOfY;
	}
	public void setFirstMove( int firstMove ) {
		this.firstMove = firstMove;
	}
	public void setCastling( boolean castling ) {
		this.castling = castling;
	}
	public void setEnPassant( int enPassant ) {
		this.enPassant = enPassant;
	}

//----------------------------------------------------------------------------------------------------------------------

	//コンストラクタ
	public Chessman( int player , String name , char mark ) {
		this.setPlayer( player );
		this.setName( name );
		this.setMark( mark );
		this.setAmountOfX( 0 );
		this.setAmountOfY( 0 );
		this.setDirectionOfX( 1 );
		this.setDirectionOfY( 1 );
		this.setFirstMove( 0 );
		this.setMoveImpossible( false );
		this.setCastling( false );
		this.setEnPassant( 0 );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	駒によって移動の可不可を判定する 抽象 メソッド
	public abstract boolean moveJudg( Chessman[][] board, int[] intInput, int count );

//----------------------------------------------------------------------------------------------------------------------

	//	全駒共通の移動制限の確認をするメソッド
	public boolean generalMoveCheck( Chessman[][] board, int[] intInput, int count ) {

			//	手番のプレイヤー以外の駒を選んでいたら
		if(board[ intInput[ 1 ] ][ intInput[ 0 ] ].getPlayer() == count % 2 + 1 ) {
			this.setMoveImpossible( true );	//	移動不可
		}

			//	移動先(destination)の情報を代入する変数
		Chessman destination = board[ intInput[ 4 ] ][ intInput[ 3 ] ];

			//	移動先に駒がいて、その駒が自分と同じ色だったら
		if( destination != null && this.getPlayer() == destination.getPlayer() ) {
			this.setMoveImpossible( true );	//	移動不可
		}
		return this.isMoveImpossible();
	}

//----------------------------------------------------------------------------------------------------------------------

	// 移動の量(amount)と方向(direction、1か-1)の２つの変数に代入するメソッド
	public void moveAmountSet( int[] intInput ) {

		this.setDirectionOfX( 1 );			//	横(x)軸の移動の方向(direction)の初期化(移動方向がマイナスの時に-1が代入される)
		this.setDirectionOfY( 1 );			//	縦(y)軸の（以下同上）

		if( intInput[ 3 ] - intInput[ 0 ] < 0 ) {	//	横の移動先の番地から移動前の番地を引いてマイナスだったら
			this.setDirectionOfX( -1 );				//	移動方向がマイナスなので-1を代入
		}
		if( intInput[ 4 ] - intInput[ 1 ] < 0 ) {	//	縦の移動先の番地から移動前の番地を引いてマイナスだったら
			this.setDirectionOfY( -1 );				//	移動方向がマイナスなので-1を代入
		}

			//	縦(Y)と横(X)それぞれの移動の量(amount)を正数にして代入
			//	（Math.abs(引数)メソッドは、絶対値を取得するメソッド（正数は正数のまま、負数は正数に変換））
		this.setAmountOfX( Math.abs( intInput[ 3 ] - intInput[ 0 ] ) );
		this.setAmountOfY( Math.abs( intInput[ 4 ] - intInput[ 1 ] ) );
	}
}
