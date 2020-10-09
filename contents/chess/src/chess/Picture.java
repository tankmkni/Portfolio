package chess;


//	ドット絵を表記するクラス
public class Picture extends Message {

//----------------------------------------------------------------------------------------------------------------------

	//	指定された色を、指定された数だけ横に一列並べる
	public String pixel( int color, int num ) {
		this.setColor( color );
		String pic = "";
		for( int i = 0 ; i < num ; i++ ) {
			pic += "  ";
		}
		return this.getColor() + pic + this.RESTORE;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	受け取った配列から色番号と表記数を取り出して、横に一列を（ランダムで）指定された数だけ並べて表記する
	public void pixels( int[][] pixels, int num ) {
		for( int i = 0 ; i < num ; i++ ) {
			System.out.print( this.SPACE );
			for( int j = 0 ; j < pixels[0].length ; j++ ) {
				System.out.print( this.pixel( pixels[0][j], pixels[1][j] ) );
			}
		}
		System.out.println("");
	}

//----------------------------------------------------------------------------------------------------------------------

	//	横に並べて絵を描く
	public void lineUp() {

		int whatLine = 33;

		System.out.println("");

		for( int i = 0 ; i < whatLine ; i++ ) {

			this.oneLine( this.cat(), i );
			this.oneLine( this.catInBox(), i );
			this.oneLine( this.totoro(), i );

			System.out.println("");
		}

	}

//----------------------------------------------------------------------------------------------------------------------

	//	横に並べて絵を描く
	public void onePage( int[][] picture ) {

		System.out.println("");

		for( int i = 0 ; i < picture.length ; i++ ) {

			this.oneLine( picture, i );

			System.out.println("");
		}

	}

//----------------------------------------------------------------------------------------------------------------------

	//	一行を描く
	public void oneLine( int[][] pic, int row ) {

		System.out.print( this.SPACE );

		for( int i = 0 ; i < pic[ row ].length ; i++ ) {
			System.out.print( this.pixel( pic[ row ][ i ], 1 ) );
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	public int[][] totoro() {

		int[] color = { 103, 47, 100, 107, 40,   102, 42 };

		int c1 = color[ 0 ];	//	下地
		int c2 = color[ 1 ];	//	本体
		int c3 = color[ 2 ];	//	ヒゲ
		int c4 = color[ 3 ];	//	白
		int c5 = color[ 4 ];	//	黒

		int c6 = color[ 5 ];	//	緑
		int c7 = color[ 6 ];	//	緑

		int[][] totoro = {
			 { c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c2,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c2,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c2,c2, c1,c1,c7,c7,c7, c7,c7,c1,c1,c2, c2,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c2,c2, c1,c6,c7,c7,c7, c6,c7,c7,c1,c2, c2,c1,c1,c1,c1, c1,c1,c1,c1 }

			,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c2, c2,c2,c6,c7,c6, c6,c6,c2,c2,c2, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c2,c2, c2,c2,c2,c2,c2, c2,c2,c2,c2,c2, c2,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c2,c2,c4, c4,c4,c2,c2,c2, c2,c2,c4,c4,c4, c2,c2,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c2,c2,c4, c5,c4,c2,c3,c3, c3,c2,c4,c5,c4, c2,c2,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c3, c3,c3,c3,c2,c4, c4,c4,c2,c2,c2, c2,c2,c4,c4,c4, c2,c3,c3,c3,c3, c1,c1,c1,c1 }

			,{ c1,c1,c1,c1,c1, c1,c2,c2,c2,c2, c2,c2,c2,c2,c2, c2,c2,c2,c2,c2, c2,c2,c2,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c3, c3,c3,c3,c2,c2, c2,c2,c2,c2,c2, c2,c2,c2,c2,c2, c2,c3,c3,c3,c3, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c2,c2,c2,c2,c2, c4,c4,c4,c4,c4, c4,c4,c4,c4,c2, c2,c2,c2,c2,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c2,c2,c2,c4,c4, c4,c4,c4,c4,c2, c4,c4,c4,c4,c4, c4,c2,c2,c2,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c2, c2,c2,c4,c4,c2, c4,c4,c4,c2,c4, c2,c4,c4,c4,c2, c4,c4,c2,c2,c2, c1,c1,c1,c1 }

			,{ c1,c1,c1,c1,c2, c2,c4,c4,c2,c4, c2,c4,c4,c4,c4, c4,c4,c4,c2,c4, c2,c4,c4,c2,c2, c1,c1,c1,c1 }
			,{ c1,c1,c1,c2,c2, c2,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c2,c2, c2,c1,c1,c1 }
			,{ c1,c1,c1,c2,c2, c4,c4,c4,c4,c4, c4,c2,c4,c4,c4, c4,c4,c2,c4,c4, c4,c4,c4,c4,c2, c2,c1,c1,c1 }
			,{ c1,c1,c1,c2,c2, c4,c4,c2,c4,c4, c2,c4,c2,c4,c4, c4,c2,c4,c2,c4, c4,c2,c4,c4,c2, c2,c1,c1,c1 }
			,{ c1,c1,c2,c2,c2, c4,c2,c4,c2,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c2,c4,c2,c4,c2, c2,c2,c1,c1 }

			,{ c1,c1,c2,c2,c2, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c2, c2,c2,c1,c1 }
			,{ c1,c1,c2,c2,c2, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c2, c2,c2,c1,c1 }
			,{ c1,c1,c3,c2,c2, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c2, c2,c3,c1,c1 }
			,{ c1,c1,c1,c3,c2, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c2, c3,c1,c1,c1 }
			,{ c1,c1,c1,c1,c2, c2,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c2,c2, c1,c1,c1,c1 }

			,{ c1,c1,c1,c1,c2, c2,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c2,c2, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c2, c2,c2,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c4,c2,c2,c2, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c2,c2,c2,c4,c4, c4,c4,c4,c4,c4, c4,c4,c4,c4,c4, c4,c2,c2,c2,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c2,c2,c2,c2,c2, c2,c4,c4,c4,c4, c4,c4,c4,c2,c2, c2,c2,c2,c2,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c2,c2,c2,c2, c2,c2,c2,c2,c2, c2,c2,c2,c2,c2, c2,c2,c2,c1,c1, c1,c1,c1,c1 }

			,{ c1,c1,c1,c1,c1, c1,c1,c3,c2,c3, c2,c3,c2,c2,c1, c2,c2,c3,c2,c3, c2,c3,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
			,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
		};


		return totoro;
	}

//----------------------------------------------------------------------------------------------------------------------

	public int[][] catInBox() {

		int[] color = { 105, 40, 107, 106, 101,   103, 47, 43};

		int c1 = color[ 0 ];	//	下地
		int c2 = color[ 1 ];	//	輪郭
		int c3 = color[ 2 ];	//	猫
		int c4 = color[ 3 ];	//	猫模様
		int c5 = color[ 4 ];	//	口

		int c6 = color[ 5 ];	//	ダンボール
		int c7 = color[ 6 ];	//	ダンボール内側
		int c8 = color[ 7 ];	//	ダンボール角

		int[][] catInBox = {
	 { c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c2,c2,c2, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c2,c2,c7,c2,c3, c2,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }

	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c2,c2, c7,c7,c7,c2,c3, c2,c2,c2,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c2,c1,c1,c1,c1, c1,c2,c2,c7,c7, c7,c7,c7,c2,c3, c2,c7,c7,c2,c2, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c2, c3,c2,c1,c1,c1, c2,c4,c2,c7,c7, c2,c2,c2,c2,c3, c2,c7,c7,c7,c7, c2,c2,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c2, c3,c3,c2,c2,c2, c4,c4,c2,c2,c2, c3,c3,c3,c3,c3, c2,c7,c7,c2,c2, c6,c6,c2,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c2, c3,c3,c3,c4,c4, c4,c4,c2,c3,c3, c3,c3,c3,c3,c3, c3,c2,c2,c6,c6, c6,c6,c6,c2,c1, c1,c1,c1,c1 }

	,{ c1,c1,c1,c2,c3, c3,c3,c3,c4,c4, c4,c4,c2,c3,c3, c3,c3,c3,c3,c2, c2,c6,c6,c6,c6, c6,c6,c6,c6,c2, c1,c1,c1,c1 }
	,{ c1,c1,c1,c2,c3, c2,c3,c3,c4,c2, c4,c4,c2,c3,c3, c3,c3,c2,c2,c6, c6,c6,c6,c6,c6, c6,c6,c6,c6,c6, c2,c1,c1,c1 }
	,{ c1,c1,c1,c2,c3, c3,c3,c2,c3,c4, c4,c4,c4,c3,c3, c3,c2,c6,c8,c2, c6,c6,c6,c6,c6, c6,c6,c6,c2,c2, c1,c1,c1,c1 }
	,{ c1,c1,c1,c2,c3, c3,c2,c5,c2,c3, c4,c4,c3,c3,c3, c3,c2,c6,c8,c6, c2,c6,c6,c6,c6, c6,c2,c2,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c2,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c2,c6,c8,c6, c6,c2,c6,c6,c2, c2,c6,c2,c1,c1, c1,c1,c1,c1 }

	,{ c1,c1,c1,c1,c2, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c2,c6,c6,c8,c6, c6,c6,c2,c2,c6, c6,c6,c2,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c2,c3,c3,c3,c3, c3,c3,c3,c3,c3, c2,c6,c6,c8,c6, c6,c6,c6,c6,c6, c6,c6,c2,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c2,c3,c3,c3,c3, c3,c3,c3,c3,c3, c2,c6,c6,c8,c6, c6,c6,c6,c6,c6, c6,c6,c2,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c2,c3,c2,c2,c2, c3,c2,c3,c2,c2, c6,c6,c6,c8,c6, c6,c6,c6,c6,c6, c2,c2,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c2,c3,c2,c6,c2, c3,c2,c2,c6,c6, c6,c6,c6,c8,c6, c6,c6,c6,c2,c2, c1,c1,c1,c1,c1, c1,c1,c1,c1 }

	,{ c1,c1,c1,c1,c2, c6,c2,c6,c6,c2, c3,c2,c6,c2,c2, c6,c6,c6,c8,c6, c6,c2,c2,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c2,c6, c6,c6,c6,c6,c2, c3,c2,c6,c6,c6, c2,c2,c6,c8,c2, c2,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c2, c2,c6,c6,c6,c6, c2,c6,c6,c6,c2, c1,c1,c2,c2,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c2,c2,c6,c6, c6,c6,c6,c2,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c2,c2, c6,c6,c2,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }

	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c2,c2,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1,c1, c1,c1,c1,c1 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }

	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }

	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
	,{ c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3,c3, c3,c3,c3,c3 }
		};

		return catInBox;
	}

//----------------------------------------------------------------------------------------------------------------------

	public int[][] cat() {

		int[] color = { 102, 40, 107, 106, 101};

		int c1 = color[ 0 ];	//	下地
		int c2 = color[ 1 ];	//	輪郭
		int c3 = color[ 2 ];	//	本体
		int c4 = color[ 3 ];	//	模様
		int c5 = color[ 4 ];	//	口

		int[][] cat = {
						 { c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c2,c1,c1,c1,c1,c1,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c2,c3,c2,c1,c1,c1,c2,c4,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c2,c3,c3,c2,c2,c2,c4,c4,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }

						,{ c1,c1,c1,c2,c3,c3,c3,c4,c4,c4,c4,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c2,c3,c3,c3,c3,c4,c4,c4,c4,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c2,c3,c2,c3,c3,c4,c2,c4,c4,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c2,c3,c3,c3,c2,c3,c4,c4,c4,c4,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c2,c3,c3,c2,c5,c2,c3,c3,c3,c3,c2,c1,c1,c1,c1,c1,c1,c1,c1,c1 }

						,{ c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c1,c1,c1,c2,c2,c1,c1 }
						,{ c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c1,c2,c3,c2,c1,c1 }
						,{ c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c2,c3,c2,c1,c1,c1 }
						,{ c1,c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c3,c2,c1,c1,c1,c1 }

						,{ c1,c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c2,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c3,c2,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c1,c2,c3,c3,c3,c3,c3,c3,c3,c3,c2,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c1,c1,c2,c2,c2,c2,c2,c2,c2,c2,c1,c1,c1,c1,c1,c1,c1,c1 }

						,{ c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1,c1 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }

						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }

						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						,{ c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3,c3 }
						};

		return cat;
	}

//----------------------------------------------------------------------------------------------------------------------

	public void slime2() {

		int[] color = { 40, 107, 101, 100, 47};	//メタル
//		int[] color = { 40, 107, 101, 104, 104};	//ブルー
//		int[] color = { 40, 107, 101, 102, 102};	//グリーン
//		int[] color = { 40, 107, 101, 105, 105};	//ピンク

		int c1 = color[ 0 ];	//	輪郭
		int c2 = color[ 1 ];	//	白
		int c3 = color[ 2 ];	//	口
		int c4 = color[ 3 ];	//	本体
		int c5 = color[ 4 ];	//	影

		int rnd = new java.util.Random().nextInt(10) + 1;

		System.out.println("");

		int[][] pixels = { { c2, c1, c2 },{ 7, 1, 7 } };
		this.pixels( pixels, rnd );

		for( int i = 0 ; i < 3 ; i++ ) {
			pixels = new int[][] { { c2, c1, c5, c1, c2 },{ 6, 1, 1, 1, 6 } };
			this.pixels( pixels, rnd );
		}

		pixels = new int[][] { { c2, c1, c5, c4, c5, c1, c2 },{ 5, 1, 1, 1, 1, 1, 5 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c2, c1, c2, c4, c5, c1, c2 },{ 3, 2, 2, 2, 1, 2, 3 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c2, c1, c2, c4, c5, c1, c2 },{ 2, 1, 3, 4, 2, 1, 2 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c2, c1, c2, c4, c5, c1, c2 },{ 1, 1, 2, 8, 1, 1, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c1, c5, c4, c2, c4, c2, c4, c1 },{ 1, 2, 2, 1, 3, 1, 4, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c1, c5, c4, c2, c1, c2, c4, c2, c1, c2, c4, c1 },{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c1, c5, c4, c2, c4, c2, c4, c1 },{ 1, 1, 3, 1, 3, 1, 4, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c1, c5, c4, c3, c4, c3, c4, c1 },{ 1, 1, 2, 1, 5, 1, 3, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c2, c1, c5, c4, c3, c4, c1, c2 },{ 1, 1, 1, 2, 5, 3, 1, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c2, c1, c4, c1, c2 },{ 2, 2, 7, 2, 2 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { c2, c1, c2 },{ 4, 7, 4 } };
		this.pixels( pixels, rnd );
	}


//----------------------------------------------------------------------------------------------------------------------

	public void slime() {

		int rnd = new java.util.Random().nextInt(10) + 1;

		System.out.println("");

		int[][] pixels = { { 107, 40, 0 },{ 7, 1, 7 } };
		this.pixels( pixels, rnd );

		for( int i = 0 ; i < 3 ; i++ ) {
			pixels = new int[][] { { 0, 40, 106, 40, 0 },{ 6, 1, 1, 1, 6 } };
			this.pixels( pixels, rnd );
		}

		pixels = new int[][] { { 0, 40, 106, 40, 0 },{ 5, 1, 3, 1, 5 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 0, 40, 106, 40, 0 },{ 3, 2, 5, 2, 3 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 0, 40, 106, 40, 0 },{ 2, 1, 9, 1, 2 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 0, 40, 106, 40, 0 },{ 1, 1, 11, 1, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 40, 106, 107, 106, 107, 106, 40 },{ 1, 4, 1, 3, 1, 4, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 40, 106, 107, 40, 107, 106, 107, 40, 107, 106, 40 },{ 1, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 40, 106, 107, 106, 107, 106, 40 },{ 1, 4, 1, 3, 1, 4, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 40, 106, 101, 106, 101, 106, 40 },{ 1, 3, 1, 5, 1, 3, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 0, 40, 106, 101, 106, 40, 0 },{ 1, 1, 3, 5, 3, 1, 1 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 0, 40, 106, 40, 0 },{ 2, 2, 7, 2, 2 } };
		this.pixels( pixels, rnd );

		pixels = new int[][] { { 0, 40, 0 },{ 4, 7, 4 } };
		this.pixels( pixels, rnd );
	}

//----------------------------------------------------------------------------------------------------------------------

	public void mario() {

		System.out.print( ""
				+ this.BIGIN + this.pixel( 0, 3 ) + this.pixel( 101, 5 )
				+ this.BIGIN + this.pixel( 0, 2 ) + this.pixel( 101, 10 )
				+ this.BIGIN + this.pixel( 0, 2 ) + this.pixel( 41, 3 ) + this.pixel( 103, 2 )
				+ this.pixel( 41, 1 ) + this.pixel( 103, 1 )
				+ this.BIGIN + this.pixel( 0, 1 ) + this.pixel( 41, 1 ) + this.pixel( 103, 1 ) + this.pixel( 41, 1 )
				+ this.pixel( 103, 3 ) + this.pixel( 41, 1 ) + this.pixel( 103, 3 )
				+ this.BIGIN + this.pixel( 0, 1 ) + this.pixel( 41, 1 ) + this.pixel( 103, 1 ) + this.pixel( 41, 2 )
				+ this.pixel( 103, 3 ) + this.pixel( 41, 1 ) + this.pixel( 103, 3 )
				+ this.BIGIN + this.pixel( 0, 1 ) + this.pixel( 41, 2 ) + this.pixel( 103, 4 ) + this.pixel( 41, 4 )
				+ this.BIGIN + this.pixel( 0, 3 ) + this.pixel( 103, 7 )
				+ this.BIGIN + this.pixel( 0, 2 ) + this.pixel( 41, 2 ) + this.pixel( 101, 1 ) + this.pixel( 41, 3 )
				+ this.BIGIN + this.pixel( 0, 1 ) + this.pixel( 41, 3 ) + this.pixel( 101, 1 ) + this.pixel( 41, 2 )
				+ this.pixel( 101, 1 ) + this.pixel( 41, 3 )
				+ this.BIGIN + this.pixel( 41, 4 ) + this.pixel( 101, 4 ) + this.pixel( 41, 4 )
				+ this.BIGIN + this.pixel( 103, 2 ) + this.pixel( 41, 1 ) + this.pixel( 101, 1 ) + this.pixel( 103, 1 )
				+ this.pixel( 101, 2 ) + this.pixel( 103, 1 ) + this.pixel( 101, 1 ) + this.pixel( 41, 1 )
				+ this.pixel( 103, 2 )
				+ this.BIGIN + this.pixel( 103, 3 ) + this.pixel( 101, 6 ) + this.pixel( 103, 3 )
				+ this.BIGIN + this.pixel( 103, 2 ) + this.pixel( 101, 8 ) + this.pixel( 103, 2 )
				+ this.BIGIN + this.pixel( 0, 2 ) + this.pixel( 101, 3 ) + this.pixel( 0, 2 ) + this.pixel( 101, 3 )
				+ this.BIGIN + this.pixel( 0, 1 ) + this.pixel( 41, 3 ) + this.pixel( 0, 4 ) + this.pixel( 41, 3 )
				+ this.BIGIN + this.pixel( 41, 4 ) + this.pixel( 0, 4 ) + this.pixel( 41, 4 )
				+ "\n" );
	}

//----------------------------------------------------------------------------------------------------------------------

	public void mario2() {

		int c1 = 0;
		int c2 = 102;
		int c3 = 41;
		int c4 = 103;


		System.out.print( ""
			+ this.BIGIN + this.pixel( c1, 3 ) + this.pixel( c2, 5 )
			+ this.BIGIN + this.pixel( c1, 2 ) + this.pixel( c2, 10 )
			+ this.BIGIN + this.pixel( c1, 2 ) + this.pixel( c3, 3 ) + this.pixel( c4, 2 )
							+ this.pixel( c3, 1 ) + this.pixel( c4, 1 )
			+ this.BIGIN + this.pixel( c1, 1 ) + this.pixel( c3, 1 ) + this.pixel( c4, 1 ) + this.pixel( c3, 1 )
							+ this.pixel( c4, 3 ) + this.pixel( c3, 1 ) + this.pixel( c4, 3 )
			+ this.BIGIN + this.pixel( c1, 1 ) + this.pixel( c3, 1 ) + this.pixel( c4, 1 ) + this.pixel( c3, 2 )
							+ this.pixel( c4, 3 ) + this.pixel( c3, 1 ) + this.pixel( c4, 3 )
			+ this.BIGIN + this.pixel( c1, 1 ) + this.pixel( c3, 2 ) + this.pixel( c4, 4 ) + this.pixel( c3, 4 )
			+ this.BIGIN + this.pixel( c1, 3 ) + this.pixel( c4, 7 )
			+ this.BIGIN + this.pixel( c1, 2 ) + this.pixel( c3, 2 ) + this.pixel( c2, 1 ) + this.pixel( c3, 3 )
			+ this.BIGIN + this.pixel( c1, 1 ) + this.pixel( c3, 3 ) + this.pixel( c2, 1 ) + this.pixel( c3, 2 )
							+ this.pixel( c2, 1 ) + this.pixel( c3, 3 )
			+ this.BIGIN + this.pixel( c3, 4 ) + this.pixel( c2, 4 ) + this.pixel( c3, 4 )
			+ this.BIGIN + this.pixel( c4, 2 ) + this.pixel( c3, 1 ) + this.pixel( c2, 1 ) + this.pixel( c4, 1 )
							+ this.pixel( c2, 2 ) + this.pixel( c4, 1 ) + this.pixel( c2, 1 ) + this.pixel( c3, 1 )
							+ this.pixel( c4, 2 )
			+ this.BIGIN + this.pixel( c4, 3 ) + this.pixel( c2, 6 ) + this.pixel( c4, 3 )
			+ this.BIGIN + this.pixel( c4, 2 ) + this.pixel( c2, 8 ) + this.pixel( c4, 2 )
			+ this.BIGIN + this.pixel( c1, 2 ) + this.pixel( c2, 3 ) + this.pixel( c1, 2 ) + this.pixel( c2, 3 )
			+ this.BIGIN + this.pixel( c1, 1 ) + this.pixel( c3, 3 ) + this.pixel( c1, 4 ) + this.pixel( c3, 3 )
			+ this.BIGIN + this.pixel( c3, 4 ) + this.pixel( c1, 4 ) + this.pixel( c3, 4 )
			+ "\n" );
	}
}