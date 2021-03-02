<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<%@page import="model.DomandaBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%  
	int currentPage = 0;
	if(request.getParameter("pageRi") != null){
		currentPage = Integer.parseInt(request.getParameter("pageRi"));		
	}  
	request.setAttribute("pageRi", currentPage);
	
%> 

<style>

@media screen and (max-width: 515px) {


span.responsivespan{
      display: none;
}
 

button.responsivebtn{
  border: 2px solid #008CBA;
}


}





.mipiaceCliccato{
	color:black;
}

</style>


<div class="card-body">
	<div class="card-title">
	<a name="Risposte"></a>
		<h3 >Risposte:</h3>
	</div>
	
	
		<div class="debug"> 

		<div class="questions-list">
		
											<form action="VotazioneRispostaServlet" method="post" enctype="application/x-www-form-urlencoded">
						
						
								<input type="hidden" id="idRisposta" name="idRisposta" value="">
		<input type="hidden" id="value" name="value" value="">
		
				<input type="hidden" id="idDom" name="idDom" value="<%= request.getParameter("idDom")%>">
	
							
				
				<c:forEach var="risposta" items="${risposte}">
			
					
				<div class="question rounded border">
							
				
				
												
				  				<div class="d-flex w-100 justify-content-between">
																	<c:choose>
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != risposta.getAutore().getId()}">
						
						 										<small class="text-secondary">@${risposta.getAutore().getUsername()}</small>	  
						</c:when>
						<c:otherwise>
											   <small class="text-secondary">hai risposto:</small>
						
 					
 						</c:otherwise>
					</c:choose>	
					
					
					<small class="text-secondary">${risposta.getDataPubblicazione()}</small>
				</div>
				    <p>${risposta.getCorpo()}</p>
	    					
				
					 
				<c:choose>
					<c:when test="${risposta.getAllegati().size() > 0}">
						<%session.setAttribute("allegati_${risposta.getId()}", "${risposta.getAllegati()}");%>
						<%session.setAttribute("testina", "valorepassato");%>
						<div class="container">
							<div class="row">

							<c:forEach items="${risposta.getAllegati()}" var="allegato">
								
								<div class="col-0 p-0" style="margin-right:5pt; margin-bottom:16px; ">
									<img src="data:image/jpg;base64,${allegato}" alt="" class="img-fluid img-thumbnail" style="max-height: 150px; min-height: 100px; min-height: 50%;">
								</div>
								
							</c:forEach>
								</div>
							</div>
					</c:when>
				</c:choose>
											<c:choose>
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != risposta.getAutore().getId()}">
						
						<c:choose>
						<c:when test="${risposteApprezzate.contains(risposta.getId())}">
								<button name="mipiace" id="btn_${risposta.getId()}_mipiace" onmouseover="likeFunctionOnOver('${risposta.getId()}')"  class="btn btn-outline-success btn-sm border-0 btnsmussato responsivebtn mipiace"><ion-icon name="thumbs-up"></ion-icon><span class="responsivespan"> Mi piace</span>&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:#C8E6C9;"  class="badge badge-pill badge-success text-success">${risposta.getMiPiace()}</span></button>
						</c:when>
						<c:otherwise>
								<button name="mipiace" id="btn_${risposta.getId()}_mipiace" onmouseover="likeFunctionOnOver('${risposta.getId()}')" class="btn btn-outline-success btn-sm border-0 btnsmussato responsivebtn"><ion-icon name="thumbs-up"></ion-icon><span class="responsivespan"> Mi piace</span>&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:#C8E6C9;"  class="badge badge-pill badge-success text-success">${risposta.getMiPiace()}</span></button>
						</c:otherwise>
						</c:choose>					
				
												<c:choose>
						<c:when test="${risposteNonApprezzate.contains(risposta.getId())}">
								<button name="nonmipiace" id="btn_${risposta.getId()}_nonmipiace" onmouseover="dislikeFunctionOnOver('${risposta.getId()}')" type="submit" class="btn btn-outline-danger btn-sm border-0 btnsmussato responsivebtn nonmipiace" data-toggle="modal" data-target="" data-whatever="@getbootstrap"><ion-icon name="thumbs-down"></ion-icon><span class="responsivespan"> Non mi piace</span>&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:#FFCDD2" class="badge badge-pill badge-danger text-danger">${risposta.getNonMiPiace()}</span></button>
						</c:when>
						<c:otherwise>
								<button name="nonmipiace" id="btn_${risposta.getId()}_nonmipiace" onmouseover="dislikeFunctionOnOver('${risposta.getId()}')" type="submit" class="btn btn-outline-danger btn-sm border-0 btnsmussato responsivebtn" data-toggle="modal" data-target="" data-whatever="@getbootstrap"><ion-icon name="thumbs-down"></ion-icon><span class="responsivespan"> Non mi piace</span>&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:#FFCDD2" class="badge badge-pill badge-danger text-danger">${risposta.getNonMiPiace()}</span></button>
						</c:otherwise>
						</c:choose>		
									
					 <button onclick="document.getElementById('idRisposta').value='${risposta.getId()}';"  type="button" class="btn btn-outline-warning btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#segnalaRispostaModal" data-whatever="@getbootstrap"><ion-icon name="warning"></ion-icon><span class="responsivespan"> Segnala&nbsp;</span></button>
					     
					  
						</c:when>
						<c:otherwise>
		
 					<button name="mipiace" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="thumbs-up"></ion-icon><span class="responsivespan"> Mi piace</span>&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:Gainsboro;"  class="badge badge-pill badge-success text-dark">${risposta.getMiPiace()}</span></button>
					<button name="nonmipiace" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="thumbs-down"></ion-icon><span class="responsivespan"> Non mi piace</span>&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:Gainsboro" class="badge badge-pill badge-danger text-dark">${risposta.getNonMiPiace()}</span></button>
					   						</c:otherwise>
					</c:choose>	
					
					<c:choose>
					<c:when test="${risposta.getAllegati().size() > 0}">
					<button type="button" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="image"></ion-icon><span class="responsivespan"> Contiene allegati&nbsp;</span></button>
					</c:when>
					</c:choose>		
										 
				</div>
				
				
	
				
				</c:forEach>
		</form>
		</div>
		</div>	
						 						<jsp:include page="FormSegnalazioneRisposta.jsp"></jsp:include>
	



			<c:if test="${not empty risposte}">
		<nav class="paginator d-flex justify-content-center" aria-label="Page navigation example">
  		<ul class="pagination">
  		<c:if test="${pageRi > 0 }">
    		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage-1)%>)><ion-icon name="arrow-back"></ion-icon> Previous</a></li>
    		</c:if> 
    		   	  
    		   	  <c:if test="${next == 1 }">
    		   	  		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage+1)%>)>Next <ion-icon name="arrow-forward"></ion-icon></a></li>
		    		</c:if> 
		
		</ul>
		</nav>
		<br><br>		
			</c:if>	

	<c:if test="${empty risposte}">
	<div class="text-center">
		<p>Nessuna risposta</p>
		</div>
	</c:if>
		</div>
	

		<script>
		
		function likeFunctionOnOver(idRisp){
			document.getElementById("idRisposta").value=idRisp;
			document.getElementById("value").value=+1;			
		}
		
		function dislikeFunctionOnOver(idRisp){
			document.getElementById("idRisposta").value=idRisp;
			document.getElementById("value").value=-1;			
		}
		
		
		function changePage(pageNumber){
			window.location.href = "/AskToReply/VisualizzaDomandaServlet?id="+"${domanda.getId()}"+"&pageRi="+pageNumber+"#Risposte";
		}
		
	</script>
	

		