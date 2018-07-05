package model;

import dao.AccountDAO;

public class Test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
/*
		RankingLogic lo = new RankingLogic();
		List<Account> list = lo.topRanking();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUserName());
		}
		*/
		Account account = new Account("I", 9, 5);
		AccountDAO dao = new AccountDAO();
		dao.updateScore(account);
	}
}
