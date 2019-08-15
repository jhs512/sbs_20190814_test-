<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="마이페이지" />
<%@ include file="../part/head.jspf"%>

<div class="article-detail table-common con">
	<table>
		<colgroup>
			<col width="80">
		</colgroup>
		<tbody>
			<tr>
				<th>이름</th>
				<td><c:out value="${member.name}" escapeXml="true" /></td>
			</tr>
			<tr>
				<th>ID</th>
				<td><c:out value="${member.loginId}" /></td>
			</tr>
			<tr>
				<th>날짜</th>
				<td><c:out value="${member.regDate}" /></td>
			</tr>
		</tbody>
	</table>
</div>


<%@ include file="../part/foot.jspf"%>