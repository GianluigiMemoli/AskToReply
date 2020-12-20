<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="card-body">
	<div class="card-title">
	<a name="storicoRisposte"></a>
		<h3 >Risposte:</h3>
	</div>
	
	
	<c:forEach var="risposta" items="${risposte}">

		<div class="list-group">

				<div class="d-flex w-100 justify-content-between">
					<h5 class="mt-0">"Questo è il corpo della risposta"</h5>
					<small>"data pubblicazione"</small>
				</div>

				<p class="mb-1">
					pubblicata da  <b>"data"</b>
				</p>
			</a>
		</div>
		<br>
	</c:forEach>
	
	</div>

	<c:if test="${empty risposte}">
	<div class="text-center">
		<p>Nessuna risposta</p>
		</div>
	</c:if>
 