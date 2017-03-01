/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.controllers;

import edu.wctc.car.models.Author;
import edu.wctc.car.models.AuthorDao;
import edu.wctc.car.models.AuthorService;
import edu.wctc.car.models.DbAccessor;
import edu.wctc.car.models.IAuthorDao;
import edu.wctc.car.models.MySqlDbAccessor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author chris
 */
@WebServlet(name = "authorController", urlPatterns = {"/authorController"})
public class authorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String AUTH_LIST_PAGE = "/authList.jsp";
    private String ADD_AUTH_PAGE = "/addAuthor.jsp";
    private String UPDATE_AUTHOR_PAGE = "/updateAuthor.jsp";
    private String PAGE_NOT_FOUND = "/error_404.jsp";
    private String ACTION_PARAM = "";
    
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String dbStrategyClassName;
    private String daoClassName;
    private String jndiName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("op");
        RequestDispatcher view = request.getRequestDispatcher(PAGE_NOT_FOUND);

        AuthorService as = new AuthorService(
                new AuthorDao(
                        new MySqlDbAccessor(),
                        "com.mysql.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/book",
                        "root",
                        "08rollec!")
        );
        try {
            if (operation == null) {
                List<Author> listOfAuthors = refreshAuthorList(as);
                request.setAttribute("authorList", listOfAuthors);
                view = request.getRequestDispatcher(AUTH_LIST_PAGE);

            } else if (operation.equalsIgnoreCase("addAuthor")) {
                view = request.getRequestDispatcher(ADD_AUTH_PAGE);

            } else if (operation.equalsIgnoreCase("updateAuthor")) {

                //Figure out how to ascertain what authorId's were checked here. 
                String authorId = request.getQueryString();
                System.out.println(authorId);
                view = request.getRequestDispatcher(UPDATE_AUTHOR_PAGE);

            } else if (operation.equalsIgnoreCase("addToList")) {
                String authorName = request.getParameter("authorName");
                Date addedDate = new Date();
                as.addAuthor(
                        "author",
                        new ArrayList<>(Arrays.asList("author_name", "date_added")),
                        new ArrayList<>(Arrays.asList(authorName, addedDate)));
                List<Author> listOfAuthors = refreshAuthorList(as);
                request.setAttribute("authorList", listOfAuthors);
                view = request.getRequestDispatcher(AUTH_LIST_PAGE);
            }
            view.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            view = request.getRequestDispatcher(PAGE_NOT_FOUND);
            view.forward(request, response);
        }
    }
    
    private AuthorService injectDependenciesAndGetAuthorService() throws Exception {
        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DBStrategy based on the class name retrieved
        // from web.xml
        Class dbClass = Class.forName(dbStrategyClassName);
        // Use Java reflection to instanntiate the DBStrategy object
        // Note that DBStrategy classes have no constructor params
        DbAccessor db = (DbAccessor) dbClass.newInstance();

        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DAO based on the class name retrieved above.
        // This one is trickier because the available DAO classes have
        // different constructor params
        IAuthorDao authorDao = null;
        Class daoClass = Class.forName(daoClassName);
        Constructor constructor = null;
        
        // This will only work for the non-pooled AuthorDao
        try {
            constructor = daoClass.getConstructor(new Class[]{
                DbAccessor.class, String.class, String.class, String.class, String.class
            });
        } catch(NoSuchMethodException nsme) {
            // do nothing, the exception means that there is no such constructor,
            // so code will continue executing below
        }

        // constructor will be null if using connectin pool dao because the
        // constructor has a different number and type of arguments
        
        if (constructor != null) {
            // conn pool NOT used so constructor has these arguments
            Object[] constructorArgs = new Object[]{
                db, driverClass, url, username, password
            };
            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);

        } else {
            /*
             Here's what the connection pool version looks like. First
             we lookup the JNDI name of the Glassfish connection pool
             and then we use Java Refletion to create the needed
             objects based on the servlet init params
             */
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup(jndiName);
            constructor = daoClass.getConstructor(new Class[]{
                DataSource.class, DbAccessor.class
            });
            Object[] constructorArgs = new Object[]{
                ds, db
            };

            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);
        }
        
        return new AuthorService(authorDao);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        driverClass = getServletContext()
                .getInitParameter("db.driver.class");
        url = getServletContext()
                .getInitParameter("db.url");
        username = getServletContext()
                .getInitParameter("db.username");
        password = getServletContext()
                .getInitParameter("db.password");
        dbStrategyClassName = getServletContext().getInitParameter("dbStrategy");
        daoClassName = getServletContext().getInitParameter("authorDao");
        jndiName = getServletContext().getInitParameter("connPoolName");
    }
    
    

    public List<Author> refreshAuthorList(AuthorService as) throws ClassNotFoundException, SQLException {
        return as.getListOfAuthors("author", 50);
    }
}
