<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
    

<div class="container">
			<form action="PubblicazioneRispostaServlet" method="post" enctype="multipart/form-data">
			
				<div class="modal fade" id="pubblicaRispostaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Rispondi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="form-group">
          		  <input type="hidden" id="idDom" name="idDom" value="" readonly>
          		  <!-- h1 id="solo_per_testare"></h1-->
            <textarea onclick="getIdDom()" class="form-control" id="corpo" name="corpo" pattern="([A-z0-9]*\W*)" placeholder="Scrivi qui la tua risposta"></textarea>
          </div>
          
					<div class="form-group">
						<label for="allegati">Allegati</label>
						<input type="file" name="allegati" id="allegati" class="form-control-file" multiple>	
					</div>
            
        </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-secondary btnsmussato" data-dismiss="modal">Annulla</button>
        <button type="submit" class="btn btn-primary btnsmussato"><ion-icon name="send"></ion-icon> Invia risposta</button>
      </div>
    </div>
  </div>
</div>

</form>

<script>

function getIdDom(){
	document.getElementById("idDom").value=document.getElementById("idDomanda").value;
	//document.getElementById("solo_per_testare").innerHTML=document.getElementById("idDomanda").value;

}

</script>

			
</div>