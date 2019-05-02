<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charse=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/dashboard"><spring:message code="label.header"/> </a>
		</div>
		<div class="container">
			<a href="?lang=fr">fr</a> <a href="?lang=en">en</a>
		</div>
	</header>
	<div id="msg" class="container"></div>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="label.add.subtitle"/></h1>

					<form:form id="addComputer" action="new-computer" method="POST"
						modelAttribute="addComputerForm">
						<fieldset>
							<div class="form-group">
								<form:label path="name" for="computerName"><spring:message code="label.name"/></form:label>
								<form:input path="name" name="computerName" type="text"
									class="form-control" id="computerName"
									placeholder="Computer name" />
							</div>
							<div class="form-group">
								<form:label path="introduced" for="introduced"><spring:message code="label.introduced"/></form:label>
								<form:input path="introduced" type="date" class="form-control"
									name="introduced" id="introduced" placeholder="Introduced date" />
							</div>
							<div class="form-group">
								<form:label path="discontinued" for="discontinued"><spring:message code="label.discontinued"/></form:label>
								<form:input path="discontinued" type="date" class="form-control"
									name="discontinued" id="discontinued"
									placeholder="Discontinued date" />
							</div>
							<div class="form-group">
								<form:label path="company" for="companyId"><spring:message code="label.company"/></form:label>
								<form:select path="company" class="form-control" id="companyId"
									name="companyId" items="${listeCompany}" />
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="btnSubmit" type="submit" value="<spring:message code="label.btn.submit"/>"
								class="btn btn-primary">  <spring:message code="label.btn.between"/>  <a
								href="<%=request.getContextPath()%>/dashboard"
								class="btn btn-default"><spring:message code="label.btn.cancel"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="static/js/addComputer.js"></script>
</body>
</html>