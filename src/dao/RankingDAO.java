package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;

public class RankingDAO {
	private final String STR = "?useUnicode=true&useJDBCCompliantTimezoneShift="
			+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public List<Account> ranking() {
		Connection conn = null;
		ArrayList<Account> list = new ArrayList<Account>();
		int element = 0;

		//データベースに接続
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://118.27.2.80/QUIZ" + STR, "tama", "tamao");

			//データの取得(SELECT)
			String sql = "SELECT USER_NAME, SCORE, RANK() OVER (ORDER BY SCORE DESC) AS RANKING FROM ACCOUNT "
					+ "WHERE SCORE IS NOT NULL";
			//SQLの送信
			PreparedStatement pSmt = conn.prepareStatement(sql);

			ResultSet rs = pSmt.executeQuery();

			while(rs.next()){

				String userName = rs.getString("USER_NAME");
				int score = rs.getInt("SCORE");
				int ranking = rs.getInt("RANKING");

				list.add(element, new Account(userName, score, ranking));
				element++;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
				e.printStackTrace();
				return null;
				}
			}
		}
		return list;

	}
}
