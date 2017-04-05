<%-- 
    Document   : addBook
    Created on : Apr 3, 2017, 10:44:54 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="stylesheetBundle.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3 wrapper">
                    <h2>Add a Book</h2>
                    <form id="addBookForm" name="addBookForm" method="POST" action="BookController?op=addtoList">
                        <div class="form-group">
                          <label for="bookName">Book Name: </label>
                          <input type="text" required class="form-control" id="authorName" name="bookName" aria-describedby="bookName" placeholder="Where The Red Fern Grows">
                        </div>
                        <div class="form-group">
                          <label for="bookISBN">ISBN </label>
                          <input type="text" required class="form-control" id="bokISBN" name="bokISBN" aria-describedby="bokISBN" placeholder="123456789">
                        </div>
                        <div class="form-group">
                          <label for="bookAuthor">Author </label>
                          <input type="text" required class="form-control" id="bookAuthor" name="bookAuthor" aria-describedby="bookAuthor" placeholder="Author">
                        </div>
                        <button id="addAuthorSubmit"  type="submit" class="btn btn-default">Submit</button>
                    </form>                   
                </div>

            </div>
        </div>
        
    </body>
</html>
