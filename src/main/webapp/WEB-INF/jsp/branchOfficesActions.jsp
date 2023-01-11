<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./components/headContent.jsp"%>
		<script type="module" src="${pageContext.request.contextPath}/js/branch_offices_actions.js" defer></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./components/navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 id="mainTittleBadge" class="h1 text-center">
					<span class="badge" style="background-color: #46E385">
						Create New Branch Office
					</span>
				</h1>
			</div>
		</div>
		<hr class="w-50 mx-auto text-success">
		<div class="continer pt-4">
			<form>
				<div id="adminSection" class="container justify-content-md-center pt-2">
					<h3 class="h3 text-muted text-center">
						<strong>Administrator</strong>
					</h3>
					<div id="adminActionZone" class="form-floating mt-3"></div>
				</div>
				<hr class="w-50 mx-auto text-success">
				<div id="inventorySection" class="container mt-4">
					<h3 class="h3 text-muted text-center mt-4">
						<strong>Inventory</strong>
					</h3>
					<hr class="w-25 mx-auto text-success">
					<h5 class="h5 text-muted text-center mt-4">
						<strong>Description of the inventory</strong>
					</h5>
					<div class="container mt-4">
						<div id="inventoryInfoArea" class="form-floating">
							<textarea name="description" class="form-control" id="floatingTextarea2"
								style="resize:none; height: 100px" maxlength="255"></textarea>
							<label class="text-muted" for="floatingTextarea2">What's mainly in the inventory?</label>
						</div>
					</div>
					<div id="productSection" class="container justify-content-md-center pt-2"></div>
				</div>
			</form>
			<div id="mainActionBtnSection" class="container mt-4 text-center">
				<button id="createOrUpdateBtn" type="button"
					class="btn btn-outline-info btn-lg mb-4 me-4">Complete Creation</button>

				<%-- <button id="transactionBtn" type="button" class="btn btn-outline-warning btn-lg mb-4 me-4">Transactions</button> --%>
				<%-- <button id="editBtn" type="button" class="btn btn-outline-info btn-lg mb-4 me-4">Edit Subject</button> --%>
				<%-- <button id="deleteBtn" type="button" class="btn btn-outline-danger btn-lg mb-4">Delete Subject</button> --%>
			</div>
		</div>
	</body>
</html>
