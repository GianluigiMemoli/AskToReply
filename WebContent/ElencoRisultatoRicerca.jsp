<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DomandaBean" %>

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="cerca" />
</jsp:include>

<style>
	.debug-content{
		border: 2px solid red; 
	}
	.questions-list{
		padding: 2em;
	}
	.question{
		margin-bottom: 2em;
		padding: 1em;
	}
</style>


	<div class="content debug">
		<div class="container m-0">
			<div class="row">
			<div class="card-body">
					<input type="hidden" id="idDomanda" name="idDomanda" value="">
					<c:choose>
						<c:when test="${risultatoRicerca.size() > 0}">
							<div class="list-group">
								<c:forEach items="${risultatoRicerca}" var="domanda">
									<div class="question rounded border">
					
																					
									  <div>
									  	
									  				<div class="d-flex w-100 justify-content-between">
										<small class="text-secondary">@${domanda.getAutore().getUsername()}</small>
										<small class="text-secondary">${domanda.getDataPubblicazione()}</small>
									</div>
									
										<c:forEach items="${domanda.getCategorie()}" var="categoria">
											<small><a style="background-color:#EDE7F6; color:purple; border-radius:99em;" href="RicercaServlet?categorie=${categoria.getId()}" class="badge">${categoria.nome}</a></small>
										</c:forEach>
									
												<a href="VisualizzaDomandaServlet?id=${domanda.getId()}"
									class="list-group-item-action">
									    <h5 style="margin-bottom:0pt; color:black;" class="lead">${domanda.getTitolo()}</h5>
									    <p style="color:black;">${domanda.getCorpo()}</p>			
									    </a>	    
									  </div>
									<div>							
										
										<button onclick="document.getElementById('idDomanda').value='${domanda.getId()}'" type="submit" class="btn btn-outline-primary btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#pubblicaRispostaModal" data-whatever="@getbootstrap"><ion-icon name="chatbubble-ellipses"></ion-icon> Rispondi</button>
										<button type="submit" class="btn btn-outline-warning btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#dibenedettoinserisciquiiltitolodelmodalchehaifatto" data-whatever="@getbootstrap"><ion-icon name="warning"></ion-icon> Segnala</button>
										 
										 
										<jsp:include page="FormPubblicazioneRisposta.jsp"></jsp:include> 				
										
									</div>
																 
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<p class="lead">Nessun risultato.</p>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div>
							<div class="card-body">
				
					<form action="RicercaServlet" method="get">
					
						<div class="form-group">
							<input type="text" class="form-control" name="testo" placeholder="Cerca">
						</div>
						
						<div class="form-group">
							<label>Categorie:</label>
							<c:forEach items="${categorie}" var="c">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="${c.getId()}" name="categorie">
									<label class="form-check-label">
										<a style="background-color:#EDE7F6; color:purple; border-radius:99em;" class="badge">${c.nome}</a>
									</label>							
								</div>
							</c:forEach>
						</div>
						
						
						<input type="submit" value="Cerca" class="btn btn-primary" style="border-radius:99em;">
						
					</form>
					</div>
				</div>
			</div>
					</div>
			</div>
			

<jsp:include page="Footer.jsp" />