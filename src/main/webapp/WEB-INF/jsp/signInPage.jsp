<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./components/headContent.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./components/header.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #985CC4">
						Sign In
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-25 mx-auto"></div>
		<div class="continer mt-4 text-center">
			<h3 class="h3 text-muted text-center">
				<strong>
					Access Information
				</strong>
			</h3>
			<form action="/supervisor/manage/authenticate">
				<div class="d-flex justify-content-center flex-nowrap mt-5">
					<div class="input-group flex-nowrap w-25">
						<span class="input-group-text" id="addon-wrapping">@</span>
						<input name="username" type="text" class="TXT form-control" placeholder="Username"
						aria-label="Username" aria-describedby="addon-wrapping">
					</div>
				</div>
				<div class="d-flex justify-content-center flex-nowrap mt-4">
					<div class="input-group flex-nowrap w-25">
						<span class="input-group-text" id="addon-wrapping">#</span>
						<input name="password" type="password" class="TXT form-control" placeholder="Password"
						aria-label="Password" aria-describedby="addon-wrapping">
					</div>
				</div>
				<label class="text-success text-right mt-2">Not signed up?
					<a href="/supervisor/create">Sign Up</a></label>
				<div class="container mt-4 text-center">
					<input id="sbtn" type="submit" value="Sign in"
						class="btn btn-lg btn-outline-primary" disabled>
				</div>
				<%-- <c:if test="${!isLoged}"> --%>
				<%-- 	<label class="text-danger text-right mt-2">Incorrect username or password</label> --%>
				<%-- </c:if> --%>
			</form>
		</div>
	</body>
</html>
