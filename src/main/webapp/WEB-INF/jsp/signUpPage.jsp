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
						Sign Up
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-25 mx-auto"></div>
		<div class="continer mt-4 text-center">
			<h3 class="h3 text-muted text-center">
				<strong>
					User Information
				</strong>
			</h3>
			<form action="/supervisor/manage/create">
				<div class="d-flex justify-content-center flex-nowrap mt-5">
					<div class="input-group flex-nowrap w-25">
						<span class="input-group-text" id="addon-wrapping">&</span>
						<input name="name" type="text" class="TXT form-control" placeholder="Name"
						aria-label="Name" aria-describedby="addon-wrapping">
					</div>
				</div>
				<div class="d-flex justify-content-center flex-nowrap mt-4">
					<div class="input-group flex-nowrap w-25">
						<span class="input-group-text" id="addon-wrapping">&</span>
						<input name="surname" type="text" class="TXT form-control" placeholder="Surname"
						aria-label="Surname" aria-describedby="addon-wrapping">
					</div>
				</div>
				<div class="d-flex justify-content-center flex-nowrap mt-4">
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
				<div class="container mt-4 text-center">
					<input id="sbtn" type="submit" value="Sign Up"
						class="btn btn-lg btn-outline-success" disabled>
				</div>
			</form>
		</div>
	</body>
</html>
