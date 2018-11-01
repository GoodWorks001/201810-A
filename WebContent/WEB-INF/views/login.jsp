<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 String error = (String) request.getAttribute("error");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>チームりんご ECサイト</title>
</head>
<body>
<div class="wrapper">
	<% if(error != null && error.length() > 0) { %>
		<div class="error">
			<p><%= error %></p>
		</div>
	<% } %>
	<div class="content background">
		<div class="login">
			<h1>ログイン</h1>
			<form action="login" method="POST">
				名前&emsp;&emsp;&emsp;&nbsp;&nbsp;
				<input type="text" name="user_name"><br>
				<br>
				パスワード&emsp;
				<input type="password" name="user_pass"><br>
				<br>
				<input type="submit" value="LOGIN">
			</form>
		</div>
	</div>
</div>
</body>
</html>