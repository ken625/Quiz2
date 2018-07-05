package model;

import dao.AccountDAO;

public class SetScoreLogic {

	public boolean execute(String userName, int score){
		AccountDAO dao = new AccountDAO();
		return dao.update(userName, score);
	}
}
