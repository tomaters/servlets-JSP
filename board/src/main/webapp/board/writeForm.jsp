<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="view/color.jspf"%>
<html>
<head>
<title>Bulletin</title>
</head>
<body bgcolor="${bodyback_color}">
	<div style="text-align: center">
		<b>Enter text</b> <br>
		<form method="post" name="writeform" action="/board/writeProcess.do" onsubmit="return writeSave()">
			<input type="hidden" name="num" value="${num}"> 
			<input type="hidden" name="ref" value="${ref}"> 
			<input type="hidden" name="step" value="${step}"> 
			<input type="hidden" name="depth" value="${depth}">
			<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td align="right" colspan="2" bgcolor="${value_color}"><a href="/board/list.do">Article list</a></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Author</td>
					<td width="330"><input type="text" size="10" maxlength="10" name="writer"></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Title</td>
					<td width="330"><c:if test="${num == 0}">
							<input type="text" size="40" maxlength="50" name="subject">
						</c:if> <c:if test="${num != 0}">
							<input type="text" size="40" maxlength="50" name="subject" value="[Response]">
						</c:if></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Email</td>
					<td width="330"><input type="text" size="40" maxlength="30" name="email"></td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Content</td>
					<td width="330"><textarea name="content" rows="13" cols="40"></textarea>
					</td>
				</tr>
				<tr>
					<td width="70" bgcolor="${value_color}" align="center">Password</td>
			 		<td width="330"><input type="password" size="8" maxlength="12" name="pass"></td>
 				</tr>
 				<tr>
					<td colspan=2 bgcolor="${value_color}" align="center">
 						<input type="submit" value="Submit">
						<input type="reset" value="Reset">
 						<input type="button" value="View article list" OnClick="window.location='/board/list.do'"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>