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
                    <li role="presentation"
                        class="active">
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
                    <li role="presentation">
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
                    superperson summary table
                -->
                <div class="col-md-6">
                    <h2>Super People</h2>
                    <table id="superPersonTable" class="table table-hover">
                        <tr>
                            <th width="35%">Super Name</th>
                            <th width="25%">Super Description</th>
                            <th width="15%"></th>
                            <th width="15%"></th>

                        </tr>
                        <c:forEach var="currentSuperPerson" items="${personsList}">
                            <tr>
                                <td>
                                    <a href="displaySuperPersonDetails?superPersonId=${currentSuperPerson.personId}">
                                        <c:out value="${currentSuperPerson.superName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPerson.superDescription}"/>
                                </td>
                                <td>
                                    <a href="displayEditSuperPersonForm?superPersonId=${currentSuperPerson.personId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSuperPerson?superPersonId=${currentSuperPerson.personId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
                <!-- 
                    New SuperPerson form
                -->
                <div class="col-md-6">
                    <h2>Add Person</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="addPerson">
                        <div class="form-group">
                            <label for="add-person-name" class="col-md-4 control-label">Super Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superName" placeholder="Super Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="superDescription" placeholder="Description" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-power" class="col-md-4 control-label">Power:</label>
                            <div class="col-md-8">
                                <select class="form-control" name="powerId">
                                    <c:forEach var="power" items="${powerList}">
                                        <option value="${power.getPowerId()}">
                                            ${power.getPowerName()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization" class="col-md-4 control-label">Organization:</label>
                            <div class="col-md-8">
                                <select multiple class="form-control" name="organizationId">
                                    <c:forEach var="organization" items="${organizationList}">
                                        <option value="${organization.getOrganizationId()}"  required>
                                            ${organization.getOrgName()}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-side" class="col-md-4 control-label">Good or Evil?:</label>
                            <div class="col-md-8">
                                <select class="form-control" name="side">
                                    <option value="-1">Evil</option>
                                    <option value="0">Neutral</option>
                                    <option value="1">Good</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Add Person"/>
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

