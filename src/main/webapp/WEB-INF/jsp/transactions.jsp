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
					<span class="badge" style="background-color: #7B80DB">
						Transactions
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<h3 class="h3 text-muted text-center">
				<strong>Transactions List</strong>
			</h3>
			<div class="container justify-content-md-center">
				<c:choose>
					<c:when test="${branchOffice.getTransactions().size() != 0}">
						<table class="table mt-4 table-bordered border rounded">
						  <thead class="table-secondary text-center">
							<tr>
							  <th scope="col">ID</th>
							  <th scope="col">Client Name</th>
							  <th scope="col">Client Identification</th>
							  <th scope="col" class="col col-sm-3">Transaction Cost</th>
							  <th scope="col" class="col col-sm-1">Action</th>
							</tr>
						  </thead>
						  <tbody>
							  <c:forEach items="${branchOffice.getTransactions()}" var="transaction">
								<tr>
								<th scope="row" class="text-center">${transaction.getId()}</th>
								  <td>${transaction.getClientName()}</td>
								  <td>${transaction.getClientIdentificationNumber()}</td>
								  <td>${transaction.getTrasactionCost()}</td>
								  <td class="text-center">
									<form action="/transactions/${transaction.getId()}">
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
							<h3 class="h3 text-center text-muted">
								<em>There is no transactions registered</em>
							</h3>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="container mt-4 text-center">
					<form action="/transactions/create/branch_office=${branchOffice.getId()}">
						<input type="submit" value="Register New"
							class="btn btn-outline-primary">
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
