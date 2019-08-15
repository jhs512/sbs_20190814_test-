<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
	var alertMsg = '${alertMsg}'.trim();
	var redirectUrl = '${redirectUrl}'.trim();
	var historyBack = '${historyBack}' == 'true';

	if(alertMsg){
		alert(alertMsg);
	}
	if(redirectUrl){
		location.replace(redirectUrl);
	}

	if(historyBack){
		history.back();
	}
</script>