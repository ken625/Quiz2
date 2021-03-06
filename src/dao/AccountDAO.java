package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;
import model.NewAccount;

public class AccountDAO {

	private final String STR = "?useUnicode=true&useJDBCCompliantTimezoneShift="
			+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	//ACCOUNT内のレコードを探索
	public Account findByLogin(Login login){
		Connection conn = null;
		Account account = null;

		//データベースに接続
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://118.27.2.80/QUIZ" + STR, "tama", "tamao");

			//データの取得(SELECT)
			String sql = "SELECT USER_NAME, PASS, SCORE FROM ACCOUNT WHERE USER_NAME = ? AND PASS = ?";
			//SQLの送信
			PreparedStatement pSmt = conn.prepareStatement(sql);
			pSmt.setString(1, login.getUserName());
			pSmt.setString(2, login.getPass());

			ResultSet rs = pSmt.executeQuery();

			if(rs.next()){
				String userName = rs.getString("USER_NAME");
				String pass = rs.getString("PASS");
				int score = rs.getInt("SCORE");

				account = new Account(userName, pass, score);
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
		return account;
	}

	//ACCOUNTに新たなレコードを追加
	public boolean createRecord(NewAccount newAccount){
		Connection conn = null;

		//データベースに接続
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://118.27.2.80/QUIZ" + STR, "tama", "tamao");

			//レコード追加用のSQL文(INSERT)
			String sql = "INSERT INTO ACCOUNT(USER_NAME, PASS) VALUES(?, ?)";
			//SQLの送信
			PreparedStatement pSmt = conn.prepareStatement(sql);
			//レコード追加用のSQL文(INSERT)
			pSmt.setString(1, newAccount.getUserName());
			pSmt.setString(2, newAccount.getPass());
			//INSERT文を実行する
			int result = pSmt.executeUpdate();

			if(result != 1){
				return false;
			}

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
				e.printStackTrace();
				return false;
				}
			}
		}
		return true;
	}

	//スコアの更新
	public boolean update(String userName, int score){
		Connection conn = null;

		//データベースに接続
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://118.27.2.80/QUIZ" + STR, "tama", "tamao");

			//レコード追加用のSQL文(INSERT)
			String sql = "UPDATE ACCOUNT SET SCORE = ? WHERE USER_NAME = ? AND  COALESCE(SCORE,0) <= ?";
			//SQLの送信
			PreparedStatement pSmt = conn.prepareStatement(sql);
			//レコード追加用のSQL文(INSERT)
			pSmt.setInt(1, score);
			pSmt.setString(2, userName);
			pSmt.setInt(3, score);
			//INSERT文を実行する
			int result = pSmt.executeUpdate();

			if(result != 1){
				return false;
			}

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
				e.printStackTrace();
				return false;
				}
			}
		}
		return true;
	}
}
