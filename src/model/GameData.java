package model;

public class GameData {
	private String id;
	private String text;
	private String answer;
	private String level;
	private String comment;

	public GameData(String id, String text, String answer, String level, String comment){
		this.id = id;
		this.text = text;
		this.answer = answer;
		this.level = level;
		this.comment = comment;
	}

	public String getId(){return id;}
	public String getText(){return text;}
	public String getAnswer(){return answer;}
	public String getLevel(){return level;}
	public String getComment(){return comment;}

}
