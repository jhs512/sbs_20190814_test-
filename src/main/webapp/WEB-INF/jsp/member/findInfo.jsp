<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="아이디/비번찾기" />
<%@ include file="../part/head.jspf"%>

<script>
	function searchIdFormSubmited(form) {
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

	function searchPwFormSubmited(form) {
		var emailP = /\w+@\w+\.\w+\.?\w*/;

		form.name.value = form.name.value.trim();

		if (form.name.value.length == 0) {

			alert('이름을 입력해주세요.');

			form.name.focus();

			return;

		}

		form.loginId.value = form.loginId.value.trim();

		if (form.loginId.value.length == 0) {

			alert('아이디를 입력해주세요.');

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

</head>

<body>

	<h2 class="con ">아이디 찾기</h2>

	<div class="con table-common">

		<form action="./doSearchId" method="POST"
			onsubmit="searchIdFormSubmited(this); return false;">

			<table>
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" placeholder="이름"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" placeholder="이메일"></td>
					</tr>
					<tr>
						<th></th>
						<td><input class="btn-a" type="submit" value="아이디찾기">
							<input class="btn-a" type="button" value="취소"
							onclick="location.href = './login';"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<h2 class="con ">비밀번호 찾기</h2>

	<div class="con table-common">

		<form action="./doSearchPw" method="POST"
			onsubmit="searchPwFormSubmited(this); return false;">

			<table>
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" placeholder="이름"></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="loginId" placeholder="아이디"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" placeholder="이메일"></td>
					</tr>
					<tr>
						<th></th>
						<td><input class="btn-a" type="submit" value="비밀번호찾기">
							<input class="btn-a" type="button" value="취소"
							onclick="location.href = './login';"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<%@ include file="../part/foot.jspf"%>