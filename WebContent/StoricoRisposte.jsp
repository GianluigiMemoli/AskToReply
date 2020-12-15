<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  -->

<%  
	int currentPage = 0;
	if(request.getParameter("pageRi") != null){
		currentPage = Integer.parseInt(request.getParameter("pageRi"));		
	}  
	request.setAttribute("pageRi", currentPage);
%> 

<br>
<div class="card-body">
	<div class="card-title">
	<a name="storicoRisposte"></a>
		<h3 >Storico Risposte</h3>
	</div>
	
	
	<c:forEach var="storicoRisposte" items="${storicoRisposte}">

		<div class="list-group">
			<a href="VisualizzaDomanda?id=${storicoRisposte.getIdDomanda()}"
				class="list-group-item list-group-item-action flex-column align-items-start">

				<div class="d-flex w-100 justify-content-between">
					<h5 class="mt-0">${storicoRisposte.getCorpo()}</h5>
					<small>${storicoRisposte.getDataPubblicazione()}</small>
				</div>

				<p class="mb-1">
					in risposta alla domanda <b>${storicoRisposte.getTitoloDomanda()}</b>
				</p>
			</a>
		</div>
		<br>
	</c:forEach>
	
	
	
	
			<c:if test="${not empty storicoRisposte}">
		<nav class="paginator d-flex justify-content-center" aria-label="Page navigation example">
  		<ul class="pagination">
  		<c:if test="${pageRi > 0 }">
    		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage-1)%>)><ion-icon name="arrow-back"></ion-icon> Previous</a></li>
    		</c:if>    	
    		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage+1)%>)>Next <ion-icon name="arrow-forward"></ion-icon></a></li>
		</ul>
		</nav>
		<br><br>		
			</c:if>	
	
	</div>

	<c:if test="${empty storicoRisposte}">
	<div class="text-center">
		<p>Non hai pubblicato risposte</p>
		</div>
	</c:if>
	
	
		<script>
		function changePage(pageNumber){
			window.location.href = "/AskToReply/VisualizzaProfilo?pageRi="+pageNumber+"#storicoRisposte";
		}
	</script>