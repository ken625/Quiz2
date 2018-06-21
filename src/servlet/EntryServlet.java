package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EntryLogic;
import model.NewAccount;

/**
 * Servlet implementation class EntryServlet
 */
@WebServlet("/EntryServlet")
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ログイン画面の「新規登録」押下
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/entry.jsp");
		dispatcher.forward(request, response);
	}

	//新規登録画面の「登録」ボタン押下
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");

		//入力情報をBOに渡す
		NewAccount na = new NewAccount(userName, pass);
		EntryLogic bo = new EntryLogic();
		boolean result = bo.execute(na);

		//登録可能かどうか
		if(result){
			//OKならログイン画面へフォワード
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}else{
			//NGなら登録画面にリダイレクト
			response.sendRedirect("/Quiz2/EntryServlet");
		}
	}
}
