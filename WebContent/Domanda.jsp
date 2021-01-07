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
	
					<div class="question rounded border">
	
					  				<div class="d-flex w-100 justify-content-between">
					<small class="text-secondary">@${domanda.getAutore().getUsername()}</small>
					<small class="text-secondary">${domanda.getDataPubblicazione()}</small>
				</div>
				
				<!-- titolo domanda -->
				
								
				<!-- categorie -->

					<c:forEach items="${domanda.getCategorie()}" var="categoria">
						<small><a style="background-color:#EDE7F6; color:purple; border-radius:99em;" href="RicercaServlet?categorie=${categoria.getId()}" class="badge">${categoria.nome}</a></small>
					</c:forEach>
				
					<p class="lead" style="margin-bottom:0pt;">${domanda.titolo}</p>


				<!-- corpo domanda -->
					<p>${domanda.corpo}</p>
				
				<!-- allegati domanda -->
					<c:choose>
						<c:when test="${allegati.size() > 0}">
							<c:forEach items="${allegati}" var="allegato">
								<div>
									<img src="${allegato.getPath()}" alt="" class="img-fluid">
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p><small>Nessun allegato presente.[questo messaggio va poi eliminato]</small></p>
						</c:otherwise>
					</c:choose>
				

				<!-- form risposta -->
				<!-- controllare se la domanda è archiviata -->
					<c:choose>
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != domanda.getAutore().getId()}">
								<button onclick="document.getElementById('idDomanda').value=${domanda.getId()}" type="submit" class="btn btn-outline-primary btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#pubblicaRispostaModal" data-whatever="@getbootstrap"><ion-icon name="chatbubble-ellipses"></ion-icon> Rispondi</button>
								<button type="submit" class="btn btn-outline-warning btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#segnalaDomandaModal" data-whatever="@getbootstrap"><ion-icon name="warning"></ion-icon> Segnala&nbsp;</button>
								<button type="submit" class="btn btn-outline-info btn-sm border-0 btnsmussato" name="allegati" id="allegati"><ion-icon name="eye"></ion-icon> Mostra allegati&nbsp;</button>
								
														<jsp:include page="FormPubblicazioneRisposta.jsp"></jsp:include> 		
						
						
						</c:when>
						<c:otherwise>
							<p class="lead">Non puoi rispondere</p>
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