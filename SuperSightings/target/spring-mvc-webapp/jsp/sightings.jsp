<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Sightings</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Super Sightings!</h1>
            <h2>Have you seen them?</h2>
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

            <div class="row">
                <!-- 
                    List of sightings
                -->
                <div class="col-md-6">
                    <h2>Sightings</h2>
                    <table id="sightingsTable" class="table table-hover">
                        <tr>
                            <th width="40%">Date</th>
                            <th width="30%">Location</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.date}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.location.locationName}"/>
                                </td>
                                <td>
                                    <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach> 
                    </table>                    
                </div> 
                <!-- 
                    new sighting form 
                -->
                <div class="col-md-6">
                    <h2>Add New Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="add-date" class="col-md-4 control-label">Date:</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" name="date" placeholder="Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-8">
                                <select class="form-control" name="locationId">
                                    <c:forEach var="location" items="${locationList}">
                                        <option value="${location.getLocationId()}">
                                            ${location.getLocationName()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-people" class="col-md-4 control-label">People:</label>
                            <div class="col-md-8">
                                <select multiple class="form-control" name="personId">
                                    <c:forEach var="superPerson" items="${superPersonList}">
                                        <option value="${superPerson.getPersonId()}">
                                            ${superPerson.getSuperName()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Sighting"/>
                            </div>
                        </div>
                    </form>

                </div> 

            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
