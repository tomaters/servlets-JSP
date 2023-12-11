<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Register</title>
<link href="css/style.css" type="test/css" rel="stylesheet" />
</head>
<body>
	<c:set var="flag" value="${flag}" />
	<br />
	<center>
		<c:choose>
			<c:when test="${flag}">
				<b>Account registered</b>
				<br />
				<a href='member.mdo?cmd=login'>Log in</a>
			</c:when>
			<c:otherwise>
				<b>Try again</b>
				<br />
				<a href='member.mdo?cmd=regForm'>Try again</a>
			</c:otherwise>
		</c:choose>
	</center>
</body>
</html>