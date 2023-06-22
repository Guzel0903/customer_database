<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Confirmation Update 	Page</title>
	<link type="text/css" rel="stylesheet" href="CSS/add-customer-style.css">
</head>
<body>
	<div id="container">
		<h3>以下の通り更新しました。</h3>
		<table>
			<tbody>
				<tr>
					<td><label for="name">名前:</label></td>
					<td>${param.name}</td>
				</tr>
				<tr>
					<td><label for="age">年齢:</label></td>
					<td>${param.age}</td>
				</tr>
				<tr>
					<td><label for="address">住所:</label></td>
					<td>${param.address}</td>
				</tr>
			</tbody>
		</table>
		 <div style="display: flex; margin-top: 26px;">
		<a href="CustomerControllerServlet" class="back-button">戻る</a>
	
	</div>
</body>
</html>
