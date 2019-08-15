<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="로그인" />
<%@ include file="../part/head.jspf"%>

<script>
	function subLoginForm(form) {
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();
			return false;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();
			return false;
		}
		form.submit();
	}
</script>

<div class="login-box con table-common">
	<form action="./doLogin" method="POST"
		onsubmit="subLoginForm(this); return false;">
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
					<th>로그인</th>
					<td><input class="btn-a" type="submit" value="로그인"> <input
						class="btn-a" type="reset" value="취소"
						onclick="location.href = '/';"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<%@ include file="../part/foot.jspf"%>