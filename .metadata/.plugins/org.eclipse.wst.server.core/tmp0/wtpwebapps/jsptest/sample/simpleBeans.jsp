<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//String message = request.getParameter("message");
%>
<!--  uses SimpleData.java, located in the Java Resources folder -->
<jsp:useBean id="msg" class="sample.SimpleData" />
<%-- SimpleData msg = new SimpleData(); --%>

<jsp:setProperty name="msg" property="message" />
<%-- msg.setMessage(?); --%>

<h1>simple beans results</h1>
<hr color="red"></hr>
<br></br>
<font size="5"> Message : <jsp:getProperty name="msg"
		property="message" /> <%--<%=message%> --%>
</font>