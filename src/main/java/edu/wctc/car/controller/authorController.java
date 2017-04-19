/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.car.controller;

import edu.wctc.car.entity.Author;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
    

    private AuthorService as;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String operation = request.getParameter("op");
        RequestDispatcher view = request.getRequestDispatcher(PAGE_NOT_FOUND);

        try {
            //AuthorService as = injectDependenciesAndGetAuthorService();
            if (operation == null) {
                List<Author> listOfAuthors = refreshAuthorList(as);
                request.setAttribute("authorList", listOfAuthors);
                view = request.getRequestDispatcher(AUTH_LIST_PAGE);

            } else if (operation.equalsIgnoreCase("addAuthor")) {
                view = request.getRequestDispatcher(ADD_AUTH_PAGE);

            } else if (operation.equalsIgnoreCase("updateAuthor")) {
                int authorId = Integer.parseInt(request.getParameter("authorId"));
                String authorName = request.getParameter("authorName");
                String date = request.getParameter("dateAdded");
                 request.setAttribute("authorId", authorId);
                 request.setAttribute("authorName" , authorName);
                 request.setAttribute("dateAdded", date);
                System.out.println(authorId);
                view = request.getRequestDispatcher(UPDATE_AUTHOR_PAGE);

            }else if (operation.equalsIgnoreCase("deleteAuthor")) {
                try{
                    String authorId = request.getParameter("authorId");
                    as.deleteByID(authorId);
                    List<Author> listOfAuthors = refreshAuthorList(as);
                    request.setAttribute("authorList", listOfAuthors);
                    view = request.getRequestDispatcher(AUTH_LIST_PAGE);

                }catch(Exception ex){
                    request.setAttribute("errorMessage", ex.getMessage());
                    view = request.getRequestDispatcher(PAGE_NOT_FOUND);
                }                
            }else if(operation.equalsIgnoreCase("update")){
                try{
                    String authorId = request.getParameter("authorId");
                    String authorName = request.getParameter("authorName");
                    List<String> columnNames = new ArrayList<>(Arrays.asList("author_name"));
                    List<Object> colValues = new ArrayList<>(Arrays.asList(authorName));
                    as.update(authorId, authorName);                   
                    List<Author> listOfAuthors = refreshAuthorList(as);
                    request.setAttribute("authorList", listOfAuthors);
                    view = request.getRequestDispatcher(AUTH_LIST_PAGE);

                }catch(ClassNotFoundException | NumberFormatException | SQLException ex){
                    request.setAttribute("errorMessage", ex.getMessage());
                    view = request.getRequestDispatcher(PAGE_NOT_FOUND);
                }
            }
            else if (operation.equalsIgnoreCase("addtolist")) {
                String authorName = request.getParameter("authorName");
                Date addedDate = new Date();
                as.addNewAuthor(authorName);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(authorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(authorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public List<Author> refreshAuthorList(AuthorFacade as) throws ClassNotFoundException, SQLException {
        return as.findAll();
    }
}
