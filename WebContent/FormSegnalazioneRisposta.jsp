<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<div class="container">
			<form action="SegnalazioneRispostaServlet" method="post">
			
				<div class="modal fade" id="segnalaRispostaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Segnala</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
     
          <div class="form-group">
          		  <input type="hidden" id="idRisp" name="idRisp" value="" readonly>
          		  		<input type="hidden" id="idDomanda" name="idDomanda" value="${domanda.getId()}">
          		  
          		  <!-- h1 id="solo_per_testare"></h1-->
            <textarea onclick="getIdRisp()" class="form-control" id="commento" name="commento" placeholder="Commenta segnalazione"></textarea>
   </div>
          
	<br>
	<p>Seleziona motivazione:</p>
<ul class="list-group">
				<c:forEach var="motivazione" items="${motivazioni}">
					<li class="list-group-item">					
							<input onclick="getIdRisp()"  type="radio" value="${motivazione.getId()}"
								name="idMotivazione" id="idMotivazione">
						<label for="interesseCheck"> ${motivazione.getNome()}</label></li>
				</c:forEach>
			</ul> 
  
  
        <div class="modal-footer">
        <button type="submit" class="btn btn-secondary btnsmussato" data-dismiss="modal">Annulla</button>
        <button type="submit" class="btn btn-warning btnsmussato"><ion-icon name="warning" role="img" class="md hydrated" aria-label="warning"></ion-icon> Invia segnalazione</button>
      </div>
  
       </div>
      </div>

    </div>
  </div>

</form>
</div>

<script>

function getIdRisp(){
	console.log("test"); //test
	document.getElementById("idRisp").value=document.getElementById("idRisposta").value;
	//document.getElementById("solo_per_testare").innerHTML=document.getElementById("idDomanda").value;
	console.log(document.getElementById("idRisp").value); //test
}



</script>


			
</div>