<%-- 
    Document   : result
    Created on : Oct 4, 2017, 2:12:02 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Results</title>
    </head>
    <body>
        <h1>Results</h1>
        <p>
            You listed ${flooringWidth} as the width and ${flooringLength} as the length. 
            This equates to a total flooring area of ${flooringArea}.
        </p>
        <p>
            The input ${costPerSqFt} as the cost per square foot.
        </p>
        <p>
            Based on those numbers, you're looking at an installation rate of ${installRate} feet per hour.
        </p>
        <p>
            The material cost will be ${materialCost} and the labor cost will be ${laborCost}. The total cost is ${totalCost}.
        </p>
        <p>
             <a href="index.jsp">Setup another calculation.</a>
        </p>
    </body>
</html>
