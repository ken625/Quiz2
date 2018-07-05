package model;

import java.util.ArrayList;

import dao.GameDAO;

public class GetGameDataLogic {

	public GameData[] execute(){
		GameDAO dao = new GameDAO();
		ArrayList<ArrayList<GameData>> gameData = dao.quizList();
		GameData[] questionList = new GameData[10];
		int level = -1;
		boolean flg;

		for(int i = 0; i < questionList.length; i++){
			if(i % 3 == 0){
				level++;
			}
			flg = true;
			while(flg){
				int randNum = (int) (Math.random() * gameData.get(level).size() -1);

				for(int j = 0; j < questionList.length; j++){
					if(questionList[j] == null){
						flg = false;
						questionList[j] = gameData.get(level).get(randNum);
						break;
					}else if(questionList[j].getId().equals(gameData.get(level).get(randNum).getId())){
						break;
					}
				}
			}
		}
		return questionList;
	}

}
