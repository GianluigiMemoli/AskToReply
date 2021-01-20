<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<div class="container">
			<form action="PubblicazioneRispostaServlet" method="post">
			
				<div class="modal fade" id="visualizzaAllegatoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Allegati</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		
		<%=session.getAttribute("allegati_e4f840e1-54c3-11eb-b1f9-3d47ad8fc429")%>
		<%=session.getAttribute("testina")%>
			
			
        </div>

    </div>
  </div>
</div>

</form>

<script>

function getIdDom(){
	document.getElementById("idDom").value=document.getElementById("idDomanda").value;
	//document.getElementById("solo_per_testare").innerHTML=document.getElementById("idDomanda").value;

}

</script>


			
</div>