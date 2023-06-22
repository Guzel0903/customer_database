<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Customers Tracker App</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css">
</head>
<body>
<h1 style="font-size: 32px;">　顧客一覧</h1>
		<div class="search-container">
    <form action="CustomerControllerServlet" method="GET">
        <input type="hidden" name="command" value="SEARCH" />
        名前検索<input type="text" name="theSearchName" value="${param.theSearchName}"/>
        <input type="submit" value="検索" class="search-button" />
    </form>
    <input type="button" value="新規登録" id="add-customer-button" class="add-customer-button" />
</div>
					<table>
						<tr>
								<th>id</th>
								<th>名前</th>
								<th>年齢</th>
								<th>住所</th>
								<th>アクション</th>
						</tr>
					<c:forEach var="tempCustomer" items="${CUSTOMERS_LIST }">
					
					     <c:url	var="tempLink" value="CustomerControllerServlet">
								<c:param name="command" value="LOAD"/>
								<c:param name="customerId" value="${tempCustomer.id }"/>
						</c:url>
					
				        <!-- set up a link to delete a customer -->
				        <c:url	var="deleteLink" value="CustomerControllerServlet">
								<c:param name="command" value="DELETE"/>
								<c:param name="customerId" value="${tempCustomer.id }"/>
						
						</c:url>
						<tr>
							<td> ${tempCustomer.id }</td>
							<td>  ${tempCustomer.name }</td>
							<td>  ${tempCustomer.age }</td>
							<td>  ${tempCustomer.address}</td>
							<td>   <a href="${tempLink }" title="更新">
						              <img src="images/update-icon.svg" alt="Update" class="icon">
							          </a>
							             | 
							           <a href="${deleteLink }" class="delete-link" title="削除">
							           <img src="images/delete-icon.svg" alt="Delete" class="icon">
							          </a>
							</td>
						</tr>
				　</c:forEach>
					</table>
				</div>
		</div>
<script src="js/delete-alert.js"></script>
<script src="js/add-customer-button.js"></script>
</body>
</html>
