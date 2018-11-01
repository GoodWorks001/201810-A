package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.CartBean;
import models.User;
import utils.CommitDAO;

public class CommitServlet extends HttpServlet {

	/***購入内容の値を受け取り、DBの更新を行なうメソッド***/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null) {

			return;
		}


		//セッションからログインユーザー情報を取得し、ID取得
		User user = (User) session.getAttribute("loginUser");
		int user_id = user.getUserId();
		//購入にした日時の取得
		Date ko_date = new Date(System.currentTimeMillis());
		//購入した総額
		int mei_price = (Integer)(session.getAttribute("total"));


		//■DBの更新
		CommitDAO commit = new CommitDAO();
		//明細をDBに追加
		commit.setMeisai(user_id, ko_date, mei_price);

		//■購入内容の値の受け取り
		List<CartBean> cartbean = (List<CartBean>) session.getAttribute("cartbean");


		//明細テーブルの一番最新のレコードの明細IDを取得
		int mei_id = commit.getCurrentRecord();
		//テーブルに入っている購入商品IDと購入数をDBに登録
		commit.setPurchase(mei_id, cartbean);

		//productテーブルから在庫数を購入数分減らす
		commit.reduceStock(cartbean);

		//セッションにあるCartBeanのリストを削除
		session.removeAttribute("cartbean");

		//thank.jspに移動
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/thank.jsp");
		rd.forward(request, response);

	}

}
