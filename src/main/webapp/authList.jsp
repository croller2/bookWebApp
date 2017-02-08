<%-- 
    Document   : authList
    Created on : Feb 8, 2017, 8:59:40 AM
    Author     : chris
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
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
                  <li><a href="#">Author List</a></li>
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
                <ul class="nav navbar-nav navbar-right">
                  <li class="active"><a href="./">Default <span class="sr-only">(current)</span></a></li>
                  <li><a href="../navbar-static-top/">Static top</a></li>
                  <li><a href="../navbar-fixed-top/">Fixed top</a></li>
                </ul>
              </div><!--/.nav-collapse -->
            </div><!--/.container-fluid -->
          </nav>
    </div>
        <div class="row">
            <div class="container">
                <div class="col-md-4">
                    <table class="table">
                        <th>Author Name</th>
                        <th>Author ID</th>
                        <th>Date Added</th>

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
                            <td>${authorList.authorName}</td>
                            <td>${authorList.authorId}</td>
                            <td>${authorList.dateAdded}</td>
                            </tr>
                        </c:forEach>
                </table>    
                    
                </div>
    
            </div>
   
            
        </div>

    </body>
</html>
