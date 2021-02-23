<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="profilo" />
</jsp:include>


<!--  -->
  
<%  
	int currentPage = 1;
	if(request.getParameter("page") != null){
		currentPage = Integer.parseInt(request.getParameter("page"));		
	}  
	request.setAttribute("page", currentPage);
%> 

<br>
<div class="content row">
	<div class="col-md-2 order-md-3 ">
		<jsp:include page="TabSwitcher.jsp">
				<jsp:param name="active" value="storicoRisposte" />			
		</jsp:include>
	</div>
<div class="card-body col-md-10">
	<div class="card-title">
	<a name="storicoRisposte"></a>
		<h3>Storico Risposte</h3>
	</div>
	
	
	<c:forEach var="storicoRisposte" items="${storicoRisposte}">

		<div class="list-group">
			<a href="VisualizzaDomandaServlet?id=${storicoRisposte.getIdDomanda()}"
				class="list-group-item list-group-item-action flex-column align-items-start">

				<div class="d-flex w-100 justify-content-between">
					<p style="color:black;" class="mt-0">${storicoRisposte.getCorpo()}</p>
					<small>${storicoRisposte.getDataPubblicazione()}</small>
				</div>

				<p class="mb-1">
					<small>
					in risposta a: ${storicoRisposte.getTitoloDomanda()}
					</small>
				</p>
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
	