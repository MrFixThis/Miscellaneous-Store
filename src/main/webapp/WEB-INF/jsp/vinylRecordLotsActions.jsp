<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="locker" value="${!(action.equals('post') || action.equals('put')) ? 'readonly' : ''}"/>
<c:set var="manageOp" value="${action.equals('put') ? vinylRecordLot.getId() : inventoryId}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./components/headContent.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./components/navbar.jsp"%>
		<div class="container pt-2">
			<div class="container mt-4">
				<h1 class="h1 text-center">
					<span class="badge" style="background-color: #F0787E">
						<c:choose>
							<c:when test="${action.equals('post')}">
								Add New
							</c:when>
							<c:when test="${action.equals('get')}">
								See
							</c:when>
							<c:when test="${action.equals('put')}">
								Update
							</c:when>
						</c:choose>
						Vinyl Record Lot
					</span>
				</h1>
			</div>
		</div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form action="/vinyl_record_lots/manage/${action.equals('post') ? 'create' : 'update'}/${manageOp}">
				<h3 class="h3 text-muted text-center">
					<strong>Vinyl Record Lot Information</strong>
				</h3>
				<div class="text-success"><hr class="w-25 mx-auto"></div>
				<h5 class="h5 text-muted text-center mt-4">
					<strong>Basic Data</strong>
				</h5>
				<div class="container mt-4">
					<div class="input-group mb-3">
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Record production name</span>
							<input name="recordProductionName" type="text" class="TXT form-control"
								value="${vinylRecordLot.getRecordProductionName()}" maxlength="255" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Artist/Group name</span>
							<input name="artistGroupName" type="text" class="TXT form-control"
								value="${vinylRecordLot.getArtistGroupName()}" maxlength="180" ${locker}>
							<span class="input-group-text text-muted">Musical genre</span>
							<input name="musicalGenre" type="text" class="TXT form-control"
								value="${vinylRecordLot.getMusicalGenre()}" maxlength="180" ${locker}>
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text text-muted">Price per unit</span>
							<span class="input-group-text text-muted">$</span>
							<input type="text" name="pricePerUnit" class="NBR form-control"
								value="${vinylRecordLot.getPricePerUnit()}" ${locker}>
							<span class="input-group-text text-muted">Available units</span>
							<input type="text" name="availableUnits" class="NBR form-control"
								value="${vinylRecordLot.getAvailableUnits()}" ${locker}>
							<span class="input-group-text text-muted">Publication date</span>
							<input class="TXT form-control" type="date" name="publicationDate" placeholder="yyyy-mm-dd"
							 value="${vinylRecordLot.getPublicationDate().toString()}" min="1900-01-01" max="2030-12-31" ${locker}>
						</div>
					  </div>
					</div>
				</div>
				<div class="text-success"><hr class="w-50 mx-auto"></div>
				<div class="container mt-4 text-center">
					<c:choose>
						<c:when test="${action.equals('post') || action.equals('put')}">
							<input id="sbtn" type="submit" value="Complete ${action.equals('post') ? 'Adition' : 'Update'}"
								class="btn btn-outline-primary btn-lg mb-4" disabled>
						</c:when>
						<c:otherwise>
							<form></form>
							<div class="btn-group me-4" role="group">
								<form action="/vinyl_record_lots/update/${vinylRecordLot.getId()}">
									<input type="submit" value="Edit Subject"
										class="btn btn-outline-info btn-lg mb-4">
								</form>
							</div>
							<div class="btn-group" role="group">
								<form action="/vinyl_record_lots/manage/delete/${vinylRecordLot.getId()}">
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
