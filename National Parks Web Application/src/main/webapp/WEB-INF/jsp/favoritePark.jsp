<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div class="container-fluid">
<h1 class="text-center">Favorite Parks</h1>
	<c:forEach var="park" items="${parks }">
		<div class="individualPark row">
			<div class="homePageImg col-xs-6" id="parkPicture">
				<c:url var="parkDetail" value="/parkDetail">
					<c:param name="code">${park.code }</c:param>
				</c:url>
				<a href="${parkDetail }"> <img
					class="homeParkImage favoriteParkImage img-responsive" src="img/parks/${park.code.toLowerCase() }.jpg" /></a>
			</div>
	
			<div class="parkDetails col-sm-6">
				<div class="homePageParkName text-center" id="parkName">
					<h2><c:out value="${park.name }" /></h2>
				</div>
				<div class="surveysSubmitted">
					<h4><c:out value="Amount of survey's submitted: ${park.surveyCount }" /></h4>
				</div>
			</div>
		</div>
	</c:forEach>	
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />