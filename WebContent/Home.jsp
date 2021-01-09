<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="home" />
</jsp:include>
<%@page import="model.DomandaBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import= "java.util.HashMap" %>
<%@page import= "java.util.HashSet" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int currentPage = 1;
if (request.getParameter("page") != null) {
	currentPage = Integer.parseInt(request.getParameter("page"));
}
request.setAttribute("currentPage", currentPage);
%>
<style>
.debug-content {
	border: 2px solid red;
}

.debug-media {
	border: 2px solid blue;
}

.questions-list {
	padding: 2em;
}

.question {
	margin-bottom: 2em;
	padding: 1em;
}

.paginator {
	align-self: center;
}
</style>

<div class="content debug">


	<div class="card-body">

		<input type="hidden" id="idDomanda" name="idDomanda" value="">


		<c:forEach var="domanda" items="${domande}">
			<div class="question rounded border">


				<div>

					<div class="d-flex w-100 justify-content-between">
						<small class="text-secondary">@${domanda.getAutore().getUsername()}</small>
						<small class="text-secondary">${domanda.getDataPubblicazione()}</small>
					</div>

					<c:forEach items="${domanda.getCategorie()}" var="categoria">
						<small><a
							style="background-color: #EDE7F6; color: purple; border-radius: 99em;"
							href="RicercaServlet?categorie=${categoria.getId()}"
							class="badge">${categoria.nome}</a></small>
					</c:forEach>

					<a href="VisualizzaDomandaServlet?id=${domanda.getId()}"
						class="list-group-item-action">
						<h5 style="margin-bottom: 0pt; color: black;" class="lead">${domanda.getTitolo()}</h5>
						<p style="color: black;">${domanda.getCorpo()}</p>
					</a>
				</div>
				<div>
			
				<c:if test="${!utenteLoggato.getId().equals(domanda.getId())}">
					<c:choose>
					<c:when test="${domandeRisposte.contains(domanda)}">
					<button
						style="background-color:#E3F2FD; pointer-events: none;"
						type="button"
						class="btn btn-outline-primary btn-sm border-0 btnsmussato">
						<ion-icon name="chatbubble-ellipses"></ion-icon>
						Hai risposto
						<span style="background-color:#BBDEFB;"  class="badge badge-pill badge-success text-primary">${numeroRisposte.get(domanda.getId())}</span>
					</button>
					</c:when>
					<c:otherwise>					
					<button
						onclick="document.getElementById('idDomanda').value='${domanda.getId()}'"
						type="submit"
						class="btn btn-outline-primary btn-sm border-0 btnsmussato"
						data-toggle="modal" data-target="#pubblicaRispostaModal"
						data-whatever="@getbootstrap">
						<ion-icon name="chatbubble-ellipses"></ion-icon>
						Rispondi
						<span style="background-color:#BBDEFB;"  class="badge badge-pill badge-success text-primary">${numeroRisposte.get(domanda.getId())}</span>
					</button>
					</c:otherwise>	
					</c:choose>
					</c:if>
					<button type="submit"
						class="btn btn-outline-warning btn-sm border-0 btnsmussato"
						data-toggle="modal"
						data-target="#dibenedettoinserisciquiiltitolodelmodalchehaifatto"
						data-whatever="@getbootstrap">
						<ion-icon name="warning"></ion-icon>
						Segnala
					</button>

				</div>

			</div>
		</c:forEach>

					<jsp:include page="FormPubblicazioneRisposta.jsp"></jsp:include>


		<c:if test="${empty domande}">
			<div class="card text-center">

				<div class="card-body">
					<h5 class="card-title">Non sono ancora presenti domande
						appartenenti alle categorie di tuo interesse</h5>
					<p class="card-text">Ricorda che puoi sempre aggiungere nuove
						categorie tramite l'apposito form situato nella sezione Profilo.</p>
					<a href="VisualizzaProfilo" class="btn btn-primary"><ion-icon
							name="library"></ion-icon> Gestisci categorie</a>
				</div>
			</div>
		</c:if>


	</div>
	<c:if test="${not empty domande}">
		<nav class="paginator d-flex justify-content-center" aria-label="Page navigation example">
			<ul class="pagination">
				<c:if test="${currentPage > 1 }">
					<li class="page-item"><a class="page-link" onclick="changePage(<%=(currentPage - 1)%>)">
					<ion-icon name="arrow-back"></ion-icon>
							Previous</a></li>
				</c:if>
				<c:if test="${next == 1}">
					<li class="page-item"><a class="page-link" onclick="changePage(<%=(currentPage + 1)%>)">Next <ion-icon name="arrow-forward"></ion-icon></a></li>
				</c:if>
			</ul>
		</nav>
		<br>
		<br>
	</c:if>
</div>


<script>
		function changePage(pageNumber){
			window.location.href = "/AskToReply/VisualizzaHome?page="+pageNumber;
		}
	</script>


<jsp:include page="Footer.jsp"></jsp:include>
