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
		
		<input type="hidden" id="idRisposta" name="idRisposta" value="">
				
				<c:forEach var="risposta" items="${risposte}">
			
				<div class="question rounded border">								
				  <div  style="margin:15pt">
				  				<div class="d-flex w-100 justify-content-between">
					<small class="text-secondary">@${risposta.getAutore()}</small>
					<small class="text-secondary">${risposta.getDataPubblicazione()}</small>
				</div>
				    <p>${risposta.getCorpo()}</p>
	    					
				  <button name="mipiace" id="btn_${risposta.getId()}_mipiace" onclick=likeFunction("${risposta.getId()}") class="btn btn-outline-success btn-sm border-0 btnsmussato"><ion-icon name="thumbs-up"></ion-icon> Mi piace&nbsp;<span id="span_${risposta.getId()}_mipiace" style="background-color:#C8E6C9;"  class="badge badge-pill badge-success text-success">${risposta.getMiPiace()}</span></button>
					<button name="nonmipiace" id="btn_${risposta.getId()}_nonmipiace" onclick=dislikeFunction("${risposta.getId()}") type="submit" class="btn btn-outline-danger btn-sm border-0 btnsmussato" data-toggle="modal" data-target="" data-whatever="@getbootstrap"><ion-icon name="thumbs-down"></ion-icon> Non mi piace&nbsp;<span id="span_${risposta.getId()}_nonmipiace"  style="background-color:#FFCDD2" class="badge badge-pill badge-danger text-danger">${risposta.getNonMiPiace()}</span></button>
					
					 <button onclick="document.getElementById('idRisposta').value='${risposta.getId()}';"  type="submit" class="btn btn-outline-warning btn-sm border-0 btnsmussato" data-toggle="modal" data-target="#segnalaRispostaModal" data-whatever="@getbootstrap"><ion-icon name="warning"></ion-icon> Segnala&nbsp;</button>
					     
					     <button type="submit" class="btn btn-outline-info btn-sm border-0 btnsmussato" name="ignora" id="ignora"><ion-icon name="eye"></ion-icon> Mostra allegati&nbsp;</button>
					 
					 
					 
				  </div>
										
					 
								
					
				
											 
				</div>
				<br>
				</c:forEach>
		
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

		}
		
		
		function changePage(pageNumber){
			window.location.href = "/AskToReply/VisualizzaDomandaServlet?id="+${domanda.getId()}+"&pageRi="+pageNumber+"#Risposte";
		}
	</script>
	

		