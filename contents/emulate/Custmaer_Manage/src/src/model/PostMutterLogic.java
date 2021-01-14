package model;

import static constants.MessageConstants.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.MutterDAO;

public class PostMutterLogic {
	public void execute( Mutter mutter ) {


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日 ('E') 'k':'mm':'ss");
		mutter.setDate( sdf.format( new Date() ) );

		MutterDAO dao = new MutterDAO();
		dao.create(mutter);
	}

	//つぶやきを削除する　state[0] = つぶやきのID 、state[1] = ログインユーザーの名前
	public String remove( String[] state ) {

		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();

		int id = Integer.parseInt( state[0] );

		//つぶやきのIDからそのつぶやきをした人の名前を取得
		Mutter mutter = null;
		for( Mutter mutterCandidate : mutterList ) {
			int idCandidate = mutterCandidate.getId();
			if( idCandidate == id ) {
				mutter = mutterCandidate;
				break;
			}
		}
		String mutterName = mutter.getName();
		System.out.println("loginUserName = " + state[1] + " : mutterName = " + mutterName);

		String message = null;

		//つぶやきの名前とログインユーザーの名前が一致したらそのIDのレコードを削除
		if( mutterName.equals( state[1] )) {
			dao.remove( state[0] );
			message = MESSAGE_REMOVED;
//			JOptionPane.showMessageDialog(null, "つぶやきを削除しました");

		//一致しなかったらダイアログを表示(メッセージの取得に変更)
		}else {
			message = MESSAGE_NAME_DO_NOT_MATCH;
//			JOptionPane.showMessageDialog(null, "名前が違うので削除できません");
		}
		return message;
	}


}
