package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.RankingDAO;

public class RankingLogic {
	private RankingDAO dao = new RankingDAO();
	private List<Account> list =  dao.ranking();
	Iterator<Account> iterator = list.iterator();

	public List<Account> topRanking() {
		List<Account> topList = new ArrayList<Account>();
		for(int i = 0; i < list.size(); i++) {
			if(iterator.hasNext() && i <= 10) {
				topList.add(list.get(i));
			} else {
				return topList;
			}
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
