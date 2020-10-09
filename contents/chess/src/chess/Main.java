package chess;

import java.util.Scanner;

public class Main {

	public static void main( String[] args ){

		/*インスタンス化以外の処理(代入、表記など)はMainクラスでは行わない
		 * (呼び出し、分岐、ループのみ)
		 * 定数もMainクラスでは扱わない
		 * (できる限りの部品化＆共有化でメンテナンス性を考慮)(日本語表記はMessageに集約させるなど)
		 * 可読性
		 * (クラス名、メソッド名、変数名はその内容がわかるよう命名)
		 * */

//	インスタンス化------------------------------------------------------------------------------------------------------


		Draw			 draw		= new Draw();					//	盤を市松模様で表記するインスタンス
		DrawColorPattern color		= new DrawColorPattern();		//	盤と駒の色見本を表記するインスタンス
		DrawMoveResult	 result		= new DrawMoveResult();			//	移動前と移動後の盤を表記するインスタンス
		Board			 board		= new Board();					//	盤のインスタンス
		Input			 input		= new Input();					//	指し手の入力インスタンス
		Move			 move		= new Move();					//	駒の移動インスタンス
		Scanner			 scan		= new Scanner( System.in );		//	入力用インスタンス
		Counter			 counter	= new Counter();				//	手番をカウントするインスタンス
		Setting			 setting	= new Setting();				//	盤に初期配置で駒を並べるインスタンス
		Message			 message	= new Message();				//	メッセージを表記するインスタンス
		Picture			 picture	= new Picture();				//	ドット絵を描くインスタンス

//	駒の並べ方を選択----------------------------------------------------------------------------------------------------


		do {	//	正しく入力されるまで繰り返すループ
			message.chess();											//	タイトルの表示
//			picture.onePage( picture.cat() );							//	猫の表示
			message.select( setting.ARRANGEMENT );						//	並べ方の選択肢の表記
				//	何も入力されなかったらやり直し
			if( !( input.setBackSentence( scan.nextLine() ).equals( "" ) ) ) {

					//	String型の入力値がint型に変換できる内容だったら
				if( input.checkNumber( input.getSentence() ) ){

						//	String型の入力値をint型に変換して代入
					input.setValue( Integer.parseInt( input.getSentence() ) );
				}

					//	入力が選択肢の範囲内(Within)じゃなかったら
				if( !( input.checkWithin( setting.ARRANGEMENT.length ) ) ){

					message.numberOutOfRenge( input.getSentence() );	//	範囲外だと表示
					message.pushEnterAndLine();							//	Enterキーが押されるまで待機
				}
			}else {
				message.numberOutOfRenge( input.getSentence() );		//	エラーメッセージ
				message.line();											//	仕切り線の表記
			}

			//	入力が選択肢の範囲内(Within)じゃなかったらやり直し
		}while( !( input.checkWithin( setting.ARRANGEMENT.length ) ) );


//	盤の色のパターンをランダムで表記して、その中から選択----------------------------------------------------------------


		board.setBoard( setting.select( board.before(), input.getValue() ) );		//	盤に駒を並べる
		message.line();										//	仕切り線の表記

		do {
			color.drawRegular( board.before() );			//	色見本を規則正しく(Regular)表記する
			message.colorSelect();							//	色のパターン№を選ぶよう表記

				//	なんらかの入力があったら
			if( !( input.setBackSentence( scan.nextLine() ).equals( "" ) ) ) {

					//	入力値がint型に変換できる場合
				if( input.checkNumber( input.getSentence() ) ) {
					input.setValue( Integer.parseInt( input.getSentence() ) );	//	№を変換して入力
				}

					//	入力値が範囲外だったら
				if( !( input.checkWithin( color.CHOICE_PATTERN ) ) ) {
					message.numberOutOfRenge( input.getSentence() );	//	範囲外と表記
					message.pushEnter();								//	Enterキーが押されるまで待機
				}
			}

			message.line();		//	仕切り線の表記

			//	入力値が範囲外だったら戻ってやり直し
		}while( !( input.checkWithin( color.CHOICE_PATTERN ) ) );

			//	選択した色指定番号を色指定文に組み込む
		draw.colorSet( color.getColorPatternPrat( input.getValue() ) );
		result.colorSet( color.getColorPatternPrat( input.getValue() ) );


//	ゲーム開始----------------------------------------------------------------------------------------------------------


		do {						//	表記パターンを "鑑賞" するためだけのループ（必要なし）
			message.gameStart();	//	スタート文の表記
//			picture.onePage( picture.catInBox() );	//	猫の表示


			//	何らかの入力があったらやり直し
		}while( !( input.setBackSentence( scan.nextLine() ).equals( "" ) ) );

		message.inputMethod();			//	入力方法の説明
		message.pushEnterAndLine();		//	Enterキーが押されるまで待機


//	ゲーム実行----------------------------------------------------------------------------------------------------------


		do {	//	３．入力された指し手を実行して表記する一番目のループ(勝負の終了まで繰り返す）

			counter.upCount();				//	手番のカウントを一つ増やす

			if( counter.notFirstTurn() ) {	//	初手以外の場合（カウントが１より大きかったら）
				message.line();				//	仕切り線の表記
				message.reversal();			//	反転したと表記
			}

			do {	//	２．指し手入力～移動終了までの二番目のループ（このループ１回で１手（棋譜的には0.5手））


			//	１．入力が適切になるまで繰り返す------------------------------------------------------------------------

				do {	//	１．入力値が適切になるまで入力を繰り返す三番目のループ

					message.turn( counter.turn(), counter.whatMove() );		//	手番の表記
					draw.draw( counter.turn(), board.before() );			//	盤の表記
					message.moveInput();									//	移動の入力をするよう表記

						//	移動の入力をして、入力方法が合っていたら、char型とint型の配列を用意(prepare)
					if( input.checkMove( input.setBackSentence( scan.nextLine() ) ) ) {
						input.prepareType( draw.MARK );
					}

						//	ゲームを終了する場合（入力文が終了文と同じだったら）
					if( input.checkWord( message.GAME_END ) ) {
						break;

						//	入力方法が違ったら
					}else if( !( input.checkMove( input.getSentence() ) ) ) {
						message.inputImpermission( input.getSentence() );	//	入力法が違うと表記
						message.pushEnterAndLine();							//	Enterキーが押されたら仕切り線を表示

						//	移動させるコマが存在しなかった場合
					}else if( board.mover( input.getInted() ) == null ) {
						input.setPermission( false );						//	入力値を不許可に
						message.notExist( input.getSentence() );			//	コマが存在しないと表記
						message.pushEnterAndLine();							//	Enterキーが押されたら仕切り線を表示
					}

				}while( !( input.getPermission() ));
			//	１．入力値が許可(Permission)でなかったら三番目のループをやり直し----------------------------------------

		//	２．移動が可能かどうかを判定して、移動できなかったらメッセージを表記----------------------------------------


					//	ゲームを終了する場合（入力文が終了文と同じだったら）
				if( input.checkWord( message.GAME_END ) ) {
					break;
				}

					//	移動が可能かどうかを判定し、不可能だった場合、
				if( move.moveJudg( board.before(), input.getInted(), counter.getCount() ) ){
					message.moveImpermissible( input.getSentence() );		//	移動できないと表記
					message.pushEnterAndLine();								//	Enterキーが押されるまで待機
				}

			}while( board.mover( input.getInted() ).isMoveImpossible() );
		//	２．移動が不可能（Impossible）なら二番目のループをやり直し--------------------------------------------------

	//	３．入力値の実行------------------------------------------------------------------------------------------------


				//	ゲームを終了する場合（入力文が終了文と同じだったら）
			if( input.checkWord( message.GAME_END ) ) {
				break;
			}

				//	駒の移動（駒の並べ換え）をさせて移動内容を表示
			board.setBoard( move.move( board.before(), input.getInted() ) );
			message.move( move.getWhatDid(), board.moved( input.getInted() ).getName(), input.getChared() );


		//	プロモーションの実行----------------------------------------------------------------------------------------


				//	ポーンがプロモーション（昇進）できるならプロモーションさせる
			if( move.isPromotion() ) {

				do {	//	正しく入力されるまで繰り返すループ

					result.draw( counter.turn(), board.getBoard() );	//	盤の表記
					message.select( setting.PROMOTION );				//	プロモーションの選択肢の表記

						//	String型の入力値がint型に変換できる内容だったら
					if( input.checkNumber( input.setBackSentence( scan.nextLine() ) ) ){

							//	String型の入力値をint型に変換して代入
						input.setValue( Integer.parseInt( input.getSentence() ) );
					}

						//	入力が不適切だったら（選択できる駒の種類の数でなかったら）
					if( !( input.checkWithin( setting.PROMOTION.length ) ) )  {

						message.numberOutOfRenge( input.getSentence() );	//	範囲外だと表示
						message.pushEnterAndLine();							//	Enterキーが押されるまで待機										//	仕切り線の表記
					}

					//	入力値が不適切だったらやり直し（選択できる駒の種類の数でなかったら）
				}while( !( input.checkWithin( setting.PROMOTION.length ) ) );

					//	プロモーションの実行（駒の入れ替え）
				board.setBoard( setting.promotion( board.before(), input.getInted(), input.getValue() ) );

					//	実行結果の表記
				message.promotion( board.moved( input.getInted() ).getName() );

			}	//	プロモーションの終了

			result.draw( counter.turn(), board.getBoard() );	//	盤の表記
			message.pushEnter();								//	Enterキーが押されるまで待機

		}while( !( input.checkWord( message.GAME_END ) ) );
	//	３．ゲームが終了するまで一番目のループを繰り返す----------------------------------------------------------------

//	ゲーム終了----------------------------------------------------------------------------------------------------------

		message.line();			//	仕切り線の表記
		message.gameEnd();		//	ゲーム終了文の表記
	}
}