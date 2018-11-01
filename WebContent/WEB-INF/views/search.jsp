<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import = "java.util.List" %>
    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "models.ProductBean" %>
    <%@ page import = "controllers.SearchServlet" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>チームりんご ECサイト</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="content">
<h1><img src="images/ringo.jpg" class="title_img">検索<img src="images/ringo.jpg" class="title_img"></h1><br>

<form action="search" method = "post">

<%
List<String> list = (List<String>) request.getAttribute("cat_names");
List<ProductBean> products = (List<ProductBean>) request.getAttribute("products");
%>

<input type = "text" name = "key">

<select name = "category">
<%for(String cat_name: list){ %>
<option value = "<%= cat_name%>"> <%=cat_name%> </option>
<%} %>
</select><br>
<br>
<input type="submit" value="検索"><br>
<br>
<%if(products == null){ %>
<%}else if(products.size() >= 1){ %>
<table class="search_table">
<tr>
<th>商品名</th><th>価格</th><th>詳細</th>
</tr>
<% for(ProductBean product: products){ %>
<tr>
<td> <%= product.getProName() %> </td>
<td> &yen;&nbsp;<%= product.getProPrice() %> </td>
<td> <a href = "show?proId=<%=product.getProCd()%>" >詳細</a></td>
</tr>
<%}%>
</table>

<%}else{ %>
<p>検索結果がありません。</p>
<%} %>

</form>

</div>
</body>
</html>