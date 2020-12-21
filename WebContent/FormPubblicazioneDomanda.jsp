<%@page import="java.util.ArrayList" %>
<%@page import="model.CategoriaBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp" />

<div class="content edit-profile">
	<div class="card-body">
	
		<div class="card-title">
			<h3>Pubblicazione domanda</h3>
		</div>		
			
				<form action="PubblicazioneDomandaServlet" method="post" enctype="multipart/form-data">
				
					<div>
						<label for="titolo">Titolo</label>
						<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Titolo"> <br/>
					</div>
				
					<div class="form-group">
						<label for="corpo">Corpo della domanda</label>
						<textarea name="corpo" id="corpo" class="form-control" rows="5" cols="50"></textarea>		
					</div>
				
					<div class="form-group">
						<label for="allegati">Allegati</label>
						<input type="file" name="allegati" id="allegati" class="form-control-file" multiple>	
					</div>
				
					<div class="form-group">
						<c:forEach var="cat" items="${categorie}">
							<div class="form-check">
								<input type="checkbox" name="categorie" class="form-check-input" value="${cat.id}">
								<label class="form-check-label">
									<a style="background-color:#EDE7F6; color:purple; border-radius:99em;" class="badge">${cat.nome}</a>
								</label>
							</div>
						</c:forEach>
					</div>
						
					<input type="submit" class="btn btn-primary" value="Pubblica" style="border-radius:99em;">		

				</form>
				
			</div>
		</div>
		
		<div>
		
		</div>
		
	</div>

</div>
	
<jsp:include page="Footer.jsp" />	