<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="endpoint_sfx" value="${type.equals('administrator') ? 'administrators' : 'workers'}"/>
<c:set var="tittle_sfx" value="${action.equals('get') ? 'administrators' : 'workers'}"/>
<c:set var="locker" value="${!(action.equals('post') || action.equals('put')) ? 'readonly' : ''}"/>
<c:set var="manageOp" value="${action.equals('put') ? employee.getId() : ''}"/>
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
					<span class="badge" style="background-color: #E3B64D">
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
						</c:choose>
						${type.equals("administrator") ? "Administrator" : "Worker"}
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form action="/${endpoint_sfx}/manage/${action.equals('post') ? 'create' : 'update'}/${manageOp}">
				<h3 class="h3 text-muted text-center">
					<strong>
						${type.equals("administrator") ? "Administrator" : "Worker"} Information
					</strong>
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
							  value="${employee.getFirstName()}" maxlength="80" ${locker}>
						  <span class="input-group-text text-muted">Middle name</span>
						  <input type="text" name="middleName" class="TXT form-control"
							  value="${employee.getMiddleName()}" maxlength="80" ${locker}>
						</div>
						<div class="input-group mb-3">
						  <span class="input-group-text text-muted">Paternal last name</span>
						  <input type="text" name="paternalLastName" class="TXT form-control"
							  value="${employee.getPaternalLastName()}" maxlength="80" ${locker}>
						  <span class="input-group-text text-muted">Paternal last name</span>
						  <input type="text" name="maternalLastName" class="TXT form-control"
							  value="${employee.getMaternalLastName()}" maxlength="80" ${locker}>
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
							<span class="input-group-text text-muted">Residence address</span>
							<input type="text" name="residenceAddress" class="TXT form-control"
								value="${employee.getResidenceAddress()}" maxlength="255" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Identification number</span>
							<input type="text" name="identificationNumber" class="NBR form-control"
								value="${employee.getIdentificationNumber()}" maxlength="25" ${locker}>
							<span class="input-group-text text-muted">Identification type</span>
							<input type="text" name="identificationType" class="TXT form-control"
								value="${employee.getIdentificationType()}" maxlength="80" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Phone number</span>
							<input type="text" name="phoneNumber" class="NBR form-control w-25"
								value="${employee.getPhoneNumber()}" maxlength="15" ${locker}>
							<span class="input-group-text text-muted">Email address</span>
							<input type="text" name="emailAddress" class="TXT form-control w-25"
								value="${employee.getEmailAddress()}" maxlength="255" ${locker}>
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
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Employment Information</strong>
				</h5>
				<div class="container mt-4">
					<div class="input-group mb-3">
						<span class="input-group-text text-muted">Role</span>
						<input type="text" name="role" class="TXT form-control"
							value="${employee.getRole()}" maxlength="80" ${locker}>
					</div>
				<div class="input-group mb-3">
					<c:if test="${type.equals('administrator') && employee.getBranchOffice() != null && action.equals('get') || type.equals('worker')}">
							<span class="input-group-text text-muted">Working BO</span>
						<span class="d-inline-block w-25"
							${action.equals('post') && branchOffices.size() == 0 ? 'tabindex="0" data-bs-toggle="tooltip" data-bs-placement="bottom" title="There is no branch offices active"' : ''}>
							<select name="branchOfficeId" class="NBR form-select ${branchOffices.size() == 0 ? 'is-invalid' : ''}"
							  ${branchOffices.size() == 0 ? 'style="pointer-events: none;"' : ''}
							  id="floatingSelect" ${action.equals('post') || action.equals('put') && branchOffices.size() != 0 ? '' : 'disabled'}>
								<c:choose>
									<c:when test="${action.equals('post') && branchOffices.size() == 0}">
										<option value="${branchOffice.getId()}">
											No B.Os
										</option>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${(type.equals('worker') && !action.equals('put') && !action.equals('post')) || type.equals('administrator') && action.equals('get')}">
												<option value="${employee.getBranchOffice().getId()}">
													${employee.getBranchOffice().getId()}
												</option>
											</c:when>
											<c:otherwise>
												<c:forEach items="${branchOffices}" var="branchOffice">
													<option value="${branchOffice.getId()}">
														${branchOffice.getId()}
													</option>
												 </c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</select>
						</span>
						</c:if>
						<span class="input-group-text text-muted">Basic salary</span>
						<span class="input-group-text text-muted">$</span>
						<input type="text" name="basicSalary" class="NBR form-control w-25"
							value="${employee.getBasicSalary()}" ${locker}>
						<span class="input-group-text text-muted">Date of hire</span>
						<input name="hDay" class="DTD form-control" type="text" maxlength="2" placeholder="DD"
							  value="${hDate[2]}" ${locker}>
						<input name="hMonth" class="DTM form-control" type="text" maxlength="2" placeholder="MM"
							value="${hDate[1]}" ${locker}>
						<input name="hYear" class="DTY form-control" type="text" maxlength="4" placeholder="YY"
							value="${hDate[0]}" ${locker}>
					</div>
				</div>
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
								<form action="/${endpoint_sfx}/update/${employee.getId()}">
									<input type="submit" value="Edit Subject"
										class="btn btn-outline-info btn-lg mb-4">
								</form>
							</div>
							<div class="btn-group" role="group">
								<form action="/${endpoint_sfx}/manage/delete/${employee.getId()}"
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
