<%@ page import="model.DomandaBean" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="profilo" />
</jsp:include>

<div class="content debug">
			<div class="row" style="padding:0pt;">
			
				<div class="col-md-4 order-md-2 mb-4" unselectable="on"  onselectstart="return false;" onmousedown="return false;">
		<jsp:include page="TabSwitcher.jsp">
				<jsp:param name="active" value="storicoDomande" />			
		</jsp:include>
	</div>
			
	<div class="col-md-8 order-md-1" style="border-right: 1px solid #f0f0f0;">
					<div class="card-body">	
	<div class="card-title">
	<a name="storicoRisposte"></a>
		<h3>Storico Domande</h3>
	</div>	
					<c:choose>
						<c:when test="${domande.size() > 0}">
						
							<!-- domande -->
							<c:forEach items="${domande}" var="d">
								<div class="list-group">
							
										<a href="VisualizzaDomandaServlet?id=${d.getId()}" 	class="list-group-item list-group-item-action flex-column align-items-start">
										
														<div class="d-flex w-100 justify-content-between">
										
														<p style="color:black; margin-bottom:0pt;" class="mt-0">${d.getTitolo()}</p>
					<small>${d.getDataPubblicazione()}</small>
										
						</div>					
									
				<p class="mb-1">
					<small>
					${d.getCorpo()}
					</small>
				
				</p>										
													
									<button
						onclick="document.getElementById('idDomanda').value='${d.getId()}'"
						style="pointer-events: none; padding-left:0;"
						type="button"
						class="btn btn-outline-primary btn-sm border-0 btnsmussato text-dark" disabled>
						<ion-icon name="chatbubble-ellipses"></ion-icon>
						Risposte ricevute
						<span style="background-color:Gainsboro;"  class="badge badge-pill badge-success text-dark">${numeroRisposte.get(d.getId())}</span>
					</button>
									
																					<c:choose>
					<c:when test="${d.getAllegati().size() > 0}">
					<button type="button" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="image"></ion-icon><span class="responsivespan"> Contiene allegati&nbsp;</span></button>
					</c:when>
					</c:choose>						
				
				
				</a>
								</div>
										<br>
								
							</c:forEach>
							
							<!-- paginazione -->
							<nav>
							  <ul class="pagination">
							  	<c:if test="${currentPage > 1}">
							  		<li class="page-item">
							    		<a class="page-link" href="VisualizzaStoricoDomandeServlet?p=${currentPage-1}">Precedente</a>
							   		</li>
							  	</c:if>
							    <li class="page-item disabled">
							    	<span class="page-link">${currentPage} / ${totalPages}</span>
							    </li>
							    
							    <c:if test="${currentPage < totalPages}">
							    	<li class="page-item">
							    		<a class="page-link" href="VisualizzaStoricoDomandeServlet?p=${currentPage+1}">Avanti</a>
							    	</li>
							    </c:if>
							  </ul>
							</nav>
							
						</c:when>
						<c:otherwise>
							<p>Nessuna domanda.</p>
						</c:otherwise>
					</c:choose>
					
				</div>
				</div>
			</div>
						</div>
			

<jsp:include page="Footer.jsp" />