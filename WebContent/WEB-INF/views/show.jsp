<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.ProductBean" %>
<%
ProductBean pb = (ProductBean) session.getAttribute("productbean");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>チームりんご ECサイト</title>
</head>
<body>
<div class="content">
<h1><img src="images/ringo.jpg" class="title_img">
商品紹介
<img src="images/ringo.jpg" class="title_img">
</h1>
	<table class="noborder_table">
		<tr>
			<td>
				<img src="images/<%= pb.getProImg() %>">
			</td>
			<td class="product_show">
				<table class="show_table">
					<tr>
						<th>商品名</th>
						<td><%= pb.getProName() %></td>
					</tr>
					<tr>
						<th>カテゴリ</th>
						<td><%= pb.getCatName() %></td>
					</tr>
					<tr>
						<th>価格</th>
						<td>&yen;&nbsp;<%= pb.getProPrice() %></td>
					</tr>
					<tr>
						<th>在庫</th>
						<td><%= pb.getStockNo() %></td>
					</tr>
					<tr>
						<th>商品紹介</th>
						<td><%= pb.getProMsg() %></td>
					</tr>
				</table>
				<br><br>
				<form action="cart" method="POST">
				個数
				<select name="kosuu" >
					<% for(int i=1;i<=pb.getStockNo();i++){ %>
						<option value="<%= i %>"><%= i %></option>
					<% } %>
				</select>
				<input type="submit"  value="カートへ" class="to_cart_button">
				<input type="button"  onclick="location.href='search'" value="戻る">
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>