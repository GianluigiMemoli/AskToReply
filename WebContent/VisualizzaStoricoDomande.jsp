<%@ page import="model.DomandaBean" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp" />

	<div class="content">
		<div class="container">
			<div class="row">
				<div class="col-12">
				
					<c:choose>
						<c:when test="${domande.size() > 0}">
						
							<!-- domande -->
							<c:forEach items="${domande}" var="d">
								<div class="list-group mt-2">
									<h4>
										<a href="VisualizzaDomandaServlet?id=${d.getId()}">
											${d.getTitolo()}
										</a>
									</h4>
									<small>${d.getDataPubblicazione()}</small>
									<p>${d.getCorpo()}</p>
								</div>
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