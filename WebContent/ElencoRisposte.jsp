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
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != risposta.getIdAutore()}">
						
						 										<small class="text-secondary">@${risposta.getAutore()}</small>
						
					  
					  
						</c:when>
						<c:otherwise>
											   <small class="text-secondary">hai risposto:</small>
						
 					
 						</c:otherwise>
					</c:choose>	
					
					
					<small class="text-secondary">${risposta.getDataPubblicazione()}</small>
				</div>
				    <p>${risposta.getCorpo()}</p>
	    					
				
					 
					 
					 
										
					
					
					
					
														<c:choose>
						<c:when test="${utenteLoggato != null && utenteLoggato.getId() != risposta.getIdAutore()}">
						
				  <button name="mipiace" id="btn_${risposta.getId()}_mipiace" onmouseover=likeFunctionOnOver("${risposta.getId()}") onclick=likeFunction("${risposta.getId()}") class="btn btn-outline-success btn-sm border-0 btnsmussato"><ion-icon name="thumbs-up"></ion-icon> Mi piace&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:#C8E6C9;"  class="badge badge-pill badge-success text-success">${risposta.getMiPiace()}</span></button>
					<button name="nonmipiace" id="btn_${risposta.getId()}_nonmipiace" onmouseover=dislikeFunctionOnOver("${risposta.getId()}") onclick=dislikeFunction("${risposta.getId()}") type="submit" class="btn btn-outline-danger btn-sm border-0 btnsmussato" data-toggle="modal" data-target="" data-whatever="@getbootstrap"><ion-icon name="thumbs-down"></ion-icon> Non mi piace&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:#FFCDD2" class="badge badge-pill badge-danger text-danger">${risposta.getNonMiPiace()}</span></button>
					 <button onclick="document.getElementById('idRisposta').value='${risposta.getId()}';"  type="button" class="btn btn-outline-warning btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#segnalaRispostaModal" data-whatever="@getbootstrap"><ion-icon name="warning"></ion-icon> Segnala&nbsp;</button>
					     
					  
						</c:when>
						<c:otherwise>
 					<button name="mipiace" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="thumbs-up"></ion-icon> Mi piace&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:Gainsboro;"  class="badge badge-pill badge-success text-dark">${risposta.getMiPiace()}</span></button>
					<button name="nonmipiace" class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark" disabled><ion-icon name="thumbs-down"></ion-icon> Non mi piace&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:Gainsboro" class="badge badge-pill badge-danger text-dark">${risposta.getNonMiPiace()}</span></button>
					   						</c:otherwise>
					</c:choose>	
					
						    		   <button type="button" class="btn btn-outline-info btn-sm border-0 btnsmussato" name="allegati" id="allegati"><ion-icon name="eye"></ion-icon> Mostra allegati&nbsp;</button>
					
					 
								
					
				
											 
				</div>
				
				
				
				
				
				

				
				
				
				
				
				</c:forEach>
		</form>
		</div>
		</div>	
						 						<jsp:include page="FormSegnalazioneRisposta.jsp"></jsp:include>
	
	<jsp:include page="FormPubblicazioneRisposta.jsp"></jsp:include> 	
	


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
		
		function likeFunction(idRisp){
			
			var idBtn="btn_"+idRisp+"_mipiace";
			var x = document.getElementById(idBtn);
			var y = document.getElementById("btn_"+idRisp+"_nonmipiace");

			
			if(x.name=="mipiace"){
				x.className+=" mipiace";
				x.name="mipiace_cliccato";
				document.getElementById("span_"+idRisp+"_mipiace").innerText-=-1;
				if(y.name=="nonmipiace_cliccato"){
				y.classList.remove("nonmipiace");
				y.name="nonmipiace";
				document.getElementById("span_"+idRisp+"_nonmipiace").innerText-=1;
				}
				
			}else if (x.name=="mipiace_cliccato"){
				x.classList.remove("mipiace")
				x.name="mipiace";
				document.getElementById("span_"+idRisp+"_mipiace").innerText-=1;

			}
			document.getElementById("idRisposta").value=idRisp;
			document.getElementById("value").value=+1;			
		}
		
		function dislikeFunction(idRisp){
		
			var idBtn="btn_"+idRisp+"_nonmipiace";
			var x = document.getElementById(idBtn);
			var y = document.getElementById("btn_"+idRisp+"_mipiace");
			
			if(x.name=="nonmipiace"){
				x.className+=" nonmipiace";
				x.name="nonmipiace_cliccato";
				document.getElementById("span_"+idRisp+"_nonmipiace").innerText-=-1;
				
				if(y.name=="mipiace_cliccato"){
					y.classList.remove("mipiace")
					y.name="mipiace";
					document.getElementById("span_"+idRisp+"_mipiace").innerText-=1;
					}
				
			}else if (x.name=="nonmipiace_cliccato"){
				x.classList.remove("nonmipiace");
				x.name="nonmipiace";
				document.getElementById("span_"+idRisp+"_nonmipiace").innerText-=1;

			}
			document.getElementById("idRisposta").value=idRisp;
			document.getElementById("value").value=-1;
		}
		
		
		function changePage(pageNumber){
			//window.location.href = "/AskToReply/VisualizzaDomandaServlet?id="+${domanda.getId()}+"&pageRi="+pageNumber+"#Risposte";
		}
		
	</script>
	

		