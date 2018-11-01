package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.CartBean;
import models.ProductBean;

public class CartServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session=req.getSession(false);
		if(session==null) {

			return;
	}
		//ProductBeanのデータを格納
		ProductBean pb=(ProductBean) session.getAttribute("productbean");
		int count=Integer.parseInt(req.getParameter("kosuu"));

		//CartBeanをインスタンス化
		CartBean cb =new CartBean();

		//CartBeanのデータを格納
		cb.setName(pb.getProName());
		cb.setPrice(pb.getProPrice());
		cb.setProCd(pb.getProCd());
		cb.setStockNo(pb.getStockNo());
		cb.setKosuu(count);

		//Listの宣言

		List<CartBean> c = (ArrayList<CartBean>) session.getAttribute("cartbean");

		if(c == null || c.size() <= 0 ) {
			c =new ArrayList<CartBean>();

		}

		c.add(cb);



		session.setAttribute("cartbean",c);
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/views/cart.jsp");
		rd.forward(req,resp);

		session.removeAttribute("pb");


	}
}
