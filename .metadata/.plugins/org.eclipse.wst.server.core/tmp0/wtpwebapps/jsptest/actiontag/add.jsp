<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="customer" class="actiontag.Customer" scope="page" />
<jsp:setProperty name="customer" property="*" />
<html>
<body>
	<ul>
		<li>Name : <jsp:getProperty name="customer" property="name" /></li>
		<li>Email : <jsp:getProperty name="customer" property="email" /></li>
		<li>Phone : <jsp:getProperty name="customer" property="phone" /></li>
	</ul>
</body>
</html>