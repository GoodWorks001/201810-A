package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

	public List<String> getCategory() {

		String url = "jdbc:mysql://localhost/ECRingo";
		String id = "root";
		String pass = "password";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		List<String> categorys = new ArrayList<>();

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pass);
			String query
			= "select cat_name from category";
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()) {
				categorys.add(rs.getString("cat_name"));
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
				if(st != null) st.close();
				if(rs != null) rs.close();
			}
			catch(Exception ex) {}
		}

		return categorys;

	}
}
