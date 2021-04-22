<div 
	class="modal fade" 
	id="${param.idModal}" 
	tabindex="-1" 
	role="dialog" 
	aria-hidden="true">

	<div class="modal-dialog" role="document">
	
		<div class="modal-content">
		
			<div class="modal-header">
        		<h5 class="modal-title">Segnala domanda</h5>
               	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         				<span aria-hidden="true">&times;</span>
       			</button>
      		</div>
      		
      		<div class="modal-body">
      		
      			<form action="SegnalazioneDomandaServlet" method="post">
      		
      				<input type="hidden" name="idDomandaSegnalata" value="${param.idDomanda}">
      		
	         		<div class="form-group">
	          		  	<textarea 
		          		  	class="form-control"
		          		  	name="commento" 
		          		  	placeholder="Inserisci qui il commento della tua segnalazione"></textarea>
	   				</div>
	   				
	         		<br>
	         		
					<p>Seleziona motivazione:</p>
					
					<ul class="list-group">
						<li class="list-group-item">					
							<input type="radio" value="1" name="idMotivazione" id="radio1" required>
							<label for="radio1">Contenuto offensivo</label>
						</li>
	
						<li class="list-group-item">					
							<input type="radio" value="2" name="idMotivazione" id="radio2" required>
							<label for="radio2">Off-topic</label>
						</li>
					</ul> 
	
				    <div class="modal-footer">
				     	<button type="submit" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
				     	<button type="submit" class="btn btn-warning">
				     		<ion-icon 
				     			name="warning" 
				     			role="img" 
				     			class="md hydrated" 
				     			aria-label="warning"></ion-icon> 
				     			Invia segnalazione
				     	</button>
				   	</div>
				   	
				</form>
				
   			</div>
   			
   		</div>
   		
  	</div>
  	
</div>