<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
		<div class="sidebar aperta" id="sidebar">
			<div class="line">
				<a class="title" > <b>Ask to Reply</b></a>
			</div>
			<a href="Home.jsp" id="home">
				<svg  class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M8 3.293l6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6zm5-.793V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
					<path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
				</svg> Home
			</a>
			<a href="Profilo.jsp" id="profilo">
				<svg class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
				</svg> Profilo
			</a>
			<a href="Ricerca.jsp" id="ricerca">
				<svg class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
					<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
				</svg> Cerca
			</a>
			<a href="Logout.jsp" id="logout">
				<svg class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-signpost-split-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path d="M7 16h2V6h5a1 1 0 0 0 .8-.4l.975-1.3a.5.5 0 0 0 0-.6L14.8 2.4A1 1 0 0 0 14 2H9v-.586a1 1 0 0 0-2 0V7H2a1 1 0 0 0-.8.4L.225 8.7a.5.5 0 0 0 0 .6l.975 1.3a1 1 0 0 0 .8.4h5v5z"/>
				</svg> Esci
			</a>
			<button href="FormPubblicazioneDomanda.jsp" id="chiedi_sidebar_button">
				CHIEDI
			</button>
			<button style="display:none;" id="bottonesuperiore_apri" class="bottonesuperiore" href="javascript:void(0);" onclick="openBar()">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
				</svg>
			</button>
			<button style="display:none;" id="bottonesuperiore_chiudi" class="bottonesuperiore" href="javascript:void(0);" onclick="openBar()">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-dash-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1h-7z"/>
				</svg>
			</button>
		</div>

		<!--fine sidebar-->


		<!--inizio script sidebar-->

		<script>

			document.getElementById("<%= request.getParameter("active")%>").className="active";
		
			fixbtn();
			
			window.addEventListener('resize', fixSidebar);

			function fixbtn(){
				var w = window.outerWidth;
				if(w<=529){
					document.getElementById("bottonesuperiore_chiudi").style.display="block";
				}
			}

			function openBar(){
				var apri = document.getElementById("bottonesuperiore_apri");
				var chiudi = document.getElementById("bottonesuperiore_chiudi");
				//mi è venuta sete
				var x = document.getElementById("sidebar");
				if (x.className === "sidebar") {
					x.style.height="355pt";
					x.className += " aperta";
					console.log("la sidebar si è aperta");
					apri.style.top="35px";
					chiudi.style.top="35px";
					apri.style.display="none";
					chiudi.style.display="block";
				}
				else{
					console.log("la sidebar si è chiusa");
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