<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
 request.setAttribute("name", "Bob");
%>
<html>
<head><title>EL Example 4</title></head>
<body>
request URI: ${pageContext.request.requestURI} <br></br>
request name property: ${requestScope.name} <br></br>
code parameter: ${param.code} <br></br>
</body>
</html>