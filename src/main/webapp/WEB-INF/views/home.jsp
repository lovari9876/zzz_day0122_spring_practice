<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- <%@ page session="false" %> --%>
<!-- session="false" 해 두면 session 적용 안된다..-->
<html>
<head>
<title>메인페이지</title>
</head>
<body>
	<h1>메인페이지</h1>

	<!-- 검증 안 된 익명이 접근 시 로그인 페이지 띄워준다. -->
	<sec:authorize access="isAnonymous()">
		<P>
			<a href="<c:url value="/login/loginForm" />">로그인</a>
		</P>
	</sec:authorize>

	<!-- 권한있는지 확인하고 권한 있으면, logout할 수 있는 폼 띄운다  -->
	<sec:authorize access="isAuthenticated()">

		<!-- ${pageContext.request.contextPath } 이 현재 경로야. /board 이거.. -->
		<!-- xml에서 아래와 같이 설정해 주었기에, /logout 으로 로그아웃페이지로 갈 수 있는 것!! -->
		<!-- <logout logout-url="/logout" logout-success-url="/" /> -->
		<form:form action="${pageContext.request.contextPath }/logout"
			method="POST">
			<input type="submit" value="로그아웃" />
		</form:form>
		<p>
			<a href="<c:url value="/loginInfo"/>">로그인 정보 확인 방법 3가지</a>
		</p>
	</sec:authorize>

	<!-- 권한에 따른 접속 가능페이지 url설정! -->
	<h3>
		<a href="<c:url value="/user/userHome" />">유저 홈</a> 
		<a href="<c:url value="/list2" />">관리자 홈</a>
	</h3>

</body>
</html>
