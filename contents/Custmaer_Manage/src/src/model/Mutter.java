package model;

import java.io.Serializable;

public class Mutter implements Serializable{


	//フィールド
	private int id;
	private String name;
	private String text;
	private String date;

	//コンストラクタ
	public Mutter() {}
	public Mutter( String name , String text ) {
		this.name = name;
		this.text = text;
	}
	public Mutter( int id , String name , String text ) {
		this.id = id;
		this.name = name;
		this.text = text;
	}
	public Mutter( int id , String name , String text , String date ) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.date = date;
	}

	//アクセサ
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setText(String text) {
		this.text = text;
	}


}
