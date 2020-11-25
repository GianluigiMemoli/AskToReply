<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
		<div class="sidebar aperta" id="sidebar">
			<div class="line">
				<a class="title" > <b>Ask to Reply</b></a>
			</div>
			<a class="active" href="#home">
				<svg  class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M8 3.293l6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6zm5-.793V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
					<path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
				</svg> Home
			</a>
			<a href="#profilo">
				<svg class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
				</svg> Profilo
			</a>
			<a href="#cerca">
				<svg class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
					<path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
				</svg> Cerca
			</a>
			<a href="#logout">
				<svg class ="responsivesvg" style="margin-right:5pt" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-signpost-split-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path d="M7 16h2V6h5a1 1 0 0 0 .8-.4l.975-1.3a.5.5 0 0 0 0-.6L14.8 2.4A1 1 0 0 0 14 2H9v-.586a1 1 0 0 0-2 0V7H2a1 1 0 0 0-.8.4L.225 8.7a.5.5 0 0 0 0 .6l.975 1.3a1 1 0 0 0 .8.4h5v5z"/>
				</svg> Esci
			</a>
			<button href="#CHIEDI">
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

		<!-- div class="content">
			<p></p>
		</div-->

		<!--inizio script sidebar-->

		<script>

			fixbtn();

			window.addEventListener('resize', fixSidebar);

			function fixbtn(){
				var w = window.outerWidth;
				if(w<=529){
					document.getElementById("bottonesuperiore_chiudi").style.display="block";
				}
			}

			function openBar(){
				//mi è venuta sete
				var x = document.getElementById("sidebar");
				if (x.className === "sidebar") {
					x.style.height="355pt";
					document.getElementById("bottonesuperiore_apri").style.top="35px";
					x.className += " aperta";
					var a = "la sidebar si è aperta";
					console.log(a);
					document.getElementById("bottonesuperiore_apri").style.top="35px";
					document.getElementById("bottonesuperiore_chiudi").style.top="35px";
					document.getElementById("bottonesuperiore_apri").style.display="none";
					document.getElementById("bottonesuperiore_chiudi").style.display="block";
				}
				else{
					var a = "la sidebar si è chiusa";
					console.log(a);
					x.style.height="55pt";
					x.className = "sidebar";
					document.getElementById("bottonesuperiore_apri").style.top="35px";
					document.getElementById("bottonesuperiore_chiudi").style.top="35px";
					document.getElementById("bottonesuperiore_apri").style.display="block";
					document.getElementById("bottonesuperiore_chiudi").style.display="none";
				}
			}

			function fixSidebar(){
				var w = window.outerWidth;
				var x = document.getElementById("sidebar");
				if(w>529){
					x.style.height="100%";
					document.getElementById("bottonesuperiore_apri").style.display="none";
					document.getElementById("bottonesuperiore_chiudi").style.display="none";
					document.getElementById("sidebar").className="sidebar";
					console.log(w);
				}
				else{
					document.getElementById("bottonesuperiore_chiudi").style.display="block";
					x.style.height="355pt";
					if(document.getElementById("sidebar").className!="sidebar aperta"){
						document.getElementById("sidebar").className+=" aperta";
					}
					console.log(w);
				}
			}

		</script>

		<!--fine script sidebar-->  