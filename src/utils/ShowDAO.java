package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ProductBean;

public class ShowDAO {

	public ProductBean execute(int id) {

		//DBに接続や、SQL文に実行に使用する変数の設定
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ProductBean p = new ProductBean();

		try {

			//ドライバの登録
			Class.forName("com.mysql.jdbc.Driver");

			//DBへの接続
			conn = DriverManager.getConnection(

					"jdbc:mysql://localhost/ECRingo",
					"root",
					"password"
					);

			//SQLの設定
			String query = "SELECT * FROM product INNER JOIN category ON product.cat_id = category.cat_id WHERE product.pro_cd = ?";
			//SQL文を渡す
			pstmt = conn.prepareStatement(query);

			//値をセット
			pstmt.setInt(1, id);

			//SQL文の実行と結果の格納
			rs = pstmt.executeQuery();


			while(rs.next()) {

				//検索結果から、ID、名前、パスワードを取得
				int pro_id = rs.getInt("pro_cd");
				String pro_name = rs.getString("pro_name");
				int pro_price = rs.getInt("pro_price");
				String pro_img = rs.getString("pro_img");
				String cat_name = rs.getString("cat_name");
				int stock_no = rs.getInt("stock_no");
				String pro_msg = rs.getString("pro_msg");


				//userに検索結果をセット
				p.setProCd(pro_id);
				p.setProName(pro_name);
				p.setProPrice(pro_price);
				p.setProImg(pro_img);
				p.setCatName(cat_name);
				p.setStockNo(stock_no);
				p.setProMsg(pro_msg);

			}



		} catch(ClassNotFoundException e) {
			//ドライバの登録などにエラーが出た場合、詳細出力
			e.printStackTrace();
		} catch(SQLException e) {
			//DBへの接続時などにエラーが出た場合、詳細出力
			e.printStackTrace();
		} finally {

			try {
				//rs、pstmt、connを順番に切断
				if(rs != null) { rs.close();}
				if(pstmt != null) { rs.close();}
				if(conn != null) { rs.close();}

			} catch(Exception e) {}

		}

		return p;

	}

}
