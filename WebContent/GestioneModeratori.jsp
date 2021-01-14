<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#collapseOne {
	max-height: 400px;
	overflow: scroll;
}

.mod-item:hover {
	background-color: #5d34af;
	color: white;
	cursor: pointer;
}

.mod-list{
	max-height: 500px;
	overflow-y: scroll;
}

.mod-container{	
	margin-top: 2em;
}
.mod-form{
align-self: center;
	max-width: 80%;
}
.content{
	padding: 3em;
}
</style>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="SidebarMasterMod.jsp">
	<jsp:param name="active" value="gestione" />
</jsp:include>

<div class="content">
	<div class="row">
		<div class="col-12">
		<div class="card border-0 mod-container">
			<h4 class="card-title">Moderatori esistenti</h4>
			<div class="card-body mod-list">
				<table class="table table-responsive">
					<thead>
						<tr>
							<th scope="col">Username</th>
							<th scope="col">Nome</th>
							<th scope="col">Cognome</th>
							<th scope="col">Email</th>
							<th scope="col">Disattivato</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					<div class="mod-list">
						<c:forEach var="moderatore" items="${moderatoriList}"
							varStatus="loop">
							<tr>
								<td>${moderatore.getUsername()}</td>
								<td>${moderatore.getNome()}</td>
								<td>${moderatore.getCognome()}</td>
								<td>${moderatore.getEmail()}</td>
								<c:choose>
									<c:when test="${moderatore.isDisattivato() == true}">
										<td>Sì</td>
									</c:when>
									<c:when test="${moderatore.isDisattivato() == false}">
										<td>No</td>
										<td><a type="button" class="btn btn-outline-danger" href= "/AskToReply/DisattivazioneModeratore?idModeratore=${moderatore.getId() }">Disattiva</button></td>
									</c:when>
								</c:choose>																
							</tr>
						</c:forEach>
						</div>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>



<jsp:include page="Footer.jsp"></jsp:include>
