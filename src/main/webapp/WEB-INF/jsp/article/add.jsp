<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="${board.name} 글 작성" />
<%@ include file="../part/head.jspf"%>

<script>
	function addFormSubmited(form) {

		form.title.value = form.title.value.trim();

		if (form.title.value.length == 0) {

			alert('제목을 입력해주주세요.');

			form.title.focus();

			return;

		}

		form.body.value = form.body.value.trim();

		if (form.body.value.length == 0) {

			alert('내용을 입력해주세요.');

			form.body.focus();

			return;

		}

		form.passwd.value = form.passwd.value.trim();

		if (form.passwd.value.length < 4) {

			alert('비밀번호를 4자 이상 입력해주세요.');

			form.passwd.focus();

			return;

		}

		form.submit();

	}
</script>

</head>

<body>

	<div class="con table-common">

		<form action="./doAdd" method="POST"
			onsubmit="addFormSubmited(this); return false;">

			<input type="hidden" name="boardId" value="${param.boardId}">
			<table>
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" placeholder="제목"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea placeholder="내용" rows="10" name="body"></textarea></td>
					</tr>
					<tr>
						<th></th>
						<td><input class="btn-a" type="submit" value="작성"> <input
							class="btn-a" type="button" value="취소"
							onclick="location.href = './list?boardId=${param.boardId}';"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<%@ include file="../part/foot.jspf"%>