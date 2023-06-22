<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Customer</title>
	<link type="text/css" rel="stylesheet" href="CSS/add-customer-style.css">
	<script src="js/validation.js"></script>
</head>
<body>
	<div id="container">
		<h3>顧客情報を⼊⼒して下さい。</h3>
		
	    <form action="CustomerControllerServlet" method="GET" onsubmit="return validateForm()">
	      <input type="hidden" name="command" value="CONFIRMADD">
			<table>
				<tbody>
					<tr>
						<td><label>名前:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td><label>年齢:</label></td>
						<td><input type="text" name="age" /></td>
					</tr>
					<tr>
						<td><label>住所:</label></td>
						<td><input type="text" name="address" /></td>
					</tr>
				</tbody>
			</table>
			<div style="display: flex; margin-top: 26px;">
			
			<a href="CustomerControllerServlet" class="back-button">戻る</a>
			<input type="submit" value="確認へ" class="submit-button" />
				
	</div>
	</form>
</body>
</html> 











