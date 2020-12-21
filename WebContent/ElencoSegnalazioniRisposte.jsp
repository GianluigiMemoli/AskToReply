<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="SidebarMod.jsp" ></jsp:include>

<style>
	.segnalazioni-list{
		padding: 2em;
	}
</style>

	<div class="content">
		<div class="segnalazioni-list">
	
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
    <button type="submit" class="btn btn-outline-success btn-sm border-0" name="approva" id="approva"><ion-icon name="shield-checkmark"></ion-icon> Approva</button>
  <button type="submit" class="btn btn-outline-danger btn-sm border-0" name="ignora" id="ignora"><ion-icon name="trash"></ion-icon> Ignora</button>
  
    <button type="submit" class="btn btn-outline-info btn-sm border-0" name="ignora" id="ignora"><ion-icon name="eye"></ion-icon> Mostra allegato</button>
  
  
  </a>
  </div>
  </form>
  <br>		
  
							</c:forEach>						
					
						<c:if test="${empty segnalazionirisposte}">
	<div class="text-center">
		<p>Nessuna segnalazione</p>
		</div>
	</c:if>
		</div>

	</div>				
	</div>


<jsp:include page="Footer.jsp"></jsp:include> 