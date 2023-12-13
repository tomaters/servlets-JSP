<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="view/color.jspf"%>
<html>
<head>
<title>Bulletin</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
<!--
function deleteSave(){
	if(document.delForm.pass.value==''){
	alert("비밀번호를 입력하십시요.");
	document.delForm.pass.focus();
	return false;
	}
}
//--> 
</script>
</head>
<body bgcolor="${bodyback_c}">
	<div style="text-align:center">
		<b>Delete</b> <br>
		<form method="POST" name="deleteform" action="/board/deleteProcess.do?pageNum=${pageNum}"> <!--  onsubmit="return deleteSave()"  -->
			<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
				<tr height="30">
					<td align=center bgcolor="${value_}"><b>Enter password</b></td>
				</tr>
				<tr height="30">
					<td align=center>Password: 
						<input type="password" name="pass" size="8" maxlength="12">
 						<input type="hidden" name="num" value="${num}"></td>
				</tr>
				<tr height="30">
					<td align=center bgcolor="${value_c}">
 						<input type="submit" value="Delete">
						<input type="button" value="Articles list" onclick="document.location.href=/board/list.do?pageNum=${pageNum}'"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>