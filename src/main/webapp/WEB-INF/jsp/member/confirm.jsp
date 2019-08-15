<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../part/head.jspf" %>


<c:if test="${success != null}">
	<c:if test="${success}">
		<script>	
			alert("이메일 인증이 성공되었습니다.");
			if(confirm("로그인 페이지로 이동하시겠습니까 ? ")){
				location.replace("/member/login");
			}
		</script>
		<h1>환영합니다.</h1>	
	</c:if>
	
	<c:if test="${!success}">
		<script>	
			alert("이메일 인증이 실패되었습니다.");
		</script>	
	</c:if>
</c:if>
	
		
<%@ include file="../part/foot.jspf"%>