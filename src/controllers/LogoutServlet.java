package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {


    //継続してるセッションがあればセッション終了するメソッド
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/logout.jsp");
			rd.forward(request, response);
		}
	}
}
