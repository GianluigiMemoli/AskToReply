<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="profilo" />
</jsp:include>
<%@page import="java.util.HashMap"%>

<style>
.edit-profile {
	padding: 1em;
}
</style>
<div class="content edit-profile">
<div class="row">
	<div class="col-md-4 order-md-2 mb-4" unselectable="on"  onselectstart="return false;" onmousedown="return false;">
		<jsp:include page="TabSwitcher.jsp">
				<jsp:param name="active" value="modificaProfilo" />			
		</jsp:include>
	</div>
	<div class="col-md-8 order-md-1" style="border-right: 1px solid #f0f0f0;">	
	<div class="card-body">
		<jsp:include page="PopupErrore.jsp"></jsp:include>
		<div class="card-title">
			<h3>Modifica profilo</h3>
		</div>
		<form action="UpdateProfilo" method="post"
			enctype="application/x-www-form-urlencoded">
			<div class="form-group">
				<label for="nome">Nome</label> <input id="nome" type="text"
					class="form-control" name="nome" value="${utenteLoggato.getNome()}">
			</div>
			<div class="form-group">
				<label for="cognome">Cognome</label> <input id="cognome" type="text"
					class="form-control" name="cognome"
					value="${utenteLoggato.getCognome()}">
			</div>
			<div class="form-group">
				<label for="username">Username</label> <input id="username"
					type="text" class="form-control" name="username"
					value="${utenteLoggato.getUsername()}">
			</div>
			<div class="form-group">
				<label for="email">Indirizzo email</label> <input id="email"
					type="text" class="form-control" name="email"
					value="${utenteLoggato.getEmail()}" >
			</div>
			<div class="form-group">
				<label for="password">Nuova password</label> <input id="password"
					type="password" class="form-control" name="password" autocomplete="off">
			</div>
 
			<label>Scegli almeno un'area di interesse</label>
			<div class="overflow-auto" style="height: 229px;">  
				<ul class="list-group" style="margin-right:5pt;">
					<c:forEach var="interesse" items="${interessi}" varStatus="loop">					
						<li class="list-group-item"><c:if
								test="${utenteLoggato.getInteressi().contains(interesse)}">
								<label for="interesseCheck${loop.index}">
								<input type="checkbox" value="${interesse.getNome()}"
									name="interessi" id="interesseCheck${loop.index}" checked>
									${interesse.getNome()}</label>
							</c:if> <c:if test="${utenteLoggato.getInteressi().contains(interesse) == false}">
								<label for="interesseCheck${loop.index}">
								<input type="checkbox" value="${interesse.getNome()}"
									name="interessi" id="interesseCheck${loop.index}">
									${interesse.getNome()}
									</label>
							</c:if>  </li>
					</c:forEach>
				</ul>
			</div>
			<br>
			<button type="submit" class="btn btn-primary btnsmussato">Salva modifiche</button>
		</form>
		</div>		
	</div>	
	</div>


</div>


<jsp:include page="Footer.jsp"></jsp:include>
