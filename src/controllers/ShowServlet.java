package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ProductBean;
import utils.ShowDAO;

public class ShowServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if(session == null){
			return;
		}


		//URLで記載したパラメータを取得
		int id = Integer.parseInt(request.getParameter("proId"));

		//パラメータの値を元に検索した結果取得
		ShowDAO pd = new ShowDAO();
		ProductBean p = pd.execute(id);

		//リクエストオブジェクトにProductBeanインスタンスを格納
		session.setAttribute("productbean", p);

		//show.jspへ移動
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/show.jsp");
		rd.forward(request, response);

	}

}
