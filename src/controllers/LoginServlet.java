package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import utils.LoginDAO;

public class LoginServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getAttribute("error") != null) {

			request.removeAttribute("error");
		}

		//logion.jspに移動
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストの文字コードを指定
		request.setCharacterEncoding("UTF-8");
		//パラメータを取得
		String name = request.getParameter("user_name");
		String pass = request.getParameter("user_pass");

		//Userインスタンスを生成
		User login_user = new User();

		if((name == null || name.equals(""))
				&& (pass == null || pass.equals(""))) {
			//名前とパスワードがnullや空行の時
			//エラーメッセージを設定
			String error1 = "名前またはパスワードを入力してください";
			//エラーメッセージをリクエストオブジェクトに格納
			request.setAttribute("error", error1);
			//ログイン画面へ移動
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(request, response);

		} else {

			//LoginDAOのインスタンスを生成
			LoginDAO ld = new LoginDAO();
			//フォームで入力したデータで検索した結果を格納
			login_user = ld.logincheck(name, pass);


			if((login_user.getUserName() == null || !login_user.getUserName().equals(name))
					&& (login_user.getLoginPw() == null || !login_user.getLoginPw().equals(pass))) {
				//検索結果がnullの場合
				//エラーメッセージを設定
				String error2 = "名前またはパスワードが一致しません";
				//エラーメッセージをリクエストオブジェクトに代入
				request.setAttribute("error", error2);
				//login.jspに戻る
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				rd.forward(request, response);

			} else {
				//セッション開始
				HttpSession session = request.getSession(true);
				//セッションオブジェクトにログインユーザー情報を格納
				session.setAttribute("loginUser", login_user);
				//検索画面へリダイレクト
				response.sendRedirect(request.getContextPath() + "/search");
			}
		}

	}
}
