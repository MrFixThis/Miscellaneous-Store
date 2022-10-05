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
					<span class="badge" style="background-color: #dee34d">
						Register New Branch Office
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<h3 class="h3 text-muted text-center">
				<strong>Administrator Specification</strong>
			</h3>
			<div class="container justify-content-md-center">
				<c:choose>
					<c:when test="${administrators.size() != 0}">
							<table class="table mt-4 table-bordered border rounded">
							  <thead class="table-light text-center">
								<tr>
								  <th scope="col">ID</th>
								  <th scope="col">First Name</th>
								  <th scope="col">Last Name</th>
								  <th scope="col" class="col col-sm-3">Email</th>
								  <th scope="col" class="col col-sm-1">Action</th>
								</tr>
							  </thead>
							  <tbody>
								  <c:forEach items="${administrators}" var="administrator">
									<tr>
									<th scope="row" class="text-center">${administrator.getId()}</th>
									  <td>${administrator.getFirstName()}</td>
									  <td>${administrator.getPaternalLastName()}</td>
									  <td>${administrator.getEmailAddress()}</td>
									  <td class="text-center">
										<form action="/administrators/${administrator.getId()}">
											<input type="submit" value="See more"
												class="btn btn-primary btn-sm">
										</form>
									  </td>
									</tr>
								  </c:forEach>
							  </tbody>
							</table>
					</c:when>
					<c:otherwise>
						<div class="container mt-4">
							<h5 class="h5 text-center text-muted">
								<em>There is no administrators registered</em>
							</h5>
							<div class="container mt-4 text-center">
								<form action="/administrators/new">
									<input type="submit" value="Register New"
										class="btn btn-outline-primary btn-sm">
								</form>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>
