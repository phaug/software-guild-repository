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
                        <a href="${pageContext.request.contextPath}/index.jsp">
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
                    <li role="presentation"
                        class="active">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Super Locations!
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Super Sightings!
                        </a>
                    </li>
                </ul>    
            </div>

            <div class="row">
                <!-- 
                    Location summary table
                -->
                <div class="col-md-6">
                    <h2>Locations</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Description</th>
                            <th width="20%">Address</th>
                            <th width="20%">Latitude</th>
                            <th width="20%">Longitude</th>
                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <c:out value="${currentLocation.locationName}"/>
                                </td>
                                <td>
                                     <c:out value="${currentLocation.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.address}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.latitude}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.longitude}"/>
                                </td>
                                <td>
                                    Edit
                                </td>
                                <td>
                                    Delete
                                </td>
                            </tr>
                        </c:forEach>

                    </table>                    
                </div>
                <!-- 
                    New location form
                -->
                <div class="col-md-6">
                    <h2>Add New Location</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createContact">
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locationName" placeholder="Location Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="address" placeholder="Address"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="latitude" placeholder="Latitude"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="latitude" placeholder="Latitude"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Location"/>
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
