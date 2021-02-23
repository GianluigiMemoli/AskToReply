<style>	
	.active{
		color: #6615b4;
	}
	a {
		color: black; 
	}
	a:hover {
		color: black;
		text-decoration: none;
	}
		 @media only screen and (min-width: 769px) {
			.switcher-container{
				margin-top: 1em;	
				border-left: 1px solid #f0f0f0;
			}
			.switcher{
				list-style: none;
				font-family: 'Lexend Deca', sans-serif;
		  		font-weight: 200;
		  		font-size: 15pt;
		  		height: 100%;  		
			}
			
			.switcher li{
				margin-bottom: 1em;
			}	 
		 }	
	
	.switcher li{
			cursor: pointer;
			}
	 @media only screen and (max-width: 768px) {	
		li{
		margin-bottom: 0.3em;
		}	
		
		.switcher-container{			
			width:100%;
			margin-top: 1em;
		}
		
		.switcher{
			margin: 0;
			list-style: none;
			font-family: 'Lexend Deca', sans-serif;
	  		font-weight: 200;		  		
	  		font-size: 13pt;
	  		width:100%;		  				  				  	 
		}
		
	}
</style>
<div class="switcher-container">
	<ul class="switcher">
		<li ><a id="modificaProfilo" href="VisualizzaProfilo">Modifica profilo</a></li>
		<li id="storicoDomande">Storico domande</li>
		<li ><a id="storicoRisposte"  href="VisualizzaStoricoRisposte">Storico risposte</a></li>	
	</ul>
</div>

<script>
	document.getElementById("<%= request.getParameter("active")%>").className="active";
</script>
