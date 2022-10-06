<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="endpoint_sfx" value="${type.equals('administrator') ? 'administrators' : 'workers'}"/>
<c:set var="tittle_sfx" value="${action.equals('get') ? 'administrators' : 'workers'}"/>
<html lang="en">
    <head>
		<%@include file="./headContent.jsp"%>
    </head>
    <body class="pt-5">
		<%@include file="./navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #dee34d">
						<c:choose>
							<c:when test="${action.equals('post')}">
								Register New
							</c:when>
							<c:when test="${action.equals('get')}">
								See
							</c:when>
							<c:when test="${action.equals('put')}">
								Update New
							</c:when>
							<c:otherwise>
								Delete
							</c:otherwise>
						</c:choose>
						${type.equals("administrator") ? "Administrator" : "Worker"}
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
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
					  <input type="text" class="form-control">
					  <span class="input-group-text text-muted">Middle name</span>
					  <input type="text" class="form-control">
					</div>
					<div class="input-group mb-3">
					  <span class="input-group-text text-muted">Paternal last name</span>
					  <input type="text" class="form-control">
					  <span class="input-group-text text-muted">Paternal last name</span>
					  <input type="text" class="form-control">
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
						<span class="input-group-text text-muted">Identification number</span>
						<input type="text" class="form-control">
						<span class="input-group-text text-muted">Identification type</span>
						<input type="text" class="form-control">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text text-muted">Phone number</span>
						<input type="text" class="form-control w-25">
						<span class="input-group-text text-muted">Email address</span>
						<input type="text" class="form-control w-25">
						<span class="input-group-text text-muted">Date of birth</span>
						<input type="text" maxlength="2" class="form-control" placeholder="DD" aria-label="DD">
						<input type="text" maxlength="2" class="form-control" placeholder="MM" aria-label="MM">
						<input type="text" maxlength="2" class="form-control" placeholder="YY" aria-label="YY">
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
					<c:if test="${!(employee.getBranchOffice() == null)}">
					  <span class="input-group-text text-muted">Working BO</span>
					  <input type="text" class="form-control" placeholder="${employee.getBranchOffice().getId()}" readonly>
					</c:if>
					<span class="input-group-text text-muted">Role</span>
					<input type="text" class="form-control w-25">
					<span class="input-group-text text-muted">Basic salary</span>
					<input type="text" class="form-control w-25">
					<span class="input-group-text text-muted">Date of hire</span>
					<input type="text" maxlength="2" class="form-control" placeholder="DD" aria-label="DD">
					<input type="text" maxlength="2" class="form-control" placeholder="MM" aria-label="MM">
					<input type="text" maxlength="2" class="form-control" placeholder="YY" aria-label="YY">
				</div>
			</div>
			<div class="text-success"><hr class="w-50 mx-auto"></div>
			<div class="container mt-4 text-center">
				<c:choose>
					<c:when test="action.equals('post')">
						<form action="/${endpoint_sfx}/new/register">
							<input type="submit" value="Finish Registration"
								class="btn btn-outline-primary btn-lg mb-4">
						</form>
					</c:when>
					<c:otherwise>
						<div class="btn-group me-4" role="group">
							<form action="/${endpoint_sfx}/edit/${employee.getId()}">
								<input type="submit" value="Edit Subject"
									class="btn btn-outline-info btn-lg mb-4">
							</form>
						</div>
						<div class="btn-group" role="group">
							<form action="/${endpoint_sfx}/delete/${employee.getId()}">
								<input type="submit" value="Delete Subject"
									class="btn btn-outline-danger btn-lg mb-4">
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>
