<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../part/head.jspf"%>

<script>
	var joinFormSubmited = false;

	function submitJoinForm(form) {
		if (joinFormSubmited) {
			alert('처리중입니다.');
			return false;
		}

		var emailP = /\w+@\w+\.\w+\.?\w*/;

		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value.length == 0) {
			alert('비밀번호 확인을 입력해주세요.');
			form.loginPwConfirm.focus();
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length < 4) {
			alert('비밀번호를 4자 이상 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		if (form.loginPw.value != form.loginPwConfirm.value) {
			alert('비밀번호가 같지 않습니다.');
			form.loginPwConfirm.value = "";
			form.loginPwConfirm.focus();
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
		joinFormSubmited = true;
	}
</script>

<div class="login-box con table-common">
	<form action="./doJoin" method="POST"
		onsubmit="submitJoinForm(this); return false;">
		<table>
			<colgroup>
				<col width="150">
			</colgroup>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="loginId" autocomplete="off"
						autofocus="autofocus" placeholder="아이디를 입력해주세요."></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="loginPw"
						placeholder="비밀번호를 입력해주세요."></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="loginPwConfirm"
						placeholder="비밀번호 확인을 입력해주세요."></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" autocomplete="off"
						placeholder="이름을 입력해주세요."></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="email" placeHolder="이메일을 입력해주세요."></td>
				</tr>
				<tr>
					<th>가입</th>
					<td><input class="btn-a" type="submit" value="가입"> <input
						class="btn-a" type="reset" value="취소"
						onclick="location.href = '/';"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<%@ include file="../part/foot.jspf"%>