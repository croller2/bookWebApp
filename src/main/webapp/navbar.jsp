<%-- 
    Document   : navbar
    Created on : Mar 6, 2017, 11:59:16 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><i class="fa fa-book" aria-hidden="true"></i> BookWebApp</a>
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                  <li class="active"><a href="index.jsp"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
                  <li><a href="authorController"><i class="fa fa-user" aria-hidden="true"></i> Author List</a></li>
                  <li><a href="authorController?op=addAuthor"><i class="fa fa-user-plus" aria-hidden="true"></i> Add Author</a></li>
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
            </div>
          </nav>
        </div>
