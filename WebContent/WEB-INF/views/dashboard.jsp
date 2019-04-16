<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.excilys.cdb.dto.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="/static/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="/static/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="/static/css/main.css"
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
			<h1 id="homeTitle">${totalCount}Computersfound</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm"
						action="<%=request.getContextPath()%>/dashboard" method="GET"
						class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="<%=request.getContextPath()%>/add-computer">Add Computer</a>
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>
		<form id="deleteForm"
			action="<%=request.getContextPath()%>/delete-computer" method="POST">
			<c:forEach items="${listeComputer}" var="Computer">
				<input type="hidden" name="selection" value="${Computer.id}">
			</c:forEach>
		</form>
		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name <a
							href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
						<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
						orderBy=1&reversed=0"><span
								class="glyphicon glyphicon-chevron-up"></span> </a> <a
							href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
							<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
							orderBy=1&reversed=1"><span
								class="glyphicon glyphicon-chevron-down"></span></a>
						</th>
						<th>Introduced date <a
							href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
						<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
						orderBy=2&reversed=0"><span
								class="glyphicon glyphicon-chevron-up"></span> </a> <a
							href="?<%=("").equals(request.getParameter("search")) ? ""
					: "search=" + request.getParameter("search") + "&"%>
							<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
							orderBy=2&reversed=1"><span
								class="glyphicon glyphicon-chevron-down"></span></a>
						</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date <a
							href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
						<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
						orderBy=3&reversed=0"><span
								class="glyphicon glyphicon-chevron-up"></span> </a> <a
							href="?<%=("").equals(request.getParameter("search")) ? ""
					: "search=" + request.getParameter("search") + "&"%>
							<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
							orderBy=3&reversed=1"><span
								class="glyphicon glyphicon-chevron-down"></span></a>
						</th>
						<!-- Table header for Company -->
						<th>Company <a
							href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
						<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
						orderBy=4&reversed=0"><span
								class="glyphicon glyphicon-chevron-up"></span> </a> <a
							href="?<%=("").equals(request.getParameter("search")) ? ""
					: "search=" + request.getParameter("search") + "&"%>
							<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
							orderBy=4&reversed=1"><span
								class="glyphicon glyphicon-chevron-down"></span></a>
						</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${listeComputer}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a
								href="<%=request.getContextPath()%>/edit-computer?computer=${computer.id}"
								onclick="">${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a
					href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
				<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
				<%=request.getParameter("orderBy") == null & request.getParameter("reversed") == null ? ""
					: "orderBy=" + request.getParameter("orderBy") + "&reversed=" + request.getParameter("reversed")
							+ "&"%>arrayIndice=previous"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach items="${listIndice}" var="indice">
					<li><a
						href="?<%=request.getParameter("search") == null? "" :"search=" +request.getParameter("search")+"&"%>
					<%=request.getParameter("numberOfComputer") == null ? "" :"numberOfComputer=" +request.getParameter("numberOfComputer")+"&"%>
					<%=request.getParameter("orderBy") == null & request.getParameter("reversed") == null ? "" :"orderBy="+request.getParameter("orderBy")+"&reversed=" +request.getParameter("reversed")+"&"%>
					indice=${indice}">${indice}</a></li>
				</c:forEach>
				<li><a
					href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
				<%=request.getParameter("numberOfComputer") == null ? ""
					: "numberOfComputer=" + request.getParameter("numberOfComputer") + "&"%>
				<%=request.getParameter("orderBy") == null & request.getParameter("reversed") == null ? ""
					: "orderBy=" + request.getParameter("orderBy") + "&reversed=" + request.getParameter("reversed")
							+ "&"%>
				arrayIndice=next"
					aria-label="Next"><span aria-hidden="true">&raquo;</span> </a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
					<%=request.getParameter("orderBy") == null & request.getParameter("reversed") == null ? ""
					: "orderBy=" + request.getParameter("orderBy") + "&reversed=" + request.getParameter("reversed")
							+ "&"%>numberOfComputer=10"><button
						type="button" class="btn btn-default">10</button></a> <a
					href="?<%=("").equals(request.getParameter("search")) ? ""
					: "search=" + request.getParameter("search") + "&"%>
						<%=request.getParameter("orderBy") == null & request.getParameter("reversed") == null ? ""
					: "orderBy=" + request.getParameter("orderBy") + "&reversed=" + request.getParameter("reversed")
							+ "&"%>
						numberOfComputer=50"><button
						type="button" class="btn btn-default">50</button></a> <a
					href="?<%=request.getParameter("search") == null ? "" : "search=" + request.getParameter("search") + "&"%>
					<%=request.getParameter("orderBy") == null & request.getParameter("reversed") == null ? ""
					: "orderBy=" + request.getParameter("orderBy") + "&reversed=" + request.getParameter("reversed")
							+ "&"%>
					numberOfComputer=100"><button
						type="button" class="btn btn-default">100</button></a>
			</div>
		</div>
	</footer>
	<script src="/static/js/jquery.min.js"></script>
	<script src="/static/js/bootstrap.min.js"></script>
	<script src="/static/js/dashboard.js"></script>

</body>
</html>