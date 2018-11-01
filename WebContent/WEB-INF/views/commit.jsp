<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "models.CartBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>チームりんご ECサイト</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="content">
<%
List<CartBean> cartbean = (List<CartBean>) session.getAttribute("cartbean");
%>

<h1><img src="images/ringo.jpg" class="title_img">
購入を確定させますか？
<img src="images/ringo.jpg" class="title_img">
</h1>

<table class="commit_table">
<tr>
<th>商品名</th><th>価格</th><th>数量</th>
</tr>
<% for(CartBean cart: cartbean){ %>
<tr>
<td> <%= cart.getName() %> </td>
<td> &yen;&nbsp;<%= cart.getPrice() %> </td>
<td> <%= cart.getKosuu() %></td>
</tr>
<%}%>
<tr>
<th colspan="2">消費税</th><td>&yen;&nbsp;<%= request.getAttribute("tax") %></td>
</tr>
<tr>
<th colspan="2">合計金額</th><td>&yen;&nbsp;<%= request.getAttribute("total") %></td>
</tr>
</table><br>
<br><br>
	<a href = "search" ><button>いいえ</button></a>
	<a href = "commit" ><button>はい</button></a>
</div>
</body>
</html>