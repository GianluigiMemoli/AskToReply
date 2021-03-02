<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="profilo" />
</jsp:include>


  
<%  
	int currentPage = 1;
	if(request.getParameter("page") != null){
		currentPage = Integer.parseInt(request.getParameter("page"));		
	}  
	request.setAttribute("page", currentPage);
%> 

<div class="content row" style="padding:0pt";>
	<div class="col-md-4 order-md-2 mb-4" unselectable="on"  onselectstart="return false;" onmousedown="return false;">
		<jsp:include page="TabSwitcher.jsp">
				<jsp:param name="active" value="storicoRisposte" />			
		</jsp:include>
	</div>
	
	<div class="col-md-8 order-md-1" style="border-right: 1px solid #f0f0f0;">
		<div class="card-body">	
	<div class="card-title">
	<a name="storicoRisposte"></a>
		<h3>Storico Risposte</h3>
	</div>
	
	
	<c:forEach var="risposta" items="${storicoRisposte}">

		<div class="list-group">
			<a href="VisualizzaDomandaServlet?id=${risposta.getIdDomanda()}"
				class="list-group-item list-group-item-action flex-column align-items-start">

				<div class="d-flex w-100 justify-content-between">
					<p style="color:black; margin-bottom:0pt;" class="mt-0"> ${risposta.getCorpo()}</p>
					<small>${risposta.getDataPubblicazione()}</small>
				</div>

				<p class="mb-1">
					<small>
					in risposta a: ${risposta.getTitoloDomanda()}
					</small>
				</p>
				
				
				
					<button name="mipiace" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" style="padding-left:0;" disabled><ion-icon name="thumbs-up"></ion-icon><span class="responsivespan"> Mi piace</span>&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:Gainsboro;"  class="badge badge-pill badge-success text-dark">${risposta.getMiPiace()}</span></button>
					<button name="nonmipiace" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="thumbs-down"></ion-icon><span class="responsivespan"> Non mi piace</span>&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:Gainsboro" class="badge badge-pill badge-danger text-dark">${risposta.getNonMiPiace()}</span></button>
					   					
					<c:choose>
					<c:when test="${risposta.getAllegati().size() > 0}">
					<button type="button" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="image"></ion-icon><span class="responsivespan"> Contiene allegati&nbsp;</span></button>
					</c:when>
					</c:choose>		
									
			</a>
		</div>
		<br>
	</c:forEach>

	
			<c:if test="${not empty storicoRisposte}">
		<nav class="paginator d-flex justify-content-center" aria-label="Page navigation example">
  		<ul class="pagination">
  		<c:if test="${page > 1 }">
    		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage-1)%>)><ion-icon name="arrow-back"></ion-icon> Previous</a></li>
    		</c:if> 
    		   	  
    		   	  <c:if test="${hasNext}">
    		   	  		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage+1)%>)>Next <ion-icon name="arrow-forward"></ion-icon></a></li>
		    		</c:if> 
		
		</ul>
		</nav>
		<br><br>		
			</c:if>	
	
	</div>
	</div>
	</div>

	<c:if test="${empty storicoRisposte}">
	<div class="text-center">
		<p>Non hai pubblicato risposte</p>
		</div>
	</c:if>
	
	
		<script>
		function changePage(pageNumber){
			window.location.href = "/AskToReply/VisualizzaStoricoRisposte?page="+pageNumber+"#storicoRisposte";
		}
	</script>
	<jsp:include page="Footer.jsp"></jsp:include>
	