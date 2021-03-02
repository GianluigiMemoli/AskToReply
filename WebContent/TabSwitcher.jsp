<style>	
	.active{
		color: #6615b4;		
	}
	.active:hover{
		color: #6615b4;
		font-weight:bold;	
	}
	a {
		color: black; 
	}
	a:hover {
		color: black;
		text-decoration: none;
		font-weight:bold;
	}
		 @media only screen and (min-width: 769px) {
			.switcher-container{
				margin-top: 1em;	
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
	<ul class="switcher" style="padding:0px 20px;">
		<li ><a id="modificaProfilo" href="VisualizzaProfilo"><ion-icon name="settings"></ion-icon> Modifica profilo</a></li>
		<li ><a id="storicoDomande"  href="VisualizzaStoricoDomandeServlet"><ion-icon name="megaphone"></ion-icon> Storico domande</a></li>	
		<li ><a id="storicoRisposte"  href="VisualizzaStoricoRisposte"><ion-icon name="chatbubbles"></ion-icon> Storico risposte</a></li>	
	</ul>
</div>

<script>
	document.getElementById("<%= request.getParameter("active")%>").className="active";
</script>
