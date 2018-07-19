package model;

public class NewAccount {
	private String userName;
	private String pass;

	public NewAccount(String userName, String pass){
		HashPass hp = new HashPass();
		this.userName = userName.replace("<","&lt").replace(">", "&gt");
		this.pass = hp.encodePassdigiest(pass);
	}
	public String getUserName(){return userName;}
	public String getPass(){return pass;}
}
