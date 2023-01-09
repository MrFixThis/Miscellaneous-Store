<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./components/headContent.jsp"%>
		<script type="module" src="${pageContext.request.contextPath}/js/branch_offices.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./components/navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #46E385">
						Actvie Branch Offices
					</span>
				</h1>
			</div>
			<div class="text-success"><hr class="w-50 mx-auto"></div>
			<div class="container mt-4">
				<div id="cardContainer" class="row justify-content-md-center"></div>
			</div>
		</div>
	</body>
</html>
