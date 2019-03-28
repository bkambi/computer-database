<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->

<link href="<%=request.getContextPath()%>/static/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/static/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/static/css/main.css"
	rel="stylesheet" media="screen">
		
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form id="formEdit" action="<%=request.getContextPath()%>/editComputer" method="POST">
						<input type="hidden" value="0" id="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName" name="computerName"
									placeholder ="${computer.name}" value="${computer.name}" data-validation="alphanumeric" data-validation-ignore=" ,&,.,-,/,+,:" >
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced" name="introduced"
									value="${computer.introduced}" data-validation="date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									value="${computer.discontinued}" data-validation="date" name="discontinued">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" data-validation="number" 
									data-validation-error-msg="Choix d'entreprise invalide" name ="companyId">
									<option  value="inconnue" selected="selected" > Choose company</option>
									<c:forEach items="${listeCompany}" var="company">
										<option value="${company.id}"
											${computer.company == company.name ? 'selected' : ''}>${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard.html" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.79/jquery.form-validator.min.js"></script>
	<script
src="<%=request.getContextPath()%>/static/js/editFormValidator.js"></script>
</body>
</html>