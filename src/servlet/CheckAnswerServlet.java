package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GameData;

@WebServlet("/CheckAnswerServlet")
public class CheckAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		String answer = request.getParameter("answer");
		int questionNum = 0;
		try{
			questionNum = Integer.parseInt(request.getParameter("questionNum"))-1;
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		GameData gameData = ((GameData[])session.getAttribute("questionList"))[questionNum];
		String[] result = (String[])session.getAttribute("result");


		result[questionNum] = gameData.getAnswer().equals(answer) ? "1": "0";
		session.setAttribute("result", result);
		String json = result[questionNum];
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
	}
}
