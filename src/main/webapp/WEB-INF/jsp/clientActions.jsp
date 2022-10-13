<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="locker" value="${!(action.equals('post') || action.equals('put')) ? 'readonly' : ''}"/>
<c:set var="manageOp" value="${action.equals('put') ? client.getId() : ''}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./headContent.jsp"%>
		<script src="../../js/data_validation.js" defer></script>
		<script src="../../js/tooltip.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #D362D9">
						<c:choose>
							<c:when test="${action.equals('post')}">
								Register New
							</c:when>
							<c:when test="${action.equals('get')}">
								See
							</c:when>
							<c:when test="${action.equals('put')}">
								Update
							</c:when>
							<c:otherwise>
								Delete
							</c:otherwise>
						</c:choose>
						Client
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form action="/clients/manage/${action.equals('post') ? 'create' : 'update'}/${manageOp}">
				<h3 class="h3 text-muted text-center">
					<strong>Client Information</strong>
				</h3>
				<div class="text-success"><hr class="w-25 mx-auto"></div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Names and surnames</strong>
				</h5>
				<div class="container mt-4">
					<div class="input-group mb-3">
						<div class="input-group mb-3">
						  <span class="input-group-text text-muted">First name</span>
						  <input type="text" name="firstName" class="TXT form-control"
							  value="${client.getFirstName()}" maxlength="80" ${locker}>
						  <span class="input-group-text text-muted">Middle name</span>
						  <input type="text" name="middleName" class="TXT form-control"
							  value="${client.getMiddleName()}" maxlength="80" ${locker}>
						</div>
						<div class="input-group mb-3">
						  <span class="input-group-text text-muted">Paternal last name</span>
						  <input type="text" name="paternalLastName" class="TXT form-control"
							  value="${client.getPaternalLastName()}" maxlength="80" ${locker}>
						  <span class="input-group-text text-muted">Paternal last name</span>
						  <input type="text" name="maternalLastName" class="TXT form-control"
							  value="${client.getMaternalLastName()}" maxlength="80" ${locker}>
						</div>
					  </div>
					</div>
				</div>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Personal data</strong>
				</h5>
				<div class="container mt-4">
					<div class="input-group mb-3">
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Email address</span>
							<input type="text" name="emailAddress" class="TXT form-control"
								value="${client.getEmailAddress()}" maxlength="255" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Identification number</span>
							<input type="text" name="identificationNumber" class="NBR form-control"
								value="${client.getIdentificationNumber()}" maxlength="25" ${locker}>
							<span class="input-group-text text-muted">Identification type</span>
							<input type="text" name="identificationType" class="TXT form-control"
								value="${client.getIdentificationType()}" maxlength="80" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Phone number</span>
							<input type="text" name="phoneNumber" class="NBR form-control w-25"
								value="${client.getPhoneNumber()}" maxlength="15" ${locker}>
							<span class="input-group-text text-muted">Date of birth</span>
							<input name="bDay" class="DTD form-control" type="text" maxlength="2" placeholder="DD"
								value="${bDate[2]}" ${locker}>
							<input name="bMonth" class="DTM form-control" type="text" maxlength="2" placeholder="MM"
								value="${bDate[1]}" ${locker}>
							<input name="bYear" class="DTY form-control" type="text" maxlength="4" placeholder="YY"
								value="${bDate[0]}" ${locker}>
						</div>
					  </div>
					</div>
				</div>
					<c:if test="${action.equals('get')}">
						<div class="text-success"><hr class="w-50 mx-auto"></div>
						<h5 class="h5 text-muted text-center mt-4">
							<strong>Shopping Information</strong>
						</h5>
						<div class="container mt-4">
							<div class="input-group mb-3">
								<span class="input-group-text text-muted">Purchases Number</span>
								<input type="text" name="numberOfPurchases" class="TXT form-control"
									value="${client.getPurchasesNumber()}" ${locker}>
								<span class="input-group-text text-muted">Client of branch office</span>
								<select class="NBR form-select" id="floatingSelect">
									<c:forEach items="${cBranchOffices}" var="branchOffice">
										<option value="${branchOffice.getId()}">
											${branchOffice.getId()}
										</option>
									 </c:forEach>
								</select>
							</div>
						</div>
					</c:if>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<div class="container mt-4 text-center">
					<c:choose>
						<c:when test="${action.equals('post') || action.equals('put')}">
							<input id="sbtn" type="submit" value="Complete ${action.equals('post') ? 'Registration' : 'Update'}"
								class="btn btn-outline-primary btn-lg mb-4" disabled>
						</c:when>
						<c:otherwise>
							<form></form>
							<div class="btn-group me-4" role="group">
								<form action="/clients/update/${client.getId()}">
									<input type="submit" value="Edit Subject"
										class="btn btn-outline-info btn-lg mb-4">
								</form>
							</div>
							<div class="btn-group" role="group">
								<form action="/clients/manage/delete/${client.getId()}"
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
