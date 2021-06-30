<%@page import="java.util.ArrayList" %>
<%@page import="gestioneDomanda.CategoriaBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="Header.jsp" />

<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="empty" />
</jsp:include>

<div class="content edit-profile">
	<div class="card-body">
	
		<div class="card-title">
			<h3>Pubblicazione domanda</h3>
		</div>
		
				<jsp:include page="PopupErrore.jsp"></jsp:include>
			
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
						<div class="overflow-auto" style="height: 229px;">  
							<ul class="list-group" style="margin-right:5pt;">
								<c:forEach items="${categorie}" var="cat">
									<li class="list-group-item">
										<input type="checkbox" value="${cat.getId()}" name="categorie" id="interesseCheck">
										<label for="interesseCheck"><a style="background-color:#EDE7F6; color:purple; border-radius:99em;" class="badge">${cat.getNome()}</a></label>
									</li>
								</c:forEach>
							</ul>
						</div>	
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