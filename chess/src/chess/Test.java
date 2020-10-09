package chess;

import java.util.Scanner;

public class Test {

	public static void main( String[] args ){

			//	インスタンス化

		Draw			 draw		= new Draw();					//	盤を市松模様で表記するインスタンス
		DrawColorPattern color		= new DrawColorPattern();		//	盤と駒の色見本を表記するインスタンス
		DrawMoveResult	 drawRow	= new DrawMoveResult();			//	移動前と移動後の盤を表記するインスタンス
		Board			 board		= new Board();					//	盤のインスタンス
		Input			 input		= new Input();					//	指し手の入力インスタンス
		Move			 move		= new Move();					//	駒の移動インスタンス
		Scanner			 scan		= new Scanner( System.in );		//	入力用インスタンス
		Counter			 counter	= new Counter();				//	手番をカウントするインスタンス
		Setting			 setting	= new Setting();				//	盤に初期配置で駒を並べるインスタンス
		Message			 message	= new Message();				//	メッセージを表記するインスタンス
		Picture			 picture	= new Picture();

//ドット絵を表記--------------------------------------------------------------------------------------------------------

		picture.mario();
		picture.mario2();
		picture.slime();
		picture.slime2();
		picture.lineUp();

//チェス盤を段々で表記させる--------------------------------------------------------------------------------------------

//		color.drawgGradually(board.getBoard()[0], 0);	//	カラーパターンを段々で表記


//		counter.setCount( counter.getCount() + 1 );
////		color.boardSelect(board.getBoard());
//

//
//		drawRow.draw( counter.turn(), board.getBoard( 0 ) );
//
//		counter.setCount( counter.getCount() + 1 );
//		drawRow.draw( counter.turn(), board.getBoard( 0 ) );
//
	}
}