<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="locker" value="${!(action.equals('post') || action.equals('put')) ? 'readonly' : ''}"/>
<c:set var="manageOp" value="${action.equals('put') ? magazineLot.getIsbn() : inventoryId}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./components/headContent.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./components/navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #F0787E">
						<c:choose>
							<c:when test="${action.equals('post')}">
								Add New
							</c:when>
							<c:when test="${action.equals('get')}">
								See
							</c:when>
							<c:when test="${action.equals('put')}">
								Update
							</c:when>
						</c:choose>
						Magazine Lot
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form action="/magazine_lots/manage/${action.equals('post') ? 'create' : 'update'}/${manageOp}">
				<h3 class="h3 text-muted text-center">
					<strong>Magazine Lot Information</strong>
				</h3>
				<div class="text-success"><hr class="w-25 mx-auto"></div>
				<c:if test="${action.equals('get')}">
					<h5 class="h5 text-muted text-center mt-4">
						<strong>Identification</strong>
					</h5>
					<div class="container mt-4">
						<div class="input-group mb-3">
							<div class="input-group mb-3">
							  <span class="input-group-text text-muted">ISBN</span>
							  <input type="text" name="isbn" class="TXT form-control"
								  value="${magazineLot.getIsbn()}" readonly>
						  </div>
						</div>
					</div>
					<div class="text-success"><hr class="w-50 mx-auto"></div>
				</c:if>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Basic Data</strong>
				</h5>
				<div class="container mt-4">
					<div class="input-group mb-3">
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Name</span>
							<input type="text" name="name" class="TXT form-control"
								value="${magazineLot.getName()}" maxlength="255" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Publisher name</span>
							<input type="text" name="publisherName" class="TXT form-control w-25"
								value="${magazineLot.getPublisherName()}" maxlength="255" ${locker}>
							<span class="input-group-text text-muted">Price per unit</span>
							<span class="input-group-text text-muted">$</span>
							<input type="text" name="pricePerUnit" class="NBR form-control"
								value="${magazineLot.getPricePerUnit()}" ${locker}>
							<span class="input-group-text text-muted">Available units</span>
							<input type="text" name="availableUnits" class="NBR form-control"
								value="${magazineLot.getAvailableUnits()}" ${locker}>
						</div>
					  </div>
					</div>
				</div>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<div class="container mt-4 text-center">
					<c:choose>
						<c:when test="${action.equals('post') || action.equals('put')}">
							<input id="sbtn" type="submit" value="Complete ${action.equals('post') ? 'Adition' : 'Update'}"
								class="btn btn-outline-primary btn-lg mb-4" disabled>
						</c:when>
						<c:otherwise>
							<form></form>
							<div class="btn-group me-4" role="group">
								<form action="/magazine_lots/update/${magazineLot.getIsbn()}">
									<input type="submit" value="Edit Subject"
										class="btn btn-outline-info btn-lg mb-4">
								</form>
							</div>
							<div class="btn-group" role="group">
								<form action="/magazine_lots/manage/delete/${magazineLot.getIsbn()}">
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
