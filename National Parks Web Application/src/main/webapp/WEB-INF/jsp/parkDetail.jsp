<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1 class="nameHeading">${park.name }, ${park.state }</h1>
<div class="container">
	<div class="row parkInformation">
		<div class="col-xs-6 parkImageWrapper">
			<img class="parkImage img-responsive" src="img/parks/${park.code.toLowerCase() }.jpg" />
		</div>
		<div class="parkInfo col-xs-6"> 
			<h2 class="text-center">Park Information</h2>
			<div class="row">
				<div class="col-md-offset-2 col-xs-4">
					<h5>Year Founded: ${park.yearFounded }</h5>
					<h5>Acreage: ${park.acreage } acres</h5>
					<h5>Elevation:  ${park.elevationInFeet } ft</h5>
					<h5>Miles of Trail: ${ park.milesOfTrail } miles</h5>
					<h5>Number of Campsites: ${park.numberOfCampsites }</h5>
				</div>
				<div class="col-xs-4">
					<h5>Climate: ${park.climate }</h5>
					<h5>Annual Visitors: ${park.annualVisitors }</h5>
					<h5>Entry Fee: $${park.entryFee }.00</h5>
					<h5>Number of Species: ${park.numberOfSpecies }</h5>
				</div>
			</div>
			<div class="text-center qoute">
				<div class="quoteFont">
					<c:out value="${park.inspirationalQuote}" />
				</div>
				<div>
					<c:out value="- ${park.inspirationalQuoteSource }" />
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container textContainer">
	<p>
		<c:out value="${park.description }" />
	</p>
	
</div>

<div class="todayTemp container">
	
	
	<div class="todayForecast">
	<h2 class="text-center">5 Day Forecast</h2>
	<form action="<c:url value="/parkDetail"/>" method="get" class="text-center">
		<label class="radio-inline">
	  		<input type="radio" name="tempType" value="Celcius" onClick="submit()">Celcius
		</label>
		<label class="radio-inline">
	  		<input type="radio" name="tempType" value="Fahrenheit" onClick="submit()">Fahrenheit
		</label>
		<input type="hidden" name="code" value="${park.code }"/>
	</form>
	</div>
	
	
	<div class="weather row">
	
		<c:forEach var="weather" items="${weather }">			
			<div class="col-xs-2">
				<div class="weatherDay">
					<img class="weatherImage img-responsive " src="img/weather/${weather.forecast }.png" />
					<div class="text-capitalize">
						<c:out value="${weather.forecast }" />
					</div>
					<c:choose>
						<c:when test="${tempType == 'Celcius'}">
							<div>
							<fmt:formatNumber value="${weather.high}" maxFractionDigits="2" var="formattedHigh"/>
							<fmt:formatNumber value="${weather.low}" maxFractionDigits="2" var="formattedLow"/>
								<c:out value="High: ${formattedHigh } ºC" />
								<br>
								<c:out value="Low: ${formattedLow} ºC"/>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<c:out value="High: ${weather.high}ºF" />
								<br>
								<c:out value="Low: ${weather.low}ºF" />
							</div>
						</c:otherwise>
					</c:choose>
					<c:forEach var="weatherAdvisory" items="${weather.advisoryList}">
						<c:out value="${weatherAdvisory}"/>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	
	
	</div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />