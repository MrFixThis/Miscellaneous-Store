<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="locker" value="${clients.size() == 0 ? 'disabled' : ''}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./headContent.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #7B80DB">
						<c:choose>
							<c:when test="${action.equals('post')}">
								Register New
							</c:when>
							<c:otherwise>
								See
							</c:otherwise>
						</c:choose>
						Transaction
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form action="/transactions/manage/create/${branchOfficeId}/${productId}">
				<h3 class="h3 text-muted text-center">
					<strong>Transaction Information</strong>
				</h3>
				<div class="text-success"><hr class="w-25 mx-auto"></div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Product Information</strong>
				</h5>
				<div class="container mt-4">
					<div class="input-group mb-3">
						<div class="input-group mb-3">
						  <span class="input-group-text text-muted">Product name</span>
						  <input type="text" name="productName" class="form-control"
							  value="${!action.equals('post') ? transaction.getProductName() : productName}" readonly >
					  </div>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text text-muted">Price per unit</span>
						<span class="input-group-text text-muted">$</span>
						<input type="text" name="productPrice" class="form-control"
							value="${!action.equals('post') ? transaction.getProductPrice() : productPrice}" readonly>
						<span class="input-group-text text-muted">Product type</span>
						<input type="text" name="productType" class="form-control"
							value="${!action.equals('post')
							? transaction.getProductType()
							: productType}" readonly>
					</div>
				</div>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Purchase information</strong>
				</h5>
				<hr class="w-25 mx-auto">
				<h5 class="h5 text-muted text-center mt-4">
					<em>Client</em>
				</h5>
				<div class="container justify-content-md-center">
					<c:choose>
						<c:when test="${action.equals('get') || action.equals('post') && clients.size() != 0}">
							<div class="form-floating mt-3" >
								<select name="clientId" class="form-select" id="floatingSelect" ${!action.equals('post') ? 'disabled' : ''}>
									<c:choose>
										<c:when test="${!action.equals('post')}">
											<option value="${client.getId()}">
												${transaction.getClientName()} - ${transaction.getClientIdentificationNumber()} -
												${transaction.getClientIdentificationType()}
											</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${clients}" var="client">
												<option value="${client.getId()}">
													${client.getFirstName()} ${client.getPaternalLastName()}
													- ${client.getIdentificationNumber()} - ${client.getIdentificationType()}
												</option>
											 </c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
								<label for="floatingSelect" class"text-center">${action.equals('post') ? "Select one of them" : "Transaction owner"}</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="container mt-4">
								<h5 class="h5 text-center text-muted">
									<em>
										There is no clients registered
									</em>
								</h5>
								<form></form>
								<div class="container mt-4 text-center">
									<form action="/clients/create">
										<input type="submit" value="Register New"
											class="btn btn-outline-primary btn-sm">
									</form>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="container mt-4" ${action.equals('post') ? "style='width: 600px;'" : ""}>
					<div class="input-group mb-3">
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Quantity of the product</span>
							<input type="number" name="productQuantity" class="NBR form-control"
								value="${transaction.getProductQuantity()}" min="1" max="${maxQuantity}"
									onkeydown="return false" ${!action.equals('post') || action.equals('post') && clients.size() == 0 ? 'readonly' : ''}>
							<c:if test="${!action.equals('post')}">
								<span class="input-group-text text-muted">Transaction cost</span>
								<span class="input-group-text text-muted">$</span> <input type="text" name="transactionCost" class="NBR form-control"
									value="${transaction.getTransactionCost()}"
									${!action.equals('post') || action.equals('post') && clients == null ? 'readonly' : ''}>
							</c:if>
						</div>
					</div>
				</div>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<div class="container mt-4 text-center">
					<c:choose>
						<c:when test="${action.equals('post')}">
							<input id="sbtn" type="submit" value="Complete Registration"
							   class="btn btn-outline-primary btn-lg mb-4" disabled>
						</c:when>
						<c:otherwise>
							<form></form>
							<div class="btn-group" role="group">
								<form action="/transactions/manage/delete/${transaction.getId()}"
									  method="POST">
									<input type="submit" value="Delete Subject"
										class="btn btn-outline-danger btn-lg mb-4">
								</form>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</form>
		</div>
	</body>
</html>
