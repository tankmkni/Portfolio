package model;

import java.io.Serializable;

public class User implements Serializable{

	//フィールド
	private String name;
	private String pass;

	//コンストラクタ
	public User() {}
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	//アクセサ
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}


}
