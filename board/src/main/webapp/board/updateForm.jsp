<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="view/color.jspf"%>
<html>
<head>
<title>Bulletin</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_color}">
	<div style="text-align:center">
		<b>Edit content</b> <br>
		<form method="post" name="writeform" action="/board/updatePro.do?pageNum=${pageNum}" onsubmit="return writeSave()">
			<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Name</td>
					<td align="left" width="330"><input type="text" size="10" maxlength="10" name="writer" value="${article.writer}"> 
					<input type="hidden" name="num" value="${article.num}"></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Content</td>
					<td align="left" width="330"><input type="text" size="40" maxlength="50" name="subject" value="${article.subject}"></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Email</td>
					<td align="left" width="330"><input type="text" size="40" maxlength="30" name="email" value="${article.email}"></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Content</td>
					<td align="left" width="330"><textarea name="content" rows="13" cols="40">
 						${article.content}</textarea></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Password</td>
					<td align="left" width="330"><input type="password" size="8"
						maxlength="12" name="pass"></td>
				</tr>
				<tr>
					<td colspan=2 bgcolor="${value_color}" align="center">
					<input type="submit" value="Edit Content"> 
					<input type="reset" value="Reset"> 
					<input type="button" value="View articles" onclick="document.location.href='/board/list.do?pageNum=${pageNum}'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
