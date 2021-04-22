<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="Header.jsp"></jsp:include>

<jsp:include page="SidebarMod.jsp">
	<jsp:param name="active" value="domande" />
</jsp:include>

<div class="content">

	<ul class="list-group">

		<% int counter = 1; %>

		<c:forEach items="${segnalazioniDomanda}" var="s">

			<li class="list-group-item">
			
				<c:choose>
					<c:when test="${s.getMotivazione().getId() == 1}">
						<c:set var="url_risoluzione_segnalazione"
							value="RisolviSegnalazioneDomandaServlet" />
					</c:when>
					<c:when test="${s.getMotivazione().getId() == 2}">
						<c:set var="url_risoluzione_segnalazione"
							value="CambiaCategorieDomandaServlet" />
					</c:when>
				</c:choose>

				<form action="${url_risoluzione_segnalazione}" method="get">

					<input type="hidden" name="idSegnalazione" value="${s.getId()}" />

					<p class="text-secondary">${s.getDataSegnalazione()}</p>

					<p class="text-secondary">Motivazione: ${s.getMotivazione().getNome()}</p>

					<h5>Titolo domanda segnalata: ${s.getDomandaSegnalata().getTitolo()}</h5>

					<p>Corpo domanda segnalata: ${s.getDomandaSegnalata().getCorpo()}</p>

					<p>Commento segnalazione: ${s.getCommento()}</p>

					<c:choose>

						<c:when test="${s.getMotivazione().getId() == 1}">

							<button 
								type="submit"
								class="btn btn-outline-success btn-sm border-0 btnsmussato"
								name="approva" id="approva">
								<ion-icon name="shield-checkmark"></ion-icon>
								Approva
							</button>

						</c:when>

						<c:when test="${s.getMotivazione().getId() == 2}">

							<button type="button" class="btn btn-success border-0"
								data-toggle="modal" data-target="<%= "#modal" + counter %>">
								<ion-icon name="shield-checkmark"></ion-icon>
								Approva
							</button>

							<div class="modal fade" id="<%= "modal" + counter  %>" tabindex="-1"
								role="dialog" aria-labelledby="${s.getId()}" aria-hidden="true">

								<div class="modal-dialog">

									<div class="modal-content">

										<div class="modal-header">
											<h5 class="modal-title">Segnalazione '${s.getId()}'</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>

										<div class="modal-body">

											<form action="CambiaCategorieDomandaServlet" method="post">

												<input type="hidden" name="idSegnalazione"
													value="${s.getId()}" />

												<c:forEach items="${categorie}" var="c">
													<div class="form-check">
														<input class="form-check-input" type="checkbox"
															name="categorieDomanda" value="${c.getId()}"> <label
															class="form-check-label"> ${c.getNome()} </label>
													</div>
												</c:forEach>

												<input type="submit" class="btn btn-primary mt-2"
													value="Invia" />

											</form>
										</div>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>

					<a
						href="DeclinaSegnalazioneDomandaServlet?idSegnalazione=${s.getId()}"
						class="btn btn-outline-danger btn-sm border-0 btnsmussato"
						name="ignora" id="ignora"> <ion-icon name="trash"></ion-icon>
						Ignora
					</a>

					<c:choose>
						<c:when test="${s.getDomandaSegnalata().getAllegati().size() > 0}">
							<button type="button"
								class="btn btn-outline-light btn-sm border-0 btnsmussato text-dark"
								disabled>
								<ion-icon name="image"></ion-icon>
								<span class="responsivespan"> Contiene allegati&nbsp;</span>
							</button>
						</c:when>
					</c:choose>

				</form></li>
				
				<c:set var="counter" value="${counter + 1}"></c:set>
				
		</c:forEach>

	</ul>

	<div class="col-12">


		<!-- paginazione -->
		<nav>
			<ul class="pagination">
				<c:if test="${paginaCorrente > 1}">
					<li class="page-item"><a class="page-link"
						href="ElencoSegnalazioniDomandaServlet?p=${paginaCorrente-1}">Precedente</a>
					</li>
				</c:if>
				
				<li class="page-item disabled"><span class="page-link">${paginaCorrente}
						/ ${pagineTotali}</span></li>

				<c:if test="${paginaCorrente < pagineTotali}">
					<li class="page-item"><a class="page-link"
						href="ElencoSegnalazioniDomandaServlet?p=${paginaCorrente+1}">Avanti</a>
					</li>
				</c:if>
			</ul>
		</nav>

	</div>

</div>


<jsp:include page="Footer.jsp" />