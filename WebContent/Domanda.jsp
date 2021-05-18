<%@ page import="model.DomandaBean"%>
<%@ page import="java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="Header.jsp"/>
	
<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="empty" />
</jsp:include>

<style> 
	.question{
		margin-bottom: 2em;
		padding: 1em;
	}
</style>

<div class="content debug">

	<div class="card-body">
	
		<div class="questions-list">
		
				<jsp:include page="PopupErrore.jsp"></jsp:include>
		
				<div class="question rounded border">
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
					<small><a style="background-color:#EDE7F6; color:purple; border-radius:99em;" href="RicercaServlet?categorie=${categoria.getId()}" class="badge">${categoria.nome}</a></small>
				</c:forEach>
				
				<p class="lead" style="margin-bottom:0pt;">${domanda.titolo}</p>

				<p>${domanda.corpo}</p>
				
				<!-- allegati domanda -->
				<c:choose>
					<c:when test="${domanda.getAllegati().size() > 0}">
						<div class="container" style="margin-left:0px">
							<div class="row">

							<c:forEach items="${domanda.getAllegati()}" var="allegato">
								<div class="col-0 p-0" style="margin-right:5pt; margin-bottom:16px; ">
									<img src="data:image/jpg;base64,${allegato}" alt="" class="img-fluid img-thumbnail" style="max-height: 150px; min-height: 100px; min-height: 50%;">
								</div>
								
							</c:forEach>
								</div>
							</div>
					</c:when>
				</c:choose>
				
				<!-- form risposta -->
				<!-- controllare se la domanda è archiviata -->
					<c:choose>
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != domanda.getAutore().getId()}">
									
									
									
									
									
									
									
					
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
							<button onclick="document.getElementById('idDomanda').value='${domanda.getId()}'" type="submit" class="btn btn-outline-primary btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#pubblicaRispostaModal" data-whatever="@getbootstrap"><ion-icon name="chatbubble-ellipses"></ion-icon> Rispondi</button>
					</c:if>
					</c:otherwise>	
					</c:choose>
					</c:if>
					
							<button type="button" class="btn btn-outline-warning btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#segnalaDomandaModal" data-whatever="@getbootstrap"><ion-icon name="warning"></ion-icon> Segnala&nbsp;</button>
							
							<jsp:include page="ModalSegnalazioneDomanda.jsp">
								<jsp:param value="segnalaDomandaModal" name="idModal"/>
								<jsp:param value="${domanda.getId()}" name="idDomanda"/>
							</jsp:include>
									<c:choose>
					<c:when test="${domanda.getAllegati().size() > 0}">
					<button type="button" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="image"></ion-icon><span class="responsivespan"> Contiene allegati&nbsp;</span></button>
					</c:when>
					</c:choose>		
						<jsp:include page="FormPubblicazioneRisposta.jsp"></jsp:include>
						</c:when>
						<c:otherwise>
															<c:choose>
					<c:when test="${domanda.getAllegati().size() > 0}">
					<button type="button" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="image"></ion-icon><span class="responsivespan"> Contiene allegati&nbsp;</span></button>
					</c:when>
					</c:choose>		
						</c:otherwise>
					</c:choose>	
				

				
				<!-- risposte -->
				<!-- 
					aggiungere bottone upvote, downvote, nome utente, bottone per segnalare la risposta, voti 
				-->
			</div>

		</div>
		
	</div>
	
	<jsp:include page="ElencoRisposte.jsp"><jsp:param name="idDom" value="${domanda.id}"/></jsp:include>
	
</div>
		
<jsp:include page="Footer.jsp"/>