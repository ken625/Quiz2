package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.SetScoreLogic;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		System.out.println(account.getUserName());
		String[] result = (String[])session.getAttribute("result");

		// 正誤判定をスコアに換算(L1:5×3, L2:9×3, L3:13×3, L4:19×1 最大100点)
		int[] point = {5, 9, 13, 19};
		int level = -1;
		int score = 0;
		for(int i = 0; i < result.length; i++){
			if(i % 3 == 0){
				level++;
			}
			if(result[i].equals("1")){
				score += point[level];
			}
		}
		SetScoreLogic bo = new SetScoreLogic();
		// スコアをアカウント情報に保存
		bo.execute(account.getUserName(), score);

		request.setAttribute("score", score);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
