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
						Register New Transaction
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<h5 class="h5 text-muted text-center mt-4">
			<strong>Products List</strong>
		</h5>
		<hr class="w-25 mx-auto">
		<h5 class="h5 text-muted text-center mt-4">
			<em><u>Magazine Lots</u></em>
		</h5>
		<div class="container justify-content-md-center">
			<c:set var="magazineLots" value="${branchOffice.getInventory().getInventoryMagazineLots()}"/>
			<c:choose>
				<c:when test="${magazineLots.size() != 0}">
					<table class="table mt-4 table-bordered border rounded">
					  <thead class="table-secondary text-center">
						<tr>
						  <th scope="col">ISBN</th>
						  <th scope="col" class="col col-sm-5">Name</th>
						  <th scope="col">Available Units</th>
						  <th scope="col" class="col col-sm-3">Price P/U</th>
						  <th scope="col" ${tableMod}>Action</th>
						</tr>
					  </thead>
					  <tbody>
						  <c:forEach items="${magazineLots}" var="magazineLot">
							<tr>
							<th scope="row" class="text-center">${magazineLot.getIsbn()}</th>
							  <td>${magazineLot.getName()}</td>
							  <td>${magazineLot.getAvailableUnits()}</td>
							  <td>$${magazineLot.getPricePerUnit()}</td>
							  <td class="text-center" ${tableMod}>
								  <form action="/transactions/create/branch_office=${branchOffice.getId()}/product_type=MAGAZINE&product_id=${magazineLot.getIsbn()}">
									<input type="submit" value="Select"
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
						<h5 class="h5 text-center text-muted mb-4">
							<em>There is no magazine lots available in the inventory</em>
						</h5>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<hr class="w-75 mx-auto">
		<h5 class="h5 text-muted text-center mt-4">
			<em><u>Books Lots</u></em>
		</h5>
		<div class="container justify-content-md-center">
			<c:set var="bookLots" value="${branchOffice.getInventory().getInventoryBookLots()}"/>
			<c:choose>
				<c:when test="${bookLots.size() != 0}">
					<table class="table mt-4 table-bordered border rounded">
					  <thead class="table-secondary text-center">
						<tr>
						  <th scope="col">ISBN</th>
						  <th scope="col" class="col col-sm-5">Name</th>
						  <th scope="col">Available Units</th>
						  <th scope="col" class="col col-sm-3">Price P/U</th>
						  <th scope="col" ${tableMod}>Action</th>
						</tr>
					  </thead>
					  <tbody>
						  <c:forEach items="${bookLots}" var="bookLot">
							<tr>
							<th scope="row" class="text-center">${bookLot.getIsbn()}</th>
							  <td>${bookLot.getName()}</td>
							  <td>${bookLot.getAvailableUnits()}</td>
							  <td>$${bookLot.getPricePerUnit()}</td>
							  <td class="text-center" ${tableMod}>
								  <form action="/transactions/create/branch_office=${branchOffice.getId()}/product_type=BOOK&product_id=${bookLot.getIsbn()}">
									<input type="submit" value="Select"
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
						<h5 class="h5 text-center text-muted mb-4">
							<em>There is no book lots available in the inventory</em>
						</h5>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<hr class="w-75 mx-auto">
		<h5 class="h5 text-muted text-center mt-4">
			<em><u>Disc Lots</u></em>
		</h5>
		<div class="container justify-content-md-center">
			<c:set var="discLots" value="${branchOffice.getInventory().getInventoryDiscLots()}"/>
			<c:choose>
				<c:when test="${discLots.size() != 0}">
					<table class="table mt-4 table-bordered border rounded">
					  <thead class="table-secondary text-center">
						<tr>
						  <th scope="col" class="col col-sm-1">ID</th>
						  <th scope="col" class="col col-sm-5">Name</th>
						  <th scope="col">Available Units</th>
						  <th scope="col" class="col col-sm-3">Price P/U</th>
						  <th scope="col" ${tableMod}>Action</th>
						</tr>
					  </thead>
					  <tbody>
						  <c:forEach items="${discLots}" var="discLot">
							<tr>
							<th scope="row" class="text-center">${discLot.getId()}</th>
							  <td>${discLot.getName()}</td>
							  <td>${discLot.getAvailableUnits()}</td>
							  <td>$${discLot.getPricePerUnit()}</td>
							  <td class="text-center" ${tableMod}>
								  <form action="/transactions/create/branch_office=${branchOffice.getId()}/product_type=DISC&product_id=${discLot.getId()}">
									<input type="submit" value="Select"
										class="btn btn-primary btn-sm">
								  </form>
								</form>
							  </td>
							</tr>
						  </c:forEach>
					  </tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div class="container mt-4">
						<h5 class="h5 text-center text-muted mb-4">
							<em>There is no disc lots available in the inventory</em>
						</h5>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<hr class="w-75 mx-auto">
		<h5 class="h5 text-muted text-center mt-4">
			<em><u>Vinyl Record Lots</u></em>
		</h5>
		<div class="container justify-content-md-center pb-5">
			<c:set var="vinylRecordLots" value="${branchOffice.getInventory().getInventoryVinylRecordLots()}"/>
			<c:choose>
				<c:when test="${vinylRecordLots.size() != 0}">
					<table class="table mt-4 table-bordered border rounded">
					  <thead class="table-secondary text-center">
						<tr>
						  <th scope="col" class="col col-sm-1">ID</th>
						  <th scope="col" class="col col-sm-5">Production Name</th>
						  <th scope="col">Available Units</th>
						  <th scope="col" class="col col-sm-3">Price P/U</th>
						  <th scope="col" ${tableMod}>Action</th>
						</tr>
					  </thead>
					  <tbody>
						  <c:forEach items="${vinylRecordLots}" var="vinylRecordLot">
							<tr>
							<th scope="row" class="text-center">${vinylRecordLot.getId()}</th>
							  <td>${vinylRecordLot.getRecordProductionName()}</td>
							  <td>${vinylRecordLot.getAvailableUnits()}</td>
							  <td>$${vinylRecordLot.getPricePerUnit()}</td>
							  <td class="text-center" ${tableMod}>
								  <form action="/transactions/create/branch_office=${branchOffice.getId()}/product_type=VINYL_RECORD&product_id=${vinylRecordLot.getId()}">
									<input type="submit" value="Select"
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
						<h5 class="h5 text-center text-muted mb-4">
							<em>There is no vinyl record lots available in the inventory</em>
						</h5>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>
