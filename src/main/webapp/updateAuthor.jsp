  <%--  Document   : addAuthor
    Created on : Feb 22, 2017, 10:49:56 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="stylesheetBundle.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Author</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-3 col-xs-offset-5">
                    <h2>Update Authors</h2>
                    <form id="upDateAuthorForm" name="upDateAuthorForm" method="POST" action="authorController?op=update&authorId=${authorId}">
                        <div class="form-group">
                            <label for="authorId">Author ID:</label>
                            <input id="authorId" name="authorId" class="form-control" type="text" disabled value="${authorId}"</>    
                        </div>                            
                        <div class="form-group">
                            <label for="authorName">Author Name: </label>
                            <input id="authorName" name="authorName" class="form-control" type="text" value="${authorName}"/>   
                        </div>  
                            
                        <div class="form-group">
                            <label for="dateAdded">Date Added</label>
                            <input id="dateAdded" name="dateAdded" class="form-control" type="text" disabled value="${dateAdded}"</>                                    
                        </div>
                        <button id="upDateAuthors" type="submit" class="btn btn-default">Update</button> 
                    </form>                                      
                </div>                       
                </div>
            </div>
        </div>       
    </body>
</html>