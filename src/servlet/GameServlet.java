package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GameData;
import model.GetGameDataLogic;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String forwardPath = "/WEB-INF/jsp/index.jsp";

		HttpSession session = request.getSession();

		if(action == null){
			// リダイレクトに変える（予定）
			forwardPath = "/WEB-INF/jsp/index.jsp";

		}else if(action.equals("start")){
			GetGameDataLogic ggdl = new GetGameDataLogic();
			GameData[] questionList = ggdl.execute();
			session.setAttribute("questionList", questionList);
			session.setAttribute("result", new String[10]);
			forwardPath = "/WEB-INF/jsp/GamePlay.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

		}else if(action.equals("getData")){
			int questionNum = 0;

			try{
				questionNum = Integer.parseInt(request.getParameter("questionNum")) -1;
			}catch(NumberFormatException e){
				e.printStackTrace();
			}

			GameData[] questionList = (GameData[])session.getAttribute("questionList");

			String json = "\"" + questionList[questionNum].getText().replace("\\", "\\\\").replace("\'", "\\\'").replace("\"", "\\\"") +"\"";

			response.setContentType("application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		}
	}
}
