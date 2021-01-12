<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
<jsp:include page="Header.jsp"></jsp:include>

<jsp:include page="SidebarMod.jsp">
	<jsp:param name="active" value="risposte" />
</jsp:include>

<style>
.debug-content {
	border: 2px solid red;
}

.debug-media {
	border: 2px solid blue;
}

.questions-list {
	padding: 2em;
}

.question {
	margin-bottom: 2em;
	padding: 1em;
}

.paginator {
	align-self: center;
}
</style>





<div class="content debug">


	<div class="card-body">

		<input type="hidden" id="idDomanda" name="idDomanda" value="">


		<c:forEach var="segnalazionerisposta" items="${segnalazionirisposte}">
			<form action="gestioneSegnalazioneRispostaServlet" method="post" enctype="application/x-www-form-urlencoded">
				<input type="hidden" id="idSegnalazione" name="idSegnalazione" value="${segnalazionerisposta.getIdSegnalazione()}"/>
				<input type="hidden" id="idRisposta" name="idRisposta" value="${segnalazionerisposta.getIdRisposta()}"/>
					
			<div class="question rounded border">


				<div>

					<div class="d-flex w-100 justify-content-between">
						<small class="text-secondary" style="  text-transform: uppercase;">${segnalazionerisposta.getMotivazione()}</small>
						<small class="text-secondary">${segnalazionerisposta.getDataSegnalazione()}</small>
					</div>

						<h5 style="margin-bottom: 0pt; color: black;" class="lead">${segnalazionerisposta.getCorpoRisposta()}</h5>
						<p style="color: black; margin-bottom:0pt;">in risposta a: ${segnalazionerisposta.getTitoloDomanda()} ${segnalazionerisposta.getCorpoDomanda()}</p>
						<small>Commento segnalazione: ${segnalazionerisposta.getCommento()}</small>
					
				</div>
		
			    <button type="submit" class="btn btn-outline-success btn-sm border-0 btnsmussato" name="approva" id="approva"><ion-icon name="shield-checkmark"></ion-icon> Approva</button>
			  	<button type="submit" class="btn btn-outline-danger btn-sm border-0 btnsmussato" name="ignora" id="ignora"><ion-icon name="trash"></ion-icon> Ignora</button>
			    <button type="submit" class="btn btn-outline-info btn-sm border-0 btnsmussato" name="mostraAllegato" id="mostraAllegato"><ion-icon name="eye"></ion-icon> Mostra allegato</button>
			  

			</div>
			</form>
		</c:forEach>

		<c:if test="${empty segnalazionirisposte}">
			<div class="text-center" style="margin-top:25pt;">
					<p>Non sono ancora presenti segnalazioni a risposte</p>
			</div>
		</c:if>
	</div>
</div>

<jsp:include page="Footer.jsp"></jsp:include> 