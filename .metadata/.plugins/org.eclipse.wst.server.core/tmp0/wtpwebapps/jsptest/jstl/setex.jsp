<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="vo" class="jstl.MemberVO"/>
<%
	// alternative to the code in the body; now scriplets are not necessary
	vo.setName("Jimmer");
	int age_ = 20;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL example</title>
</head>
<body>
	<c:set target="${vo}" property="name" value="Jimmer"/>
	<!--  two ways to set attributes -->
	<c:set target="${vo}" property="email">	jimmer@zimmer.net </c:set>
	<c:set target="${vo}" property="age" value="20"/>
	<ul>
		<li>Name: ${vo.name} </li>
		<li>Email: ${vo.email} </li>
		<li>Age: ${vo.age} </li>
	</ul>
</body>
</html>