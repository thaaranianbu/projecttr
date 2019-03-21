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
		<h1>Welcome</h1>
		
		<form class="form" action="gmail_authendication" method="get">
			<input type="text" placeholder='<%= (String)request.getAttribute("hint")%>' name="check">
			<% 
			String ekey=(String)request.getAttribute("ekey");
			String pkey=(String)request.getAttribute("pkey");
			String id=(String)request.getAttribute("id");
			String status=(String)request.getAttribute("status");
			String en=(String)request.getAttribute("en");
			out.write("<input type='text'  name='ekey' value='"+ekey+"' style='display:none'>");
			out.write("<input type='text'  name='pkey' value='"+pkey+"'style='display:none' >");
			out.write("<input type='text'  name='id' value='"+id+"' hidden style='display:none'>");
			out.write("<input type='text'  name='status' value='"+status+"' hidden style='display:none'>");
			%>
			<input type="text"  disabled id="encry"  value='<%=en %>'>
			<button type="submit" id="login-button">Send</button>
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