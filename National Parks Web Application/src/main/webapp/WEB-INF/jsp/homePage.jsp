<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<c:url value="/" var="formAction"></c:url>
<form method="GET" action="${formAction }"></form>

<div class="container-fluid">
	<c:forEach var="park" items="${parks }">
		<div class="individualPark row">
			<div class="homePageImg col-xs-4" id="parkPicture">
				<c:url var="parkDetail" value="/parkDetail">
					<c:param name="code">${park.code }</c:param>
				</c:url>
				<a href="${parkDetail }"> <img
					class="homeParkImage img-responsive" src="img/parks/${park.code.toLowerCase() }.jpg" /></a>
			</div>
	
			<div class="parkDetails col-sm-8">
			<div class="homePageParkName" id="parkName">
				<h3><c:out value="${park.name }" /></h3>
			</div>
			<div class="homePageDescription" id="parkDescription">
				<c:out value="${park.description }" />
			</div>
			</div>
	
		</div>

	</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />