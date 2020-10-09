package chess;

//	インプットされた入力値を処理するクラス
public class Input {

	//	フィールド
	private String sentence;			//	入力した文を代入しておく変数
	private int value;					//	入力した値を代入しておく変数
	private char[] chared;			//	String型で入力された入力値をchar型に分解して代入する配列
	private int[] inted;				//	添え字に利用するためchar型の値をint型に変換して代入する配列
	private boolean permission;		//	入力を許可(permission)する(true)か否(false)かを代入

//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター
	public String getSentence() {
		return this.sentence;
	}
	public int getValue() {
		return value;
	}
	public char[] getChared() {
		return chared;
	}
	public char getChared( int index ) {
		return this.chared[ index ];
	}
	public int[] getInted() {
		return this.inted;
	}
	public int getInted( int index ) {
		return this.inted[ index ];
	}
	public boolean getPermission() {
		return permission;
	}

	//	セッター
	public void setSentence( String sentence ) {
		this.setValue( 0 );							//	int型の変数を０で初期化
		this.sentence = sentence;
	}
	public void setValue( int value ) {
		this.value = value;
	}
	public void setChared( char[] chared ) {
		this.chared = chared;
	}
	public void setInted( int[] inted ) {
		this.inted = inted;
	}
	public void setInted( int index, int element ) {
		this.inted[ index ] = element;
	}
	public void setPermission( boolean permission ) {
		this.permission = permission;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	入力値を変数に代入してその変数を返すメソッド
	public String setBackSentence( String input ) {
		this.setSentence( input );
		return this.getSentence();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	入力規則をチェックして変数 permission(許可) を代入して返すメソッド
	public boolean checkMove( String input) {

		this.setPermission( false );

		if( input.matches("[a-h][1-8][-][a-h][1-8]") ) {
			this.setPermission( true );
		}
		return this.getPermission();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	入力された文のchar型とint型を用意する(prepare)メソッド(マークの候補が格納された配列を受け取って比較)
	public void prepareType( char[][] mark ) {

		this.setChared( this.getSentence().toCharArray() );			//	入力文をchar型配列に分解して代入
		this.setInted( new int[ getChared().length ]);				//	charedと同じ要素数（５）で初期化

			//	一致したcharの添え字を利用してint型として返す３重ループ
		for( int i = 0; i < this.getChared().length; i++ ) {			// charedの要素の数だけループを回す(５回)
			for( int j = 0; j < mark.length; j++ ) {					// markの１つ目の要素の数だけループを回す(２回）
				for( int k = 0; k < mark[ j ].length; k++ ) {			// markの２つ目の要素の数だけループを回す(９回）
					if( mark[ j ][ k ] == this.getChared( i ) ) {		// 文字が一致した時だけ
						this.setInted( i, k );						// 添え字をint配列に代入（i⇒添え字、k⇒要素の値）
					}
				}
			}
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	入力されたString値が数字かどうかを判定するメソッド
	public boolean checkNumber( String input ) {

		this.permission = false;

		if( this.getSentence().matches( "[0-9]{0,}" ) ) {
			this.permission = true;
		}
		return this.permission;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	入力された値が範囲内(Within)かどうかを判定するメソッド
	public boolean checkWithin( int range ) {

		this.setPermission( false );	//	不許可

		if( this.getValue() > 0 && this.getValue() < range ) {
			this.setPermission( true );	//	許可
		}
		return this.getPermission();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ゲームを終了させるメッセージかどうかを判定するメソッド
	public boolean checkWord( String gmaeEnd ) {

		this.permission = false;

		if( gmaeEnd.equals( this.getSentence() )) {
			this.permission = true;
		}
		return this.permission;
	}
}