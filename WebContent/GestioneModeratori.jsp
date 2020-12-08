<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <style>
   		#collapseOne{
   			max-height: 400px;   			
   			overflow: scroll;  
   		}
   		.mod-item:hover{
   			background-color: #5d34af;
   			color: white;
   			cursor: pointer;
   		}
   </style>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp" >
	<jsp:param name="active" value="profilo"/>
</jsp:include>
 
	<div class="content">		
		<div class=" card col-sm-12 col-md-12">		
	  	<div class="card-body">
	  		<jsp:include page="PopupErrore.jsp"></jsp:include>	  	
	  		<div class="card-title">
	  			<h3>Dashboard</h3>
	  		</div>
	  		<div class="accordion" id="accordionExample">
			  <div class="card">
			    <div class="card-header" id="headingOne">
			      <h2 class="mb-0">
			        <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
			          Gestione moderatori
			        </button>
			      </h2>
			    </div>
			
			    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
			      <div class="card-body">			        
					<c:forEach var="moderatore" items="${moderatoriList}" varStatus="loop">
							<li class="list-group-item mod-item" data-toggle="modal" data-target="${'#mod'}${loop.index}">								
								  ${moderatore.getUsername()}								
							</li>
						</c:forEach>
			      </div>
			    </div>
			  </div>
			  <div class="card">
			    <div class="card-header" id="headingTwo">
			      <h2 class="mb-0">
			        <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
			          Creazione moderatori
			        </button>
			      </h2>
			    </div>
			    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
			      <div class="card-body">
			        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
			      </div>
			    </div>
			  </div>
		</div>
  	</div>
  </div>
<!-- Modal -->
<c:forEach var="moderatore" items="${moderatoriList}" varStatus="loop">
	<div class="modal fade" id="${'mod'}${loop.index}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">${moderatore.getUsername()}</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
</c:forEach>

<jsp:include page="Footer.jsp"></jsp:include> 
  