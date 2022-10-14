<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="manageOp" value="${action.equals('put') ? branchOffice.getId() : ''}"/>
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
					<span class="badge" style="background-color: #46E385">
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
					<strong>
						Administrator
						<c:choose>
							<c:when test="${action.equals('post')}">
								Specification
							</c:when>
							<c:when test="${action.equals('put') && branchOffice.getAdministrator() == null}">
								Assignment
							</c:when>
						</c:choose>
						</strong>
				</h3>
				<div class="container justify-content-md-center">
					<c:choose>
					<c:when test="${action.equals('post') && administrators.size() != 0 || action.equals('put') && administrators.size() != 0 || action.equals('put') && branchOffice.getAdministrator() != null || action.equals('get')}">
							<div class="form-floating mt-3" >
								<select name="administratorId" class="form-select" id="floatingSelect"
									${!(action.equals('post') || action.equals('put') && branchOffice.getAdministrator() == null) ? 'disabled' : ''}>
									<c:choose>
										<c:when test="${action.equals('get') || action.equals('put') && branchOffice.getAdministrator() != null}">
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
				<c:if test="${!(action.equals('put') && branchOffice.getAdministrator() == null)}">
					<hr class="w-50 mx-auto">
					<div class="container mt-4">
						<h3 class="h3 text-muted text-center mt-4">
							<strong>Inventory${action.equals("post") ? " Creation" : ""}</strong>
						</h3>
						<div class="text-success"><hr class="w-25 mx-auto"></div>
						<h5 class="h5 text-muted text-center mt-4">
							<strong>Description of the inventory</strong>
						</h5>
						<div class="container mt-4">
							<div class="form-floating">
							  <textarea name="description" class="TXT form-control" id="floatingTextarea2"
									style="resize:none; height: 100px" maxlength="255"
									${action.equals('post') && administrators.size() == 0 || action.equals('get') ? 'readonly' : ''}>${branchOffice.getInventory().getDescription()}</textarea>
							  <label class="text-muted" for="floatingTextarea2">What's mainly in the inventory?</label>
							</div>
						</div>
					<c:if test="${!action.equals('post')}">
						<hr class="w-50 mx-auto">
						<h5 class="h5 text-muted text-center mt-4">
							<strong>Product List</strong>
						</h5>
						<div class="text-success"><hr class="w-25 mx-auto"></div>
						<h5 class="h5 text-muted text-center mt-4">
							Magazines Lots
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
										  <th scope="col">Action</th>
										</tr>
									  </thead>
									  <tbody>
										  <c:forEach items="${magazineLots}" var="magazineLot">
											<tr>
											<th scope="row" class="text-center">${magazineLot.getIsbn()}</th>
											  <td>${magazineLot.getName()}</td>
											  <td>${magazineLot.getAvailableUnits()}</td>
											  <td>${magazineLot.getPricePerUnit()}</td>
											  <td class="text-center">
												<form action="/magazine_lots/${magazineLot.getIsbn()}">
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
										<h5 class="h5 text-center text-muted mb-4">
											<em>There is no magazine lots available in the inventory</em>
										</h5>
									</div>
								</c:otherwise>
							</c:choose>
							<c:if test="${action.equals('put')}">
								<div class="container mt-4 mb-4 text-center">
									<form action="/magazine_lots/create">
										<input type="submit" value="Add New Lot"
											class="btn btn-outline-primary">
									</form>
								</div>
							</c:if>
						</div>
						<div class="text-success"><hr class="w-50 mx-auto"></div>
						<h5 class="h5 text-muted text-center mt-4">
							Books Lots
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
										  <th scope="col">Action</th>
										</tr>
									  </thead>
									  <tbody>
										  <c:forEach items="${bookLots}" var="bookLot">
											<tr>
											<th scope="row" class="text-center">${bookLot.getIsbn()}</th>
											  <td>${bookLot.getName()}</td>
											  <td>${bookLot.getAvailableUnits()}</td>
											  <td>${bookLot.getPricePerUnit()}</td>
											  <td class="text-center">
												<form action="/book_loots/${bookLot.getIsbn()}">
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
										<h5 class="h5 text-center text-muted mb-4">
											<em>There is no book lots available in the inventory</em>
										</h5>
									</div>
								</c:otherwise>
							</c:choose>
							<c:if test="${action.equals('put')}">
								<div class="container mt-4 mb-4 text-center">
									<form action="/book_lots/create">
										<input type="submit" value="Add New Lot"
											class="btn btn-outline-primary">
									</form>
								</div>
							</c:if>
						</div>
						<div class="text-success"><hr class="w-50 mx-auto"></div>
						<h5 class="h5 text-muted text-center mt-4">
							Disc Lots
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
										  <th scope="col">Action</th>
										</tr>
									  </thead>
									  <tbody>
										  <c:forEach items="${discLots}" var="discLot">
											<tr>
											<th scope="row" class="text-center">${discLot.getId()}</th>
											  <td>${discLot.getName()}</td>
											  <td>${discLot.getAvailableUnits()}</td>
											  <td>${discLot.getPricePerUnit()}</td>
											  <td class="text-center">
												<form action="/disc_loots/${discLot.getId()}">
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
										<h5 class="h5 text-center text-muted mb-4">
											<em>There is no disc lots available in the inventory</em>
										</h5>
									</div>
								</c:otherwise>
							</c:choose>
							<c:if test="${action.equals('put')}">
								<div class="container mt-4 mb-4 text-center">
									<form action="/disc_lots/create">
										<input type="submit" value="Add New Lot"
											class="btn btn-outline-primary">
									</form>
								</div>
							</c:if>
						</div>
						<div class="text-success"><hr class="w-50 mx-auto"></div>
						<h5 class="h5 text-muted text-center mt-4">
							Vinyl Record Lots
						</h5>
						<div class="container justify-content-md-center">
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
										  <th scope="col">Action</th>
										</tr>
									  </thead>
									  <tbody>
										  <c:forEach items="${vinylRecordLots}" var="vinylRecordLot">
											<tr>
											<th scope="row" class="text-center">${vinylRecordLot.getId()}</th>
											  <td>${vinylRecordLot.getRecordProductionName()}</td>
											  <td>${vinylRecordLot.getAvailableUnits()}</td>
											  <td>${vinylRecordLot.getPricePerUnit()}</td>
											  <td class="text-center">
												<form action="/vinyl_record_loots/${vinylRecordLot.getId()}">
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
										<h5 class="h5 text-center text-muted mb-4">
											<em>There is no vinyl record lots available in the inventory</em>
										</h5>
									</div>
								</c:otherwise>
							</c:choose>
							<c:if test="${action.equals('put')}">
								<div class="container mt-4 mb-4 text-center">
									<form action="/vinyl_record_loots/create">
										<input type="submit" value="Add New Lot"
											class="btn btn-outline-primary">
									</form>
								</div>
							</c:if>
						</div>
						<div class="text-success"><hr class="w-50 mx-auto"></div>
					</c:if>
				</c:if>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
					<div class="container mt-4 text-center">
						<form></form>
						<c:choose>
							<c:when test="${action.equals('post') || action.equals('put')}">
								<input id="sbtn" type="submit" value="Complete ${action.equals('post') ? 'Registration' : 'Update'}"
									class="btn btn-outline-primary btn-lg mb-4"
									${action.equals('put') && branchOffice.getAdministrator() == null && !allBusy ? '' : 'disabled'}>
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
				</div>
			</form>
		</div>
	</body>
</html>
