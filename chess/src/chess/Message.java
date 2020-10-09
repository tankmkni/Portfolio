
package chess;

public class Message {

	//	フィールド
	final String GAME_END = "まいりました";		//	これが入力されるとゲームが終了する
	final String RESTORE = "\u001b[0m";			//	色指定を初期値に戻す（restore）
	final String RED = "\u001b[91m";			//	赤文字の色指定文
	final String SPACE = "\t\t\t\t";			//	スペースの確保
	final String BIGIN = "\n" + this.SPACE;	//	文頭の改行とスペースの確保
	private String color;						//	色指定文を代入する変数
	private String redWord;						//	文章を赤文字に指定して代入する変数
	private String colorWord;					//	文章を指定された色文字で代入する変数
	private String word;						//	入力文を代入する変数


//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター
	public String getColor() {
		return this.color;
	}
	public String getRedWord() {
		return this.redWord;
	}
	public String getColorWord() {
		return this.colorWord;
	}
	public String getWord() {
		return word;
	}

	//	セッター
	public void setColor( int color ) {
		this.color = "\u001b[" + color + "m";
	}
	public void setColor( int color1, int color2 ) {
		this.color = "\u001b[" + color1 + "m\u001b[" + color2 + "m";
	}
	public void setRedWord( String word ) {
		this.redWord = this.RED + word + this.RESTORE;
	}
	public void setColorWord( String word ) {
		this.colorWord = this.getColor() + word + this.RESTORE;
	}
	public void setWord( String word ) {
		this.word = word;
	}

//----------------------------------------------------------------------------------------------------------------------

	// 色№を指定文に組み込んで返すメソッド
	public String color( int color1, int color2 ) {
		this.setColor( color1, color2 );
		return this.getColor();
	}

//----------------------------------------------------------------------------------------------------------------------

	// 	入力された文を赤字の指定分に組み込んで返すメソッド
	public String redWord( String word ) {
		this.setRedWord( word );
		return this.getRedWord();
	}

//----------------------------------------------------------------------------------------------------------------------

	// 色№をと文を指定文に組み込んで返すメソッド
	public String colorWord( String word, int color ) {
		this.setColor( color );
		this.setColorWord( word );
		return this.getColorWord();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	千切り表示(stripe)用の文を返すメソッド
	public String stripe( String word ) {
		this.setColorWord( word );
		return this.BIGIN + this.getColorWord();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	選択肢を表記
	public void select( String[] select) {

		System.out.print( this.BIGIN + select[0] + " を選んで、" + this.redWord("数値") + " を入力してください\n\n\n\n");

		for( int i = 1; i < select.length; i++ ) {
			System.out.printf( this.SPACE + "\t\t№%3d%s", i, "\t=\t" + select[i] + "\n\n" );
		}

		System.out.print( "\n" + this.BIGIN + "\t\t" + this.redWord("№ ： ") );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ランダムで文字と下地の色を決定（文字と下地が同じ色にはならない）
	public int[] randomColor() {

		int colors = 16;					//	色の種類の数
		int[] color = new int[ 11 ];		//	色の組合せのペアを代入する配列（使用するのは添え字０と10の要素だけ）
											//	[0]に文字色指定番号、[10]に下地色指定番号を格納
		int rnd, tmp = colors;				//	ランダム値と、それを一時的に代入する変数(初期値はランダム値の範囲外)

			//	色指定番号の組み合わせをランダムで生成するループ
				//	（ i を配列の添え字指定と色指定番号の加算に共用）
					//	（ i を10づつ加算して２回のループ）
		for( int i = 0 ; i < 20 ; i += 10 ) {

				//	文字と下地の色指定が重複したらやり直すループ
			do {	//	２回目だけ重複する可能性がある

					//	ランダムで16色の指定番号の素(もと)を生成
				rnd = new java.util.Random().nextInt( colors );

				//	ランダムの数値が重複したらやり直し
			}while( rnd == tmp );

				//	ランダム値をコピー
			tmp = rnd;

				//	ランダム値の前半(0～7)と後半(8～15)で加算する数字を変えて配列に格納
					//	１回目の添え字は[0]、２回目の添え字は[10]
			if( rnd < colors / 2 ) {

					//	前半(0～7)だったら、１回目は30、２回目は40をランダム値に加算して代入
				color[ i ] = rnd + 30 + i;	//	( １回目：30 ～ 37、２回目：40 ～ 47 )
			}else {

					//	後半(8～15)だったら、１回目は82、２回目は92をランダム値に加算して代入
				color[ i ] = rnd + 82 + i;	//	( １回目：90 ～ 97、２回目：100 ～ 107 )
			}
		}
		return color;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ラインを表記
	public void line() {

		String line = "◆";			//	ラインの記号
		int back = 1;				//	ラインに色をつけるのが、文字のみは 1、文字と下地両方は 0
		int range = 80;				//	ライン記号を並べる数

		System.out.println("\n");	//	改行

			//	並べるライン記号の数だけ繰り返すループ
		for( int i = 0; i < range; i++ ) {

				//	色指定番号の組み合わせを取得
			int[] color = this.randomColor();

				//	色を指定してライン記号を表記（添え字が[9]だったら要素の値は"0"なので色が付かない）
			System.out.print( this.color( color[ 10 - back ], color[ 0 ] ) + line + this.RESTORE );
		}
		System.out.println("\n");	//	改行
	}

//----------------------------------------------------------------------------------------------------------------------

	//	入力方法の説明文を表記
	public void inputMethod() {
		System.out.println("\n" + this.BIGIN + "\t\t＊＊＊　" + this.redWord("入力方法") + "　＊＊＊\n\n"
			+  this.BIGIN + "\t動かす駒 の " + this.redWord( "移動前" ) + " - "  + this.redWord( "移動後" ) + " の順番で\n"
			+  this.BIGIN + "\tそれぞれ " + this.redWord( "列" ) + " と " + this.redWord( "行" )+  " の記号を入力します\n"
			+  this.BIGIN + "\t\t例：e7-e5、d2-d4、f8-c5 など\n\n"
			+  this.BIGIN + "\t\t” " + this.colorWord( this.GAME_END, 95 )  + " ”\n"
			+  this.BIGIN + "\tと入力するとゲームを終了します\n");
	}

	//	色を選択するよう表記
	public void colorSelect() {
		String sentence =  "盤 と 駒" ;
		System.out.print("\n" + this.BIGIN + sentence + " の色を、パターンNoの" + this.redWord("数字") + "入力で選択してください\n"
						+ this.BIGIN + "入力無しで ENTER を押せば別の色を表記します : ");
	}

	//	色No.を入力するよう表記
	public void numberSelect() {
		System.out.print( this.BIGIN + "選んだ№の " + this.redWord("数字") + " を入力してください");
	}

	//	入力値が選択肢にないと表記( int を受け取る )
	public void numberOutOfRenge( int input ) {
		System.out.print( this.BIGIN + "選択した№  "+ input +" ですが、" + this.redWord("選択肢にありません") );
	}

	//	入力値が選択肢にないと表記( String を受け取る )
	public void numberOutOfRenge( String input ) {
		System.out.print( this.BIGIN + "入力した "+ input +" ですが、" + this.redWord("選択肢にありません") );
	}

	//	入力値が範囲外の時に表記する文
	public void inputImpermission() {
		System.out.println( this.BIGIN + this.redWord(" 範囲外 ") + "です");
		this.again();
	}

	//	入力が不許可(impermissible)の時に表記する文(Stringを受け取る)
	public void inputImpermission( String sentence ) {
		this.inputString( sentence );
		System.out.println( this.BIGIN + "入力値の" + this.redWord("入力方法が違います") );
		this.again();
	}

	//	コマが存在しない(not exist)時に表記する文
	public void notExist( String sentence ) {
		this.inputString( sentence );
		System.out.println( this.BIGIN + this.redWord("移動させるコマが存在しません") );
		this.again();
	}

	//	移動(move)が不可能(impermissible)の時に表記する文
	public void moveImpermissible( String sentence ) {
		this.inputString( sentence );
		System.out.println( this.BIGIN + this.redWord("その移動はできません") );
		this.again();
	}

	//	インプットされた文を表記
	public void inputString( String sentence ) {
		System.out.println( this.BIGIN + "入力した\t" + this.redWord( sentence ) + "\tですが");
	}

	//	やり直すように表記する文
	public void again() {
		System.out.println( this.BIGIN + "やり直してください\n");
	}

	//	手番と何手目かを表記
	public void turn( String turn, int count ) {
		System.out.println( this.BIGIN + "\t\t" + turn + " の " + count +  " 手目です");
	}

	//	入力をするようにと入力例の表記
	public void moveInput() {
		System.out.print("\n\t\t\t行と列で移動前の駒と移動先を指定してください ： ");
	}

	//	盤を反転(reversal)させた時に表記する文
	public void reversal() {
		System.out.println( this.SPACE + "\t\t盤を反転させました");
	}

	//	Enterキーを押す(push)よう表記して押されるまで待機するメソッド
	public void pushEnter() {
		System.out.print( this.BIGIN + "\t\tENTER を押してください");
		String enter = new java.util.Scanner( System.in ).nextLine();
	}

	//	Enterキーを押す(push)よう表記して押されるまで待機して仕切り線を引くメソッド
	public void pushEnterAndLine() {
		this.pushEnter();
		this.line();
	}

//----------------------------------------------------------------------------------------------------------------------

	//	移動法によって表示を分岐させるメソッド
	public void move( String whatDid, String name, char[] charType ) {

		switch( whatDid ) {
			case "normal":
				this.normal( name, charType );
					break;
			case "castling":
				this.castling( charType );
					break;
			case "enPassant":
				this.enPassant( charType );
		}
	}

	//	キャスリングをしたと表示
	public void castling( char[] charType ) {

		String side = "";

		switch( charType[3] ) {
			case 'g':
				side = "キングサイド";
					break;
			case 'c':
				side = "クイーンサイド";
		}

		System.out.print( this.before( charType ) + "King と " + side + " の Rook を\n" + this.SPACE );
		System.out.print( this.redWord( side + "キャスリング" ) + " させました。\n" );
	}

	//	アンパッサンをしたと表示
	public void enPassant( char[] charType ) {
		System.out.print( this.before( charType ) + "Pawn" + this.after( charType ) + " " );
		System.out.print( this.redWord( "アンパッサン" ) + " させました。\n");
	}

	public void normal( String name, char[] charType ) {
		System.out.print( this.before( charType ) + name + this.after( charType ) + "移動しました。\n");
	}

	//	移動前の位置を表示
	public String before( char[] charType ) {
		return "\n\n" + this.BIGIN + charType[ 0 ] + charType[ 1 ] + " の ";
	}

	//	移動後の位置を表示
	public String after( char[] charType ) {
		return " を " + charType[ 3 ] + charType[ 4 ] + " に ";
	}

	//	プロモーション結果の表示
 	public void promotion( String type ) {
		System.out.println("\n\n" + this.BIGIN + "Pawn を " + type + " に " + this.redWord("プロモーション") + " しました");
	}

//----------------------------------------------------------------------------------------------------------------------

 	//	タイトルを表記
 	public void chess() {

 		int color[] = this.randomColor();
 		this.color( color[0], color[10] );

 		System.out.print( "\n\n"
 			+ this.stripe( "                                                                  " )
 			+ this.stripe( "                                                                  " )
 			+ this.stripe( "    ■■■■■  ■      ■  ■■■■■  ■■■■■  ■■■■■    " )
 			+ this.stripe( "    ■          ■      ■  ■          ■          ■            " )
 			+ this.stripe( "    ■          ■      ■  ■          ■          ■            " )
 			+ this.stripe( "    ■          ■■■■■  ■■■■■  ■■■■■  ■■■■■    " )
 			+ this.stripe( "    ■          ■      ■  ■                  ■          ■    " )
 			+ this.stripe( "    ■          ■      ■  ■                  ■          ■    " )
 			+ this.stripe( "    ■■■■■  ■      ■  ■■■■■  ■■■■■  ■■■■■    " )
 			+ this.stripe( "                                                                  " )
 			+ this.stripe( "                                                                  " )
 			+ "\n\n\n\n\n");
 	}

//----------------------------------------------------------------------------------------------------------------------

	//	ゲーム開始を表記
	public void gameStart() {

		int color[] = this.randomColor();
		this.color( color[0], color[10] );

		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n"
			+ this.stripe( "                                                                  " )
			+ this.stripe( "                                                                  " )
			+ this.stripe( "        ■■■■■      ■      ■      ■  ■■■■■            " )
			+ this.stripe( "        ■            ■  ■    ■■  ■■  ■                    " )
			+ this.stripe( "        ■          ■      ■  ■  ■  ■  ■                    " )
			+ this.stripe( "        ■    ■■  ■      ■  ■      ■  ■■■■■            " )
			+ this.stripe( "        ■      ■  ■■■■■  ■      ■  ■                    " )
			+ this.stripe( "        ■      ■  ■      ■  ■      ■  ■                    " )
			+ this.stripe( "        ■■■■■  ■      ■  ■      ■  ■■■■■            " )
			+ this.stripe( "                                                                  " )
			+ this.stripe( "    ■■■■■  ■■■■■      ■      ■■■■    ■■■■■    " )
			+ this.stripe( "    ■              ■        ■  ■    ■      ■      ■        " )
			+ this.stripe( "    ■              ■      ■      ■  ■      ■      ■        " )
			+ this.stripe( "    ■■■■■      ■      ■      ■  ■■■■        ■        " )
			+ this.stripe( "            ■      ■      ■■■■■  ■  ■          ■        " )
			+ this.stripe( "            ■      ■      ■      ■  ■    ■        ■        " )
			+ this.stripe( "    ■■■■■      ■      ■      ■  ■      ■      ■        " )
			+ this.stripe( "                                                                  " )
			+ this.stripe( "                                                                  " )
			+ "\n\n" );
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ゲーム終了の表記
	public void gameEnd() {

		int color[] = this.randomColor();
		this.color( color[0], color[10] );

		System.out.print( "\n\n\n\n"
			+ this.stripe( "                                                      " )
			+ this.stripe( "                                                      " )
			+ this.stripe( "    ■■■■■      ■      ■      ■  ■■■■■    " )
			+ this.stripe( "    ■            ■  ■    ■■  ■■  ■            " )
			+ this.stripe( "    ■    ■■  ■      ■  ■  ■  ■  ■■■■■    " )
			+ this.stripe( "    ■      ■  ■■■■■  ■      ■  ■            " )
			+ this.stripe( "    ■■■■■  ■      ■  ■      ■  ■■■■■    " )
			+ this.stripe( "                                                      " )
			+ this.stripe( "          ■■■■■  ■      ■  ■■■■            " )
			+ this.stripe( "          ■          ■■    ■  ■      ■          " )
			+ this.stripe( "          ■■■■■  ■  ■  ■  ■      ■          " )
			+ this.stripe( "          ■          ■    ■■  ■      ■          " )
			+ this.stripe( "          ■■■■■  ■      ■  ■■■■            " )
			+ this.stripe( "                                                      " )
			+ this.stripe( "                                                      " )
			+ "\n\n\n\n" );
	}
}