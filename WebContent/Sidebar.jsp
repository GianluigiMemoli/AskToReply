<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
		<div class="sidebar aperta" id="sidebar"  unselectable="on"  onselectstart="return false;" onmousedown="return false;">
			<div class="line">
				<a class="title" > <b>Ask to Reply</b></a>
			</div>
		    <a href="VisualizzaHome" id="home"><ion-icon class="responsive" name = "home"></ion-icon> Home</a>
		    <a href="VisualizzaProfilo" id="profilo"><ion-icon name="person" class="responsive"></ion-icon> Profilo</a>
		    <a href="RicercaServlet" id="cerca"><ion-icon name="search" class="responsive"></ion-icon> Cerca</a>
		    <a href="LogoutServlet" id="logout"><ion-icon name="trail-sign" class="responsive"></ion-icon> Esci</a>
			<a href="VisualizzaFormPubblicazioneDomandaServlet">
				<button id="chiedi_sidebar_button">CHIEDI</button>
			</a>
			<button style="display:none;" id="bottonesuperiore_apri" class="bottonesuperiore" href="javascript:void(0);" onclick="openBar()">
				<ion-icon name="add-circle"></ion-icon>
			</button>
			<button style="display:none;" id="bottonesuperiore_chiudi" class="bottonesuperiore" href="javascript:void(0);" onclick="openBar()">
				<ion-icon name="remove-circle"></ion-icon>
			</button>
		</div>

		<!--fine sidebar-->


		<!--inizio script sidebar-->

		<script>
			document.getElementById("<%= request.getParameter("active")%>").className="active";
		
			fixbtn();
			
			window.addEventListener('resize', fixSidebar);
			
			function fixbtn(){
				console.log("test"); //test
				var w = window.outerWidth;
				if(w<=529){
					document.getElementById("bottonesuperiore_chiudi").style.display="block";
				}
			}
			
			function openBar(){
				var apri = document.getElementById("bottonesuperiore_apri");
				var chiudi = document.getElementById("bottonesuperiore_chiudi");
				//mi � venuta sete
				var x = document.getElementById("sidebar");
				if (x.className === "sidebar") {
					x.style.height="355pt";
					x.className += " aperta";
					console.log("la sidebar si � aperta");
					apri.style.top="35px";
					chiudi.style.top="35px";
					apri.style.display="none";
					chiudi.style.display="block";
				}
				else{
					console.log("la sidebar si � chiusa");
					x.style.height="55pt";
					x.className = "sidebar";
					apri.style.top="35px";
					chiudi.style.top="35px";
					apri.style.display="block";
					chiudi.style.display="none";
				}
			}
			function fixSidebar(){
				var apri = document.getElementById("bottonesuperiore_apri");
				var chiudi = document.getElementById("bottonesuperiore_chiudi");
				var w = window.outerWidth;
				var sidebar = document.getElementById("sidebar");
				if(w>529){
					sidebar.style.height="100%";
					apri.style.display="none";
					chiudi.style.display="none";
					sidebar.className="sidebar";
					console.log(w);
				}
				else{
					chiudi.style.display="block";
					sidebar.style.height="355pt";
					if(sidebar.className!="sidebar aperta"){
						sidebar.className+=" aperta";
					}
					console.log(w); //test
				}
			}
			
		</script>

		<!--fine script sidebar-->  