<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DomandaBean" %>

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp" />

	<div class="content">
		<div class="container m-2">
			<div class="row">
				<div class="col-8">
					<c:choose>
						<c:when test="${risultatoRicerca.size() > 0}">
							<div class="list-group">
								<c:forEach items="${risultatoRicerca}" var="d">
									<div class="list-group-item">
										<small>Pubblicata da: ${d.getAutore().getUsername()}</small>
										<h5>
											<a href="VisualizzaDomandaServlet?id=${d.getId()}">${d.getTitolo()}</a>
										</h5>
	    								<p>${d.getCorpo()}</p>
	    								<c:choose>
	    									<c:when test="${d.isArchiviata()}">
	    										<span class="btn">Rispondi (Archiviata)</span>
	    									</c:when>
	    									<c:otherwise>
	    										<a href="VisualizzaDomandaServlet?id=${d.getId()}" class="btn btn-dark">Rispondi</a>
	    									</c:otherwise>	
	    								</c:choose>
	    								
	    								<a href="VisualizzaFormSegnalazioneDomandaServlet?idDomanda=${d.getId()}" class="btn btn-dark">Segnala</a>
  									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<p class="lead">Nessun risultato.</p>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="col">
				
					<form action="RicercaServlet" method="get">
					
						<div class="form-group">
							<input type="text" class="form-control" name="testo" placeholder="Cerca">
						</div>
						
						<div class="form-group">
							<label>Categorie:</label>
							<c:forEach items="${categorie}" var="c">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="${c.getId()}" name="categorie">
									<label class="form-check-label">${c.getNome()}</label>
								</div>
							</c:forEach>
						</div>
						
						<div class="form-group">
							<div class="form-check">
	  							<input class="form-check-input" type="checkbox" name="archiviazione" value="archiviate">
	  							<label class="form-check-label">Archiviate</label>
	  						</div>
	  						<div class="form-check">
	  							<input class="form-check-input" type="checkbox" name="archiviazione" value="non archiviate">
	  							<label class="form-check-label">Non archiviate</label>
							</div>
						</div>
						
						<input type="submit" value="Cerca" class="btn btn-dark">
						
					</form>
					
				</div>
			</div>
		</div>
	</div>

<jsp:include page="Footer.jsp" />