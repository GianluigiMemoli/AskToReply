<%@page import="java.util.ArrayList" %>
<%@page import="model.CategoriaBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp" />

<div class="content">

	<div class="container">
	
		<div class="row">
		
			<div class="col-12"><h2 class="text-center">Pubblicazione domanda</h2></div>
		
			<div class="col-12">
			
				<form action="PubblicazioneDomandaServlet" method="post" enctype="multipart/form-data">
				
					<div class="form-group">
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
								<label class="form-check-label">${cat.nome}</label>
							</div>
						</c:forEach>
					</div>
						
					<input type="submit" class="btn btn-primary" value="Pubblica">		

				</form>
				
			</div>
		</div>
		
		<div>
		
		</div>
		
	</div>

</div>
	
<jsp:include page="Footer.jsp" />	