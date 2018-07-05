package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.GameData;

public class GameDAO {
	//QUIZテーブルのデータを取得
	public ArrayList<ArrayList<GameData>> quizList(){
		Connection conn = null;
		ArrayList<ArrayList<GameData>> list = new ArrayList<>();
		ArrayList<GameData> level1 = new ArrayList<>();
		ArrayList<GameData> level2 = new ArrayList<>();
		ArrayList<GameData> level3 = new ArrayList<>();
		ArrayList<GameData> level4 = new ArrayList<>();

		//データベースに接続
		try{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test","sa","");

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM QUIZ";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				//各列にアクセスし、全てをStringに変換
			    Integer id = rs.getInt("ID");
			    String strId = Integer.toString(id);

			    String text = rs.getString("TEXT");

			    Integer ans = rs.getInt("ANSWER");
			    String strAns = Integer.toString(ans);

			    Integer level = rs.getInt("LEVEL");
			    String strLevel = Integer.toString(level);

			    String com = rs.getString("COMMENT");

			    GameData record = new GameData(strId, text, strAns, strLevel, com);
			    switch(record.getLevel()){
			    	case "1":
			    		level1.add(record);
			    		break;
			    	case "2":
			    		level2.add(record);
			    		break;
			    	case "3":
			    		level3.add(record);
			    		break;
			    	case "4":
			    		level4.add(record);
			    }
			}
			list.add(level1);
			list.add(level2);
			list.add(level3);
			list.add(level4);

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
