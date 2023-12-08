<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="actiontag.Customer, java.util.HashMap"%>
<%
	Customer customer = new Customer();
	customer.setName("Carholder");
	customer.setEmail("carholder@email.com");
	customer.setPhone("010-1234-5678");
	request.setAttribute("customer", customer);
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("name", "Bonana");
	map.put("maker", "Myonday Automotives");
	request.setAttribute("car", map);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL example</title>
</head>
<body>
	<ul>
		<li>Name : ${customer.name}</li>
		<li>Email : ${customer.email}</li>
		<li>Phone : ${customer.phone}</li>
	</ul>
	<ul>
		<li>Name : <%= customer.getName() %></li>
		<li>Mail : <%= customer.getEmail() %></li>
		<li>Phone : <%= customer.getPhone() %></li>
	</ul>
	<ul>
		<li>Car : ${car.name} </li>
		<li>Car maker : ${car.maker} </li>
	</ul>
</body>
</html>