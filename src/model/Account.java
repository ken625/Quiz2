package model;

public class Account {
	private String userName;
	private String pass;
	private int score;
	private int ranking;

	public Account(String userName, String pass, int score){

		HashPass hp = new HashPass();

		this.userName = userName;
		this.pass = hp.encodePassdigiest(pass);
		this.score = score;
	}

	public Account(String userName, int score, int ranking){

		this.userName = userName;
		this.score = score;
		this.ranking = ranking;
	}



	public String getUserName(){return userName;}
	public String getPass(){return pass;}
	public int getScore(){return score;}
	public int getRanking(){
		return ranking;
	}
}
