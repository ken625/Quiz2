package model;

public class Ranking {
	private String userName;
	private int score;
	private int ranking;

	public Ranking(String userName, int score, int ranking) {
		this.userName = userName;
		this.score = score;
	}

	public String getUserName() {
		return userName;
	}

	public int getScore() {
		return score;
	}

	public int getRanking() {
		return ranking;
	}
}
