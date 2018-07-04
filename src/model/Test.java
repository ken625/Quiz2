package model;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		RankingLogic lo = new RankingLogic();
		List<Account> list = lo.topRanking();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUserName());
		}
	}
}
