<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="Header.jsp"></jsp:include>

<jsp:include page="Sidebar.jsp">
	<jsp:param name="active" value="home" />
</jsp:include>
<div class="content">
	<br></br>
	<jsp:include page="FormPubblicazioneRisposta.jsp"></jsp:include>
</div>
<jsp:include page="Footer.jsp"></jsp:include>