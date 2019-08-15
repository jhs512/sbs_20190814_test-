<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../part/head.jspf"%>

<script>
	function modifyFormSubmited(form) {
		var emailP = /\w+@\w+\.\w+\.?\w*/;

		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}

		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}

		if (!email.match(emailP)) {
			alert("이메일 형식에 맞지 않습니다.");
			return false;
		}

		form.submit();
	}
</script>

<div class="con table-common">
	<form action="./doModify" method="POST"
		onsubmit="modifyFormSubmited(this); return false;">
		<table>
			<colgroup>
				<col width="150">
			</colgroup>
			<tbody>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" value="${member.name}"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="email" value="${member.email}"></td>
				</tr>
				<tr>
					<th>수정</th>
					<td><input class="btn-a" type="submit" value="수정"> <input
						class="btn-a" type="reset" value="취소"
						onclick="location.href = '/';"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<button class="btn-a" type="button"
		onclick="if ( confirm('정말 탈퇴하시겠습니까?') ) location.href = './doSecession'">회원탈퇴</button>

</div>
<%@ include file="../part/foot.jspf"%>