<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Edit Sighting</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperPersonsPage">
                            Super People!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                            Super Organizations!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Super Locations!
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Super Sightings!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayPowersPage">
                            Super Powers!
                        </a>
                    </li>
                </ul>    
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                     action="editSighting" method="POST">
                <sf:input type="hidden" class="form-control" id="sightingId"
                          path="sightingId"/>
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-4">
                        <sf:input type="date" class="form-control" id="add-date"
                                  path="date" placeholder="Date"/>
                        <sf:errors path="date" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location" class="col-md-4 control-label">Location:</label>                          
                        <div class="col-md-4">
                            <select class="form-control" name="locationId">
                            <c:forEach var="location" items="${locationList}">
                                <c:choose>
                                    <c:when test="${sighting.getLocation()==location}">
                                        <option selected value="${location.getLocationId()}">
                                            ${location.getLocationName()}
                                        </c:when>   
                                        <c:otherwise>
                                        <option value="${location.getLocationId()}">
                                            ${location.getLocationName()}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-superperson" class="col-md-4 control-label">Super People:</label>
                    <div class="col-md-4">
                        <select multiple class="form-control" name="superPersonId">

                            <c:forEach var="superPerson" items="${superPersonList}">
                                <c:choose>
                                    <c:when test="${selectedPeople.contains(superPerson.getPersonId())}">
                                        <option selected value="${superPerson.getPersonId()}">
                                            ${superPerson.getSuperName()}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${superPerson.getPersonId()}">
                                            ${superPerson.getSuperName()}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>