<%@page import="java.util.ArrayList"%>
<%@page import="gestioneDomanda.CategoriaBean"%>

<jsp:include page="Header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="SidebarOspite.jsp">
	<jsp:param name="active" value="iscriviti" />
</jsp:include>


<div class="content">

	<div class="card-body">


<div class="card-title">
			<h3>Registrazione</h3>
</div>

			<jsp:include page="PopupErrore.jsp"></jsp:include>
			<form action="RegistrazioneServlet" method="post" enctype="application/x-www-form-urlencoded">

<div class="row">

              <div class="col-md-6 mb-3">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" >
                <div class="invalid-feedback">
                  Nome richiesto.
                </div>
              </div>
              
              <div class="col-md-6 mb-3">
                <label for="cognome">Cognome</label>
                <input type="text" class="form-control" id="cognome" name="cognome" >
                <div class="invalid-feedback">
                  Cognome richiesto.
                </div>
              </div>
</div>



<div class="mb-3">
              <label for="username">Username</label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">@</span>
                </div>
                <input type="text" class="form-control" id="username" name="username" >
                <div class="invalid-feedback" style="width: 100%;">
                  Username richiesto.
                </div>
              </div>
            </div>


<div class="mb-3">
              <label for="email">Email</label>
              <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" >
              <div class="invalid-feedback">
                Inserisci un indirizzo email corretto.
              </div>
            </div>

<div class="mb-3">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" name="password" >
              <div class="invalid-feedback">
				Inserisi una password.
              </div>
            </div>
<div>
		<label style="margin-bottom: 0pt;">Aree di interesse <span class="text-muted">(almeno una)</span></label>
	</div>	
		<span class="text-muted"><small>Potrai modificare le categorie scelte successivamente mediante l'apposita sezione</small></span>
		
		<div class="overflow-auto" style="height: 172px;">
		<ul class="list-group "  style="margin-right:5pt;">
			<c:forEach var="interesse" items="${interessi}">	
				<li class="list-group-item">
		              <input type="checkbox" name="interessi" id="interesseCheck" value="${interesse.getNome()}">
		              <label for="interesseCheck">${interesse.getNome()}</label>
		        </li>
		    </c:forEach>					
		</ul>
		
		</div>
<br>
				 	<button type="submit" class="btn btn-primary btnsmussato">Completa registrazione</button>

</form>

</div> 

</div>
<jsp:include page="Footer.jsp"></jsp:include>