<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>
<% response.setStatus(HttpServletResponse.SC_OK); %>
<html>
<head><title>error</title></head>
<body>
error...<br></br>
type: <%= exception.getClass().getName() %> <br></br>
message: <b><%= exception.getMessage() %></b>