<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Bulletin</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_color}">
	<div style="text-align:center">
		<b>Total words: ${count}</b>
		<table width="700">
			<tr>
				<td align="right" bgcolor="${value_color}">
				<a href="/board/writeForm.do">Write form</a></td>
			</tr>
		</table>
		<c:if test="${count == 0}">
			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">The bulletin is empty</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${count > 0}">
			<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
				<tr height="30" bgcolor="${value_color}">
					<td align="center" width="50">Number</td>
					<td align="center" width="250">Title</td>
					<td align="center" width="100">Author</td>
					<td align="center" width="150">Date</td>
					<td align="center" width="50">Search</td>
					<td align="center" width="100">IP</td>
				</tr>
				<c:forEach var="article" items="${articleList}">
					<tr height="30">
						<td align="center" width="50"><c:out value="${number}" /> <c:set var="number" value="${number - 1}" /></td>
						<td width="250"><c:if test="${article.depth > 0}">
								<!--  for adding icons -->
								<!--  <img src="images/level.gif" width="${5 * article.depth}" height="16">
								<img src="images/re.gif">
								 -->
							</c:if> <c:if test="${article.depth == 0}">
								<!-- <img src="images/level.gif" width="${5 * article.depth}" height="16">  -->
							</c:if> <a href="/board/content.do?num=${article.num}&pageNum=${currentPage}">
								${article.subject}
							</a> <c:if test="${article.readcount >= 20}">
								<!--  <img src="images/hot.gif" border="0" height="16">  -->
							</c:if></td>
						<td align="center" width="100"><a href="mailto:${article.email}">${article.writer}</a></td>
						<td align="center" width="150">${article.regdate}</td>
						<td align="center" width="50">${article.readcount}</td>
						<td align="center" width="100">${article.ip}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${count > 0}">
			<c:set var="temp" value="${count % pageSize == 0 ? 0 : 1}" />
			<c:set var="pageCount" value="${count / pageSize + temp}" />
			<c:set var="pageBlock" value="${3}" />
			<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}"
				integerOnly="true" />
			<c:set var="startPage" value="${result * pageBlock + 1}" />
			<c:set var="endPage" value="${startPage + pageBlock-1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			<c:if test="${startPage > pageBlock}">
				<a href="/board/list.do?pageNum=${startPage - pageBlock }">[Previous]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/board/list.do?pageNum=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
				<a href="/board/list.do?pageNum=${startPage + pageBlock}">[Next]</a>
			</c:if>
		</c:if>
	</div>
</body>
</html>