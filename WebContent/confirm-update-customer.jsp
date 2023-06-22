<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm Update Customer</title>
<link type="text/css" rel="stylesheet" href="CSS/update-customer-style.css">
<script src="js/validation.js"></script>
</head>
<body>
	<div id= "container">
	<h3>編集してください。</h3>
	
		<form action="CustomerControllerServlet" method="GET" onsubmit="return validateForm()">
		 <input type="hidden" name="command" value="SAVE_UPDATE"/>
		 <input type="hidden" name="customerId" value="${THE_CUSTOMER.id}" />
			
		<table>
		 	<tbody>
		 		<tr>
		 			<td><label>名前：</label></td>
		 			<td><input type="text" name="name" value="${ THE_CUSTOMER.name }" /></td>		
		 		</tr>
		 		<tr>
		 			<td><label>年齢：</label></td>
		 			<td><input type="text" name="age" value="${ THE_CUSTOMER.age }" /></td>
		 		</tr>
		 		<tr>
		 			<td><label>住所：</label></td>
		 			<td><input type="text" name="address" value="${ THE_CUSTOMER.address}" /></td>
		 		</tr>	 		
		 	</tbody>
		 </table>
		 <div style="display: flex; margin-top: 26px;">
		 
		 <a href="CustomerControllerServlet" class="back-button">戻る</a>
		 <input type="submit" value="更新" class="submit-button" />
		 
		 </div>
	</form>
</body>
</html>