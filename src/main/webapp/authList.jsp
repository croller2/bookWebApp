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
        <jsp:include page="stylesheetBundle.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>

        <div class="container">
            <div class="row">                
                <div class="col-xs-8 col-xs-offset-2 wrapper">
                    <table class="table table-bordered table-striped">
                        <th>Update</th>
                        <th>Author ID</th>
                        <th>Author Name</th>
                        <th>Date Added</th>
                        <th>Delete</th>
                        <c:forEach var="authorList" items="${authorList}" varStatus="rowCount">
                            <tr>
                                <td style="text-align: center">
                                    <form method="POST" action="authorController?op=updateAuthor&authorId=${authorList.authorId}">
                                        <input id="authorId" name="authorId" value="${authorList.authorId}" type="hidden">
                                        <input id="authorName" name="authorName" value="${authorList.authorName}" type="hidden">
                                        <input id="dateAdded" name="dateAdded" value="${authorList.dateAdded}" type="hidden">
                                        <button class="btn btn-primary" type="submit">Edit/Update</button></form>
                                </td>                                
                                <td>${authorList.authorId}</td>
                                <td>${authorList.authorName}</td>
                                <td>${authorList.dateAdded}</td>
                                <td style="text-align: center">
                                    <form method="POST" action="authorController?op=deleteAuthor">
                                        <input name="authorId" value="${authorList.authorId}" type="hidden">
                                        <button class="btn btn-danger" type="submit">Delete</button></form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                              
                </div>
            </div>  
        </div>
        <div class="container">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3">
                    <form id="addAuthorForm" name="addAuthorForm" method="POST" action="authorController?op=addAuthor">
                        <button id="addAuthor" name="addAuthor" class="btn btn-default" type="submit">Add Author</button>
                    </form>
                </div>
            </div>            

        </div>


    </body>
</html>
