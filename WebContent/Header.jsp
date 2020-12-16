
<!doctype html>

<html lang="it">




	<head>
  
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		
		<!-- Sidebar CSS -->
		<link rel="stylesheet" type="text/css" href="style/sidebar.css">
	
		<%
			String pageTitle = (String)request.getAttribute("pageTitle");
		
			if(pageTitle == null)
				pageTitle = "AskToReply";
		%>
	
	    <title><%= pageTitle %></title>
    
	</head>
  
<body>