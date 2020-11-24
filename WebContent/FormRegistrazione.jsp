<%@page import="java.util.ArrayList"%>
<%@page import="model.CategoriaBean"%>

<jsp:include page="Header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
	<div class="row">
		<div class="col-md-4"><br></div>
		<div class="col-sm-12 col-md-4">
			<h2>Registrati</h2>
			<jsp:include page="PopupErrore.jsp"></jsp:include>
			<form action="RegistrazioneServlet" method="post" enctype="application/x-www-form-urlencoded">
				<div class="form-group">
						<label for="nome">Nome</label>
						<input id="nome" type="text" class="form-control" name="nome" pattern="([A-Za-z']+\s*)+" required >
					</div>
					<div class="form-group">
						<label for="cognome">Cognome</label>
						<input id="cognome" type="text" class="form-control" name="cognome" pattern="([A-Za-z']+\s*)+" required >
					</div>
					<div class="form-group">
						<label for="username">Username</label>
						<input id="username" type="text" class="form-control" name="username" pattern=".{3,10}" required >
					</div>
					<div class="form-group">
						<label for="email">Indirizzo email</label>
						<input id="email" type="email" class="form-control" name="email" required >
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input id="password" type="password" class="form-control" name="password" pattern=".{6,32}" required >
					</div>				
					<label>Scegli almeno un'area di interesse</label>
					<ul class="list-group">
						<c:forEach var="interesse" items="${interessi}">
							<li class="list-group-item">								
								  <input type="checkbox" value="${interesse.getNome()}" name="interessi" id="interesseCheck">
								  <label for="interesseCheck">	${interesse.getNome()}</label>								
							</li>
						</c:forEach>						
					</ul>
				 	<button type="submit" class="btn btn-primary">Invia</button>
			</form>
		</div>
		<div class="col-md-4">
			
		</div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>