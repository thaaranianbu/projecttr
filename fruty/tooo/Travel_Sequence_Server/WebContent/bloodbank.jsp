<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>Login PHP</title>
	<link rel="stylesheet" href="style.css" />
	<link href='http://fonts.googleapis.com/css?family=Oleo+Script' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="jquery-1.7.min.js"></script>
	
</head>
<body>
	<div class="lg-container">
		<h1>Add Bloodbank Locations</h1>
		<form action="add_bloodbank_orphanage" id="lg-form" name="lg-form" method="get">
			
			<div>
				<label for="username">Your Name:</label>
			<input type="hidden" name="who" id="username" value="bloodbank"/>
				
				<input type="text" name="name" id="username" placeholder="your name"/>
			</div>
			
			<div>
				<label for="username">Blood bank Name:</label>
				<input type="text" name="oname" id="username" placeholder="Blood bank Name:"/>
			</div>
			
			<div>
				<label for="username">Latitude</label>
				<input type="text" name="latitude" id="username" placeholder="Latitude"/>
			</div>
			
			<div>
				<label for="username">Longitude</label>
				<input type="text" name="longitude" id="username" placeholder="Longitude"/>
			</div>
			
			<div>
				<label for="username">URL</label>
				<input type="text" name="url" id="username" placeholder="Url"/>
			</div>
			
			<div>
				<label for="password">Mail:</label>
				<input type="text" name="mail" id="password" placeholder="mail" />
			</div>
			
			
			
			<div>				
				<button type="submit" id="login">Done</button>
			</div>
			
		</form>
		<div id="message"></div>
	</div>
</body>
