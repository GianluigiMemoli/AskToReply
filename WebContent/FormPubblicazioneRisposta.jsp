<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<div class="container">
<%=request.getParameter("idDomanda") %>
			<form action="PubblicazioneRispostaServlet" method="post">
			
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
        <form>
          <div class="form-group">
            <textarea class="form-control" id="corpo" name="corpo" placeholder="Scrivi qui la tua risposta"></textarea>
          </div>
          
  <div class="custom-file">
    <input type="file" class="custom-file-input" id="customFile">
    <label class="custom-file-label" for="customFile">Carica allegato</label>
  </div>
  
</form>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
        <button type="submit" class="btn btn-primary"><ion-icon name="send"></ion-icon> Invia</button>
      </div>
    </div>
  </div>
</div>

</form>

<script>
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>


			
</div>

