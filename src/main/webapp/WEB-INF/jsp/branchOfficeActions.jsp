<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="locker" value="${!(action.equals('post') || action.equals('put')) ? 'readonly' : ''}"/>
<c:set var="manageOp" value="${action.equals('put') ? employee.getId() : ''}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./headContent.jsp"%>
		<script src="../../js/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #46e385">
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
						Branch Office
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form action="/branch_offices/manage/${action.equals('post') ? 'create' : 'update'}/${manageOp}">
				<h3 class="h3 text-muted text-center">
					<strong>Administrator Specification</strong>
				</h3>
				<div class="container justify-content-md-center">
					<c:choose>
						<c:when test="${administrators.size() != 0}">
							<div class="form-floating mt-3" >
								<select name="administratorId" class="form-select" id="floatingSelect"
									${!action.equals('post') ? 'disabled' : ''}>
									<c:choose>
										<c:when test="${action.equals('get') || action.equals('put')}">
											<option value="${branchOffice.getAdministrator().getId()}">
												${branchOffice.getAdministrator().getFirstName()}
												${branchOffice.getAdministrator().getMiddleName()}
												${branchOffice.getAdministrator().getPaternalLastName()}
												${branchOffice.getAdministrator().getMaternalLastName()}
												- ${branchOffice.getAdministrator().getRole()}
											</option>
										</c:when>
										<c:otherwise>
											<c:forEach items="${administrators}" var="administrator">
												<option value="${administrator.getId()}">
													${administrator.getFirstName()} ${administrator.getMiddleName()}
													${administrator.getPaternalLastName()} ${administrator.getMaternalLastName()}
													- ${administrator.getRole()}
												</option>
											 </c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
								<label for="floatingSelect" class"text-center">Select one of them</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="container mt-4">
								<h5 class="h5 text-center text-muted">
									<em>
										<c:choose>
											<c:when test="${allBusy}">
												All the administrators are busy
											</c:when>
											<c:otherwise>
												There is no administrators registered
											</c:otherwise>
										</c:choose>
									</em>
								</h5>
								<form></form>
								<div class="container mt-4 text-center">
									<form action="/administrators/create">
										<input type="submit" value="Register New"
											class="btn btn-outline-primary btn-sm">
									</form>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<hr class="w-50 mx-auto">
				<div class="container mt-4">
					<h3 class="h3 text-muted text-center mt-4">
						<strong>Inventory Creation</strong>
					</h3>
					<div class="text-success"><hr class="w-25 mx-auto"></div>
					<h5 class="h5 text-muted text-center mt-4">
						<strong>Description of the inventory</strong>
					</h5>
					<div class="container mt-4">
						<div class="form-floating">
						  <textarea name="description" class="TXT form-control" id="floatingTextarea2"
								style="resize:none; height: 100px" maxlength="255"
								${administrators.size() == 0 || action.equals('get') ? 'readonly' : ''}>${branchOffice.getInventory().getDescription()}</textarea>
						  <label class="text-muted" for="floatingTextarea2">What's mainly in the inventory?</label>
						</div>
					</div>
					<div class="text-success"><hr class="w-50 mx-auto"></div>
					<div class="container mt-4 text-center">
						<form></form>
						<c:choose>
							<c:when test="${action.equals('post') || action.equals('put')}">
								<input id="sbtn" type="submit" value="Complete ${action.equals('post') ? 'Registration' : 'Update'}"
									class="btn btn-outline-primary btn-lg mb-4" disabled>
							</c:when>
							<c:otherwise>
								<div class="btn-group me-4" role="group">
									<form action="/branch_offices/update/${branchOffice.getId()}">
										<input type="submit" value="Edit Subject"
											class="btn btn-outline-info btn-lg mb-4">
									</form>
								</div>
								<div class="btn-group" role="group">
									<form action="/branch_offices/manage/delete/${branchOffice.getId()}"
										method="POST">
										<input type="submit" value="Delete Subject"
											class="btn btn-outline-danger btn-lg mb-4">
									</form>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<%-- <h5 class="h5 text-muted text-center mt-4"> --%>
					<%-- 	<strong>Product List</strong> --%>
					<%-- </h5> --%>
					<%-- <h5 class="h5 text-muted text-center mt-4"> --%>
					<%-- 	Magazines --%>
					<%-- </h5> --%>
					<%-- <div class="container mt-4 mb-4 text-center"> --%>
					<%-- 	<form action="/${endpoint_sfx}/create"> --%>
					<%-- 		<input type="submit" value="Add New" --%>
					<%-- 			class="btn btn-outline-primary"> --%>
					<%-- 	</form> --%>
					<%-- </div> --%>
					<%-- <div class="text-success"><hr class="w-50 mx-auto"></div> --%>
					<%-- <h5 class="h5 text-muted text-center mt-4"> --%>
					<%-- 	Books --%>
					<%-- </h5> --%>
					<%-- <div class="container mt-4 mb-4 text-center"> --%>
					<%-- 	<form action="/${endpoint_sfx}/create"> --%>
					<%-- 		<input type="submit" value="Add New" --%>
					<%-- 			class="btn btn-outline-primary"> --%>
					<%-- 	</form> --%>
					<%-- </div> --%>
					<%-- <div class="text-success"><hr class="w-50 mx-auto"></div> --%>
					<%-- <h5 class="h5 text-muted text-center mt-4"> --%>
					<%-- 	Discs --%>
					<%-- </h5> --%>
					<%-- <div class="container mt-4 mb-4 text-center"> --%>
					<%-- 	<form action="/${endpoint_sfx}/create"> --%>
					<%-- 		<input type="submit" value="Add New" --%>
					<%-- 			class="btn btn-outline-primary"> --%>
					<%-- 	</form> --%>
					<%-- </div> --%>
					<%-- <h5 class="h5 text-muted text-center mt-4"> --%>
					<%-- 	Vinyl Records --%>
					<%-- </h5> --%>
					<%-- <div class="container mt-4 mb-4 text-center"> --%>
					<%-- 	<form action="/${endpoint_sfx}/create"> --%>
					<%-- 		<input type="submit" value="Add New" --%>
					<%-- 			class="btn btn-outline-primary"> --%>
					<%-- 	</form> --%>
					<%-- </div> --%>
				</div>
			</form>
		</div>
	</body>
</html>
