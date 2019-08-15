<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.cuni.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="${board.name} 글 리스트" />
<%@ include file="../part/head.jspf"%>

<div class="con">

	<form action="./list" method="GET">
		<c:forEach items="${param}" var="entry">
			<c:if test="${entry.key != 'arrayType'}">
				<input type="hidden" name="${entry.key}" value="${entry.value}" />
			</c:if>
		</c:forEach>

		<select name="arrayType">
			<option value="">정렬기준</option>
			<option value="new">최신순</option>
			<option value="old">오래된순</option>
		</select>

		<c:if test="${param.arrayType != null && param.arrayType != ''}">
			<script>
				$('select[name="arrayType"]').val('${param.arrayType}');
			</script>
		</c:if>
		<input type="submit" value="정렬" />
	</form>

</div>

<br>

<div class="list-1 table-common con">
	<table>
		<colgroup>
			<col width="80">
			<col width="180">
			<col width="100">
			<col>
			<col width="100">
			<col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>작성자</th>
				<th>제목</th>
				<th>댓글</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${pagedListRs.list}">
				<tr>
					<td><c:out value="${article.id}" /></td>
					<td><c:out value="${article.regDate}" /></td>
					<td><c:out value="${article.extra.articleWriter}"/></td>
					<td><a href="detail?id=${article.id}&boardId=${param.boardId}"><c:out
								value="${article.title}" /></a></td>
					<td><c:out value="${article.extra.repliesCount}" /></td>
					<td><c:if test="${loginedMemberId == article.memberId || (loginedMember != null && loginedMember.permissionLevel > 0)}">
							<a onclick="return confirm('정말 삭제하시겠습니까?');" href="/article/doDelete?id=${article.id}&boardId=${param.boardId}">삭제</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="con text-align-center">

	<form action="./list" method="GET">

		<c:forEach items="${param}" var="entry">
			<c:if
				test="${entry.key != 'searchType' && entry.key != 'searchKeyword'}">
				<input type="hidden" name="${entry.key}" value="${entry.value}" />
			</c:if>
		</c:forEach>

		<select name="searchType">
			<option value="title">제목</option>
			<option value="body">내용</option>
		</select>

		<c:if test="${param.searchType != null && param.searchType != ''}">
			<script>
				$('select[name="searchType"]').val('${param.searchType}');
			</script>
		</c:if>

		<input type="text" name="searchKeyword" placeholder="검색어"
			value="${param.searchKeyword}"> <input type="submit"
			value="검색" />
	</form>

</div>

<div class="page-nav con text-align-center line-height-0-ch-only">
	<ul class="row inline-block">
		<c:forEach var="currentPage" begin="1" end="${pagedListRs.lastPage}">
			<!-- URL 초기값 -->
			<c:url var="url" value="">
				<c:forEach items="${param}" var="entry">
					<c:if test="${entry.key != 'page'}">
						<c:param name="${entry.key}" value="${entry.value}" />
					</c:if>
				</c:forEach>
				<c:param name="page" value="${currentPage}" />
			</c:url>
			<c:set var="aElclass"
				value="${currentPage == pagedListRs.page ? 'red bold' : ''}" />
			<li class="cell"><a href="${url}" class="block ${aElclass}">${currentPage}</a></li>
		</c:forEach>
	</ul>
</div>


<%@ include file="../part/foot.jspf"%>