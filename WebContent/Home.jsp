<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp" ></jsp:include>
<style>
	.debug-content{
		border: 2px solid red; 
	}
	.debug-media{
		border: 2px solid blue; 
	}
	.questions-list{
		padding: 2em;
	}
	.question{
		border-bottom: 2px solid #f0f0f0;
	}
</style>
<div class="content debug"> 
	<div class="row">
		<ul class="list-unstyled questions-list">
			<c:forEach var="domanda" items="${domande}">				
				<div class="media question">			
					<div>
						<h5>Utente1</h5>
					</div>		
				  <div class="media-body">
				    <h5 class="mt-0">Titolo domanda</h5>
				    <p>Corpo domanda</p>				    
				  </div>
				</div>
				</ul>														
		</c:forEach>
		</ul>
		</div> 
	</div>
<jsp:include page="Footer.jsp"></jsp:include> 
