<%-- 
    Document   : results
    Created on : Oct 3, 2017, 8:09:01 PM
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
        <h1>Dice Results</h1>
        <p>
            You started with ${userMoney}; 
        </p>
        <p>
            You rolled at total of ${totalRolls} rolls before you ran out of money.
        </p>
        <p>
            The max amount of money you had was ${maxMoney} after ${maxRolls};
        </p>
    </body>
</html>
