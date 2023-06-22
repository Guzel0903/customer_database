<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm Delete Customer</title>
<link type="text/css" rel="stylesheet" href="CSS/delete-customer-style.css">
</head>
<body>
	<div id= "container">
	<h3>選択した顧客は以下の通りです。削除しますか？</h3>
	
	<ul>
        <li>    ID：${customerId}</li>
        <li>名前：${THE_CUSTOMER.name}</li>
        <li>年齢：${THE_CUSTOMER.age}</li>
        <li>住所：${THE_CUSTOMER.address}</li>
    </ul>
    
			<form action="CustomerControllerServlet" method="GET" >
		   <input type="hidden" name="command" value="SAVEDELETE"/>
		    <input type="hidden" name="customerId" value="${THE_CUSTOMER.id }" />
		    
		    <a href="CustomerControllerServlet" class="back-button">戻る</a>
			<input type="submit" value="削除" class="submit-button" />
			
		</div>
	     </form>
</body>
</html>