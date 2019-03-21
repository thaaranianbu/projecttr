
<%@page import="java.util.Iterator"%>
<%@page import="secure_mail.interfaces.User_info"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body
{
background-image: url("images/back.jpg");
}
header
{
position: absolute;
left: 0px;
top: 0px;
width: 100%;
height: 20%;
}
header #img_left
{
width: 20%;
height: 100%;
float: left;
;
}
header #img_right
{
width: 20%;
height: 100%;
float: right;

}
section
{
position: absolute;
left: 0px;
top: 20%;
width: 100%;
height: 60%;
}
section div
{
background-image: url("images/center.jpg");
background-position:center;
background-repeat:no-repeat;
position: absolute;
left: 20%;
width: 60%;
height: 70%;
top: 10%;
z-index: 0;

}

section div form
{
position: absolute;
left: 20%;
width: 60%;
height: 70%;
top: 20%;
z-index: 1;

}
footer
{
position: absolute;
left: 0px;
top: 80%;
width: 100%;
height: 20%;
background-image: url("images/footer.jpg");
}
label
{

font-weight: bold;
text-shadow: black;
color: green;
font-size: large;
}


</style>

</head>
<body>
<% List<User_info> list=(List<User_info>)request.getAttribute("data");%>
<header>
<img id="img_left" alt="images" src="images/fl.jpg" >

<img id="img_right" alt="images_right" src="images/fr.jpg">.
<h1 align="center"><%=User_info.name %></h1>
</header>
<section>
<div align="center">
<table >
<caption>User Health Data's</caption>
<tr>
<th>Pid</th>
<th>Heart</th>
<th>Pressure</th>
<th>Sugar</th>
<th>Date</th>
</tr>
<% Iterator<User_info> ite =list.iterator(); 
while(ite.hasNext())
{
	User_info info=ite.next();
%>
<tr>
<td><%=info.getLpid() %></td>
<td><%=info.getHeatr() %></td>
<td><%=info.getPressure()%></td>
<td><%=info.getSugar() %></td>
<td><%=info.getDate() %></td>
</tr>
<%
}
%>
</table>
<div>
</section>

<footer>

</footer>
</body>
</html>