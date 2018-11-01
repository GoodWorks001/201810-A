package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.CartBean;


public class CommitDAO {
	/***購入を確定した情報をmeisaiテーブルに入力するメソッド***/
	public void setMeisai(int user_id, Date ko_date, int mei_price) {

	    	//引数を検索条件にして、DBに問い合わせ
			String url = "jdbc:mysql://localhost/ECRingo";
			String id = "root";
			String pass = "password";

			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;

			try {

				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(url, id, pass);
				String query = "insert into meisai value (?, ?, ?, ?)";
				pst = conn.prepareStatement(query);

				int num = this.getCurrentRecord();


				if(num <= 0) {
					//プリペアドステートメントに値をセット
					pst.setInt(1, 1);
					pst.setInt(2, user_id);
					pst.setDate(3, ko_date);
					pst.setInt(4, mei_price);
					//SQL文の実行
				    pst.executeUpdate();
				}

				//プリペアドステートメントに値をセット
				pst.setInt(1, (num +1));
				pst.setInt(2, user_id);
				pst.setDate(3, ko_date);
				pst.setInt(4, mei_price);
				//SQL文の実行
			    pst.executeUpdate();
			}
			catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			finally {
				try {
					if(conn != null) conn.close();
					if(pst != null) pst.close();
					if(rs != null) rs.close();
					}
				catch(Exception ex) {}
			}
	}


	/***明細テーブルが一番大きいレコードのID(最新のID)を取得するメソッド***/
	public int getCurrentRecord() {

		String url = "jdbc:mysql://localhost/ECRingo";
		String id = "root";
		String pass = "password";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		int mei_id = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pass);
			String query = "select max(mei_id) from meisai";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				mei_id = rs.getInt("max(mei_id)");
			}

		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if(conn != null) conn.close();
				if(pst != null) pst.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex) {}
		}

		return mei_id;

	}






	/***購入を確定した情報をpurchaseテーブルに入力するメソッド***/
	public void setPurchase(int mei_id, List<CartBean> cars) {

		String url = "jdbc:mysql://localhost/ECRingo";
		String id = "root";
		String pass = "password";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CartBean cart = new CartBean();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pass);
			String query = "insert into purchase value (?,?,?)";
			pst = conn.prepareStatement(query);

			for(CartBean c : cars) {
				pst.setInt(1, mei_id);
				pst.setInt(2, c.getProCd());
				pst.setInt(3, c.getKosuu());
			    pst.executeUpdate();
			}

		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if(conn != null) conn.close();
				if(pst != null) pst.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex) {}
		}

	}

	/***購入によって減った在庫数をproductテーブルに反映させるメソッド***/
	public void reduceStock(List<CartBean> carts) {

		String url = "jdbc:mysql://localhost/ECRingo";
		String id = "root";
		String pass = "password";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		CartBean cart = new CartBean();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pass);
			String query = "update product set stock_no = ? where pro_cd = ?";
			pst = conn.prepareStatement(query);

			for(CartBean c : carts) {
				int nowStockNo = (c.getStockNo()) - c.getKosuu();
				pst.setInt(1, nowStockNo);
				pst.setInt(2, c.getProCd());
			    pst.executeUpdate();
			    }

		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if(conn != null) conn.close();
				if(pst != null) pst.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex) {}
		}

	}
}
