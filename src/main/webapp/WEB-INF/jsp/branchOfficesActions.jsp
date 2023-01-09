<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="manageOp" value="${action.equals('put') ? branchOffice.getId() : ''}"/>
<c:set var="tableMod" value="${!action.equals('put') ? 'style=&quot;display: none;&quot;' : ''}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="./components/headContent.jsp"%>
		<script type="module" src="${pageContext.request.contextPath}/js/branch_offices_actions.js" defer></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/data_validation.js" defer></script>
    </head>
    <body class="pt-5">
		<%@include file="./components/navbar.jsp"%>
		<div id="tittleBadge" class="container pt-2"></div>
		<div class="text-success"><hr class="w-50 mx-auto"></div>
		<div class="continer mt-4">
			<form id="mainDataForm">
				<div id="adminSection" class="container justify-content-md-center"></div>
				<div id="inventorySection" class="container mt-4"></div>
			</form>

				<%-- BUTTONS! --%>
				<%-- <div class="text-success"><hr class="w-50 mx-auto"></div> --%>
				<%-- 	<div class="container mt-4 text-center"> --%>
				<%-- 		<form></form> --%>
				<%-- 		<c:choose> --%>
				<%-- 			<c:when test="${action.equals('post') || action.equals('put')}"> --%>
				<%-- 				<input id="sbtn" type="submit" value="Complete ${action.equals('post') ? 'Registration' : 'Update'}" --%>
				<%-- 					class="btn btn-outline-primary btn-lg mb-4" --%>
				<%-- 					${action.equals('put') && branchOffice.getAdministrator() == null && (!allBusy && administrators.size() != 0) ? '' : 'disabled'}> --%>
				<%-- 			</c:when> --%>
				<%-- 			<c:otherwise> --%>
				<%-- 				<div class="btn-group me-4" role="group"> --%>
				<%-- 					<form action="/transactions/branch_office=${branchOffice.getId()}"> --%>
				<%-- 						<input type="submit" value="Transactions" --%>
				<%-- 							class="btn btn-outline-warning btn-lg mb-4"> --%>
				<%-- 					</form> --%>
				<%-- 				</div> --%>
				<%-- 				<div class="btn-group me-4" role="group"> --%>
				<%-- 					<form action="/branch_offices/update/${branchOffice.getId()}"> --%>
				<%-- 						<input type="submit" value="Edit Subject" --%>
				<%-- 							class="btn btn-outline-info btn-lg mb-4"> --%>
				<%-- 					</form> --%>
				<%-- 				</div> --%>
				<%-- 				<div class="btn-group" role="group"> --%>
				<%-- 					<form action="/branch_offices/manage/delete/${branchOffice.getId()}" --%>
				<%-- 						method="POST"> --%>
				<%-- 						<input type="submit" value="Delete Subject" --%>
				<%-- 							class="btn btn-outline-danger btn-lg mb-4"> --%>
				<%-- 					</form> --%>
				<%-- 				</div> --%>
				<%-- 			</c:otherwise> --%>
				<%-- 		</c:choose> --%>
				<%-- 	</div> --%>
				<%-- </div> --%>

		</div>
	</body>
</html>
