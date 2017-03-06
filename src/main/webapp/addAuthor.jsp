<%-- 
    Document   : addAuthor
    Created on : Feb 22, 2017, 10:49:56 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Author</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3">
                    <h2>Add an Author:</h2>
                    <form id="addAuthorForm" name="addAuthorForm" method="POST" action="authorController?op=addToList">
                        <div class="form-group">
                          <label for="authorName">Author Name: </label>
                          <input type="text" required class="form-control" id="authorName" name="authorName" aria-describedby="authorName" placeholder="Mark Twain">
                        </div>
                        <button id="addAuthorSubmit"  type="submit" class="btn btn-default">Submit</button>
                    </form>                   
                </div>

            </div>
        </div>
        
    </body>
</html>
