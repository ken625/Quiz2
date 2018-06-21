package model;

public class Account {
	private String userName;
	private String pass;
	private int score;

	public Account(String userName, String pass, int score){

		HashPass hp = new HashPass();

		this.userName = userName;
		this.pass = hp.encodePassdigiest(pass);
		this.score = score;
	}

	public String getUserName(){return userName;}
	public String getPass(){return pass;}
	public int getScore(){return score;}
}
