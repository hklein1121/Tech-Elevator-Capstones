<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>
		National Park Geek
	</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
	<c:url var="cssURL" value="/css/site.css" />
	<link rel="stylesheet" href="${cssURL}">
</head>
<body>
	<div class="container-fluid navigationBar">
		<div class="row">
			<header class="col-sm-6">
				<c:url value="/" var="homePage" />
				<c:url value="/img/logo.png" var="headerImg" />
				<a href="<c:url value="/"/>">
					<img id="logo" src="${headerImg}" alt="Logo">
				</a>
			</header>
			<nav class="navigation col-sm-6 ">
				<ul id="headerLinks" class="pull-right">
					<c:url value="/" var="homePage" />		
					<li><a class="button" href="${homePage}">Home</a></li>
					<c:url var="survey" value="survey" />
					<li><a class="button" href="${survey}">Survey</a></li>
					<c:url var="favoriteParks" value="/favoritePark" />
					<li><a class="button" href="${favoriteParks}">Favorite Parks</a></li>
				</ul>
			</nav>
		</div>
	</div>