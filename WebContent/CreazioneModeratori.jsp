<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#collapseOne {
	max-height: 400px;
	overflow: scroll;
}

.mod-item:hover {
	background-color: #5d34af;
	color: white;
	cursor: pointer;
}

.mod-list{
	max-height: 500px;
	overflow-y: scroll;
}

.mod-container-form{	
	margin-top: 2em;
}
.mod-form{
align-self: center;
	max-width: 80%;
}
.content{
	padding: 3em;
}
</style>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="SidebarMasterMod.jsp">
	<jsp:param name="active" value="registra" />
</jsp:include>

<div class="content">
	<div class="row">
		<div class="col-12">		
		<div class="card border-0 mod-container-form col-12">
			<h4 class="card-title">Registra un moderatore</h4>
			<div class="card-body">
				<jsp:include page="PopupErrore.jsp"></jsp:include>
				<form class="mod-form" action="CreazioneModeratoreServlet" method="post" enctype="application/x-www-form-urlencoded">
				<div class="form-group">
						<label for="nome">Nome</label>
						<input id="nome" type="text" class="form-control" name="nome" >
					</div>
					<div class="form-group">
						<label for="cognome">Cognome</label>
						<input id="cognome" type="text" class="form-control" name="cognome"  >
					</div>
					<div class="form-group">
						<label for="username">Username</label>
						<input id="username" type="text" class="form-control" name="username" >
					</div>
					<div class="form-group">
						<label for="email">Indirizzo email</label>
						<input id="email" type="email" class="form-control" name="email">
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input id="password" type="password" class="form-control" name="password">
					</div>									
				 	<button type="submit" class="btn btn-primary">Invia</button>
			</form>
			</div>
	</div>
</div>



<jsp:include page="Footer.jsp"></jsp:include>
