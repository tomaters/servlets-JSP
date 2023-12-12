<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Bulletin</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="$bodyback_color">
	<div style="text-align: center">
		<b>View articles</b><br>
		<form>
			<table width="500" border="1" cellspacing="0" cellpadding="0"
				align="center">
				<tr height="30">
					<td align="center" width="125" bgcolor="${value_color}">Number</td>
					<td align="center" width="125" align="center">${article.num}</td>
					<td align="center" width="125" bgcolor="${value_color}">Views</td>
					<td align="center" width="125" align="center">
						${article.readcount}</td>
				</tr>
				<tr height="30">
					<td align="center" width="125" bgcolor="${value_color}">Author</td>
					<td align="center" width="125" align="center">${article.writer}</td>
					<td align="center" width="125" bgcolor="${value_color}">Date</td>
					<td align="center" width="125" align="center">${article.regdate}</td>
				</tr>
				<tr height="30">
					<td align="center" width="125" bgcolor="${value_color}">Title</td>
					<td align="center" width="375" align="center" colspan="3">
						${article.subject}</td>
				</tr>
				<tr>
					<td align="center" width="125" bgcolor="${value_color}">Content</td>
					<td align="left" width="375" colspan="3"><pre>${article.content}</pre></td>
				</tr>
				<tr height="30">
					<td colspan="4" bgcolor="${value_color}" align="right"><input type="button" value="Edit" onclick="document.location.href='/board/updateForm.do?num=${article.num}&pageNum=${pageNum}'">
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="button" value="Delete"	onclick="document.location.href='/board/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="button" value="Reply" onclick="document.location.href='/board/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}'">
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="button" value="Article list" onclick="document.location.href='/board/list.do?pageNum=${pageNum}'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>