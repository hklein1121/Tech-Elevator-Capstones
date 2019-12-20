<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div class="surveyHeading">
<h1>National Park Survey Form</h1>

<h3>Please fill out the form below and let us know which National Park is your favorite! </h3>

</div>
<div class="survey">
<c:url value="/survey" var="SurveyURL"/>
<form:form action="${SurveyURL }" method="POST" modelAttribute="survey" class="form-horizontal">
  <div class="form-group">
    <label for="parkCode" class="col-sm-2 control-label"> Favorite National Park</label>
    <div class="col-sm-10">
      <select name="parkCode" class="form-control">
			<option value="CVNP">Cuyahoga Valley National Park</option>
			<option value="ENP">Everglades National Park</option>
			<option value="GCNP">Grand Canyon National Park</option>
			<option value="GNP">Glacier National Park</option>
			<option value="GSMNP">Great Smoky Mountains National Park</option>
			<option value="GTNP">Grand Teton National Park</option>
			<option value="RMNP">Rocky Mountain National Park</option>
			<option value="YNP">Yellowstone National Park</option>
			<option value="YNP2">Yosemite National Park</option>
			<option value="MRNP">Mount Rainier National Park</option>
	</select>
    </div>
  </div>
  <div class="form-group">
    <label for="emailAddress" class="col-sm-2 control-label">Your Email:</label>
    <div class="col-sm-10">
	    <form:input path="emailAddress" class="form-control" placeholder="Email"/>
	    <form:errors path="emailAddress" cssClass="form-control error" />
    </div>
  </div>
  <div class="form-group">
    <label for="state" class="col-sm-2 control-label"> State of Residence</label>
    <div class="col-sm-10">
      <select name="state"  class="form-control">
			<option value="AL">AL</option>
			<option value="AK">AK</option>
			<option value="AR">AR</option>	
			<option value="AZ">AZ</option>
			<option value="CA">CA</option>
			<option value="CO">CO</option>
			<option value="CT">CT</option>
			<option value="DC">DC</option>
			<option value="DE">DE</option>
			<option value="FL">FL</option>
			<option value="GA">GA</option>
			<option value="HI">HI</option>
			<option value="IA">IA</option>	
			<option value="ID">ID</option>
			<option value="IL">IL</option>
			<option value="IN">IN</option>
			<option value="KS">KS</option>
			<option value="KY">KY</option>
			<option value="LA">LA</option>
			<option value="MA">MA</option>
			<option value="MD">MD</option>
			<option value="ME">ME</option>
			<option value="MI">MI</option>
			<option value="MN">MN</option>
			<option value="MO">MO</option>	
			<option value="MS">MS</option>
			<option value="MT">MT</option>
			<option value="NC">NC</option>	
			<option value="NE">NE</option>
			<option value="NH">NH</option>
			<option value="NJ">NJ</option>
			<option value="NM">NM</option>			
			<option value="NV">NV</option>
			<option value="NY">NY</option>
			<option value="ND">ND</option>
			<option value="OH">OH</option>
			<option value="OK">OK</option>
			<option value="OR">OR</option>
			<option value="PA">PA</option>
			<option value="RI">RI</option>
			<option value="SC">SC</option>
			<option value="SD">SD</option>
			<option value="TN">TN</option>
			<option value="TX">TX</option>
			<option value="UT">UT</option>
			<option value="VT">VT</option>
			<option value="VA">VA</option>
			<option value="WA">WA</option>
			<option value="WI">WI</option>	
			<option value="WV">WV</option>
			<option value="WY">WY</option>
		</select>
    </div>
  </div>
  <div class="form-group">
    <label for="parkCode" class="col-sm-2 control-label"> Favorite National Park</label>
    <div class="col-sm-10">
      	<select name="activityLevel" class="form-control">
			<option value="inactive">Inactive</option>
			<option value="sedentary">Sedentary</option>
			<option value="active">Active</option>
			<option value="extremelyActive">Extremely Active</option>
		</select>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-12">
      <button type="submit" class="btn btn-default surveyButton">Submit</button>
    </div>
  </div>
</form:form>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />