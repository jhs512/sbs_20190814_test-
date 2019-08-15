<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Article"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 상세" />
<%@ include file="../part/head.jspf"%>
<script>
	var articleId = parseInt('${param.id}');
</script>

<script>
	function enableEditMode(el) {
		var $el = $(el);
		var $tr = $el.closest('tr');
		$tr.addClass('edit-mode');
		$tr.siblings('.edit-mode').removeClass('edit-mode');
	}
	function disableEditMode(el) {
		var $el = $(el);
		var $tr = $el.closest('tr');
		$tr.removeClass('edit-mode');
	}
</script>

<style>
.editable-item {
	display: none;
}

.editable .editable-item {
	display: block;
}

.article-replies-list tr .edit-mode-visible {
	display: none;
}

.article-replies-list tr.edit-mode .edit-mode-visible {
	display: block;
}

.article-replies-list tr.edit-mode .read-mode-visible {
	display: none;
}
</style>


<div class="article-detail table-common con">
	<table>
		<colgroup>
			<col width="80">
		</colgroup>
		<tbody>
			<tr>
				<th>ID</th>
				<td><c:out value="${article.id}" /></td>
			</tr>
			<tr>
				<th>날짜</th>
				<td><c:out value="${article.regDate}" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><c:out value="${article.title}" escapeXml="true" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${article.bodyForPrint}</td>
			</tr>
		</tbody>
	</table>
</div>

<c:if test="${isLogined}">
	<h2 class="con">댓글 작성</h2>

	<div class="add-reply-form-box con table-common">

		<form name="add-reply-form" onsubmit="Article__doAddReply(this); return false;">
			<input type="hidden" name="articleId" value="${article.id}">
			<table>
				<colgroup>
					<col>
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th><input type="text" name="body" placeholder="내용"></th>
						<th><input type="submit" name="submit-btn" value="댓글작성">
					</tr>
				</thead>
			</table>
		</form>
	</div>
</c:if>

<h2 class="con">댓글 리스트</h2>

<div class="list-1 table-common con article-replies-list">
	<table>
		<colgroup>
			<col width="80">
			<col width="180">
			<col width="100">
			<col>
			<col width="120">
		</colgroup>

		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>작성자</th>
				<th>내용</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
</div>

<%@ include file="../part/foot.jspf"%>