package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.ProductBean;

public class SearchDAO {

	/***
	引数を検索条件にＤＢに問合せた結果をレコードごとに取得した値をListに格納し、
	そのListを呼び出し元に返すメソッド
	***/
    public List<ProductBean> getResult(String keyWord, String keyCat) {

    	//■引数を検索条件にして、DBに問い合わせ
		String url = "jdbc:mysql://localhost/ECRingo";
		String id = "root";
		String pass = "password";

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<ProductBean> products = new ArrayList<>();


		try {

			Class.forName("com.mysql.jdbc.Driver");


			conn = DriverManager.getConnection(url, id, pass);

			if(keyWord == null || keyWord == "") {
				String query
				= "select product.pro_cd, product.pro_name, product.pro_price from product inner join category on product.cat_id = category.cat_id where category.cat_name = ?";
				pst = conn.prepareStatement(query);
				pst.setString(1, keyCat);
				rs = pst.executeQuery();
			}else {
				String query
				= "select product.pro_cd, product.pro_name, product.pro_price from product inner join category on product.cat_id = category.cat_id where category.cat_name = ? and product.pro_name = ?";
				pst = conn.prepareStatement(query);
				pst.setString(1, keyCat);
				pst.setString(2, keyWord);
				rs = pst.executeQuery();
			}


			//■取得した値をBeanプロパティに格納
			//　インスタンス化したBeanをListに格納
			while(rs.next()) {
				ProductBean product = new ProductBean();
				product.setProCd(rs.getInt("product.pro_cd"));
				product.setProName(rs.getString("product.pro_name"));
				product.setProPrice(rs.getInt("product.pro_price"));
				product.setProCd(rs.getInt("product.pro_cd"));
				products.add(product);
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

		//■インスタンス化したBeanを格納したListを
		//　メソッドの呼び出し元に返す
		return products;
	}

}
