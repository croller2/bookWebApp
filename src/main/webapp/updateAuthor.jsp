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
        <div class="container">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">BookWebApp</a>
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                  <li class="active"><a href="index.jsp">Home</a></li>
                  <li><a href="authorController">Author List</a></li>
                  <li><a href="#">Contact</a></li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="#">Action</a></li>
                      <li><a href="#">Another action</a></li>
                      <li><a href="#">Something else here</a></li>
                      <li role="separator" class="divider"></li>
                      <li class="dropdown-header">Nav header</li>
                      <li><a href="#">Separated link</a></li>
                      <li><a href="#">One more separated link</a></li>
                    </ul>
                  </li>
                </ul>
            </div><!--/.nav-collapse -->
          </nav>
        </div>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3">
                    <h2>Update Authors</h2>
                    <form id="upDateAuthorForm" name="upDateAuthorForm" method="POST" action="authorController?op=update">
                        <div class="form-group">
                         <c:forEach var="authorList" items="${authorList}" varStatus="rowCount">
                                <tr>
                                <c:choose>
                                    <c:when test="${rowCount.count % 2 != 0}">
                                        <tr style="background-color: white">     
                                    </c:when>
                                    <c:otherwise>
                                        <tr style="background-color: lightgrey">     
                                    </c:otherwise>
                                </c:choose>
                               
                                <td><input class="form-control" type="text" placeholder="${authorList.authorName}"</td>
                                <td><input class="form-control" type="text" placeholder="${authorList.authorId}"</td>
                                <td><input class="form-control" type="text" placeholder="${authorList.dateAdded}"</td>
                                </tr>
                            </c:forEach>
                        </div>
                        <button id="upDateAuthors" disabled type="submit" class="btn btn-default">Update</button>
                    </form>                   
                </div>

            </div>
        </div>
        
    </body>
</html>