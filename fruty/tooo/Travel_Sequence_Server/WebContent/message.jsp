<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="UTF-8">
    <title> login screen</title>
    
        <link rel="stylesheet" href="css/style1.css">

  </head>

  <body>

    <div class="wrapper">
	<div class="container">
		<h1><%= (String)request.getAttribute("subject")%></h1>
		
		<form class="form" action="index.html" method="get">
		<button type="submit" id="login-button"><%= (String)request.getAttribute("msg")%></button>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index2.js"></script>
        
        

    
    
    
  </body>
</html>