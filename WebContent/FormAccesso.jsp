<%@page import="java.util.ArrayList"%>
<jsp:include page="Header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div class="row">
		<div class="col-md-4"><br></div>
		<div class="col-sm-12 col-md-4">
			<h2>Accedi</h2>
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
				 	<button type="submit" class="btn btn-primary">Invia</button>
			</form>
		</div>
		<div class="col-md-4">			
		</div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>