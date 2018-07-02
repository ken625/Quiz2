package model;

import java.util.ArrayList;
import java.util.List;

import dao.RankingDAO;

public class RankingLogic {
	private RankingDAO dao = new RankingDAO();
	private List<Account> list =  dao.ranking();

	public List<Account> topRanking() {
		List<Account> topList = new ArrayList<Account>();
		for(int i = 0; i < 10; i++) {
			topList.add(list.get(i));
		}
		return topList;
	}

	public int myRanking(String userName) {
		for(Account account : list){
			if(account.getUserName().equals(userName)){
				account.getRanking();
				return account.getRanking();
			}
		}
		return 0;
	}
}
