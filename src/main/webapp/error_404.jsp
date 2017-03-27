<%-- 
    Document   : error_404
    Created on : Feb 27, 2017, 8:47:31 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="stylesheetBundle.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uhoh...this isn't right</title>
    </head>
    <body>
        <div class="row">
            <div class="container">
                <div class="col-xs-6 col-xs-offset-3">
                    <h1>Uh oh you hit an error...</h1>
                    <h1>Here's the message that got served up:</h1>
                    <div class='row'>
                        <h1>${errorMessage}</h1>  
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
