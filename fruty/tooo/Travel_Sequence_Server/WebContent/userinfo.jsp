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
<header>
<img id="img_left" alt="images" src="images/fl.jpg" >

<img id="img_right" alt="images_right" src="images/fr.jpg">.
<h1 align="center">User Login</h1>
</header>
<section>
<div align="center">
<form action="login"  method="post">
<label>User name :</label> <input type="text" required="required" maxlength="40" name="username"><br><br>
<label>Password :</label> <input type="password" required="required" maxlength="40" name="password"><br><br><br>
<input type="submit" >
</form>
<div>
</section>

<footer>

</footer>
</body>
</html>