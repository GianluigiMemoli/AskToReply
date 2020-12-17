<%@ page import="model.DomandaBean"%>
<%@ page import="java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="Header.jsp"/>
	
<jsp:include page="Sidebar.jsp"/>

<div class="content">

	<div class="container">
	
		<div class="row">
		
			<div class="col-xs-12 col-sm-8">
				
				<!-- titolo domanda -->
				<div class="col-12">
					<h3>${domanda.titolo}</h3>
				</div>
				
				<!-- categorie -->
				<div class="col-12 mt-3 mb-3">
					<c:forEach items="${domanda.getCategorie()}" var="categoria">
						<a href="RicercaServlet?categorie=${categoria.getId()}" class="badge badge-dark">${categoria.nome}</a>
					</c:forEach>
				</div>
				
				<!-- username autore domanda -->
				<div class="col-12">
					<h6>Pubblicata da: ${domanda.getAutore().getUsername()}</h6>
				</div>
				
				<!-- corpo domanda -->
				<div class="col-12">
					<p class="lead">${domanda.corpo}</p>
				</div>
				
				<!-- allegati domanda -->
				<div class="col-12">
					<c:choose>
						<c:when test="${allegati.size() > 0}">
							<c:forEach items="${allegati}" var="allegato">
								<div>
									<img src="${allegato.getPath()}" alt="" class="img-fluid">
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="lead">Nessun allegato presente.</p>
						</c:otherwise>
					</c:choose>
				</div>
				
				<!-- segnala domanda -->
				<div class="col-12">
					<a href="/SegnalazioneDomandaServlet?idDomandaSegnalata=${domanda.getId()}" class="btn btn-danger">Segnala</a>
				</div>
				
				<!-- form risposta -->
				<!-- controllare se la domanda è archiviata -->
				<div class="col-12">
					<c:choose>
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != domanda.getAutore().getId()}">
							<form action="" method="">
								<textarea name="" rows="3"></textarea>
								<input type="submit" value="Rispondi" />
							</form>
						</c:when>
						<c:otherwise>
							<p class="lead">Non puoi rispondere</p>
						</c:otherwise>
					</c:choose>	
				</div>
				
				<!-- risposte -->
				<!-- 
					aggiungere bottone upvote, downvote, nome utente, bottone per segnalare la risposta, voti 
				-->
				<div class="col-12">
					<c:choose>
						<c:when test="${risposte.size() > 0}">
							<ul class="list-group">
								<c:forEach items="${risposte}" var="risposta">
									<li class="list-group-item">
										${risposta.getCorpo()}
									</li>
								</c:forEach>
							</ul>	
						</c:when>
						<c:otherwise>
							<p class="lead">Nessuna risposta.</p>
						</c:otherwise>
					</c:choose>		
				</div>
			
			</div>
		
		</div>
		
	</div>
	
</div>
		
<jsp:include page="Footer.jsp"/>