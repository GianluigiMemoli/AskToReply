<%@page import="java.util.ArrayList"%>
<jsp:include page="Header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="SidebarOspite.jsp">
	<jsp:param name="active" value="accedi" />
</jsp:include>

<div class="content">
	<div class="card-body">

	<div class="card-title">
			<h3>Accesso</h3>
	</div>
			<jsp:include page="PopupErrore.jsp"></jsp:include>
			<form action="AccessoServlet" method="post" enctype="application/x-www-form-urlencoded">														
					<div class="form-group">
						<label for="email">Indirizzo email</label>
						<input id="email" type="email" class="form-control" name="email" required >
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input id="password" type="password" class="form-control" name="password" pattern=".{6,32}" required >
					</div>
				 	<button type="submit" class="btn btn-primary btnsmussato">Accedi</button>
			</form>
			</div>
			</div>
<jsp:include page="Footer.jsp"></jsp:include>