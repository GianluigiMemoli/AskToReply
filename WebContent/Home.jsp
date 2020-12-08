<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp" >
	<jsp:param name="active" value="home"/>
</jsp:include>
<%@page import="model.DomandaBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
	int currentPage = 1;
	if(request.getParameter("page") != null){
		currentPage = Integer.parseInt(request.getParameter("page"));		
	}  
	request.setAttribute("currentPage", currentPage);
%>  
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
		margin-bottom: 2em;
		padding: 1em;
	}
	.paginator{
		align-self: center;
	}
</style>

<div class="content debug"> 

	<div class="col-sm-12">
		<div class="questions-list">
			<c:forEach var="domanda" items="${domande}">				
				<div class="question rounded border">								
				  <div>
				    <h5 class="mt-0">${domanda.getTitolo()}</h5>
				    <p>${domanda.getCorpo()}</p>				    
				  </div>
				<h6>Pubblicato da: ${domanda.getAutore().getUsername()}</h6>
				<div>
					<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-chat-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v11.586l2-2A2 2 0 0 1 4.414 11H14a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
					</svg>
					<span>Rispondi</span>
				</div>							 
				</div>
		</c:forEach>		
		</div>
		<nav class="paginator" aria-label="Page navigation example">
  		<ul class="pagination">
  		<c:if test="${currentPage > 1 }">
    		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage - 1)%>)>Previous</a></li>
    		</c:if>    	
    		<li class="page-item"><a class="page-link" onclick=changePage(<%=(currentPage+1)%>)>Next</a></li>
		</ul>
		</nav>
		</div>  
	</div>
	<script>
		function changePage(pageNumber){
			window.location.href = "/AskToReply/VisualizzaHome?page="+pageNumber;
		}
	</script>
<jsp:include page="Footer.jsp"></jsp:include> 









