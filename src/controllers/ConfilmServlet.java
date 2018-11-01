package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfilmServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null) {

			return;
		}

		request.setCharacterEncoding("UTF-8");
		int tax = Integer.parseInt(request.getParameter("tax"));
		int total = Integer.parseInt(request.getParameter("total"));

		request.setAttribute("tax", tax);
		request.setAttribute("total", total);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/commit.jsp");
		rd.forward(request, response);

	}
}
