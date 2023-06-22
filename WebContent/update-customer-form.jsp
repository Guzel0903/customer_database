<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Update Customer Profile</title>
		<link type="text/css" rel="stylesheet" href="CSS/update-customer-style.css">
</head>
<body>
	
	<div id="container">
		<h3>選択した顧客は以下の通りです。編集しますか？</h3>
		
		<ul>
        <li>    ID：${THE_CUSTOMER.id }</li>
        <li>名前：${THE_CUSTOMER.name}</li>
        <li>年齢：${THE_CUSTOMER.age}</li>
        <li>住所：${THE_CUSTOMER.address}</li>
    </ul>
		
		<form action="CustomerControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="customerId" value="${THE_CUSTOMER.id }" />
			
			<a href="CustomerControllerServlet" class="back-button">戻る</a>
			<input type="submit" value="編集" class="submit-button" />
			
		</div>
		</form>
</body>
</html>
