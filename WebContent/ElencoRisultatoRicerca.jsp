<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DomandaBean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="Header.jsp" />
<c:choose>
	<c:when test="${utenteLoggato != null }">
		<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="cerca" />
</jsp:include>
</c:when>
<c:otherwise>
		<jsp:include page="SidebarOspite.jsp">
	<jsp:param name="active" value="cerca" />
</jsp:include>
</c:otherwise>
</c:choose>
	



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
			<div class="row">

							
				<div class="col-md-4 order-md-2 mb-4">
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
			
			
			
			
			
			<div class="col-md-8 order-md-1" style="border-right: 1px solid #f0f0f0;">
			<div class="card-body">
					<input type="hidden" id="idDomanda" name="idDomanda" value="">
					<c:choose>
						<c:when test="${risultatoRicerca.size() > 0}">
							<div class="list-group">
							
								<% int counter = 1; %>
							
								<c:forEach items="${risultatoRicerca}" var="domanda">
									<div class="question rounded border">
					
																					
									  <div>
									  	
						  				<div class="d-flex w-100 justify-content-between">
						  				<c:choose>
						  						<c:when test="${utenteLoggato.getId().equals(domanda.getAutore().getId())}">
						  							<small class="text-secondary">hai chiesto:</small>						  							
						  						</c:when>
						  						<c:when  test="${utenteLoggato.getId().equals(domanda.getAutore().getId()) == false}">  
						  							<small class="text-secondary">${domanda.getAutore().getUsername()}</small>						  							
						  						</c:when>
						  				</c:choose>
						  													  				
											<small class="text-secondary">${domanda.getDataPubblicazione()}</small>
									</div>
									
										<c:forEach items="${domanda.getCategorie()}" var="categoria">
											<small><a style="background-color:#EDE7F6; color:purple; border-radius:99em;" href="RicercaServlet?categorie=${categoria.getId()}" class="badge">${categoria.nome}</a></small>
										</c:forEach>
										<c:choose>
											<c:when test="${utenteLoggato != null}">
														<a href="VisualizzaDomandaServlet?id=${domanda.getId()}"
															class="list-group-item-action">
									    				<h5 style="margin-bottom:0pt; color:black;" class="lead">${domanda.getTitolo()}</h5>
									    				<p style="color:black;">${domanda.getCorpo()}</p>			
									    				</a>	
											</c:when>
											<c:otherwise>
												<a href="registrazione"
															class="list-group-item-action">
									    				<h5 style="margin-bottom:0pt; color:black;" class="lead">${domanda.getTitolo()}</h5>
									    				<p style="color:black;">${domanda.getCorpo()}</p>
											</c:otherwise>
										</c:choose>
										
									    	    
									  </div>
									  <c:if test="${utenteLoggato != null }">
									<div>																											
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
									</c:if>
																 
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<p class="lead">Nessun risultato.</p>
						</c:otherwise>
					</c:choose>
				</div>
				</div>

			</div>
			
			</div>
			

<jsp:include page="Footer.jsp" />