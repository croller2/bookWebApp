<%-- 
    Document   : bookList
    Created on : Apr 3, 2017, 10:41:44 AM
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
                            <th>Book ID</th>
                            <th>Book Name</th>
                            <th>ISBN</th>
                            <th>Author</th>
                            <th>Delete</th>

                            <c:forEach var="bookList" items="${bookList}" varStatus="rowCount">
                                <tr>

                                <td style="text-align: center">
                                    <form method="POST" action="BookController?op=updateBook&bookID=${bookList.bookId}">
                                    <input id="bookId" name="bookId" value="${bookList.bookId}" type="hidden">
                                    <input id="bookTitle" name="bookTitle" value="${bookList.title}" type="hidden">
                                    <input id="ISBN" name="ISBN" value="${bookList.isbn}" type="hidden">
                                    <input id="authorId" name="authorId" value="${bookList.author.authorId}" type="hidden">
                                    <button class="btn btn-primary" type="submit">Edit/Update</button></form>
                                </td>                                
                                <td>${bookList.bookId}</td>
                                <td>${bookList.title}</td>
                                <td>${bookList.isbn}</td>
                                <td>${bookList.author.authorName}</td>
                                <td style="text-align: center">
                                    <form method="POST" action="BookController?op=deleteBook">
                                    <input id="bookId" name="bookId" value="${bookList.bookId}" type="hidden">
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
                    <form id="addBookForm" name="addBookForm" method="POST" action="BookController?op=addBook">
                        <button id="addAuthor" name="addAuthor" class="btn btn-default" type="submit">Add Book</button>
                    </form>
                </div>
            </div>            
            
        </div>


    </body>
</html>
