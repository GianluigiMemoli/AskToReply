			<div class="question rounded border">


				<div>

					<div class="d-flex w-100 justify-content-between">
						<small class="text-secondary">@${param.autore}</small>
						<small class="text-secondary">${param.data}</small>
					</div>

					<c:forEach items="${domanda.getCategorie()}" var="categoria">
						<small><a
							style="background-color: #EDE7F6; color: purple; border-radius: 99em;"
							href="RicercaServlet?categorie=${categoria.getId()}"
							class="badge">${categoria.nome}</a></small>
					</c:forEach>

					<a href="VisualizzaDomandaServlet?id=${domanda.getId()}"
						class="list-group-item-action">
						<h5 style="margin-bottom: 0pt; color: black;" class="lead">${domanda.getTitolo()}</h5>
						<p style="color: black;">${domanda.getCorpo()}</p>
					</a>
				</div>
				<div>
			
				<c:if test="${!utenteLoggato.getId().equals(domanda.getId())}">
					<c:choose>
					<c:when test="${domandeRisposte.contains(domanda)}">
					<button
						style="background-color:#E3F2FD; pointer-events: none;"
						type="button"
						class="btn btn-outline-primary btn-sm border-0 btnsmussato">
						<ion-icon name="chatbubble-ellipses"></ion-icon>
						Hai risposto
						<span style="background-color:#BBDEFB;"  class="badge badge-pill badge-success text-primary">${numeroRisposte.get(domanda.getId())}</span>
					</button>
					</c:when>
					<c:otherwise>
					<c:if test="${utenteLoggato.getId().equals(domanda.getAutore().getId()) == false}">					
					<button
						onclick="document.getElementById('idDomanda').value='${domanda.getId()}'"
						type="submit"
						class="btn btn-outline-primary btn-sm border-0 btnsmussato"
						data-toggle="modal" data-target="#pubblicaRispostaModal"
						data-whatever="@getbootstrap">
						<ion-icon name="chatbubble-ellipses"></ion-icon>
						Rispondi
						<span style="background-color:#BBDEFB;"  class="badge badge-pill badge-success text-primary">${numeroRisposte.get(domanda.getId())}</span>
					</button>
					</c:if>
					</c:otherwise>	
					</c:choose>
					</c:if>
					<c:if test="${utenteLoggato.getId().equals(domanda.getAutore().getId()) == false}">										
						<button type="submit"
							class="btn btn-outline-warning btn-sm border-0 btnsmussato"
							data-toggle="modal"
							data-target="#dibenedettoinserisciquiiltitolodelmodalchehaifatto"
							data-whatever="@getbootstrap">
							<ion-icon name="warning"></ion-icon>
							Segnala
						</button>
					</c:if>
				</div>
			</div>