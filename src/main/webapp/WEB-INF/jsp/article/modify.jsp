<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../part/head.jspf"%>

<script>
	function modifyFormSubmited(form) {
		form.title.value = form.title.value.trim();

		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요.');
			form.title.focus();

			return;
		}

		form.body.value = form.body.value.trim();

		if (form.body.value.length == 0) {
			alert('내용을 입력해주세요.');
			form.body.focus();

			return;
		}

		form.submit();
	}
</script>

</head>

<body>

	<div class="con table-common">
		<form action="./doModify" method="POST"
			onsubmit="modifyFormSubmited(this); return false;">

			<input type="hidden" name="id" value="${article.id}"> <input
				type="hidden" name="boardId" value="${param.boardId}">
			<table>
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${article.title}"
							placeholder="제목"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="body" placeholder="내용" rows="10">${article.body}</textarea></td>
					</tr>
					<tr>
						<th></th>
						<td><input class="btn-a" type="submit" value="수정"> <input
							class="btn-a" type="button" value="취소"
							onclick="location.href = './detail?id=${article.id}';"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<%@ include file="../part/foot.jspf"%>