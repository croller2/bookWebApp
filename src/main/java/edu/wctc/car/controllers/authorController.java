/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.controllers;

import edu.wctc.car.models.Author;
import edu.wctc.car.models.AuthorDao;
import edu.wctc.car.models.AuthorService;
import edu.wctc.car.models.MySqlDbAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try{
            if(operation == null){
                List<Author> listOfAuthors = refreshAuthorList(as);
                request.setAttribute("authorList", listOfAuthors);
                view = request.getRequestDispatcher(AUTH_LIST_PAGE);   
  
            }
            else if(operation.equalsIgnoreCase("addAuthor")){
                view = request.getRequestDispatcher(ADD_AUTH_PAGE);   
    
            }else if(operation.equalsIgnoreCase("updateAuthor")){
                
                
                //Figure out how to ascertain what authorId's were checked here. 
                String authorId = request.getQueryString();
                System.out.println(authorId);
                view = request.getRequestDispatcher(UPDATE_AUTHOR_PAGE);

            }else if(operation.equalsIgnoreCase("addToList")){
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
        }catch(Exception e){
            request.setAttribute("errorMessage", e.getMessage());
            view = request.getRequestDispatcher(PAGE_NOT_FOUND);
            
        }
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

    
    public List<Author> refreshAuthorList(AuthorService as) throws ClassNotFoundException, SQLException{
        return as.getListOfAuthors("author" , 50);
    }
}
