<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="SidebarMod.jsp" ></jsp:include>

	<div class="content">

				<br>	
		<c:forEach var="segnalazionerisposta" items="${segnalazionirisposte}">
					<form action="gestioneSegnalazioneRispostaServlet" method="post" enctype="application/x-www-form-urlencoded">
					        <input type="hidden" id="idSegnalazione" name="idSegnalazione" value="${segnalazionerisposta.getIdSegnalazione()}"/>
						<input type="hidden" id="idRisposta" name="idRisposta" value="${segnalazionerisposta.getIdRisposta()}"/>
					
	<div class="list-group">
  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
  
    <div class="d-flex w-100 justify-content-between">
               <small for="segnalazionirisposte">${segnalazionerisposta.getMotivazione()}</small>
     
      <small>${segnalazionerisposta.getDataSegnalazione()}</small>
    </div>
          <h5 class="mb-1">${segnalazionerisposta.getCorpoRisposta()}</h5>
    
    <p class="mb-1">in risposta a: ${segnalazionerisposta.getTitoloDomanda()} ${segnalazionerisposta.getCorpoDomanda()}</p>
        <div class="d-flex w-100 justify-content-between">
    <small for="segnalazionirisposte">Commento: ${segnalazionerisposta.getCommento()}</small>

    </div>
    <br>
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
    <button type="submit" class="btn btn-outline-success btn-sm" name="approva" id="approva"><ion-icon name="shield-checkmark-outline"></ion-icon> Approva</button>
  <button type="submit" class="btn btn-outline-danger btn-sm" name="ignora" id="ignora"><ion-icon name="trash-outline"></ion-icon></ion-icon> Ignora</button>
  </div>
  </a>
  </div>
  </form>
  <br>		
  
							</c:forEach>						
					

					
	</div>


<jsp:include page="Footer.jsp"></jsp:include> 