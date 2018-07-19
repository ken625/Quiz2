package model;

import dao.AccountDAO;

public class EntryLogic {

	public boolean execute(NewAccount NewAccount){
		String name = NewAccount.getUserName();
		String pass = NewAccount.getPass();

		//名前とパスワードのどちらかが空白の場合falseを返す(リダイレクト)
		if(name.isEmpty() || pass.isEmpty() || name.equals("") || pass.equals("")){
			boolean result = false;
			return result;
		}else{
			AccountDAO dao = new AccountDAO();
			System.out.println("exe");
			boolean result = dao.createRecord(NewAccount);
			return result;
		}
	}
}
