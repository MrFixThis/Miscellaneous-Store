<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./headContent.jsp"%>
    </head>
    <body class="pt-5">
		<%@include file="./navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #46e385">
						Actvie Branch Offices
					</span>
				</h1>
			</div>
			<div class="text-success"><hr class="w-50 mx-auto"></div>
			<c:choose>
				<c:when test="${branchOffices.size() != 0}">
					<div class="container mt-4">
						<div class="row justify-content-md-center">
							<c:forEach items="${branchOffices}" var="branchOffice">
								<div class="col-md-auto">
									<div class="card text-center mb-4" style="width: 18rem; background-color: #FAEFFF">
									  <div class="card-body">
										<h5 class="card-title">Branch Office #${branchOffice.getId()}</h5>
										<p class="card-text">Managed by ${branchOffice.getAdministrator()
											.getFirstName()} ${branchOffice.getAdministrator()
											.getPaternalLastName()}</p>
										<a href="#" class="btn btn-primary">See more</a>
									  </div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="container mt-4">
						<h2 class="h2 text-center text-muted">
							<em>There is no branch offices active</em>
						</h2>
						<div class="container mt-4 text-center">
							<form action="/branch_offices/new">
								<input type="submit" value="Register New"
									class="btn btn-outline-primary">
							</form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>
