<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Confirm Add Customer</title>
  <link type="text/css" rel="stylesheet" href="CSS/add-customer-style.css">
</head>

<body>
	<div id= "container">
	<h3>以下の内容で登録しますか？</h3>
	<link type="text/css" rel="stylesheet" href="CSS/add-customer-style.css">
	
	<form action="CustomerControllerServlet" method="GET">
		 <input type="hidden" name="command" value="ADD"/>
		 <input type="hidden" name="name" value="${param.name}" />
			<input type="hidden" name="age" value="${param.age}" />
			<input type="hidden" name="address" value="${param.address}" />
		 
		 <table>
		 	<tbody>
		 		<tr>
		 			<td><label>名前：</label></td>
		 			<td>${ param.name}</td>
		 		</tr>
		 		<tr>
		 			<td><label>年齢：</label></td>
		 			<td>${ param.age}</td>
		 		</tr>
		 		
		 		<tr>
		 			<td><label>住所：</label></td>
		 			<td>${ param.address}</td>
		 		</tr>
		 	</tbody>
		 </table>
		 <div style="display: flex; margin-top: 26px;">
		 
		 <a href="CustomerControllerServlet" class="back-button">戻る</a>
		 <input type="submit" value="登録" class="submit-button" />
		 
	</div>
	</form>
</body>
</html>  

 
