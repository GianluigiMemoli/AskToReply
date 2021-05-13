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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

		<jsp:include page="PopupErrore.jsp"></jsp:include>

		<% int counter = 1; %>

		<c:forEach var="domanda" items="${domande}">
		
			<div class="question rounded border">

				<div>

					<div class="d-flex w-100 justify-content-between">
						<c:if test="${utenteLoggato.getId().equals(domanda.getAutore().getId())}">
							<small class="text-secondary">hai chiesto:</small>
						</c:if>
						<c:if test="${!utenteLoggato.getId().equals(domanda.getAutore().getId())}">
							<small class="text-secondary">@${domanda.getAutore().getUsername()}</small>
						</c:if>
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
			
			
				<c:if test="${utenteLoggato.getId().equals(domanda.getAutore().getId())}">
					<button
						onclick="document.getElementById('idDomanda').value='${domanda.getId()}'"
						style="pointer-events: none;"
						type="button"
						class="btn btn-outline-primary btn-sm border-0 btnsmussato text-dark">
						<ion-icon name="chatbubble-ellipses"></ion-icon>
						Risposte ricevute
						<span style="background-color:Gainsboro;"  class="badge badge-pill badge-success text-dark">${numeroRisposte.get(domanda.getId())}</span>
					</button>
				</c:if>
			
			
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
					<c:if test="${utenteLoggato.getId().equals(domanda.getAutore().getId()) == false}">					
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
					</c:if>
					</c:otherwise>	
					</c:choose>
					</c:if>

					
					<c:if test="${utenteLoggato.getId().equals(domanda.getAutore().getId()) == false}">
					
						<% String idModalSegnalazioneDomanda = "msd" + counter; %>
														
						<button 
							type="submit"
							class="btn btn-outline-warning btn-sm border-0 btnsmussato"
							data-toggle="modal"
							data-target="<%= "#" + idModalSegnalazioneDomanda %>"
							data-whatever="@getbootstrap">
							<ion-icon name="warning"></ion-icon>
							Segnala
						</button>
						
						<jsp:include page="ModalSegnalazioneDomanda.jsp">
							<jsp:param value="${domanda.getId()}" name="idDomanda"/>
							<jsp:param value="<%= idModalSegnalazioneDomanda %>" name="idModal"/>
						</jsp:include>
						
						<% counter++; %>
						
					</c:if>				
				  
				  					<c:if test="${fn:length(domanda.getAllegati()) > 0}"> 
					<button 
						type="button"
						class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark"
						disabled><ion-icon name="image">
						</ion-icon><span class="responsivespan">
						 Contiene allegati&nbsp;
						 </span>
					 </button>
					</c:if>
				  
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
				<c:if test="${hasNext}">
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