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
						<div class="form-floating mt-3">
							<select class="form-select" id="floatingSelect" style="background-color: #f0f5f4">
								<c:forEach items="${administrators}" var="administrator">
									<option value="${administrator.getId()}">
										${administrator.getFirstName()} ${administrator.getPaternalLastName()}
									</option>
								 </c:forEach>
							</select>
							<label for="floatingSelect" class"text-center">Select one of them</label>
						</div>
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
			<hr class="w-50 mx-auto">
			<div class="container mt-4">
				<h3 class="h3 text-muted text-center mt-4">
					<strong>Inventory Creation</strong>
				</h3>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Description of the inventory</strong>
				</h5>
				<div class="container mt-4">
					<div class="form-floating">
					  <textarea class="form-control"id="floatingTextarea2"
							style="resize:none; height: 100px; background-color: #f0f5f4"
						  maxlength="255"></textarea>
					  <label class="text-muted" for="floatingTextarea2">What's mainly in the inventory?</label>
					</div>
				</div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Products specification</strong>
				</h5>
				<h6 class="h6 text-muted text-center mt-4">
					Magazines
				</h6>
				<div class="container mt-4">
					
				</div>
			</div>
		</div>
	</body>
</html>
