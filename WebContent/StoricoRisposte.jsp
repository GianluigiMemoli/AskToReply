<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	
<!--  -->
<br>
	 	<div class=" card col-sm-12 col-md-12">
	  	<div class="card-body">
					<div class="card-title">
	  			<h3>Storico Risposte</h3>
	  		</div>
		<c:forEach var="storicoRisposte" items="${storicoRisposte}">
				
	<div class="list-group">
  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
  
    <div class="d-flex w-100 justify-content-between">
               <small for="segnalazionirisposte">${segnalazionerisposta.getMotivazione()}</small>
     
      <small>${storicoRisposte.getDataPubblicazione()}</small>
    </div>
          <h5 class="mb-1">${storicoRisposte.getCorpo()}</h5>
    
    <p class="mb-1">in risposta a: TITOLODOMANDA CORPODOMANDA</p>
  </a>
  </div>
  
							</c:forEach>	
		</div></div>			
<!--  -->
