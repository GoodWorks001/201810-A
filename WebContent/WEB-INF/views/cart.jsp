<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import= "java.util.List"%>
<%@ page import="models.CartBean" %>
<% List<CartBean> c=(List<CartBean>)session.getAttribute("cartbean");%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>チームりんご ECサイト</title>
</head>
<body>
<div class="content">
<h1><img src="images/ringo.jpg" class="title_img">
カート
<img src="images/ringo.jpg" class="title_img">
</h1>

<table class="cart_table">
	<tr align="center">
		<th>商品名</th>
		<th>単価</th>
		<th>数量</th>
	</tr>

	<% for(CartBean cb:c){ %>
	<tr>
		<td  align="center"><%= cb.getName() %></td>
		<td align="right">&yen;&nbsp;<%= cb.getPrice() %></td>
		<td align="right"><%= cb.getKosuu() %></td>
	</tr>
	<% } %>

	<tr align="center">
		<th colspan="2">消費税</th>
		<td><% int sum=0; %>
			<% for(CartBean cb:c){ %>
				<%sum+=cb.getKosuu()*cb.getPrice()*0.08; %>
			<% } %>
			<%=sum %>
		</td>
	</tr>

	<tr align="center">
	<th colspan="2">合計金額</th>
	<td>
		<% int sum1=0; %>
		<% for(CartBean cb:c){ %>
			<% sum1+=cb.getKosuu()*cb.getPrice()*1.08; %>
		<% } %>
		<%= sum1 %></td>
		<% session.setAttribute("total", sum1); %>
	</tr>
	</table><br>
	<br>
<form action="confilm" method="POST">
<input type="hidden" name="tax" value=<%= sum %>>
<input type="hidden" name="total" value=<%= sum1 %>>
<input type="button"  onclick="location.href='search'" value="買い物を続ける">
<input type="submit"  value="購入">
</form>
</div>
</body>
</html>