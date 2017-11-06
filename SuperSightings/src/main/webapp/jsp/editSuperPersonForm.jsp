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
            <h1>Edit Super Person</h1>
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
            <sf:form class="form-horizontal" role="form" modelAttribute="superPerson"
                     action="editSuperPerson" method="POST">
                <sf:input type="hidden" class="form-control" id="personId"
                                  path="personId"/>
                <div class="form-group">
                    <label for="add-superPersonName" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="superName"
                                  path="superName" placeholder="Super Person Name"/>
                        <sf:errors path="superName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-4">
                        <sf:input type="text" class="form-control" id="superDescription"
                                  path="superDescription" placeholder="description"/>
                        <sf:errors path="superDescription" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-power" class="col-md-4 control-label">Power:</label>                          
                        <div class="col-md-4">
                            <select class="form-control" name="powerId">
                            <c:forEach var="power" items="${powerList}">
                                <c:choose>
                                    <c:when test="${superPerson.getPower()==power}">
                                        <option selected value="${power.getPowerId()}">
                                            ${power.getPowerName()}
                                        </c:when>   
                                        <c:otherwise>
                                        <option value="${power.getPowerId()}">
                                            ${power.getPowerName()}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-organization" class="col-md-4 control-label">Organization:</label>
                    <div class="col-md-4">
                        <select multiple class="form-control" name="organizationId">

                            <c:forEach var="organization" items="${organizationList}">
                                <c:choose>
                                    <c:when test="${selectedOrgs.contains( organization.getOrganizationId())}">
                                        <option selected value="${organization.getOrganizationId()}">
                                            ${organization.getOrgName()}
                                        </option>
                                        </c:when>
                                        <c:otherwise>
                                        <option value="${organization.getOrganizationId()}">
                                            ${organization.getOrgName()}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-side" class="col-md-4 control-label">Good or Evil? :</label>
                    <div class="col-md-4">
                        <select class="form-control" name="side">
                            <c:choose>
                                <c:when test="${superPerson.side==-1}">
                                    <option selected value="-1">Evil</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="-1">Evil</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${superPerson.side==0}">
                                    <option selected value="0">Neutral</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0">Neutral</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${superPerson.side==1}">
                                    <option selected value="1">Good</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="1">Good</option>
                                </c:otherwise>
                            </c:choose>

                        </select>
                        <sf:hidden path="personId"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Super Person"/>
                </div>
            </div>
        </sf:form>                
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>