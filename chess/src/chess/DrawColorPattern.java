package chess;

import chessman.Chessman;

//	チェス盤の色のパターンを選ぶクラス(非常に複雑)
public class DrawColorPattern extends Draw{

//----------------------------------------------------------------------------------------------------------------------

	//	フィールド

		//	配色パターンの色番号を格納する２次元配列
	final int[][] COLORS =	{{40,41,42,43,44,45,46,47,100,101,102,103,104,105,106,107}	//	下地（盤）の色番号
							,{30,31,32,33,34,35,36,37,90 ,91 ,92 ,93 ,94 ,95 ,96 ,97}};	//	文字（駒）の色番号

		//	横に並べる範囲(renge)を決める変数
	final int ALL_RANGE = this.COLORS[0].length - 1;

		//	表示するパターン数を決める変数
	final int CHOICE_PATTERN = 16;

		//	横に何パターンづつ並べるかを決める変数
	final int CHOICE_RANGE = 8;

		// 	重複しない組み合わせ(combination)の数（ 計算式 ⇒ ( ㎡ - ｍ ) ÷ ２ )（ m = 組み合わせる数字の個数 ）
		//	16個の数字の重複しない組み合わせは、( (16 × 16) - 16 ) ÷ 2 = 120 通りになる
	final int COMBINATION = (this.COLORS[0].length * this.COLORS[0].length - this.COLORS[0].length) / 2;

		//	全ての組合せを格納する配列
	private int[][] colorPattern;

		//	組合せの一部(prat)を格納する配列
	private int[][] colorPatternPrat;

//----------------------------------------------------------------------------------------------------------------------

	//	コンストラクタ
	public DrawColorPattern() {
		super();
		this.colorPattern = new int[this.COMBINATION][2];
		this.colorPatternPrat = new int[this.CHOICE_PATTERN][4];
	}

//----------------------------------------------------------------------------------------------------------------------

	//	ゲッター
	public int[][] getColorPattern() {
		return colorPattern;
	}
	public int[] getColorPattern(int color) {
		color--;
		int[] handOver = new int[getColorPattern()[color].length];
		for(int i = 0; i < getColorPattern()[color].length; i++) {
			handOver[i] = this.getColorPattern(color, i);
		}
		return handOver;
	}
	public int getColorPattern(int index1,int index2) {
		return colorPattern[index1][index2];
	}

	public int[][] getColorPatternPrat() {
		return colorPatternPrat;
	}
	public int[] getColorPatternPrat(int color) {
		color--;
		int[] handOver = new int[getColorPatternPrat()[0].length];
		for(int i = 0; i < getColorPatternPrat()[color].length; i++) {
			handOver[i] = this.getColorPatternPrat(color, i);
		}
		return handOver;
	}
	public int getColorPatternPrat(int index1,int index2) {
		return colorPatternPrat[index1][index2];
	}

	//	セッター
	public void setColorPattern(int[][] colorPattern) {
		this.colorPattern = colorPattern;
	}
	public void setColorPattern(int index1, int index2,int element) {
		this.colorPattern[index1][index2] = element;
	}

	public void setColorPatternPrat(int[][] colorPatternPrat) {
		this.colorPatternPrat = colorPatternPrat;
	}
	public void setColorPatternPrat(int index1, int index2,int element) {
		this.colorPatternPrat[index1][index2] = element;
	}

//----------------------------------------------------------------------------------------------------------------------

	//	盤だけのパターンを表記させるメソッド
	public void boardSelect(Chessman[][] board) {
		int index = 0;
		this.drawgGradually(board,index);
	}

//----------------------------------------------------------------------------------------------------------------------

	//	盤の色は固定で駒だけのパターンを表記させるメソッド
	public void pieceSelect(Chessman[][] board, int[] colorNominate) {
		int index = 1;
		this.setBackColor1Sentence(colorNominate[2]);
		this.setBackColor2Sentence(colorNominate[3]);
		this.drawgGradually(board,index);
	}

//----------------------------------------------------------------------------------------------------------------------

	//チェス盤を段々(gradually)で表記するメソッド
	public void drawgGradually( Chessman[][] board, int index ) {

		int paragraph;							//	段落（paragraph）がいくつ目かを知るための変数
		int decrease = 0;						//	範囲を減らす（Decrease）していく変数
		int colorIndex = this.COMBINATION;		//	色番号の添え字を調整する変数

		this.combinationSet(index);

			//	表記する総段落（paragraph）数を決めるスイッチ
		switch(this.COMBINATION % this.ALL_RANGE) {
			case 0:
				paragraph =	this.COMBINATION / this.ALL_RANGE;
				break;
			default:
				paragraph =	this.COMBINATION / this.ALL_RANGE + 1;
		}

		paragraph += (paragraph - 1);

			//	全てのパターンを段落に分けて表記するループ
		for(int i = 0; i < paragraph; i++) {
			System.out.print("\n");

			//	通しNo.と色番号のセットを表記
			for(int j = 0; j < this.ALL_RANGE - decrease; j++) {

				System.out.printf("№%3d (%3d,%3d)  "
						,this.COMBINATION - (colorIndex - (this.ALL_RANGE - decrease -j - 1)) + 1
						,this.getColorPattern(this.COMBINATION - (colorIndex - (this.ALL_RANGE - decrease -j - 1)), 1)
						,this.getColorPattern(this.COMBINATION - (colorIndex - (this.ALL_RANGE - decrease -j - 1)), 0));

				//	パターンの最後だったら、ループ	終了
				if(j + this.ALL_RANGE * i + 1 == this.COMBINATION) {
					break;
				}
			}

				//	段落毎に表記するループ
			for(int j = board[0].length - 1; j >= 0; j--) {		//	盤の行の数（８回）だけ”降順”で回すループ

					//	コマを表示する時のみ、上下２行づつ以外は省略
				if(index == 1 && j < board[0].length - 2 && j > 1) {

//					//	２行だけで表記
//				if( j < board[0].length - 1 && j > 0) {
					continue;
				}

				System.out.print("\n");

					//	行毎に表記するループ
//				for(int k = 0; k < this.RANGE - rangeDecrease ; k++){		//	昇順で表記
				for(int k = this.ALL_RANGE - 1 - decrease; k >= 0 ; k--){	//	降順で表記

						//	カラーナンバーを代入
					switch(index) {
						case 0:
							this.setBackColor1Sentence(this.getColorPattern(this.COMBINATION - (colorIndex - k), 1));
							this.setBackColor2Sentence(this.getColorPattern(this.COMBINATION - (colorIndex - k), 0));
								break;
						case 1:
							this.setPlayer1ColorSentence(this.getColorPattern(this.COMBINATION - (colorIndex - k), 1));
							this.setPlayer2ColorSentence(this.getColorPattern(this.COMBINATION - (colorIndex - k), 0));
					}

						//	盤の列の数（８回）だけ”降順”で回すループ
					for(int m = board.length - 1; m >= 0; m--) {

						this.classify(board,j,m);	//	マス目の表記
					}

					System.out.print(" ");		//	盤上部と左端の空白確保

						//	パターンの最後だったら、ループ終了
					if(k + this.ALL_RANGE * i + 1 == this.COMBINATION) {
						break;
					}
				}
			}
			colorIndex = colorIndex - (this.ALL_RANGE  - decrease);
			System.out.println("");
			decrease++;
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//チェス盤を規則正しく(Regular)表記するメソッド
	public void drawRegular(Chessman[][] board) {

		int paragraph;		//	段落(paragraph)数を代入する変数

		this.choiseCombinationSet();		//	色指定番号の組み合わせ(Combination)を配列に代入するメソッドの呼び出し


			//	段落の数を決めるスイッチ文
		switch(this.colorPatternPrat.length % CHOICE_RANGE) {
			case 0:
				paragraph =	this.colorPatternPrat.length / CHOICE_RANGE;
					break;
			default:
				paragraph =	this.colorPatternPrat.length / CHOICE_RANGE + 1;
		}

			//	段落毎に表記するループ
		for(int i = 0; i < paragraph; i++) {
			System.out.print("\n");

				//	パターンNo.を表記するループ
			for(int j = 0; j < CHOICE_RANGE; j++) {
				System.out.printf("№%2d             ",j + CHOICE_RANGE * i + 1);

				if(j + CHOICE_RANGE * i +1 == this.colorPatternPrat.length) {
					break;
				}
			}

				//
			for(int j = board[0].length - 1; j >= 0; j--) {		//	盤の行の数（８回）だけ”降順”で回すループ
				System.out.print("\n");

					//	選んだ数づつ盤を表記していくループ
				for(int k= 0; k < CHOICE_RANGE; k++){

					this.setPlayer1ColorSentence(this.getColorPatternPrat(k + CHOICE_RANGE * i, 3));
					this.setPlayer2ColorSentence(this.getColorPatternPrat(k + CHOICE_RANGE * i, 2));
					this.setBackColor1Sentence(this.getColorPatternPrat(k + CHOICE_RANGE * i, 1));
					this.setBackColor2Sentence(this.getColorPatternPrat(k + CHOICE_RANGE * i, 0));

						//盤の列の数だけ表記するループ
					for(int m = board.length - 1; m >= 0; m--) {	//	盤の列の数（８回）だけ”降順”で回すループ
						classify(board,j,m);						//	縦の罫線とマス目の表記
					}

					System.out.print(" ");		//	盤上部と左端の空白確保

					if(k + CHOICE_RANGE * i +1 == this.colorPatternPrat.length) {
						break;
					}
				}
			}
			System.out.print("\n");

			//	４つの色指定番号を表記するループ
			for(int j = 0; j < CHOICE_RANGE; j++) {
				System.out.printf("%3d,%3d,%3d,%3d  "	,this.getColorPatternPrat(j + CHOICE_RANGE * i, 3)
														,this.getColorPatternPrat(j + CHOICE_RANGE * i, 2)
														,this.getColorPatternPrat(j + CHOICE_RANGE * i, 1)
														,this.getColorPatternPrat(j + CHOICE_RANGE * i, 0));

				if(j + CHOICE_RANGE * i + 1 == this.colorPatternPrat.length) {
					break;
				}
			}
			System.out.println("");
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	色番号の組み合わせを２次元配列に代入するメソッド
	public void combinationSet(int index) {

		int howMany = this.COLORS[0].length;	//	ループ回数を調整する変数
		int subtract = this.COMBINATION;		//	colorPatternの添え字をCOMBINATIONから引いて(subtract)指定する変数
		int suffix = 0;						//	第２要素の添え字（suffix）を調整する変数

			//	１つ目の要素を代入していくループ
		for(int i = 0; i < this.getColorPattern().length; i++) {
			howMany--;
			for(int j= 0; j < howMany; j++) {
				this.setColorPattern(this.COMBINATION - subtract, 0, this.COLORS[index][i]);
				subtract--;
			}
		}

		howMany = this.COLORS[0].length;
		subtract = this.COMBINATION;

			//	２つ目の要素に代入していくループ
		for(int i = 0; i < this.getColorPattern().length; i++) {
			howMany--;
			for(int j= 0; j < howMany; j++) {

				while(COLORS[index][suffix] <= this.getColorPattern()[this.COMBINATION - subtract][0]) {
					suffix++;
				}
				this.setColorPattern(this.COMBINATION - subtract, 1, this.COLORS[index][suffix + j]);
				subtract--;
			}
		}
	}

//----------------------------------------------------------------------------------------------------------------------

	//	選んだ数だけ色番号の組み合わせを２次元配列に代入するメソッド
	public void choiseCombinationSet() {

		this.combinationSet(0);

		for(int i = 0 ; i < this.CHOICE_PATTERN ; i++) {
			int pattern = new java.util.Random().nextInt(this.COMBINATION);
			this.setColorPatternPrat(i,0,this.getColorPattern(pattern, 0));
			this.setColorPatternPrat(i,1,this.getColorPattern(pattern, 1));
		}

		this.combinationSet(1);

		for(int i = 0 ; i < this.CHOICE_PATTERN ; i++) {
			int pattern = new java.util.Random().nextInt(this.COMBINATION);
			this.setColorPatternPrat(i,2,this.getColorPattern(pattern, 0));
			this.setColorPatternPrat(i,3,this.getColorPattern(pattern, 1));
		}
	}
}